from random import choice
from db.mongo_wrapper import MongoWrapper


def map_producer_to_receipt_entry(producers_collection: any, receipts_collection: any):
    producers_cursor = producers_collection.find()
    receipts = receipts_collection.find()

    producers = [producer for producer in producers_cursor]

    for receipt in receipts:
        for entry in receipt["entries"]:
            random_producer = choice(producers)
            entry["producerId"] = random_producer["_id"]

        receipts_collection.update_one({"_id": receipt["_id"]}, {"$set": receipt})


if __name__ == "__main__":

    mongo_wrapper = MongoWrapper("mongodb://localhost:27017")
    bbb_database = mongo_wrapper.get_database("baltic-byte-buccaneers")

    producers_collection = bbb_database["producers"]
    receipts_collection = bbb_database["receipts"]

    map_producer_to_receipt_entry(producers_collection, receipts_collection)

