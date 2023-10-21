package api

type ReceiptEntry struct {
	Title         string  `bson:"title" json:"title"`
	Quantity      int     `bson:"quanitiy" json:"quantity"`
	Amount        float64 `bson:"amount" json:"amount"`
	Category      float64 `bson:"category" json:"category"`
	VatRate       float32 `bson:"vatRate" json:"vatRate,omitempty"`
	ProducerId    string  `bson:"producerId" json:"producerId,omitempty"`
	PriceTendency string  `bson:"priceTendency" json:"priceTendency"`
}
