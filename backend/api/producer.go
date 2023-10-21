package api

import (
	"go.mongodb.org/mongo-driver/bson/primitive"
)

type Producer struct {
	Id      primitive.ObjectID `bson:"_id" json:"id"`
	Name    string             `bson:"name" json:"name"`
	IconId  string             `bson:"iconId" json:"iconId"`
	StockId string             `bson:"stockId" json:"stockId"`
}
