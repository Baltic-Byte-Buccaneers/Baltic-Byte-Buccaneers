package api

import (
	"backend/logger"
	"backend/mongodb"
	"backend/utils"
	"context"
	"fmt"
	"net/http"
	"time"

	"github.com/gin-gonic/gin"
	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/bson/primitive"
)

// GetAllRetailers godoc
// @Summary Get all retailers
// @Schemes
// @Tags Retailer
// @Accept json
// @Produce json
// @Success 200 {object} []Retailer "ok"
// @Failure 500 {string} Placeholder
// @Router /retailers [get]
func GetAllRetailers(ginContext *gin.Context) {

	var retailer []Retailer
	retailersCursor, err := mongodb.RetailersCollection.Find(context.TODO(), bson.M{})
	if err != nil {
		panic(err)
	}

	if err = retailersCursor.All(context.TODO(), &retailer); err != nil {
		panic(err)
	}

	retailersCursor.Close(context.TODO())
	ginContext.JSON(http.StatusOK, retailer)
}

// GetRetailerById godoc
// @Summary Get single retailer by its id
// @Schemes
// @Tags Retailer
// @Param id path string true "Retailer Id"
// @Produce json
// @Success 200 {object} Retailer "ok"
// @Failure 500 {string} Placeholder
// @Router /retailer/{id} [get]
func GetRetailerById(ginContext *gin.Context) {

	retailerId := ginContext.Param("id")
	objectId := utils.GetObjectId(retailerId)

	var retailer Retailer
	err := mongodb.RetailersCollection.FindOne(context.TODO(), bson.M{"_id": objectId}).Decode(&retailer)

	if err != nil {
		panic(err)
	}
	logger.MongoInfo(fmt.Sprintf("Retailer retrieved: %s\n", objectId))

	ginContext.JSON(http.StatusOK, retailer)
}

// CreateRetailer godoc
// @Summary Create retailer
// @Schemes
// @Tags Retailer
// @Accept json
// @Param Retailer body Retailer true "Add Retailer"
// @Produce json
// @Success 200 {object} Retailer "ok"
// @Failure 500 {string} Placeholder
// @Router /retailers [post]
func CreateRetailer(ginContext *gin.Context) {
	var json Retailer
	if err := ginContext.ShouldBindJSON(&json); err != nil {
		ginContext.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}

	json.Id = primitive.NewObjectIDFromTimestamp(time.Now())
	result, err := mongodb.RetailersCollection.InsertOne(context.TODO(), json)

	if err != nil {
		panic(err)
	}

	logger.MongoInfo(fmt.Sprintf("Retailer inserted: %s\n", result.InsertedID))

	ginContext.JSON(http.StatusOK, gin.H{"status": fmt.Sprintf("Successfully created retailer %s", result.InsertedID)})
}
