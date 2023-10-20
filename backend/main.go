package main

import (
	"backend/api"
	"backend/docs"
	"backend/mongodb"
	"fmt"

	"net/http"

	"github.com/gin-contrib/cors"
	"github.com/gin-gonic/gin"
	"go.mongodb.org/mongo-driver/mongo"

	swaggerfiles "github.com/swaggo/files"
	ginSwagger "github.com/swaggo/gin-swagger"
)

var MongoClient *mongo.Client

func setupRouter(useSwagger bool) *gin.Engine {
	// Creates a gin router with default middleware:
	// logger and recovery (crash-free) middleware
	router := gin.Default()

	// default cors = allow all origins, change for production
	router.Use(cors.Default())

	docs.SwaggerInfo.BasePath = "/api"

	// @BasePath /api
	apiRouter := router.Group("/api")
	{
		apiRouter.GET("/healthcheck", func(context *gin.Context) {
			context.String(http.StatusOK, "Alive!")
		})

		apiRouter.GET("/transactions", api.GetAllTransactions)
		apiRouter.GET("/transactions/:id", api.GetTransactionById)
		apiRouter.POST("/transactions", api.CreateTransaction)

		apiRouter.GET("/receipts", api.GetAllReceipts)
		apiRouter.GET("/receipts/:id", api.GetReceiptById)
		apiRouter.POST("/receipts", api.CreateReceipt)

		apiRouter.GET("/retailers", api.GetAllRetailers)
		apiRouter.GET("/retailers/:id", api.GetRetailerById)
		apiRouter.POST("/retailers", api.CreateRetailer)

		apiRouter.GET("/branches", api.GetAllBranches)
		apiRouter.GET("/branches/:id", api.GetBranchById)
		apiRouter.POST("/branches", api.CreateBranch)
	}

	if useSwagger {
		router.GET("/swagger/*any", ginSwagger.WrapHandler(swaggerfiles.Handler))
	}

	return router
}

func main() {
	host := "localhost"
	listenPort := "8080"
	connectionString := fmt.Sprintf("mongodb://%s:27017", host)
	useSwagger := true

	mongodb.InitDbConnection(connectionString)

	router := setupRouter(useSwagger)
	router.Run(fmt.Sprintf(":%s", listenPort))
}
