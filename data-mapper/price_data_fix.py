from random import choice
from db.mongo_wrapper import MongoWrapper


def fix_price_data(stocks_collection: any):
    stocks = stocks_collection.find()
    for stock in stocks:
        if stock["priceData"] is not None:
            for priceData in stock["priceData"]:
                try:
                    priceData["open"] = float(priceData["open"])
                except ValueError:
                    priceData["open"] = ""

                try:
                    priceData["high"] = float(priceData["high"])
                except ValueError:
                    priceData["high"] = ""

                try:
                    priceData["low"] = float(priceData["low"])
                except ValueError:
                    priceData["low"] = ""

                try:
                    priceData["close"] = float(priceData["close"])
                except ValueError:
                    priceData["close"] = ""

                try:
                    priceData["adjClose"] = float(priceData["adjClose"])
                except ValueError:
                    priceData["adjClose"] = ""

                try:
                    priceData["volume"] = int(priceData["volume"])
                except ValueError:
                    priceData["volume"] = ""

        stocks_collection.update_one({"_id": stock["_id"]}, {"$set": stock})


if __name__ == "__main__":

    mongo_wrapper = MongoWrapper("mongodb://localhost:27017")
    bbb_database = mongo_wrapper.get_database("baltic-byte-buccaneers")

    stocks_collection = bbb_database["stocks"]

    fix_price_data(stocks_collection)

