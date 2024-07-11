import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();
        TransactionLogger logger = new ConsoleTransactionLoger();
        TransactionService tService = new TransactionService(logger); 

        // create account (sample initialization)\

        bank.createAccount("0001", 1000);
        bank.createAccount("0002", 500);


        boolean continueExecution = true;

        while (continueExecution) {
            System.out.println("\n Choose an option:");
            System.out.println("1. create new Account: ");
            System.out.println("2. Deposit funds :");
            System.out.println("3. WithDraw funds: ");
            System.out.println("4. Transfer Funds:");
            System.out.println("5. check account balance: ");
            System.out.println("6. show all accounts: ");
            System.out.println("7. Exit.... ");

            System.out.println("Enter your choice : ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    createNewAccount(bank, sc);
                    break;
                case 2:
                    deposit(tService, bank, sc);
                    break;
                case 3:
                    withDraw(tService, bank, sc);
                    break;
                case 4:
                    executeTransfer(tService, bank, sc);
                    break;
                case 5:
                    CheckAccountBalance(bank, sc);
                    break;
                case 6:
                    showAllAccounts(bank);
                    break;
                case 7:
                    continueExecution = false;
                    break;
                default:
                    System.out.println("Invalid choice . please enter a number from 1 to 5.");
                    break;

            }
        }
        System.out.println("Exiting the program.");
        sc.close();
    }
    private static void createNewAccount(Bank bank, Scanner sc) {
        System.out.print("Enter account number:");
        String accountNumber = sc.nextLine();
        System.out.print("Enter initial Balance:");
        double InitialBalance = sc.nextDouble();
        bank.createAccount(accountNumber, InitialBalance);
        System.out.println("Account created Successfully.");
    }

    private static void deposit(TransactionService transactionService, Bank bank, Scanner sc) {
        try {
            System.out.print("Enter account Number: ");
            String accountNumber = sc.nextLine();
            BankAccount account = bank.getAccount(accountNumber);
            System.out.println("Enter amount to deposit :");
            double amount = sc.nextDouble();
            transactionService.deposit(account, amount);
            System.out.println("Deposit SuccessFul. Updated Balance: " + account.getBalance());
        } catch (InvalidAccountException e) {
            System.out.println("Error : " + e.getMessage());

        }
    }

    private static void withDraw(TransactionService transactionService, Bank bank, Scanner sc) {
        try {
            System.out.println("Enter account number:");
            String accountNumber = sc.nextLine();
            BankAccount account = bank.getAccount(accountNumber);
            System.out.println("Enter amount to withdraw: ");
            double amount = sc.nextDouble();
            transactionService.Withdraw(account, amount);
            System.out.println("Withdraw successful. updated balance: " + account.getBalance());
            sc.nextLine(); //consume new line 
        } catch (InvalidAccountException e) {
            System.out.println("Error : " + e.getMessage());
        }
        catch(InsufficientFundsException e)
        {
            System.out.println("Error:" + e.getMessage());
        }
        catch( Exception e)
        {
System.out.println("An unexpected error occurred:" + e.getMessage());
        }
    }

    private static void executeTransfer(TransactionService transactionService, Bank bank, Scanner sc) {
        try {
            System.out.println("Enter account number to transfer from:");
            String fromAccountNumber = sc.nextLine();
            BankAccount fromAccount = bank.getAccount(fromAccountNumber);

            System.out.println("Enter account number to transfer to: ");
            String toAccountNumber = sc.nextLine();
            BankAccount toAccount = bank.getAccount(toAccountNumber);

            System.out.println("Enter amount to transfer: ");
            double amount = sc.nextDouble();

            transactionService.transfer(fromAccount, toAccount, amount);
            System.out.println("Tranfer successful.");
            System.out.println(
                    "Updated balance of account " + fromAccount.getAccountNumber() + ": " + fromAccount.getBalance());
            System.out.println(
                    "Updated balance of account " + toAccount.getAccountNumber() + ": " + toAccount.getBalance());

        } catch (InvalidAccountException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    private static void CheckAccountBalance(Bank bank, Scanner sc) {
        System.out.println("Enter account number:");
        String accountNumber = sc.nextLine();
        try {
            BankAccount account = bank.getAccount(accountNumber);
            System.out.println("Current balance of account :-" + accountNumber + ": " + account.getBalance());

        } catch (InvalidAccountException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    private static void showAllAccounts(Bank bank) {
        try {
            List<BankAccount> accounts = bank.getAllAccounts();
            if (accounts.isEmpty()) {
                throw new NoAccountFounfException("No account found");
            } else {
                System.out.println("List of all accounts:");
                for (BankAccount account : accounts) {
                    System.out.println(
                            "Account Number: " + account.getAccountNumber() + ",Balance " + account.getBalance());
                }
            }
        } catch (NoAccountFounfException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

}