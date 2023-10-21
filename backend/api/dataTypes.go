package api

type DataType interface {
	Branch | Producer | Receipt | Retailer | Stock | Transaction | User
}
