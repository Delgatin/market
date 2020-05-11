package company.util;

import company.dao.AccountDao;
import company.domain.Account;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import java.util.List;
import java.util.Scanner;

public class AccountCreator {

    EntityManager em;
    AccountDao accountDao;

    public AccountCreator(EntityManager em) {
        this.em = em;
        accountDao = new AccountDao(this.em);
    }

    public void createAccount() {
        Scanner scanner = new Scanner(System.in);
        boolean accountSuccesfullyCreated = false;
        while(!accountSuccesfullyCreated) {
            System.out.println("Account name:");
            String accountName = scanner.nextLine();
            System.out.println("Password:");
            String accountPassword = scanner.nextLine();
            try {
                accountDao.insertAccount(new Account(accountName, accountPassword));
                accountSuccesfullyCreated = true;
                System.out.println("Account successfully created.");
            } catch (RollbackException e) {
                if (accountNameTaken(accountName)) {
                    System.out.println("That account name is already taken, please try a different one.");
                } else {
                    System.out.println("Either the name or password was invalid, please fill it in again.");
                }
            }
        }
    }

    private boolean accountNameTaken(String name) {
        List<Account> account = accountDao.getAccountByName(name);
        return account.size() > 0;
    }
}
