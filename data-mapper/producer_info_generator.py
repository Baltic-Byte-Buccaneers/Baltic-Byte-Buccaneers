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

def __add_producer(producers_collection: any):
    producer = dict()
    producer["description"] = "Associated British Foods is a diversified international food, ingredients and retail group with sales of £13.9bn, 128,000 employees and operations in 53 countries across Europe, Africa, the Americas, Asia and Australia."
    producer["isin"] = "GB0006731235"
    producer["lastPrice"] = 21.85
    producer["name"] = "Associated British Foods"
    producer["symbol"] = "ASBFF"
    producer["tendency"] = "down"
    producer["priceData"] = __create_price_data("stock-price-data/ABF.L.csv")
    producer["wkn"] = "920876"
    producers_collection.insert_one(producer)

    producer = dict()
    producer["description"] = "The Bonduelle Group is a French family-owned business present in nearly 100 countries, whose mission is to inspire the transition toward a plant-based diet to contribute to people's well-being and planet health."
    producer["isin"] = "FR0000063935"
    producer["lastPrice"] = 10.56
    producer["name"] = "Bonduelle"
    producer["symbol"] = ""
    producer["tendency"] = "down"
    producer["priceData"] = __create_price_data("stock-price-data/BON.PA.csv")
    producer["wkn"] = "915165"
    producers_collection.insert_one(producer)

    producer = dict()
    producer["description"] = "Welcome to FRoSTA AG. As one of the largest manufacturers of frozen foods in Europe what we want more than anything is that our customers are always delighted with our products. We take the utmost care to ensure that all our products are of the highest quality and always safe."
    producer["isin"] = "DE0006069008"
    producer["lastPrice"] = 63.20
    producer["name"] = "FRoSTA"
    producer["symbol"] = ""
    producer["tendency"] = "down"
    producer["priceData"] = __create_price_data("stock-price-data/NLM.F.csv")
    producer["wkn"] = "606900"
    producers_collection.insert_one(producer)

    producer = dict()
    producer["description"] = "The Bel Group is a family company with over 150 years of history and ranks as a major international player in food. Marketing products made from milk, fruit and vegetables, the Group's corporate mission is to provide everyone with a healthier and more sustainable food."
    producer["isin"] = "FR0000121857"
    producer["lastPrice"] = 540.0
    producer["name"] = "Fromageries Bel"
    producer["symbol"] = "FGRBF"
    producer["tendency"] = "neutral"
    producer["priceData"] = list()
    producer["wkn"] = "854249"
    producers_collection.insert_one(producer)

    producer = dict()
    producer["description"] = "The food brand from Berlin with an innovative, sustainable variety of products and pure vegan pleasure."
    producer["isin"] = "DE000A3E5ED2"
    producer["lastPrice"] = 27.4
    producer["name"] = "Veganz"
    producer["symbol"] = ""
    producer["tendency"] = "down"
    producer["priceData"] = __create_price_data("stock-price-data/VEZ.DE.csv")
    producer["wkn"] = "A3E5ED"
    producers_collection.insert_one(producer)

    producer = dict()
    producer["description"] = "At Unilever we meet everyday needs for nutrition, hygiene and personal care with brands that help people feel good, look good and get more out of life."
    producer["isin"] = "GB00B10RZP78"
    producer["lastPrice"] = 3990.00
    producer["name"] = "Unilever"
    producer["symbol"] = "UNLYF"
    producer["tendency"] = "up"
    producer["priceData"] = __create_price_data("stock-price-data/ULVR.L.csv")
    producer["wkn"] = "A0JNE2"
    producers_collection.insert_one(producer)

    producer = dict()
    producer["description"] = "The Kraft Heinz Company is the third-largest food and beverage company in North America and the fifth-largest food and beverage company in the world, with eight $1 billion+ brands."
    producer["isin"] = "US5007541064"
    producer["lastPrice"] = 31.31
    producer["name"] = "The Kraft Heinz Company"
    producer["symbol"] = "KHC"
    producer["tendency"] = "down"
    producer["priceData"] = __create_price_data("stock-price-data/KHC.csv")
    producer["wkn"] = "A14TU4"
    producers_collection.insert_one(producer)

    producer = dict()
    producer["description"] = "Südzucker offers the broadest range of beet sugars and sugar specialties. Made from European beets. Experience our customer-centric solutions and services."
    producer["isin"] = "DE0007297004"
    producer["lastPrice"] = 14.14
    producer["name"] = "Südzucker"
    producer["symbol"] = "SUEZF"
    producer["tendency"] = "neutral"
    producer["priceData"] = __create_price_data("stock-price-data/SZU.DE.csv")
    producer["wkn"] = "729700"
    producers_collection.insert_one(producer)

    producer = dict()
    producer["description"] = "P&G is improving everyday life as a Force for Growth and a Force for Good — for you, for the world, and for generations to come."
    producer["isin"] = "US7427181091"
    producer["lastPrice"] = 148.05
    producer["name"] = "Procter Gamble"
    producer["symbol"] = "PG"
    producer["tendency"] = "down"
    producer["priceData"] = __create_price_data("stock-price-data/PG.csv")
    producer["wkn"] = "852062"
    producers_collection.insert_one(producer)

    producer = dict()
    producer["description"] = "Global Leader in Convenient Foods and Beverages. PepsiCo products are enjoyed by consumers more than one billion times a day in more than 200 countries and territories around the world."
    producer["isin"] = "US7134481081"
    producer["lastPrice"] = 160.00
    producer["name"] = "PepsiCo"
    producer["symbol"] = "PEP"
    producer["tendency"] = "down"
    producer["priceData"] = __create_price_data("stock-price-data/PEP.csv")
    producer["wkn"] = "851995"
    producers_collection.insert_one(producer)

    producer = dict()
    producer["description"] = "#16erBlech"
    producer["isin"] = "AT0000758008"
    producer["lastPrice"] = 86.00
    producer["name"] = "Ottakringer Brauerei"
    producer["symbol"] = ""
    producer["tendency"] = "up"
    producer["priceData"] = __create_price_data("stock-price-data/OTS.VI.csv")
    producer["wkn"] = "896689"
    producers_collection.insert_one(producer)

    producer = dict()
    producer["description"] = "Josef Manner & Comp AG, an Austrian family business, unifies five of the best-known and well-liked confectionary brands. Our brands are associated with high quality and delicious taste. All products are manufactured exclusively at locations in Austria."
    producer["isin"] = "AT0000728209"
    producer["lastPrice"] = 110.00
    producer["name"] = "Josef Manner"
    producer["symbol"] = ""
    producer["tendency"] = "neutral"
    producer["priceData"] = __create_price_data("stock-price-data/MAN.VI.csv")
    producer["wkn"] = "851676"
    producers_collection.insert_one(producer)

    producer = dict()
    producer["description"] = "Kellogg's products are manufactured and marketed in over 180 countries. Kellogg's largest factory is at Trafford Park in Trafford, Greater Manchester, United Kingdom, which is also the location of its UK headquarters."
    producer["isin"] = "US4878361082"
    producer["lastPrice"] = 50.12
    producer["name"] = "Kellogg's"
    producer["symbol"] = "K"
    producer["tendency"] = "up"
    producer["priceData"] = __create_price_data("stock-price-data/K.csv")
    producer["wkn"] = "853265"
    producers_collection.insert_one(producer)

    producer = dict()
    producer["description"] = "Carl Kühne KG is a German food manufacturer which mainly produces vinegars and delicacies. As one of the largest vinegar, pickled gherkin and mustard producers in Europe, the family-owned business sells its products in over 50 countries."
    producer["isin"] = None
    producer["lastPrice"] = None
    producer["name"] = "Carl Kühne KG"
    producer["symbol"] = None
    producer["tendency"] = None
    producer["priceData"] = None
    producer["wkn"] = None
    producers_collection.insert_one(producer)

    producer = dict()
    producer["description"] = "Stadtfleischerei Lange Taste with tradition - since 1878 Daily fresh meat from pork and beef in eight branches in Schwerin - meat enjoyment from Stadtfleischerei Lange We stand for freshness & quality"
    producer["isin"] = None
    producer["lastPrice"] = None
    producer["name"] = "Stadtfleischerei Lange"
    producer["symbol"] = None
    producer["tendency"] = None
    producer["priceData"] = None
    producer["wkn"] = None
    producers_collection.insert_one(producer)

    producer = dict()
    producer["description"] = "Bautz'ner Senf mittelscharf. Bautz'ner Senf & Feinkost GmbH is a food company in Bautzen of Develey Senf & Feinkost. The most important product is Bautz'ner mustard. This mustard is one of the most famous East German products."
    producer["isin"] = None
    producer["lastPrice"] = None
    producer["name"] = "Bautz'ner Senf & Feinkost GmbH"
    producer["symbol"] = None
    producer["tendency"] = None
    producer["priceData"] = None
    producer["wkn"] = None
    producers_collection.insert_one(producer)

    producer = dict()
    producer["description"] = "WERDER quality from Havelland. We are pleased that you have found the way to the beautiful Havelland to us in Werder. Here we have been producing all kinds of tasty products with a lot of heart and passion since 1873. By the way, our name Werder means: island in the river."
    producer["isin"] = None
    producer["lastPrice"] = None
    producer["name"] = "Werder Feinkost"
    producer["symbol"] = None
    producer["tendency"] = None
    producer["priceData"] = None
    producer["wkn"] = None
    producers_collection.insert_one(producer)


if __name__ == "__main__":

    mongo_wrapper = MongoWrapper("mongodb://localhost:27017")
    bbb_database = mongo_wrapper.get_database("baltic-byte-buccaneers")

    producers_collection = bbb_database["producers"]

    __add_producer(producers_collection)

