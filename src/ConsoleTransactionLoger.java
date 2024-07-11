public class ConsoleTransactionLoger implements TransactionLogger
{
    public void logTransaction(String message)
    {
        System.out.println(message);
    }

}