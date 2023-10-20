from datetime import datetime
from typing import Collection
from db.mongo_wrapper import MongoWrapper
from utils.random_data_generator import RandomDataGenerator

def __generate_receipt_metadata() -> dict:
    receipt_metadata = dict()
    receipt_metadata["transactionId"] = RandomDataGenerator.generate_random_number_string(6)
    receipt_metadata["serialNumber"] = RandomDataGenerator.generate_random_hex_string(32)
    receipt_metadata["signatureCount"] = RandomDataGenerator.generate_random_number_string(6)
    receipt_metadata["terminalId"] = RandomDataGenerator.generate_random_hex_string(10)
    receipt_metadata["checkSum"] = RandomDataGenerator.generate_random_string(64)

    return receipt_metadata

def __apply_receipt_metadata(receipts_collection: any):
    receipts = receipts_collection.find()

    for receipt in receipts:
        receipt["metadata"] = __generate_receipt_metadata()
        receipts_collection.update_one({"_id": receipt["_id"]}, {"$set": receipt})


if __name__ == "__main__":

    mongo_wrapper = MongoWrapper("mongodb://localhost:27017")
    bbb_database = mongo_wrapper.get_database("baltic-byte-buccaneers")

    receipts_collection = bbb_database["receipts"]

    __apply_receipt_metadata(receipts_collection)

