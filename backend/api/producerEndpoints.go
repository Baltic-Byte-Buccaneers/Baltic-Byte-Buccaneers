package api

import (
	"backend/mongodb"
	"fmt"
	"net/http"
	"time"

	"github.com/gin-gonic/gin"
	"go.mongodb.org/mongo-driver/bson/primitive"
)

// GetAllProducers godoc
// @Summary Get all producers
// @Schemes
// @Tags Producer
// @Accept json
// @Produce json
// @Success 200 {object} []Producer "ok"
// @Failure 500 {string} Placeholder
// @Router /producers [get]
func GetAllProducers(ginContext *gin.Context) {
	producers := GetAll[Producer](mongodb.ProducersCollection)
	ginContext.JSON(http.StatusOK, producers)
}

// GetProducerById godoc
// @Summary Get single producer by its id
// @Schemes
// @Tags Producer
// @Param id path string true "Producer Id"
// @Produce json
// @Success 200 {object} Producer "ok"
// @Failure 500 {string} Placeholder
// @Router /producers/{id} [get]
func GetProducerById(ginContext *gin.Context) {
	producerId := ginContext.Param("id")
	producer := GetById[Producer](mongodb.ProducersCollection, producerId)
	ginContext.JSON(http.StatusOK, producer)
}

// CreateProducer godoc
// @Summary Create producer
// @Schemes
// @Tags Producer
// @Accept json
// @Param Producer body Producer true "Add Producer"
// @Produce json
// @Success 200 {object} Producer "ok"
// @Failure 500 {string} Placeholder
// @Router /producers [post]
func CreateProducer(ginContext *gin.Context) {
	var producer Producer
	if err := ginContext.ShouldBindJSON(&producer); err != nil {
		ginContext.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}

	producer.Id = primitive.NewObjectIDFromTimestamp(time.Now())
	result := Create[Producer](mongodb.ProducersCollection, producer)
	ginContext.JSON(http.StatusOK, gin.H{"status": fmt.Sprintf("Successfully created producer %s", result.Id)})
}
