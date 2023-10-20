from pymongo import MongoClient


class MongoWrapper():

    def __init__(self, host: str) -> None:
        
        connection_string = host
        self.client = MongoClient(connection_string)

    def get_database(self, db_name: str) -> MongoClient:
        return self.client[db_name]
