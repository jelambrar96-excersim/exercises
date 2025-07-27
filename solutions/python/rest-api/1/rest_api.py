import json


def calc_balance(user):
    return sum(user["owed_by"].values(), 0.0) - sum(user["owes"].values(), 0.0)


class RestAPI:
    def __init__(self, database=None):
        self.db = database

    def get(self, url, payload=None):
        if url == "/users":
            list_user = self.db["users"]
            if payload is not None:
                users_payload = json.loads(payload)["users"]
                list_user = [ user for user in list_user if user["name"] in users_payload]
            list_user = [ {**user, "balance": calc_balance(user)} for user in list_user]
            return json.dumps({"users": list_user})

    def post(self, url, payload=None):
        if url == "/add":
            json_payload = json.loads(payload)
            json_user = {"name": json_payload["user"], "owes": {}, "owed_by": {}}
            self.db["users"].append(json_user)
            self.db["users"].sort(key=lambda x: x["name"])
            json_user["balance"] = calc_balance(json_user)
            return json.dumps(json_user)

        if url == "/iou":
            json_payload = json.loads(payload)
            amount = json_payload["amount"]
            lender_user = json_payload["lender"]
            borrower_user = json_payload["borrower"]
            for i in range(len(self.db["users"])):
                if self.db["users"][i]["name"] == lender_user:
                    amount_b = amount
                    if borrower_user in self.db["users"][i]["owes"]:
                        if self.db["users"][i]["owes"][borrower_user] > amount_b:
                            self.db["users"][i]["owes"][borrower_user] -= amount_b
                            amount_b = 0
                        else:
                            amount_b -=  self.db["users"][i]["owes"][borrower_user]
                            del self.db["users"][i]["owes"][borrower_user]
                    if amount_b > 0:
                        if borrower_user in self.db["users"][i]["owed_by"]:
                            self.db["users"][i]["owed_by"][borrower_user] += amount_b
                        else:
                            self.db["users"][i]["owed_by"][borrower_user] = amount_b
                
                if self.db["users"][i]["name"] == borrower_user:
                    amount_b = amount
                    if lender_user in self.db["users"][i]["owed_by"]:
                        if self.db["users"][i]["owed_by"][lender_user] > amount_b:
                            self.db["users"][i]["owed_by"][lender_user] -= amount_b
                            amount_b = 0
                        else:
                            amount_b -=  self.db["users"][i]["owed_by"][lender_user]
                            del self.db["users"][i]["owed_by"][lender_user]
                    if amount_b > 0:
                        if lender_user in self.db["users"][i]["owes"]:
                            self.db["users"][i]["owes"][lender_user] += amount_b
                        else:
                            self.db["users"][i]["owes"][lender_user] = amount_b
            users_payload = [lender_user, borrower_user]
            list_user = [ user for user in self.db["users"] if user["name"] in users_payload]
            list_user = [ {**user, "balance": calc_balance(user)} for user in list_user]
            return json.dumps({"users": list_user})

        return ""
