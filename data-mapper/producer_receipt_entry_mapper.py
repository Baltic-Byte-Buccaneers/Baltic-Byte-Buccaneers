from datetime import datetime
from random import choice
from typing import Collection
from db.mongo_wrapper import MongoWrapper
from utils.file_loader import FileLoader
from utils.random_data_generator import RandomDataGenerator


def __create_price_data(path: str) -> list:
    price_datas =  FileLoader.load_file_as_csv(path)

    for price_data in price_datas:
        price_data["date"] = datetime.strptime(price_data["date"], "%Y-%m-%d")

    return price_datas

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

