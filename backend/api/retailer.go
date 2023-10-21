package api

import (
	"go.mongodb.org/mongo-driver/bson/primitive"
)

type Retailer struct {
	Id           primitive.ObjectID `bson:"_id" json:"id"`
	Name         string             `bson:"name" json:"name"`
	Country      string             `bson:"country" json:"country"`
	Municipality string             `bson:"municipality" json:"municipality"`
	Street       string             `bson:"street" json:"street"`
	Category     string             `bson:"category" json:"category"`
}
