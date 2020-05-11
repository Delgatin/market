package company.dao;

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
public class AccountDaoTest {

    @Mock
    EntityManager emMock = Persistence.createEntityManagerFactory("H2").createEntityManager();

    @Mock
    EntityTransaction entityTransactionMock;

    AccountDao accountDao;

    @BeforeEach
    void beforeAll() {
        accountDao = new AccountDao(emMock);
    }

    @Test
    void TestIfAccountInsertionWorksCorrectlyWithCorrectInput() {
        when(emMock.getTransaction()).thenReturn(entityTransactionMock);
        doNothing().when(entityTransactionMock).begin();
        doNothing().when(entityTransactionMock).commit();

        accountDao.insertAccount(new Account());

        verify(emMock).persist(isA(Account.class));
        verify(emMock, atLeastOnce()).getTransaction();
        verify(entityTransactionMock).begin();
        verify(entityTransactionMock).commit();
    }


    @Mock
    TypedQuery<Account> query;
    @Mock
    Account a = new Account("nino", "aap");
    @Mock
    Account a1 = new Account("jaap", "aap");
    List<Account> accounts = Arrays.asList(a, a1);

    @Test
    void TestFindingAccountByName() {
        when(emMock.createQuery(anyString(), eq(Account.class))).thenReturn(query);
        when(query.getResultList()).thenReturn(accounts);

        List<Account> singleAccount = accountDao.getAccountByName("nino");
        assertThat(singleAccount.get(0).getName()).isEqualTo("nino");
    }

    @Test
    void TestFindingAccountByNameAndPassword() {
        when(emMock.createQuery(anyString(), eq(Account.class))).thenReturn(query);
        when(query.getResultList()).thenReturn(accounts);
        String name = "jaap";
        String password = "aap";

        List<Account> accountWithNameAndPassword = accountDao.getAccountByNameAndPassword(name, password);
        assertThat(accountWithNameAndPassword.get(1).getName()).isEqualTo(name);
        assertThat(accountWithNameAndPassword.get(1).getPassword()).isEqualTo(password);
    }


//    @Test
//    void TestIfAccountInstertionWorksCorrectlyWithFalseInput() {
//        when(emMock.getTransaction()).thenReturn(entityTransactionMock);
//        doNothing().when(entityTransactionMock).begin();
//        doNothing().when(entityTransactionMock).commit();
////        doNothing().when(entityTransactionMock).rollback();
//
//        accountDao.insertAccount(new Account("aaaabaaaabaaaabaaaabaaaabaaaabaaaabaaaabaaaabaaaab"
//                                            + "aaaabaaaabaaaabaaaabaaaabaaaabaaaabaaaabaaaabaaaab"
//                                            + "aaaabaaaabaaaabaaaabaaaabaaaabaaaabaaaabaaaabaaaab"
//                                            + "aaaabaaaabaaaabaaaabaaaabaaaabaaaabaaaabaaaabaaaab"
//                                            + "aaaabaaaabaaaabaaaabaaaabaaaabaaaabaaaabaaaabaaaab"
//                                            + "aaaabaaaabaaaabaaaabaaaabaaaabaaaabaaaabaaaabaaaab"
//                                            , "hallo"));
//
//        verify(emMock).persist(isA(Account.class));
//        verify(emMock, atLeastOnce()).getTransaction();
//        verify(entityTransactionMock).begin();
//        verify(entityTransactionMock).commit();
////        verify(entityTransactionMock).rollback();
//    }

}
