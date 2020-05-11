package company.util;

import company.dao.AccountDao;
import company.dao.ProductDao;
import company.domain.Product;

import javax.persistence.EntityManager;
import java.util.List;

public class ProductSearch {

    EntityManager em;
    ProductDao productDao;
    ScannerWrapper scanner = new ScannerWrapper();

    public ProductSearch(EntityManager em) {
        this.em = em;
        productDao = new ProductDao(em);
    }

    public List<Product> searchAll() {
        return productDao.getAllProducts();
    }

    public List<Product> searchProductsByCategory(String category) {
        return productDao.getAllProductsWithCategory(category);
    }

    public List<Product> searchProductsByType(String type) {
        return productDao.getAllProductsWithType(type);
    }
}
