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
var RetailersCollection *mongo.Collection
var BranchesCollection *mongo.Collection
var ProducersCollection *mongo.Collection
var StocksCollection *mongo.Collection
var UsersCollection *mongo.Collection

var dbName string = "baltic-byte-buccaneers"

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
	TransactionsCollection = MongoClient.Database(dbName).Collection("transactions")
	ReceiptsCollection = MongoClient.Database(dbName).Collection("receipts")
	RetailersCollection = MongoClient.Database(dbName).Collection("retailers")
	BranchesCollection = MongoClient.Database(dbName).Collection("branches")
	ProducersCollection = MongoClient.Database(dbName).Collection("producers")
	StocksCollection = MongoClient.Database(dbName).Collection("stocks")
	UsersCollection = MongoClient.Database(dbName).Collection("users")
}

func DisconnectDbConnection() {
	defer func() {
		if err := MongoClient.Disconnect(context.TODO()); err != nil {
			panic(err)
		}
	}()
}
