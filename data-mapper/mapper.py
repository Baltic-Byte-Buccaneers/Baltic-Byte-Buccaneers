from datetime import datetime
from db.mongo_wrapper import MongoWrapper
from utils.file_loader import FileLoader

def __createRetailer(purchase: dict) -> dict:
    retailer = dict()
    retailer["name"] = purchase["organization_name"]
    retailer["country"] = purchase["organization_country"]
    retailer["municipality"] = purchase["organization_municipality"]
    retailer["street"] = purchase["organization_street"]
    retailer["category"] = purchase["organization_category"]

    return retailer

def __createBranch(purchase: dict, retailers: dict) -> dict:
    branch = dict()

    retailerId: str = __get_retailer_id_for_organization_name(retailers, purchase["organization_name"])

    branch["retailerId"] = retailerId
    branch["retailerName"] = purchase["organization_name"]
    branch["name"] = purchase["unit_name"]
    branch["municipality"] = purchase["unit_municipality"]
    branch["street"] = purchase["unit_street"]
    
    return branch

def __createReceipt(purchase: dict, retailers: dict, branches: dict) -> dict:
    receipt = dict()
    receipt["date"] = __get_date_from_string(purchase["issue_date"])
    
    retailerId: str = __get_retailer_id_for_organization_name(retailers, purchase["organization_name"])

    receipt["retailerId"] = retailerId

    branchId: str = ""
    for branch in branches:
        if __is_branch_matching_retailer(purchase, branch):
            branchId = branch["_id"]

    receipt["branchId"] = branchId
    receipt["entries"] = list()

    return receipt

def __createReceiptEntry(purchase: dict) -> dict:
    receipt_entry = dict()
    receipt_entry["title"] = purchase["name"]
    receipt_entry["quantity"] = purchase["quantity"]
    receipt_entry["amount"] = purchase["price"]
    receipt_entry["vatRate"] = purchase["vat_rate"]

    return receipt_entry

def __createTransaction(purchase: dict) -> dict:
    transaction = dict()
    transaction["date"] = __get_date_from_string(purchase["issue_date"])
    transaction["amount"] = 0
    transaction["purpose"] = "VISA"
    
    return transaction

def __get_branch_name(purchase: dict) -> str:
    return purchase["organization_name"] + purchase["organization_municipality"] + purchase["organization_street"] + purchase["unit_name"] + purchase["unit_street"]

def __get_date_from_string(formattedDate: str) -> datetime:
    return datetime.strptime(formattedDate, "%d.%m.%Y %H:%M:%S")

def __get_retailer_id_for_organization_name(retailers: dict, organization_name: str) -> str:
    return [retailer["_id"] for retailer in retailers if retailer["name"] == organization_name][0]

def __is_branch_matching_retailer(purchase: dict, branch: dict) -> bool:
    return branch["retailerName"] == purchase["organization_name"] and branch["municipality"] == purchase["unit_municipality"] and branch["street"] == purchase["unit_street"]

def __map_data():
    data_dict = FileLoader.load_json_file_as_dict("purchases.json")

    currentTransactionId: int = 0
    currentReceipt: dict = None
    currentTransaction: dict
    retailers: list = list()
    retailer_names: list = list()
    branches: list = list()
    branch_names: list = list()

    for purchase in data_dict:

        if purchase["organization_name"] not in retailer_names:
            retailer = __createRetailer(purchase)

            retailers.append(retailer)
            retailer_names.append(retailer["name"])
            retailers_collection.insert_one(retailer)

        branch_name = __get_branch_name(purchase)
        if branch_name not in branch_names:
            branch = __createBranch(purchase, retailers)

            branches.append(branch)
            branch_names.append(branch_name)
            branches_collection.insert_one(branch)

        if purchase["transaction"] is not currentTransactionId:
            currentTransactionId = purchase["transaction"]

            if currentReceipt is not None:
                receipts_collection.insert_one(currentReceipt)

                currentTransaction["receiptId"] = currentReceipt["_id"]
                transactions_collection.insert_one(currentTransaction)

            currentReceipt = __createReceipt(purchase, retailers, branches)
            currentTransaction = __createTransaction(purchase)

        receipt_entry = __createReceiptEntry(purchase)
        currentReceipt["entries"].append(receipt_entry)
        currentTransaction["amount"] += purchase["price"]


if __name__ == "__main__":

    mongo_wrapper = MongoWrapper("mongodb://localhost:27017")
    bbb_database = mongo_wrapper.get_database("baltic-byte-buccaneers")

    transactions_collection = bbb_database["transactions"]
    receipts_collection = bbb_database["receipts"]
    retailers_collection = bbb_database["retailers"]
    branches_collection = bbb_database["branches"]

    __map_data()

