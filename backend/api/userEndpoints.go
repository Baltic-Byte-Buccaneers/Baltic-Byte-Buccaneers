package api

import (
	"backend/mongodb"
	"fmt"
	"net/http"
	"time"

	"github.com/gin-gonic/gin"
	"go.mongodb.org/mongo-driver/bson/primitive"
)

// GetAllUsers godoc
// @Summary Get all users
// @Schemes
// @Tags User
// @Accept json
// @Produce json
// @Success 200 {object} []User "ok"
// @Failure 500 {string} Placeholder
// @Router /users [get]
func GetAllUsers(ginContext *gin.Context) {
	users := GetAll[User](mongodb.UsersCollection)
	ginContext.JSON(http.StatusOK, users)
}

// GetUserById godoc
// @Summary Get single user by his id
// @Schemes
// @Tags User
// @Param id path string true "User Id"
// @Produce json
// @Success 200 {object} User "ok"
// @Failure 500 {string} Placeholder
// @Router /users/{id} [get]
func GetUserById(ginContext *gin.Context) {
	userId := ginContext.Param("id")
	user := GetById[User](mongodb.UsersCollection, userId)
	ginContext.JSON(http.StatusOK, user)
}

// CreateUser godoc
// @Summary Create user
// @Schemes
// @Tags User
// @Accept json
// @Param User body User true "Add User"
// @Produce json
// @Success 200 {object} User "ok"
// @Failure 500 {string} Placeholder
// @Router /users [post]
func CreateUser(ginContext *gin.Context) {
	var user User
	if err := ginContext.ShouldBindJSON(&user); err != nil {
		ginContext.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}

	user.Id = primitive.NewObjectIDFromTimestamp(time.Now())
	result := Create[User](mongodb.UsersCollection, user)
	ginContext.JSON(http.StatusOK, gin.H{"status": fmt.Sprintf("Successfully created user %s", result.Id)})
}
