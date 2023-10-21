from random import choice
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

def add_users(users_collection: any, transactions_collection: any, receipts_collection: any):
    user_ids = [""]

    user = dict()
    user["firstname"] = "John"
    user["lastname"] = "Doe"
    result = users_collection.insert_one(user)
    user_ids.append(result.inserted_id)

    user = dict()
    user["firstname"] = "Max"
    user["lastname"] = "Mustermann"
    result = users_collection.insert_one(user)
    user_ids.append(result.inserted_id)

    user = dict()
    user["firstname"] = "Ludwig"
    user["lastname"] = "Husten"
    result = users_collection.insert_one(user)
    user_ids.append(result.inserted_id)

    user = dict()
    user["firstname"] = "Bob Baumeister"
    user["lastname"] = "Doe"
    result = users_collection.insert_one(user)
    user_ids.append(result.inserted_id)

    user = dict()
    user["firstname"] = "Heinz"
    user["lastname"] = "Ketchup"
    result = users_collection.insert_one(user)
    user_ids.append(result.inserted_id)

    transactions = transactions_collection.find()

    for transaction in transactions:
        random_user_id = choice(user_ids)
        transaction["userId"] = random_user_id
        transactions_collection.update_one({"_id": transaction["_id"]}, {"$set": transaction})

        receipt = receipts_collection.find_one({"_id": transaction["receiptId"]})
        receipt["userId"] = random_user_id
        receipts_collection.update_one({"_id": receipt["_id"]}, {"$set": receipt})


if __name__ == "__main__":

    mongo_wrapper = MongoWrapper("mongodb://localhost:27017")
    bbb_database = mongo_wrapper.get_database("baltic-byte-buccaneers")

    users_collection = bbb_database["users"]
    transactions_collection = bbb_database["transactions"]
    receipts_collection = bbb_database["receipts"]

    add_users(users_collection, transactions_collection, receipts_collection)

