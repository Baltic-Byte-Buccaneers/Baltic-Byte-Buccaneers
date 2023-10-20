package api

import (
	"go.mongodb.org/mongo-driver/bson/primitive"
)

type Branch struct {
	Id           primitive.ObjectID `bson:"_id" json:"id,omitempty"`
	RetailerId   string             `bson:"retailerId" json:"retailerId,omitempty"`
	RetailerName string             `bson:"retailerName" json:"retailerName,omitempty"`
	Name         string             `bson:"name" json:"name,omitempty"`
	Municipality string             `bson:"municipality" json:"municipality,omitempty"`
	Street       string             `bson:"street" json:"street,omitempty"`
}
