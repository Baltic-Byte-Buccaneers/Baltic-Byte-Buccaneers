package api

import (
	"backend/mongodb"
	"fmt"
	"net/http"
	"time"

	"github.com/gin-gonic/gin"
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
	receipts := GetAll[Receipt](mongodb.ReceiptsCollection)
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
	receipt := GetById[Receipt](mongodb.ReceiptsCollection, receiptId)
	ginContext.JSON(http.StatusOK, receipt)
}

// GetReceiptsByUserId godoc
// @Summary Get single receipt by the user id
// @Schemes
// @Tags Receipt
// @Param userId path string true "User Id"
// @Produce json
// @Success 200 {object} []Receipt "ok"
// @Failure 500 {string} Placeholder
// @Router /receipts/user/{userId} [get]
func GetReceiptsByUserId(ginContext *gin.Context) {
	userId := ginContext.Param("userId")
	receipts := GetByUserId[Receipt](mongodb.ReceiptsCollection, userId)
	ginContext.JSON(http.StatusOK, receipts)
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
	var receipt Receipt
	if err := ginContext.ShouldBindJSON(&receipt); err != nil {
		ginContext.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}

	receipt.Id = primitive.NewObjectIDFromTimestamp(time.Now())
	result := Create[Receipt](mongodb.ReceiptsCollection, receipt)
	ginContext.JSON(http.StatusOK, gin.H{"status": fmt.Sprintf("Successfully created receipt %s", result.Id)})
}
