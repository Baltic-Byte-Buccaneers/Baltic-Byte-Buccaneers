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

	var producers []Producer
	producersCursor, err := mongodb.ProducersCollection.Find(context.TODO(), bson.M{})
	if err != nil {
		panic(err)
	}

	if err = producersCursor.All(context.TODO(), &producers); err != nil {
		panic(err)
	}

	producersCursor.Close(context.TODO())
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
	objectId := utils.GetObjectId(producerId)

	var producer Producer
	err := mongodb.ProducersCollection.FindOne(context.TODO(), bson.M{"_id": objectId}).Decode(&producer)

	if err != nil {
		panic(err)
	}
	logger.MongoInfo(fmt.Sprintf("Producer retrieved: %s\n", objectId))

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
	var json Producer
	if err := ginContext.ShouldBindJSON(&json); err != nil {
		ginContext.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}

	json.Id = primitive.NewObjectIDFromTimestamp(time.Now())
	result, err := mongodb.ProducersCollection.InsertOne(context.TODO(), json)

	if err != nil {
		panic(err)
	}

	logger.MongoInfo(fmt.Sprintf("Producer inserted: %s\n", result.InsertedID))

	ginContext.JSON(http.StatusOK, gin.H{"status": fmt.Sprintf("Successfully created producer %s", result.InsertedID)})
}
