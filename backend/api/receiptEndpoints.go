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

// GetAllReceipts godoc
// @Summary Get all receipts
// @Schemes
// @Tags Receipt
// @Accept json
// @Produce json
// @Success 200 {object} []Receipt "ok"
// @Failure 500 {string} Placeholder
// @Router /receipts [get]
func GetAllReceipts(ginContext *gin.Context) {

	var receipts []Receipt
	receiptsCursor, err := mongodb.ReceiptsCollection.Find(context.TODO(), bson.M{})
	if err != nil {
		panic(err)
	}

	if err = receiptsCursor.All(context.TODO(), &receipts); err != nil {
		panic(err)
	}

	receiptsCursor.Close(context.TODO())
	ginContext.JSON(http.StatusOK, receipts)
}

// GetReceiptById godoc
// @Summary Get single receipt by its id
// @Schemes
// @Tags Receipt
// @Param id path string true "Receipt Id"
// @Produce json
// @Success 200 {object} Receipt "ok"
// @Failure 500 {string} Placeholder
// @Router /receipts/{id} [get]
func GetReceiptById(ginContext *gin.Context) {

	receiptId := ginContext.Param("id")
	objectId := utils.GetObjectId(receiptId)

	var receipt Receipt
	err := mongodb.ReceiptsCollection.FindOne(context.TODO(), bson.M{"_id": objectId}).Decode(&receipt)

	if err != nil {
		panic(err)
	}
	logger.MongoInfo(fmt.Sprintf("Receipt retrieved: %s\n", objectId))

	ginContext.JSON(http.StatusOK, receipt)
}

// CreateReceipt godoc
// @Summary Create receipt
// @Schemes
// @Tags Receipt
// @Accept json
// @Param Receipt body Receipt true "Add Receipt"
// @Produce json
// @Success 200 {object} Receipt "ok"
// @Failure 500 {string} Placeholder
// @Router /receipts [post]
func CreateReceipt(ginContext *gin.Context) {
	var json Receipt
	if err := ginContext.ShouldBindJSON(&json); err != nil {
		ginContext.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}

	json.Id = primitive.NewObjectIDFromTimestamp(time.Now())
	result, err := mongodb.ReceiptsCollection.InsertOne(context.TODO(), json)

	if err != nil {
		panic(err)
	}

	logger.MongoInfo(fmt.Sprintf("Document inserted: %s\n", result.InsertedID))

	ginContext.JSON(http.StatusOK, gin.H{"status": fmt.Sprintf("Successfully created receipt %s", result.InsertedID)})
}
