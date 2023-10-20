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

// GetAllTransactions godoc
// @Summary Get all transactions
// @Schemes
// @Tags Transaction
// @Accept json
// @Produce json
// @Success 200 {object} []Transaction "ok"
// @Failure 500 {string} Placeholder
// @Router /transactions [get]
func GetAllTransactions(ginContext *gin.Context) {

	var transactions []Transaction
	transactionsCursor, err := mongodb.TransactionsCollection.Find(context.TODO(), bson.M{})
	if err != nil {
		panic(err)
	}

	if err = transactionsCursor.All(context.TODO(), &transactions); err != nil {
		panic(err)
	}

	transactionsCursor.Close(context.TODO())
	ginContext.JSON(http.StatusOK, transactions)
}

// GetTransactionById godoc
// @Summary Get single transaction by its id
// @Schemes
// @Tags Transaction
// @Param id path string true "Transaction Id"
// @Produce json
// @Success 200 {object} Transaction "ok"
// @Failure 500 {string} Placeholder
// @Router /transactions/{id} [get]
func GetTransactionById(ginContext *gin.Context) {

	transactionId := ginContext.Param("id")
	objectId := utils.GetObjectId(transactionId)

	var transaction Transaction
	err := mongodb.TransactionsCollection.FindOne(context.TODO(), bson.M{"_id": objectId}).Decode(&transaction)

	if err != nil {
		panic(err)
	}
	logger.MongoInfo(fmt.Sprintf("Transaction retrieved: %s\n", objectId))

	ginContext.JSON(http.StatusOK, transaction)
}

// CreateTransaction godoc
// @Summary Create transaction
// @Schemes
// @Tags Transaction
// @Accept json
// @Param Transaction body Transaction true "Add Transaction"
// @Produce json
// @Success 200 {object} Transaction "ok"
// @Failure 500 {string} Placeholder
// @Router /transactions [post]
func CreateTransaction(ginContext *gin.Context) {
	var json Transaction
	if err := ginContext.ShouldBindJSON(&json); err != nil {
		ginContext.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}

	json.Id = primitive.NewObjectIDFromTimestamp(time.Now())
	result, err := mongodb.TransactionsCollection.InsertOne(context.TODO(), json)

	if err != nil {
		panic(err)
	}

	logger.MongoInfo(fmt.Sprintf("Document inserted: %s\n", result.InsertedID))

	ginContext.JSON(http.StatusOK, gin.H{"status": fmt.Sprintf("Successfully created transaction %s", result.InsertedID)})
}
