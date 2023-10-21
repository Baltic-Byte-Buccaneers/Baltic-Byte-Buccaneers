package api

import (
	"backend/mongodb"
	"fmt"
	"net/http"
	"time"

	"github.com/gin-gonic/gin"
	"go.mongodb.org/mongo-driver/bson/primitive"
)

// GetAllStocks godoc
// @Summary Get all stock datas
// @Schemes
// @Tags Stock
// @Accept json
// @Produce json
// @Success 200 {object} []Stock "ok"
// @Failure 500 {string} Placeholder
// @Router /stocks [get]
func GetAllStocks(ginContext *gin.Context) {
	stocks := GetAll[Stock](mongodb.StockCollection)
	ginContext.JSON(http.StatusOK, stocks)
}

// GetStockById godoc
// @Summary Get single price data by its id
// @Schemes
// @Tags Stock
// @Param id path string true "Stock Id"
// @Produce json
// @Success 200 {object} Stock "ok"
// @Failure 500 {string} Placeholder
// @Router /stocks/{id} [get]
func GetStockById(ginContext *gin.Context) {
	stockId := ginContext.Param("id")
	stock := GetById[Stock](mongodb.StockCollection, stockId)
	ginContext.JSON(http.StatusOK, stock)
}

// CreateStock godoc
// @Summary Create price data
// @Schemes
// @Tags Stock
// @Accept json
// @Param Stock body Stock true "Add Stock"
// @Produce json
// @Success 200 {object} Stock "ok"
// @Failure 500 {string} Placeholder
// @Router /stocks [post]
func CreateStock(ginContext *gin.Context) {
	var stock Stock
	if err := ginContext.ShouldBindJSON(&stock); err != nil {
		ginContext.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}

	stock.Id = primitive.NewObjectIDFromTimestamp(time.Now())
	result := Create[Stock](mongodb.StockCollection, stock)
	ginContext.JSON(http.StatusOK, gin.H{"status": fmt.Sprintf("Successfully created stock %s", result.Id)})
}
