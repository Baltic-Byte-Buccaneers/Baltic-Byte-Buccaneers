package api

type ReceiptMetadata struct {
	TransactionId  string `bson:"transactionId" json:"transactionId,omitempty"`
	SerialNumber   string `bson:"serialNumber" json:"serialNumber,omitempty"`
	SignatureCount string `bson:"signatureCount" json:"signatureCount,omitempty"`
	TerminalId     string `bson:"terminalId" json:"terminalId,omitempty"`
	CheckSum       string `bson:"checkSum" json:"checkSum,omitempty"`
}
