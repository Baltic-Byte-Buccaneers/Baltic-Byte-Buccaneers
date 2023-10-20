package api

import (
	"time"

	"go.mongodb.org/mongo-driver/bson/primitive"
)

type Transaction struct {
	Id          primitive.ObjectID `bson:"_id" json:"id,omitempty"`
	UserId      string             `bson:"userid" json:"userid,omitempty"`
	Iban        string             `bson:"iban" json:"iban,omitempty"`
	Value       float64            `bson:"value" json:"value,omitempty"`
	Date        time.Time          `bson:"date" json:"date,omitempty"`
	ValutaDate  time.Time          `bson:"valutaDate" json:"valutaDate,omitempty"`
	Description string             `bson:"description" json:"description,omitempty"`
	Purpose     string             `bson:"purpose" json:"purpose,omitempty"`
}
