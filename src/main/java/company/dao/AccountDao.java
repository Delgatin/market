package company.dao;

import company.domain.Account;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import java.util.List;

public class AccountDao {

    private EntityManager em;

    public AccountDao() {

    }

    public AccountDao(EntityManager em) {
        this.em = em;
    }

    public void insertAccount(Account account) throws RollbackException{
        try {
            em.getTransaction().begin();
            em.persist(account);
            em.getTransaction().commit();
        } catch (RollbackException e) {
            throw new RollbackException(e);
        }
    }

    public List<Account> getAccountByName(String name) {
        TypedQuery<Account> query = em.createQuery("select a from Account a where a.name = :firstArg", Account.class);
        query.setParameter("firstArg", name);
        return query.getResultList();
    }

    public List<Account> getAccountByNameAndPassword(String name, String password) {
        TypedQuery<Account> query = em.createQuery("select a from Account a where a.name = :firstArg and a.password = :secondArg", Account.class);
        query.setParameter("firstArg", name);
        query.setParameter("secondArg", password);
        return query.getResultList();
    }
}
