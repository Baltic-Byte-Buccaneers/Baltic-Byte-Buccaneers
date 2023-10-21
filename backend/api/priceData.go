package api

import (
	"time"
)

type PriceData struct {
	Date     time.Time `bson:"date" json:"date"`
	Open     string    `bson:"open" json:"open,omitempty"`
	High     string    `bson:"high" json:"high,omitempty"`
	Low      string    `bson:"low" json:"low,omitempty"`
	Close    string    `bson:"close" json:"close,omitempty"`
	AdjClose string    `bson:"adjClose" json:"adjClose,omitempty"`
	Volume   string    `bson:"volume" json:"volume,omitempty"`
}
