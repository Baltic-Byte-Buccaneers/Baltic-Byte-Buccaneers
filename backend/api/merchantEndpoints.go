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

// GetAllMerchants godoc
// @Summary Get all merchants
// @Schemes
// @Tags Merchant
// @Accept json
// @Produce json
// @Success 200 {object} []Merchant "ok"
// @Failure 500 {string} Placeholder
// @Router /merchants [get]
func GetAllMerchants(ginContext *gin.Context) {

	var merchant []Merchant
	merchantsCursor, err := mongodb.MerchantsCollection.Find(context.TODO(), bson.M{})
	if err != nil {
		panic(err)
	}

	if err = merchantsCursor.All(context.TODO(), &merchant); err != nil {
		panic(err)
	}

	merchantsCursor.Close(context.TODO())
	ginContext.JSON(http.StatusOK, merchant)
}

// GetMerchantById godoc
// @Summary Get single merchant by its id
// @Schemes
// @Tags Merchant
// @Param id path string true "Merchant Id"
// @Produce json
// @Success 200 {object} Merchant "ok"
// @Failure 500 {string} Placeholder
// @Router /merchants/{id} [get]
func GetMerchantById(ginContext *gin.Context) {

	merchantId := ginContext.Param("id")
	objectId := utils.GetObjectId(merchantId)

	var merchant Merchant
	err := mongodb.MerchantsCollection.FindOne(context.TODO(), bson.M{"_id": objectId}).Decode(&merchant)

	if err != nil {
		panic(err)
	}
	logger.MongoInfo(fmt.Sprintf("Merchant retrieved: %s\n", objectId))

	ginContext.JSON(http.StatusOK, merchant)
}

// CreateMerchant godoc
// @Summary Create merchant
// @Schemes
// @Tags Merchant
// @Accept json
// @Param Merchant body Merchant true "Add Merchant"
// @Produce json
// @Success 200 {object} Merchant "ok"
// @Failure 500 {string} Placeholder
// @Router /merchants [post]
func CreateMerchant(ginContext *gin.Context) {
	var json Merchant
	if err := ginContext.ShouldBindJSON(&json); err != nil {
		ginContext.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}

	json.Id = primitive.NewObjectIDFromTimestamp(time.Now())
	result, err := mongodb.MerchantsCollection.InsertOne(context.TODO(), json)

	if err != nil {
		panic(err)
	}

	logger.MongoInfo(fmt.Sprintf("Merchant inserted: %s\n", result.InsertedID))

	ginContext.JSON(http.StatusOK, gin.H{"status": fmt.Sprintf("Successfully created merchant %s", result.InsertedID)})
}
