interface Account {
    void deposit(double amount);

    void withdraw(double amount) throws InsufficientFundsException;

    void transfer(Account toAccount, double amount) throws InsufficientFundsException;

    double getBalance();

}