package api

import (
	"time"

	"go.mongodb.org/mongo-driver/bson/primitive"
)

type Transaction struct {
	Id          primitive.ObjectID `bson:"_id" json:"id"`
	UserId      string             `bson:"userid" json:"userid"`
	Iban        string             `bson:"iban" json:"iban"`
	Amount      float64            `bson:"amount" json:"amount"`
	Date        time.Time          `bson:"date" json:"date"`
	ValutaDate  time.Time          `bson:"valutaDate" json:"valutaDate"`
	Description string             `bson:"description" json:"description"`
	Purpose     string             `bson:"purpose" json:"purpose"`
	ReceiptId   string             `bson:"receiptId" json:"receiptId"`
}
