package api

import (
	"backend/mongodb"
	"fmt"
	"net/http"
	"time"

	"github.com/gin-gonic/gin"
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
	transactions := GetAll[Transaction](mongodb.TransactionsCollection)
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
	transaction := GetById[Transaction](mongodb.TransactionsCollection, transactionId)
	ginContext.JSON(http.StatusOK, transaction)
}

// GetTransactionsByUserId godoc
// @Summary Get single transaction by the user id
// @Schemes
// @Tags Transaction
// @Param userId path string true "User Id"
// @Produce json
// @Success 200 {object} []Transaction "ok"
// @Failure 500 {string} Placeholder
// @Router /transactions/user/{userId} [get]
func GetTransactionsByUserId(ginContext *gin.Context) {
	userId := ginContext.Param("userId")
	transactions := GetByUserId[Transaction](mongodb.TransactionsCollection, userId)
	ginContext.JSON(http.StatusOK, transactions)
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
	var transaction Transaction
	if err := ginContext.ShouldBindJSON(&transaction); err != nil {
		ginContext.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}

	transaction.Id = primitive.NewObjectIDFromTimestamp(time.Now())
	result := Create[Transaction](mongodb.TransactionsCollection, transaction)
	ginContext.JSON(http.StatusOK, gin.H{"status": fmt.Sprintf("Successfully created transaction %s", result.Id)})
}
