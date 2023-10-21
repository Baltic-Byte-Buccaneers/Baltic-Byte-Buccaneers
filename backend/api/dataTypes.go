package api

type DataType interface {
	Branch | PriceData | Producer | Receipt | Retailer | Transaction
}
