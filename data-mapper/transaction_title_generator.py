from db.mongo_wrapper import MongoWrapper

def __generate_new_transactions(transactions_collection: any, retailer_collection: any):
    transactions = transactions_collection.find()

    for transaction in transactions:
        if transaction["retailerId"] is not None:
            retailer = retailer_collection.find_one({"_id": transaction["retailerId"]})
            transaction["title"] = f"Shopping at {retailer['name']}"
        else:
            transaction["title"] = "Direct Debit"

        transactions_collection.update_one({"_id": transaction["_id"]}, {"$set": transaction})


if __name__ == "__main__":

    mongo_wrapper = MongoWrapper("mongodb://localhost:27017")
    bbb_database = mongo_wrapper.get_database("baltic-byte-buccaneers")

    transactions_collection = bbb_database["transactions"]
    retailer_collection = bbb_database["retailers"]

    __generate_new_transactions(transactions_collection, retailer_collection)

