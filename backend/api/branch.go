package api

import (
	"go.mongodb.org/mongo-driver/bson/primitive"
)

type Branch struct {
	Id           primitive.ObjectID `bson:"_id" json:"id"`
	RetailerId   string             `bson:"retailerId" json:"retailerId"`
	RetailerName string             `bson:"retailerName" json:"retailerName"`
	Name         string             `bson:"name" json:"name"`
	Municipality string             `bson:"municipality" json:"municipality"`
	Street       string             `bson:"street" json:"street"`
}
