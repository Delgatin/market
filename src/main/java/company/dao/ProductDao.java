package company.dao;

import company.domain.Product;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProductDao {

    private EntityManager em;

    public ProductDao() {

    }

    public ProductDao(EntityManager em) {
        this.em = em;
    }

    public List<Product> getAllProducts() {
        TypedQuery<Product> query = em.createQuery("select p from Product p", Product.class);
        return query.getResultList();
    }

    public List<Product> getAllProductsWithCategory(String category) {
        TypedQuery<Product> query = em.createQuery("select p from Product p where p.category = :firstArg", Product.class);
        query.setParameter("firstArg", category);
        return query.getResultList();
    }

    public List<Product> getAllProductsWithType(String type) {
        TypedQuery<Product> query = em.createQuery("select p from Product p where p.type = :firstArg", Product.class);
        query.setParameter("firstArg", type);
        return query.getResultList();
    }
}
