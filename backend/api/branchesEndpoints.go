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

// GetAllBranches godoc
// @Summary Get all branches
// @Schemes
// @Tags Branch
// @Accept json
// @Produce json
// @Success 200 {object} []Branch "ok"
// @Failure 500 {string} Placeholder
// @Router /branches [get]
func GetAllBranches(ginContext *gin.Context) {

	var branches []Branch
	branchesCursor, err := mongodb.BranchesCollection.Find(context.TODO(), bson.M{})
	if err != nil {
		panic(err)
	}

	if err = branchesCursor.All(context.TODO(), &branches); err != nil {
		panic(err)
	}

	branchesCursor.Close(context.TODO())
	ginContext.JSON(http.StatusOK, branches)
}

// GetBranchById godoc
// @Summary Get single branch by its id
// @Schemes
// @Tags Branch
// @Param id path string true "Branch Id"
// @Produce json
// @Success 200 {object} Branch "ok"
// @Failure 500 {string} Placeholder
// @Router /branches/{id} [get]
func GetBranchById(ginContext *gin.Context) {

	branchId := ginContext.Param("id")
	objectId := utils.GetObjectId(branchId)

	var branch Branch
	err := mongodb.BranchesCollection.FindOne(context.TODO(), bson.M{"_id": objectId}).Decode(&branch)

	if err != nil {
		panic(err)
	}
	logger.MongoInfo(fmt.Sprintf("Branch retrieved: %s\n", objectId))

	ginContext.JSON(http.StatusOK, branch)
}

// CreateBranch godoc
// @Summary Create branch
// @Schemes
// @Tags Branch
// @Accept json
// @Param Branch body Branch true "Add Branch"
// @Produce json
// @Success 200 {object} Branch "ok"
// @Failure 500 {string} Placeholder
// @Router /branches [post]
func CreateBranch(ginContext *gin.Context) {
	var json Branch
	if err := ginContext.ShouldBindJSON(&json); err != nil {
		ginContext.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}

	json.Id = primitive.NewObjectIDFromTimestamp(time.Now())
	result, err := mongodb.BranchesCollection.InsertOne(context.TODO(), json)

	if err != nil {
		panic(err)
	}

	logger.MongoInfo(fmt.Sprintf("Branch inserted: %s\n", result.InsertedID))

	ginContext.JSON(http.StatusOK, gin.H{"status": fmt.Sprintf("Successfully created branch %s", result.InsertedID)})
}
