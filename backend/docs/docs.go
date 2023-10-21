// Package docs GENERATED BY SWAG; DO NOT EDIT
// This file was generated by swaggo/swag
package docs

import "github.com/swaggo/swag"

const docTemplate = `{
    "schemes": {{ marshal .Schemes }},
    "swagger": "2.0",
    "info": {
        "description": "{{escape .Description}}",
        "title": "{{.Title}}",
        "contact": {},
        "version": "{{.Version}}"
    },
    "host": "{{.Host}}",
    "basePath": "{{.BasePath}}",
    "paths": {
        "/branches": {
            "get": {
                "consumes": [
                    "application/json"
                ],
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "Branch"
                ],
                "summary": "Get all branches",
                "responses": {
                    "200": {
                        "description": "ok",
                        "schema": {
                            "type": "array",
                            "items": {
                                "$ref": "#/definitions/api.Branch"
                            }
                        }
                    },
                    "500": {
                        "description": "Internal Server Error",
                        "schema": {
                            "type": "string"
                        }
                    }
                }
            },
            "post": {
                "consumes": [
                    "application/json"
                ],
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "Branch"
                ],
                "summary": "Create branch",
                "parameters": [
                    {
                        "description": "Add Branch",
                        "name": "Branch",
                        "in": "body",
                        "required": true,
                        "schema": {
                            "$ref": "#/definitions/api.Branch"
                        }
                    }
                ],
                "responses": {
                    "200": {
                        "description": "ok",
                        "schema": {
                            "$ref": "#/definitions/api.Branch"
                        }
                    },
                    "500": {
                        "description": "Internal Server Error",
                        "schema": {
                            "type": "string"
                        }
                    }
                }
            }
        },
        "/branches/{id}": {
            "get": {
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "Branch"
                ],
                "summary": "Get single branch by its id",
                "parameters": [
                    {
                        "type": "string",
                        "description": "Branch Id",
                        "name": "id",
                        "in": "path",
                        "required": true
                    }
                ],
                "responses": {
                    "200": {
                        "description": "ok",
                        "schema": {
                            "$ref": "#/definitions/api.Branch"
                        }
                    },
                    "500": {
                        "description": "Internal Server Error",
                        "schema": {
                            "type": "string"
                        }
                    }
                }
            }
        },
        "/producers": {
            "get": {
                "consumes": [
                    "application/json"
                ],
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "Producer"
                ],
                "summary": "Get all producers",
                "responses": {
                    "200": {
                        "description": "ok",
                        "schema": {
                            "type": "array",
                            "items": {
                                "$ref": "#/definitions/api.Producer"
                            }
                        }
                    },
                    "500": {
                        "description": "Internal Server Error",
                        "schema": {
                            "type": "string"
                        }
                    }
                }
            },
            "post": {
                "consumes": [
                    "application/json"
                ],
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "Producer"
                ],
                "summary": "Create producer",
                "parameters": [
                    {
                        "description": "Add Producer",
                        "name": "Producer",
                        "in": "body",
                        "required": true,
                        "schema": {
                            "$ref": "#/definitions/api.Producer"
                        }
                    }
                ],
                "responses": {
                    "200": {
                        "description": "ok",
                        "schema": {
                            "$ref": "#/definitions/api.Producer"
                        }
                    },
                    "500": {
                        "description": "Internal Server Error",
                        "schema": {
                            "type": "string"
                        }
                    }
                }
            }
        },
        "/producers/{id}": {
            "get": {
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "Producer"
                ],
                "summary": "Get single producer by its id",
                "parameters": [
                    {
                        "type": "string",
                        "description": "Producer Id",
                        "name": "id",
                        "in": "path",
                        "required": true
                    }
                ],
                "responses": {
                    "200": {
                        "description": "ok",
                        "schema": {
                            "$ref": "#/definitions/api.Producer"
                        }
                    },
                    "500": {
                        "description": "Internal Server Error",
                        "schema": {
                            "type": "string"
                        }
                    }
                }
            }
        },
        "/receipts": {
            "get": {
                "consumes": [
                    "application/json"
                ],
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "Receipt"
                ],
                "summary": "Get all receipts",
                "responses": {
                    "200": {
                        "description": "ok",
                        "schema": {
                            "type": "array",
                            "items": {
                                "$ref": "#/definitions/api.Receipt"
                            }
                        }
                    },
                    "500": {
                        "description": "Internal Server Error",
                        "schema": {
                            "type": "string"
                        }
                    }
                }
            },
            "post": {
                "consumes": [
                    "application/json"
                ],
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "Receipt"
                ],
                "summary": "Create receipt",
                "parameters": [
                    {
                        "description": "Add Receipt",
                        "name": "Receipt",
                        "in": "body",
                        "required": true,
                        "schema": {
                            "$ref": "#/definitions/api.Receipt"
                        }
                    }
                ],
                "responses": {
                    "200": {
                        "description": "ok",
                        "schema": {
                            "$ref": "#/definitions/api.Receipt"
                        }
                    },
                    "500": {
                        "description": "Internal Server Error",
                        "schema": {
                            "type": "string"
                        }
                    }
                }
            }
        },
        "/receipts/user/{userId}": {
            "get": {
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "Receipt"
                ],
                "summary": "Get single receipt by the user id",
                "parameters": [
                    {
                        "type": "string",
                        "description": "User Id",
                        "name": "userId",
                        "in": "path",
                        "required": true
                    }
                ],
                "responses": {
                    "200": {
                        "description": "ok",
                        "schema": {
                            "type": "array",
                            "items": {
                                "$ref": "#/definitions/api.Receipt"
                            }
                        }
                    },
                    "500": {
                        "description": "Internal Server Error",
                        "schema": {
                            "type": "string"
                        }
                    }
                }
            }
        },
        "/receipts/{id}": {
            "get": {
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "Receipt"
                ],
                "summary": "Get single receipt by its id",
                "parameters": [
                    {
                        "type": "string",
                        "description": "Receipt Id",
                        "name": "id",
                        "in": "path",
                        "required": true
                    }
                ],
                "responses": {
                    "200": {
                        "description": "ok",
                        "schema": {
                            "$ref": "#/definitions/api.Receipt"
                        }
                    },
                    "500": {
                        "description": "Internal Server Error",
                        "schema": {
                            "type": "string"
                        }
                    }
                }
            }
        },
        "/retailers": {
            "get": {
                "consumes": [
                    "application/json"
                ],
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "Retailer"
                ],
                "summary": "Get all retailers",
                "responses": {
                    "200": {
                        "description": "ok",
                        "schema": {
                            "type": "array",
                            "items": {
                                "$ref": "#/definitions/api.Retailer"
                            }
                        }
                    },
                    "500": {
                        "description": "Internal Server Error",
                        "schema": {
                            "type": "string"
                        }
                    }
                }
            },
            "post": {
                "consumes": [
                    "application/json"
                ],
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "Retailer"
                ],
                "summary": "Create retailer",
                "parameters": [
                    {
                        "description": "Add Retailer",
                        "name": "Retailer",
                        "in": "body",
                        "required": true,
                        "schema": {
                            "$ref": "#/definitions/api.Retailer"
                        }
                    }
                ],
                "responses": {
                    "200": {
                        "description": "ok",
                        "schema": {
                            "$ref": "#/definitions/api.Retailer"
                        }
                    },
                    "500": {
                        "description": "Internal Server Error",
                        "schema": {
                            "type": "string"
                        }
                    }
                }
            }
        },
        "/retailers/{id}": {
            "get": {
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "Retailer"
                ],
                "summary": "Get single retailer by its id",
                "parameters": [
                    {
                        "type": "string",
                        "description": "Retailer Id",
                        "name": "id",
                        "in": "path",
                        "required": true
                    }
                ],
                "responses": {
                    "200": {
                        "description": "ok",
                        "schema": {
                            "$ref": "#/definitions/api.Retailer"
                        }
                    },
                    "500": {
                        "description": "Internal Server Error",
                        "schema": {
                            "type": "string"
                        }
                    }
                }
            }
        },
        "/stocks": {
            "get": {
                "consumes": [
                    "application/json"
                ],
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "Stock"
                ],
                "summary": "Get all stock datas",
                "responses": {
                    "200": {
                        "description": "ok",
                        "schema": {
                            "type": "array",
                            "items": {
                                "$ref": "#/definitions/api.Stock"
                            }
                        }
                    },
                    "500": {
                        "description": "Internal Server Error",
                        "schema": {
                            "type": "string"
                        }
                    }
                }
            },
            "post": {
                "consumes": [
                    "application/json"
                ],
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "Stock"
                ],
                "summary": "Create price data",
                "parameters": [
                    {
                        "description": "Add Stock",
                        "name": "Stock",
                        "in": "body",
                        "required": true,
                        "schema": {
                            "$ref": "#/definitions/api.Stock"
                        }
                    }
                ],
                "responses": {
                    "200": {
                        "description": "ok",
                        "schema": {
                            "$ref": "#/definitions/api.Stock"
                        }
                    },
                    "500": {
                        "description": "Internal Server Error",
                        "schema": {
                            "type": "string"
                        }
                    }
                }
            }
        },
        "/stocks/{id}": {
            "get": {
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "Stock"
                ],
                "summary": "Get single price data by its id",
                "parameters": [
                    {
                        "type": "string",
                        "description": "Stock Id",
                        "name": "id",
                        "in": "path",
                        "required": true
                    }
                ],
                "responses": {
                    "200": {
                        "description": "ok",
                        "schema": {
                            "$ref": "#/definitions/api.Stock"
                        }
                    },
                    "500": {
                        "description": "Internal Server Error",
                        "schema": {
                            "type": "string"
                        }
                    }
                }
            }
        },
        "/transactions": {
            "get": {
                "consumes": [
                    "application/json"
                ],
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "Transaction"
                ],
                "summary": "Get all transactions",
                "responses": {
                    "200": {
                        "description": "ok",
                        "schema": {
                            "type": "array",
                            "items": {
                                "$ref": "#/definitions/api.Transaction"
                            }
                        }
                    },
                    "500": {
                        "description": "Internal Server Error",
                        "schema": {
                            "type": "string"
                        }
                    }
                }
            },
            "post": {
                "consumes": [
                    "application/json"
                ],
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "Transaction"
                ],
                "summary": "Create transaction",
                "parameters": [
                    {
                        "description": "Add Transaction",
                        "name": "Transaction",
                        "in": "body",
                        "required": true,
                        "schema": {
                            "$ref": "#/definitions/api.Transaction"
                        }
                    }
                ],
                "responses": {
                    "200": {
                        "description": "ok",
                        "schema": {
                            "$ref": "#/definitions/api.Transaction"
                        }
                    },
                    "500": {
                        "description": "Internal Server Error",
                        "schema": {
                            "type": "string"
                        }
                    }
                }
            }
        },
        "/transactions/user/{userId}": {
            "get": {
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "Transaction"
                ],
                "summary": "Get single transaction by the user id",
                "parameters": [
                    {
                        "type": "string",
                        "description": "User Id",
                        "name": "userId",
                        "in": "path",
                        "required": true
                    }
                ],
                "responses": {
                    "200": {
                        "description": "ok",
                        "schema": {
                            "type": "array",
                            "items": {
                                "$ref": "#/definitions/api.Transaction"
                            }
                        }
                    },
                    "500": {
                        "description": "Internal Server Error",
                        "schema": {
                            "type": "string"
                        }
                    }
                }
            }
        },
        "/transactions/{id}": {
            "get": {
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "Transaction"
                ],
                "summary": "Get single transaction by its id",
                "parameters": [
                    {
                        "type": "string",
                        "description": "Transaction Id",
                        "name": "id",
                        "in": "path",
                        "required": true
                    }
                ],
                "responses": {
                    "200": {
                        "description": "ok",
                        "schema": {
                            "$ref": "#/definitions/api.Transaction"
                        }
                    },
                    "500": {
                        "description": "Internal Server Error",
                        "schema": {
                            "type": "string"
                        }
                    }
                }
            }
        },
        "/users": {
            "get": {
                "consumes": [
                    "application/json"
                ],
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "User"
                ],
                "summary": "Get all users",
                "responses": {
                    "200": {
                        "description": "ok",
                        "schema": {
                            "type": "array",
                            "items": {
                                "$ref": "#/definitions/api.User"
                            }
                        }
                    },
                    "500": {
                        "description": "Internal Server Error",
                        "schema": {
                            "type": "string"
                        }
                    }
                }
            },
            "post": {
                "consumes": [
                    "application/json"
                ],
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "User"
                ],
                "summary": "Create user",
                "parameters": [
                    {
                        "description": "Add User",
                        "name": "User",
                        "in": "body",
                        "required": true,
                        "schema": {
                            "$ref": "#/definitions/api.User"
                        }
                    }
                ],
                "responses": {
                    "200": {
                        "description": "ok",
                        "schema": {
                            "$ref": "#/definitions/api.User"
                        }
                    },
                    "500": {
                        "description": "Internal Server Error",
                        "schema": {
                            "type": "string"
                        }
                    }
                }
            }
        },
        "/users/{id}": {
            "get": {
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "User"
                ],
                "summary": "Get single user by his id",
                "parameters": [
                    {
                        "type": "string",
                        "description": "User Id",
                        "name": "id",
                        "in": "path",
                        "required": true
                    }
                ],
                "responses": {
                    "200": {
                        "description": "ok",
                        "schema": {
                            "$ref": "#/definitions/api.User"
                        }
                    },
                    "500": {
                        "description": "Internal Server Error",
                        "schema": {
                            "type": "string"
                        }
                    }
                }
            }
        }
    },
    "definitions": {
        "api.Branch": {
            "type": "object",
            "properties": {
                "id": {
                    "type": "string"
                },
                "municipality": {
                    "type": "string"
                },
                "name": {
                    "type": "string"
                },
                "retailerId": {
                    "type": "string"
                },
                "retailerName": {
                    "type": "string"
                },
                "street": {
                    "type": "string"
                }
            }
        },
        "api.PriceData": {
            "type": "object",
            "properties": {
                "adjClose": {
                    "type": "number"
                },
                "close": {
                    "type": "number"
                },
                "date": {
                    "type": "string"
                },
                "high": {
                    "type": "number"
                },
                "low": {
                    "type": "number"
                },
                "open": {
                    "type": "number"
                },
                "volume": {
                    "type": "integer"
                }
            }
        },
        "api.Producer": {
            "type": "object",
            "properties": {
                "iconId": {
                    "type": "string"
                },
                "id": {
                    "type": "string"
                },
                "name": {
                    "type": "string"
                },
                "stockId": {
                    "type": "string"
                }
            }
        },
        "api.Receipt": {
            "type": "object",
            "properties": {
                "branchId": {
                    "type": "string"
                },
                "date": {
                    "type": "string"
                },
                "description": {
                    "type": "string"
                },
                "entries": {
                    "type": "array",
                    "items": {
                        "$ref": "#/definitions/api.ReceiptEntry"
                    }
                },
                "id": {
                    "type": "string"
                },
                "metadata": {
                    "$ref": "#/definitions/api.ReceiptMetadata"
                },
                "retailerId": {
                    "type": "string"
                },
                "userid": {
                    "type": "string"
                }
            }
        },
        "api.ReceiptEntry": {
            "type": "object",
            "properties": {
                "amount": {
                    "type": "number"
                },
                "category": {
                    "type": "string"
                },
                "priceTendency": {
                    "type": "string"
                },
                "producerId": {
                    "type": "string"
                },
                "quantity": {
                    "type": "integer"
                },
                "title": {
                    "type": "string"
                },
                "vatRate": {
                    "type": "number"
                }
            }
        },
        "api.ReceiptMetadata": {
            "type": "object",
            "properties": {
                "checkSum": {
                    "type": "string"
                },
                "serialNumber": {
                    "type": "string"
                },
                "signatureCount": {
                    "type": "string"
                },
                "terminalId": {
                    "type": "string"
                },
                "transactionId": {
                    "type": "string"
                }
            }
        },
        "api.Retailer": {
            "type": "object",
            "properties": {
                "category": {
                    "type": "string"
                },
                "country": {
                    "type": "string"
                },
                "id": {
                    "type": "string"
                },
                "municipality": {
                    "type": "string"
                },
                "name": {
                    "type": "string"
                },
                "street": {
                    "type": "string"
                }
            }
        },
        "api.Stock": {
            "type": "object",
            "properties": {
                "description": {
                    "type": "string"
                },
                "id": {
                    "type": "string"
                },
                "isin": {
                    "type": "string"
                },
                "lastPrice": {
                    "type": "number"
                },
                "name": {
                    "type": "string"
                },
                "priceData": {
                    "type": "array",
                    "items": {
                        "$ref": "#/definitions/api.PriceData"
                    }
                },
                "symbol": {
                    "type": "string"
                },
                "tendencyWeek": {
                    "type": "string"
                },
                "tendencyYear": {
                    "type": "string"
                },
                "wkn": {
                    "type": "string"
                }
            }
        },
        "api.Transaction": {
            "type": "object",
            "properties": {
                "amount": {
                    "type": "number"
                },
                "date": {
                    "type": "string"
                },
                "description": {
                    "type": "string"
                },
                "iban": {
                    "type": "string"
                },
                "id": {
                    "type": "string"
                },
                "purpose": {
                    "type": "string"
                },
                "receiptId": {
                    "type": "string"
                },
                "retailerId": {
                    "type": "string"
                },
                "title": {
                    "type": "string"
                },
                "userid": {
                    "type": "string"
                },
                "valutaDate": {
                    "type": "string"
                }
            }
        },
        "api.User": {
            "type": "object",
            "properties": {
                "firstname": {
                    "type": "string"
                },
                "id": {
                    "type": "string"
                },
                "lastname": {
                    "type": "string"
                }
            }
        }
    }
}`

// SwaggerInfo holds exported Swagger Info so clients can modify it
var SwaggerInfo = &swag.Spec{
	Version:          "",
	Host:             "",
	BasePath:         "/api",
	Schemes:          []string{},
	Title:            "",
	Description:      "",
	InfoInstanceName: "swagger",
	SwaggerTemplate:  docTemplate,
}

func init() {
	swag.Register(SwaggerInfo.InstanceName(), SwaggerInfo)
}
