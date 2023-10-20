package api

import (
	"time"

	"go.mongodb.org/mongo-driver/bson/primitive"
)

type Receipt struct {
	Id          primitive.ObjectID `bson:"_id" json:"id,omitempty"`
	Date        time.Time          `bson:"date" json:"date,omitempty"`
	Description string             `bson:"description" json:"description,omitempty"`
	Entries     []ReceiptEntry     `bson:"entries" json:"entries,omitempty"`
	RetailerId  string             `bson:"retailerId" json:"retailerId,omitempty"`
	BranchId    string             `bson:"branchId" json:"branchId,omitempty"`
}
