import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {

    private Map<String, BankAccount> accounts = new HashMap<>();

    public void createAccount(String accountNumber, double initialBalance) {
        try {
            if (accounts.containsKey(accountNumber)) {
                throw new AccountExistException("Account with number  " + accountNumber + "already Exists.");
            }
            BankAccount account = new BankAccount(accountNumber, initialBalance); // use to get key and value

            // here we will store data into the map as key value pair
            accounts.put(accountNumber, account);
        } catch (AccountExistException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    public BankAccount getAccount(String accountNumber) throws InvalidAccountException {
        BankAccount account = accounts.get(accountNumber);
        if (account == null) {
            throw new InvalidAccountException("account not found");
        }
        return account;
    }

    public List<BankAccount> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }

}