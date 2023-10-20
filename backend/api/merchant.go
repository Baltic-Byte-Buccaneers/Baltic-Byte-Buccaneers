package api

import (
	"go.mongodb.org/mongo-driver/bson/primitive"
)

type Merchant struct {
	Id           primitive.ObjectID `bson:"_id" json:"id,omitempty"`
	Name         string             `bson:"name" json:"name,omitempty"`
	Municipality string             `bson:"municipality" json:"municipality,omitempty"`
	Street       string             `bson:"street" json:"street,omitempty"`
	Category     string             `bson:"category" json:"category,omitempty"`
}
