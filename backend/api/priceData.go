package api

import (
	"time"
)

type PriceData struct {
	Date     time.Time `bson:"date" json:"date"`
	Open     float64   `bson:"open" json:"open,omitempty"`
	High     float64   `bson:"high" json:"high,omitempty"`
	Low      float64   `bson:"low" json:"low,omitempty"`
	Close    float64   `bson:"close" json:"close,omitempty"`
	AdjClose float64   `bson:"adjClose" json:"adjClose,omitempty"`
	Volume   int       `bson:"volume" json:"volume,omitempty"`
}
