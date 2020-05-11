package company.util;

import company.dao.AccountDao;
import company.domain.Account;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class AccountLogin {

    EntityManager em;
    AccountDao accountDao;
    ScannerWrapper scanner = new ScannerWrapper();


    public AccountLogin(EntityManager em) {
        this.em = em;
        accountDao = new AccountDao(this.em);
    }

    public Account login() {
        System.out.println("Account name:");
        String accountName = scanner.getNextLine();
        System.out.println("Password:");
        String accountPassword = scanner.getNextLine();
        List<Account> accounts = accountDao.getAccountByNameAndPassword(accountName, accountPassword);
        return accounts.get(0);
    }

    public void setScanner(ScannerWrapper scanner) {
        this.scanner = scanner;
    }
}
