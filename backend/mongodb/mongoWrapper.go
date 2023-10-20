package mongodb

import (
	"context"

	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"
	"go.mongodb.org/mongo-driver/mongo/readpref"
)

var MongoClient *mongo.Client

var TransactionsCollection *mongo.Collection
var ReceiptsCollection *mongo.Collection

func InitDbConnection(connectionString string) {
	client, err := mongo.Connect(context.TODO(), options.Client().ApplyURI(connectionString))

	if err != nil {
		panic(err)
	}

	// Check connection
	if err := client.Ping(context.TODO(), readpref.Primary()); err != nil {
		panic(err)
	}

	MongoClient = client
	TransactionsCollection = MongoClient.Database("baltic-byte-buccaneers").Collection("transactions")
	ReceiptsCollection = MongoClient.Database("baltic-byte-buccaneers").Collection("receipts")
}

func DisconnectDbConnection() {
	defer func() {
		if err := MongoClient.Disconnect(context.TODO()); err != nil {
			panic(err)
		}
	}()
}
