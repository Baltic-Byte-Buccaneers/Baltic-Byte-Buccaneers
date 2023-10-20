package api

import (
	"go.mongodb.org/mongo-driver/bson/primitive"
)

type ReceiptEntry struct {
	Id       primitive.ObjectID `bson:"_id" json:"id,omitempty"`
	Title    string             `bson:"title" json:"title,omitempty"`
	Quantity int                `bson:"quanitiy" json:"quantity,omitempty"`
	Value    string             `bson:"value" json:"value,omitempty"`
	VatRate  float32            `bson:"vatRate" json:"vatRate,omitempty"`
}
