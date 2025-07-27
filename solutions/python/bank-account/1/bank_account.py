


class BankAccount:
    def __init__(self):
        self.balance = 0
        self.status = "closed"

    def get_balance(self):
        if self.status == "closed":
            raise ValueError("account not open")
        return self.balance

    def open(self):
        if self.status == "opened":
            raise ValueError("account already open")
        self.status = "opened"

    def deposit(self, amount):
        if self.status == "closed":
            raise ValueError("account not open")
        if amount < 0:
            raise ValueError("amount must be greater than 0")
        self.balance += amount

    def withdraw(self, amount):
        if self.status == "closed":
            raise ValueError("account not open")
        if amount > self.balance:
            raise ValueError("amount must be less than balance")
        if amount < 0:
            raise ValueError("amount must be greater than 0")
        self.balance -= amount

    def close(self):
        if self.status == "closed":
            raise ValueError("account not open")
        self.status = "closed"
        self.balance = 0
