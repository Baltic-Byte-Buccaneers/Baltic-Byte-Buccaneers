import json
from random import choice
from string import ascii_letters, digits, hexdigits

class RandomDataGenerator:

    def __init__(self):
        pass

    @staticmethod
    def generate_random_string(length: int) -> str:
        return ''.join(choice(ascii_letters) for i in range(length))

    @staticmethod
    def generate_random_hex_string(length: int) -> str:
        return ''.join(choice(hexdigits) for i in range(length))

    @staticmethod
    def generate_random_number_string(length: int) -> str:
        return ''.join(choice(digits) for i in range(length))
