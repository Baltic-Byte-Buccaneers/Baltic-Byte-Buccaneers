package api

type ReceiptEntry struct {
	//Id       primitive.ObjectID `bson:"_id" json:"id,omitempty"`
	Title    string  `bson:"title" json:"title,omitempty"`
	Quantity int     `bson:"quanitiy" json:"quantity,omitempty"`
	Amount   float64 `bson:"amount" json:"amount,omitempty"`
	VatRate  float32 `bson:"vatRate" json:"vatRate,omitempty"`
}
