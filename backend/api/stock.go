package api

import (
	"go.mongodb.org/mongo-driver/bson/primitive"
)

type Stock struct {
	Id           primitive.ObjectID `bson:"_id" json:"id"`
	Name         string             `bson:"name" json:"name"`
	Description  string             `bson:"description" json:"description"`
	Wkn          string             `bson:"wkn" json:"wkn"`
	Isin         string             `bson:"isin" json:"isin"`
	Symbol       string             `bson:"symbol" json:"symbol"`
	TendencyWeek string             `bson:"tendencyWeek" json:"tendencyWeek"`
	TendencyYear string             `bson:"tendencyYear" json:"tendencyYear"`
	LastPrice    float64            `bson:"lastPrice" json:"lastPrice"`
	PriceData    []PriceData        `bson:"priceData" json:"priceData"`
}
