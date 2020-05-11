package company.util;

import company.dao.AccountDao;
import company.domain.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class AccountLoginTest {

    @Mock
    EntityManager emMock = Persistence.createEntityManagerFactory("H2").createEntityManager();

    @Mock
    EntityTransaction entityTransactionMock;

    AccountLogin accountLogin;

    @BeforeEach
    void beforeAll() {
        accountLogin = new AccountLogin(emMock);
        accountLogin.setScanner(scannerMock);
    }

    @Mock
    TypedQuery<Account> query;
    @Mock
    Account a = new Account("dino", "dino");
    List<Account> accounts = Arrays.asList(a);
    @Mock
    ScannerWrapper scannerMock;

    @Test
    void TestLogingInOnAccount() {
        when(emMock.createQuery(anyString(), eq(Account.class))).thenReturn(query);
        when(query.getResultList()).thenReturn(accounts);
        when(scannerMock.getNextLine()).thenReturn("dino");

        Account account = accountLogin.login();
        assertThat(account.getName()).isEqualTo("dino");
        assertThat(account.getPassword()).isEqualTo("dino");
    }
}
