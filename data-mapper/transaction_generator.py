from datetime import datetime, timedelta
from random import choice, randrange, uniform
from db.mongo_wrapper import MongoWrapper
from utils.random_data_generator import RandomDataGenerator

def __generate_transaction(userids: list) -> dict:
    transaction = dict()
    transaction["amount"] = -abs(round(uniform(1.5, 100.50), 2))

    date = datetime.now() - timedelta(days=randrange(680))
    transaction["userId"] = choice(userids)
    transaction["iban"] = ""
    transaction["date"] = date
    transaction["purpose"] = "Lastschrift (SEPA)"
    transaction["receiptId"] = None
    transaction["retailerId"] = None

    return transaction

def __generate_new_transactions(transactions_collection: any, users_collection: any):
    users = users_collection.find()
    userids = [user["_id"] for user in users]

    for i in range(3000):
        transaction = __generate_transaction(userids)
        transactions_collection.insert_one(transaction)


if __name__ == "__main__":

    mongo_wrapper = MongoWrapper("mongodb://localhost:27017")
    bbb_database = mongo_wrapper.get_database("baltic-byte-buccaneers")

    transactions_collection = bbb_database["transactions"]
    users_collection = bbb_database["users"]

    __generate_new_transactions(transactions_collection, users_collection)

