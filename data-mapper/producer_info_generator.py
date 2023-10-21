from datetime import datetime
from typing import Collection
from db.mongo_wrapper import MongoWrapper
from utils.file_loader import FileLoader
from utils.random_data_generator import RandomDataGenerator


def __create_price_data(path: str) -> list:
    price_datas =  FileLoader.load_file_as_csv(path)

    for price_data in price_datas:
        price_data["date"] = datetime.strptime(price_data["date"], "%Y-%m-%d")

    return price_datas

def __add_producers(producers_collection: any, stocks_collections: any):
    stock = dict()
    stock["description"] = "Associated British Foods is a diversified international food, ingredients and retail group with sales of £13.9bn, 128,000 employees and operations in 53 countries across Europe, Africa, the Americas, Asia and Australia."
    stock["isin"] = "GB0006731235"
    stock["name"] = "Associated British Foods"
    stock["lastPrice"] = 21.85
    stock["priceData"] = __create_price_data("stock-price-data/ABF.L.csv")
    stock["symbol"] = "ASBFF"
    stock["tendencyWeek"] = "down"
    stock["tendencyYear"] = "up"
    stock["wkn"] = "920876"
    result = stocks_collections.insert_one(stock)

    producer = dict()
    producer["name"] = "Associated British Foods"
    producer["stockId"] = result.inserted_id
    producers_collection.insert_one(producer)


    stock = dict()
    stock["description"] = "The Bonduelle Group is a French family-owned business present in nearly 100 countries, whose mission is to inspire the transition toward a plant-based diet to contribute to people's well-being and planet health."
    stock["isin"] = "FR0000063935"
    stock["lastPrice"] = 10.56
    stock["name"] = "Bonduelle"
    stock["symbol"] = ""
    stock["tendencyWeek"] = "down"
    stock["tendencyYear"] = "down"
    stock["priceData"] = __create_price_data("stock-price-data/BON.PA.csv")
    stock["wkn"] = "915165"
    result = stocks_collections.insert_one(stock)

    producer = dict()
    producer["name"] = "Bonduelle"
    producer["stockId"] = result.inserted_id
    producers_collection.insert_one(producer)


    stock = dict()
    stock["description"] = "Welcome to FRoSTA AG. As one of the largest manufacturers of frozen foods in Europe what we want more than anything is that our customers are always delighted with our products. We take the utmost care to ensure that all our products are of the highest quality and always safe."
    stock["isin"] = "DE0006069008"
    stock["lastPrice"] = 63.20
    stock["name"] = "FRoSTA"
    stock["symbol"] = ""
    stock["tendencyWeek"] = "down"
    stock["tendencyYear"] = "up"
    stock["priceData"] = __create_price_data("stock-price-data/NLM.F.csv")
    stock["wkn"] = "915165"
    result = stocks_collections.insert_one(stock)

    producer = dict()
    producer["name"] = "FRoSTA"
    producer["stockId"] = result.inserted_id
    producers_collection.insert_one(producer)


    stock = dict()
    stock["description"] = "The Bel Group is a family company with over 150 years of history and ranks as a major international player in food. Marketing products made from milk, fruit and vegetables, the Group's corporate mission is to provide everyone with a healthier and more sustainable food."
    stock["isin"] = "FR0000121857"
    stock["lastPrice"] = 540.0
    stock["name"] = "Fromageries Bel"
    stock["symbol"] = "FGRBF"
    stock["tendencyWeek"] = "neutral"
    stock["tendencyYear"] = "neutral"
    stock["priceData"] = None
    stock["wkn"] = "854249"
    result = stocks_collections.insert_one(stock)

    producer = dict()
    producer["name"] = "Fromageries Bel"
    producer["stockId"] = result.inserted_id
    producers_collection.insert_one(producer)


    stock = dict()
    stock["description"] = "The food brand from Berlin with an innovative, sustainable variety of products and pure vegan pleasure."
    stock["isin"] = "DE000A3E5ED2"
    stock["lastPrice"] = 27.4
    stock["name"] = "Veganz"
    stock["symbol"] = ""
    stock["tendencyWeek"] = "down"
    stock["tendencyYear"] = "down"
    stock["priceData"] = __create_price_data("stock-price-data/VEZ.DE.csv")
    stock["wkn"] = "A3E5ED"
    result = stocks_collections.insert_one(stock)

    producer = dict()
    producer["name"] = "Veganz"
    producer["stockId"] = result.inserted_id
    producers_collection.insert_one(producer)


    stock = dict()
    stock["description"] = "At Unilever we meet everyday needs for nutrition, hygiene and personal care with brands that help people feel good, look good and get more out of life."
    stock["isin"] = "GB00B10RZP78"
    stock["lastPrice"] = 3990.00
    stock["name"] = "Unilever"
    stock["symbol"] = "UNLYF"
    stock["tendencyWeek"] = "up"
    stock["tendencyYear"] = "up"
    stock["priceData"] = __create_price_data("stock-price-data/ULVR.L.csv")
    stock["wkn"] = "A0JNE2"
    result = stocks_collections.insert_one(stock)

    producer = dict()
    producer["name"] = "Unilever"
    producer["stockId"] = result.inserted_id
    producers_collection.insert_one(producer)


    stock = dict()
    stock["description"] = "The Kraft Heinz Company is the third-largest food and beverage company in North America and the fifth-largest food and beverage company in the world, with eight $1 billion+ brands."
    stock["isin"] = "US5007541064"
    stock["lastPrice"] = 31.31
    stock["name"] = "The Kraft Heinz Company"
    stock["symbol"] = "KHC"
    stock["tendencyWeek"] = "down"
    stock["tendencyYear"] = "up"
    stock["priceData"] = __create_price_data("stock-price-data/KHC.csv")
    stock["wkn"] = "A14TU4"
    result = stocks_collections.insert_one(stock)

    producer = dict()
    producer["name"] = "The Kraft Heinz Company"
    producer["stockId"] = result.inserted_id
    producers_collection.insert_one(producer)


    stock = dict()
    stock["description"] = "Südzucker offers the broadest range of beet sugars and sugar specialties. Made from European beets. Experience our customer-centric solutions and services."
    stock["isin"] = "DE0007297004"
    stock["lastPrice"] = 14.14
    stock["name"] = "Südzucker"
    stock["symbol"] = "SUEZF"
    stock["tendencyWeek"] = "neutral"
    stock["tendencyYear"] = "down"
    stock["priceData"] = __create_price_data("stock-price-data/SZU.DE.csv")
    stock["wkn"] = "729700"
    result = stocks_collections.insert_one(stock)

    producer = dict()
    producer["name"] = "Südzucker"
    producer["stockId"] = result.inserted_id
    producers_collection.insert_one(producer)


    stock = dict()
    stock["description"] = "P&G is improving everyday life as a Force for Growth and a Force for Good — for you, for the world, and for generations to come."
    stock["isin"] = "US7427181091"
    stock["lastPrice"] = 148.05
    stock["name"] = "Procter Gamble"
    stock["symbol"] = "PG"
    stock["tendencyWeek"] = "down"
    stock["tendencyYear"] = "up"
    stock["priceData"] = __create_price_data("stock-price-data/PG.csv")
    stock["wkn"] = "852062"
    result = stocks_collections.insert_one(stock)

    producer = dict()
    producer["name"] = "Procter Gamble"
    producer["stockId"] = result.inserted_id
    producers_collection.insert_one(producer)


    stock = dict()
    stock["description"] = "Global Leader in Convenient Foods and Beverages. PepsiCo products are enjoyed by consumers more than one billion times a day in more than 200 countries and territories around the world."
    stock["isin"] = "US7134481081"
    stock["lastPrice"] = 160.00
    stock["name"] = "PepsiCo"
    stock["symbol"] = "PEP"
    stock["tendencyWeek"] = "down"
    stock["tendencyYear"] = "down"
    stock["priceData"] = __create_price_data("stock-price-data/PEP.csv")
    stock["wkn"] = "851995"
    result = stocks_collections.insert_one(stock)

    producer = dict()
    producer["name"] = "PepsiCo"
    producer["stockId"] = result.inserted_id
    producers_collection.insert_one(producer)


    stock = dict()
    stock["description"] = "#16erBlech"
    stock["isin"] = "AT0000758008"
    stock["lastPrice"] = 86.00
    stock["name"] = "Ottakringer Brauerei"
    stock["symbol"] = ""
    stock["tendencyWeek"] = "up"
    stock["tendencyYear"] = "up"
    stock["priceData"] = __create_price_data("stock-price-data/OTS.VI.csv")
    stock["wkn"] = "896689"
    result = stocks_collections.insert_one(stock)

    producer = dict()
    producer["name"] = "Ottakringer Brauerei"
    producer["stockId"] = result.inserted_id
    producers_collection.insert_one(producer)


    stock = dict()
    stock["description"] = "Josef Manner & Comp AG, an Austrian family business, unifies five of the best-known and well-liked confectionary brands. Our brands are associated with high quality and delicious taste. All products are manufactured exclusively at locations in Austria."
    stock["isin"] = "AT0000728209"
    stock["lastPrice"] = 110.00
    stock["name"] = "Josef Manner"
    stock["symbol"] = ""
    stock["tendencyWeek"] = "neutral"
    stock["tendencyYear"] = "down"
    stock["priceData"] = __create_price_data("stock-price-data/MAN.VI.csv")
    stock["wkn"] = "851676"
    result = stocks_collections.insert_one(stock)

    producer = dict()
    producer["name"] = "Josef Manner"
    producer["stockId"] = result.inserted_id
    producers_collection.insert_one(producer)


    stock = dict()
    stock["description"] = "Kellogg's products are manufactured and marketed in over 180 countries. Kellogg's largest factory is at Trafford Park in Trafford, Greater Manchester, United Kingdom, which is also the location of its UK headquarters."
    stock["isin"] = "US4878361082"
    stock["lastPrice"] = 50.12
    stock["name"] = "Kellogg's"
    stock["symbol"] = "K"
    stock["tendencyWeek"] = "up"
    stock["tendencyYear"] = "up"
    stock["priceData"] = __create_price_data("stock-price-data/K.csv")
    stock["wkn"] = "853265"
    result = stocks_collections.insert_one(stock)

    producer = dict()
    producer["name"] = "Kellogg's"
    producer["stockId"] = result.inserted_id
    producers_collection.insert_one(producer)

    producer = dict()
    producer["name"] = "Carl Kühne KG"
    producer["stockId"] = None
    producers_collection.insert_one(producer)

    producer = dict()
    producer["name"] = "Stadtfleischerei Lange"
    producer["stockId"] = None
    producers_collection.insert_one(producer)

    producer = dict()
    producer["name"] = "Bautz'ner Senf & Feinkost GmbH"
    producer["stockId"] = None
    producers_collection.insert_one(producer)

    producer = dict()
    producer["name"] = "Werder Feinkost"
    producer["stockId"] = None
    producers_collection.insert_one(producer)


if __name__ == "__main__":

    mongo_wrapper = MongoWrapper("mongodb://localhost:27017")
    bbb_database = mongo_wrapper.get_database("baltic-byte-buccaneers")

    producers_collection = bbb_database["producers"]
    price_datas_collection = bbb_database["stocks"]

    __add_producers(producers_collection, price_datas_collection)

