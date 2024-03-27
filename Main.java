import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import constants.TradeAccountType;
import constants.Transaction;
import pojo.CashAccount;
import pojo.MarginAccount;
import pojo.TradeAccount;
import repository.TradeAccountRepository;
import service.CashAccountService;
import service.MarginAccountService;
import service.TradeAccountService;

public class Main {

    static Path[] paths = new Path[] {Paths.get("data/accounts.txt"), Paths.get("data/transactions.txt")};
    static TradeAccountRepository tradeAccountRepository = new TradeAccountRepository();
    static CashAccountService cashAccountService = new CashAccountService(tradeAccountRepository);
    static MarginAccountService marginAccountService = new MarginAccountService(tradeAccountRepository);

    public static void main(String[] args) {
        /*Part 1 */
        // CashAccount cashAccount = new CashAccount("C123", new BigDecimal("1000.00"));
        // System.out.println("Cash Account Details:");
        // System.out.println("ID: " + cashAccount.getId());
        // System.out.println("Cash Balance: " + cashAccount.getCashBalance());


        // CashAccount clonedCashAccount = (CashAccount) cashAccount.clone();
        // System.out.println("Cloned Cash Account Details:");
        // System.out.println("ID: " + clonedCashAccount.getId());
        // System.out.println("Cash Balance: " + clonedCashAccount.getCashBalance());


        // System.out.println();


        // MarginAccount marginAccount = new MarginAccount("M456", new BigDecimal("5000.00"));
        // System.out.println("Margin Account Details:");
        // System.out.println("ID: " + marginAccount.getId());
        // System.out.println("Margin: " + marginAccount.getMargin());


        // MarginAccount clonedMarginAccount = (MarginAccount) marginAccount.clone();
        // System.out.println("Cloned Margin Account Details:");
        // System.out.println("ID: " + clonedMarginAccount.getId());
        // System.out.println("Margin: " + clonedMarginAccount.getMargin());

        


    //     /*PART 2 */
    //     TradeAccountRepository repository = new TradeAccountRepository();
    
    // CashAccount cashAccount = new CashAccount("C123", new BigDecimal("1000.00"));
    // repository.createTradeAccount(cashAccount);
    
    // MarginAccount marginAccount = new MarginAccount("M456", new BigDecimal("5000.00"));
    // repository.createTradeAccount(marginAccount);
    
    // TradeAccount retrievedCashAccount = repository.retrieveTradeAccount("C123");
    // System.out.println("Retrieved Cash Account ID: " + retrievedCashAccount.getId());
    // System.out.println("Cash Balance: " + ((CashAccount) retrievedCashAccount).getCashBalance());
    
    // TradeAccount retrievedMarginAccount = repository.retrieveTradeAccount("M456");
    
    // System.out.println("Retrieved Margin Account ID: " + retrievedMarginAccount.getId());
    // System.out.println("Margin: " + ((MarginAccount) retrievedMarginAccount).getMargin());


    // // Update the cash account
    // ((CashAccount) retrievedCashAccount).setCashBalance(new BigDecimal("1500.00"));
    // repository.updateTradeAccount(retrievedCashAccount);


    // // Verify that the cash account was updated
    // retrievedCashAccount = repository.retrieveTradeAccount("C123");
    // System.out.println("Updated Cash Balance: " + ((CashAccount) retrievedCashAccount).getCashBalance());


    // // Delete the margin account
    // repository.deleteTradeAccount("M456");


    // // Verify that the margin account was deleted
    // retrievedMarginAccount = repository.retrieveTradeAccount("M456");
    // if (retrievedMarginAccount == null) {
    //     System.out.println("Margin account successfully deleted.");
    // } else {
    //     System.out.println("Margin account deletion failed.");
    // }

    // /*Part 3 */
    // TradeAccountRepository repository = new TradeAccountRepository();
    // CashAccountService cashAccountService = new CashAccountService(repository);
    // MarginAccountService marginAccountService = new MarginAccountService(repository);
    
    // // Create CashAccount and MarginAccount objects
    // CashAccount cashAccount = new CashAccount("1", BigDecimal.valueOf(1000));
    // MarginAccount marginAccount = new MarginAccount("2", BigDecimal.valueOf(2000));
    // System.out.println(marginAccount.getMargin());
        
    // // Add the accounts to the repository
    // cashAccountService.createTradeAccount(cashAccount);
    // marginAccountService.createTradeAccount(marginAccount);
    
    // // Deposit and withdraw amounts
    // cashAccountService.deposit("1", BigDecimal.valueOf(500));
    // cashAccountService.withdraw("1", BigDecimal.valueOf(200));
    // marginAccountService.deposit("2", BigDecimal.valueOf(1000));
    // marginAccountService.withdraw("2", BigDecimal.valueOf(500));
    
    // // Retrieve and print the updated account balances
    // CashAccount updatedCashAccount = cashAccountService.retreiveTradeAccount("1");
    // MarginAccount updatedMarginAccount = marginAccountService.retreiveTradeAccount("2");
    // System.out.println("Updated CashAccount balance: " + updatedCashAccount.getCashBalance());
    // System.out.println("Updated MarginAccount margin: " + updatedMarginAccount.getMargin());
    
    // // Delete the accounts
    // cashAccountService.deleteTradeAccount("1");
    // marginAccountService.deleteTradeAccount("2");


    /*Part 4 */

    try {
        loadTradeAccounts();
        applyTransaction();
        finalTest();

    } catch(IOException e) {
        System.out.println(e.getMessage());
    }
}

