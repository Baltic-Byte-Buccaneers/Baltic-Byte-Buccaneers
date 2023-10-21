package api

import (
	"backend/logger"
	"backend/mongodb"
	"backend/utils"
	"context"
	"fmt"

	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/mongo"
)

func GetAll[T DataType](collection *mongo.Collection) []T {
	var objects []T
	branchesCursor, err := collection.Find(context.TODO(), bson.M{})
	if err != nil {
		panic(err)
	}

	err = branchesCursor.All(context.TODO(), &objects)
	if err != nil {
		panic(err)
	}
	branchesCursor.Close(context.TODO())

	logger.MongoInfo(fmt.Sprintf("Found %v documents, Type: %T\n", len(objects), objects))

	return objects
}

func GetById[T DataType](collection *mongo.Collection, id string) T {
	objectId := utils.GetObjectId(id)

	var object T
	err := collection.FindOne(context.TODO(), bson.M{"_id": objectId}).Decode(&object)

	if err != nil {
		panic(err)
	}
	logger.MongoInfo(fmt.Sprintf("Document of type %T retrieved, Id: %s\n", object, objectId))

	return object
}

func Create[T DataType](collection *mongo.Collection, object T) T {
	result, err := mongodb.BranchesCollection.InsertOne(context.TODO(), object)

	if err != nil {
		panic(err)
	}

	logger.MongoInfo(fmt.Sprintf("Document of type %T inserted, Id: %s\n", object, result.InsertedID))

	return object
}
