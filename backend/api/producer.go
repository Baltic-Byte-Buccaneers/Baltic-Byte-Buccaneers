package api

import (
	"go.mongodb.org/mongo-driver/bson/primitive"
)

type Producer struct {
	Id          primitive.ObjectID `bson:"_id" json:"id,omitempty"`
	Name        string             `bson:"name" json:"name,omitempty"`
	Description string             `bson:"description" json:"description,omitempty"`
	Wkn         string             `bson:"wkn" json:"wkn,omitempty"`
	Isin        string             `bson:"isin" json:"isin,omitempty"`
	Symbol      string             `bson:"symbol" json:"symbol,omitempty"`
	IconId      string             `bson:"iconId" json:"iconId,omitempty"`
	Tendency    string             `bson:"tendency" json:"tendency,omitempty"`
	LastPrice   float64            `bson:"lastPrice" json:"lastPrice,omitempty"`
	PriceData   []PriceData        `bson:"priceData" json:"priceData,omitempty"`
}
