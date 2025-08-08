class BankAccount {

    private boolean isOpen;
    private int balance;

    void open() throws BankAccountActionInvalidException {
        if (this.isOpen)
            throw new BankAccountActionInvalidException("Account already open");
        this.isOpen = true;
        this.balance = 0;
    }

    void close() throws BankAccountActionInvalidException {
        if (!this.isOpen)
            throw new BankAccountActionInvalidException("Account not open");
        this.isOpen = false;
    }

    synchronized int getBalance() throws BankAccountActionInvalidException {
        if (!this.isOpen)
            throw new BankAccountActionInvalidException("Account closed");
        return this.balance;
    }

    synchronized void deposit(int amount) throws BankAccountActionInvalidException {
        if (!this.isOpen)
            throw new BankAccountActionInvalidException("Account closed");
        if (amount < 0)
            throw new BankAccountActionInvalidException("Cannot deposit or withdraw negative amount");
        this.balance += amount;
    }

    synchronized void withdraw(int amount) throws BankAccountActionInvalidException {
        if (!this.isOpen)
            throw new BankAccountActionInvalidException("Account closed");
        if (amount < 0)
            throw new BankAccountActionInvalidException("Cannot deposit or withdraw negative amount");
        if (amount > this.balance)
            throw new BankAccountActionInvalidException("Cannot withdraw more money than is currently in the account");
        this.balance -= amount;
    }

}
