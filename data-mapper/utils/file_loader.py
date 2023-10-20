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
