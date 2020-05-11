package company;

import company.domain.Category;
import company.domain.Product;
import company.domain.Type;
import company.util.AccountCreator;
import company.util.AccountLogin;
import company.util.Commands;
import company.domain.Account;
import company.util.ProductSearch;

import static company.util.Util.blankLine;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Main();
    }

    boolean programRunning = true;
    String readMessage = "";

    Scanner scanner;
    EntityManager em;
    AccountCreator accountCreator;
    AccountLogin accountLogin;
    Account loggedInAccount;
    ProductSearch productSearch;


    public Main() {
        setup();
        while(programRunning) {
            blankLine();
            System.out.println("Main menu \nPlease write the action you want to perform:");
            readMessage = scanner.nextLine();
            handleAction(readMessage);
        }
    }

    private void handleAction(String message) {
        blankLine();
        Commands commands = checkValidCommand(message);
        switch (commands) {
            case help:
                helpMessage();
                break;
            case exit:
                programRunning = false;
                System.out.println("System will shutdown.");
                break;
            case createaccount:
                createAccount();
                break;
            case login:
                login();
                break;
            case logout:
                logout();
                break;
            case search:
                search();
                break;
            case invalid:
                System.out.println("That input was not valid. Please try again. For a list of the commands type 'help'.");
                break;
            default:
                System.out.println("Something went wrong.");
        }
        resetReadMessage();
    }

    private void helpMessage() {
        System.out.println("Here are all the commands that are available:");
        for (Commands c : Commands.values()) {
            /*Excluding the invalid command because it's not a command users should be able to use.
            * It's simply there so that the system has a way of handling false input.
            * */
            if(!c.name().equals(Commands.invalid.name()))
            System.out.println(c.name());
        }
    }

    private Commands checkValidCommand(String message) {
        Commands temp;
        try {
            temp = Commands.valueOf(message.toLowerCase());
        } catch (IllegalArgumentException e) {
            temp = Commands.invalid;
        }
        return temp;
    }

    private void createAccount() {
        accountCreator.createAccount();
    }

    private void logout() {
        if (loggedInAccount != null) {
            loggedInAccount = null;
            System.out.println("You've logged out.");
        } else {
            System.out.println("This function is not available at the moment.");
        }
    }

    private void login() {
        if (loggedInAccount == null) {
            loggedInAccount = accountLogin.login();
            System.out.println("You've logged in.");
        } else {
            System.out.println("You're already logged in, logout first.");
        }
    }

    private void search() {
        List<Product> allProducts = productSearch.searchAll();
        for(Product p : allProducts) {
            System.out.println(p.toString());
        }
    }

    private void resetReadMessage() {
        readMessage = "";
    }

    private void setup() {
        scanner = new Scanner(System.in);
        em = Persistence.createEntityManagerFactory("MySQL").createEntityManager();
        accountCreator = new AccountCreator(em);
        accountLogin = new AccountLogin(em);
        productSearch = new ProductSearch(em);
    }
}
