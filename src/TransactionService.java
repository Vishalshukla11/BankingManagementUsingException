
public class TransactionService {
    private TransactionLogger transactionLogger;

    public TransactionService(TransactionLogger transactionLogger) {
        this.transactionLogger = transactionLogger;
    }

    public void deposit(BankAccount account, double amount) {
        account.deposit(amount);
        transactionLogger.logTransaction("Deposit " + amount + "to account " + account.getAccountNumber());
    }

    public void Withdraw(BankAccount account, double amount)throws InsufficientFundsException {
       
            account.withdraw(amount);
            transactionLogger.logTransaction("Withdrew " + amount + "for account " + account.getAccountNumber());
       
    }

    public void transfer(BankAccount fromAccount, BankAccount toAccount, double amount) {
        try {
            fromAccount.transfer(toAccount, amount);
            transactionLogger.logTransaction("Transfered " + amount + "from account " + fromAccount.getAccountNumber()
                    + "to account " + toAccount.getAccountNumber());
        } catch (InsufficientFundsException e) {
            transactionLogger
                    .logTransaction("Failed to transfer " + amount + "From account " + fromAccount.getAccountNumber()
                            + "to account " + toAccount.getAccountNumber() + ": " + e.getMessage());
        }
    }

}