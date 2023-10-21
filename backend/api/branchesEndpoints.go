package api

import (
	"backend/mongodb"
	"fmt"
	"net/http"
	"time"

	"github.com/gin-gonic/gin"
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
	branches := GetAll[Branch](mongodb.BranchesCollection)
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
	branch := GetById[Branch](mongodb.BranchesCollection, branchId)
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
	var branch Branch
	if err := ginContext.ShouldBindJSON(&branch); err != nil {
		ginContext.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}

	branch.Id = primitive.NewObjectIDFromTimestamp(time.Now())
	result := Create[Branch](mongodb.BranchesCollection, branch)
	ginContext.JSON(http.StatusOK, gin.H{"status": fmt.Sprintf("Successfully created branch %s", result.Id)})
}
