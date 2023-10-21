from random import choice
from db.mongo_wrapper import MongoWrapper
from utils.random_data_generator import RandomDataGenerator


def fix_amounts(transactions_collection: any, receipts_collection: any):
    transactions = transactions_collection.find()

    for transaction in transactions:
        transaction["amount"] = -abs(transaction["amount"])
        receiptCode = RandomDataGenerator.generate_random_string(5).upper()
        transaction["purpose"] = f"VISA SB#{receiptCode}"

        receipt = receipts_collection.find_one({"_id": transaction["receiptId"]})
        transaction["retailerId"] = receipt["retailerId"]
        transactions_collection.update_one({"_id": transaction["_id"]}, {"$set": transaction})

    receipts = receipts_collection.find()

    for receipt in receipts:
        for entry in receipt["entries"]:
            entry["amount"] = -abs(entry["amount"])

        receipts_collection.update_one({"_id": receipt["_id"]}, {"$set": receipt})

    
if __name__ == "__main__":

    mongo_wrapper = MongoWrapper("mongodb://localhost:27017")
    bbb_database = mongo_wrapper.get_database("baltic-byte-buccaneers")

    transactions_collection = bbb_database["transactions"]
    receipts_collection = bbb_database["receipts"]

    fix_amounts(transactions_collection, receipts_collection)