    public static void loadTradeAccounts() throws IOException {
        //Get the text file
        Files.lines(paths[0]).forEach(line -> {
            //Turn each line into an array element of words, delimited by a " "
            String[] words = line.split(" ");
            //See if an account is cash or margin then use respective AccountService constructor to create new TradeAccounts
            if (words[0].equals(TradeAccountType.CASH.toString())) {
                cashAccountService.createTradeAccount(new CashAccount(words[1], new BigDecimal(words[2])));
            } else {
                marginAccountService.createTradeAccount(new MarginAccount(words[1], new BigDecimal(words[2])));
            }
        });

    }

    public static void applyTransaction() throws IOException {
        //Streamline the txt file and read each line
        Files.lines(paths[1]).forEach(line ->{
            //Delimiter
            String[] words = line.split(" ");
            //Determine if it is a Cash account or Margin account
            Boolean isCash = words[0].equals(TradeAccountType.CASH.toString());
            //Loosely couple the two accounts, determine if the TradeAccount is cash or not
            TradeAccountService tradeAccountService = isCash ? cashAccountService : marginAccountService;
            //Determine if it is deposit or withdraw, earlier we already decided if the account is a Cash or Margin type, under abstracted TradeAccount
            Transaction transaction = Transaction.valueOf(words[2]);
            if (transaction.equals(Transaction.DEPOSIT)) {
                tradeAccountService.deposit(words[1], new BigDecimal(words[3]));

            } else {
                tradeAccountService.withdraw(words[1], new BigDecimal(words[3]));
            }
        });
    }

    public static void finalTest() throws IOException {
        System.out.println("Account A1234B Cash Balance: " + cashAccountService.retreiveTradeAccount("A1234B").getCashBalance());
        System.out.println("Account E3456F Cash Balance: " + cashAccountService.retreiveTradeAccount("E3456F").getCashBalance());
        System.out.println("Account I5678J Cash Balance: " + cashAccountService.retreiveTradeAccount("I5678J").getCashBalance());
        System.out.println("Account C2345D Margin: " + marginAccountService.retreiveTradeAccount("C2345D").getMargin());
        System.out.println("Account G4567H Margin: " + marginAccountService.retreiveTradeAccount("G4567H").getMargin());
    }
    

}