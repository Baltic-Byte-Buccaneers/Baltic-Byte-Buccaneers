package api

import (
	"time"

	"go.mongodb.org/mongo-driver/bson/primitive"
)

type Receipt struct {
	Id          primitive.ObjectID `bson:"_id" json:"id"`
	Date        time.Time          `bson:"date" json:"date"`
	Description string             `bson:"description" json:"description"`
	Entries     []ReceiptEntry     `bson:"entries" json:"entries"`
	RetailerId  string             `bson:"retailerId" json:"retailerId"`
	BranchId    string             `bson:"branchId" json:"branchId"`
	Metadata    ReceiptMetadata    `bson:"metadata" json:"metadata"`
}
