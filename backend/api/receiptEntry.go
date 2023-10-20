package api

import (
	"go.mongodb.org/mongo-driver/bson/primitive"
)

type ReceiptEntry struct {
	Id    primitive.ObjectID `bson:"_id" json:"id,omitempty"`
	Title string             `bson:"title" json:"title,omitempty"`
	Value string             `bson:"value" json:"value,omitempty"`
}
