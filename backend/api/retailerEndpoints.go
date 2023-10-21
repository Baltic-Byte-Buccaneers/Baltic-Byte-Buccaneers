package api

import (
	"backend/mongodb"
	"fmt"
	"net/http"
	"time"

	"github.com/gin-gonic/gin"
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
	retailers := GetAll[Retailer](mongodb.RetailersCollection)
	ginContext.JSON(http.StatusOK, retailers)
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
	retailer := GetById[Retailer](mongodb.RetailersCollection, retailerId)
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
	var retailer Retailer
	if err := ginContext.ShouldBindJSON(&retailer); err != nil {
		ginContext.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}

	retailer.Id = primitive.NewObjectIDFromTimestamp(time.Now())
	result := Create[Retailer](mongodb.RetailersCollection, retailer)
	ginContext.JSON(http.StatusOK, gin.H{"status": fmt.Sprintf("Successfully created retailer %s", result.Id)})
}
