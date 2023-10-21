import csv
import json

class FileLoader:

    def __init__(self):
        pass

    @staticmethod
    def load_json_file_as_dict(path: str) -> dict:
        with open(path) as json_file:
            data = json_file.read()
            data_dict = json.loads(data)
        return data_dict
    
    @staticmethod
    def load_file_as_csv(path: str):
        with open(f"{path}") as csv_file:
            reader = csv.DictReader(csv_file, delimiter = ",", quotechar = "|")
            rows = [row for row in reader]
            return rows
