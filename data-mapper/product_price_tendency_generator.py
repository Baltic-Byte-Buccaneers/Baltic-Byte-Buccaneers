from random import choice
from db.mongo_wrapper import MongoWrapper
from utils.random_data_generator import RandomDataGenerator


price_tendencies = ["up", "down", "neutral"]

def __get_random_price_tendency() -> str:
    return choice(price_tendencies)

def __apply_receipt_price_tendency(receipts_collection: any):
    receipts = receipts_collection.find()

    for receipt in receipts:
        for entry in receipt["entries"]:
            entry["priceTendency"] = __get_random_price_tendency()

        receipts_collection.update_one({"_id": receipt["_id"]}, {"$set": receipt})


if __name__ == "__main__":

    mongo_wrapper = MongoWrapper("mongodb://localhost:27017")
    bbb_database = mongo_wrapper.get_database("baltic-byte-buccaneers")

    receipts_collection = bbb_database["receipts"]

    __apply_receipt_price_tendency(receipts_collection)

