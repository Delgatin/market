package company.dao;

import company.domain.Account;
import company.domain.Category;
import company.domain.Product;
import company.domain.Type;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductDaoTest {

    @Mock
    EntityManager emMock = Persistence.createEntityManagerFactory("H2").createEntityManager();

    @Mock
    EntityTransaction entityTransactionMock;

    ProductDao productDao;

    @BeforeEach
    void beforeAll() {
        productDao = new ProductDao(emMock);
    }

    @Mock
    TypedQuery<Product> query;
    @Mock
    Product p = new Product("Lawn Mower", Category.garden, Type.product);
    @Mock
    Product p1 = new Product("Knife", Category.kitchen, Type.product);
    @Mock
    Product p2 = new Product("Spoon", Category.kitchen, Type.product);
    @Mock
    Product p3 = new Product("Repair wood toys", Category.toys, Type.service);

    List<Product> products = Arrays.asList(p, p1, p2, p3);

    @Test
    void TestFindingAllProducts() {
        when(emMock.createQuery(anyString(), eq(Product.class))).thenReturn(query);
        when(query.getResultList()).thenReturn(products);

        List<Product> allProducts = productDao.getAllProducts();
        assertThat(allProducts.size()).isEqualTo(4);
    }

    @Test
    void TestFindAllProductsFromCertainCategory() {
        when(emMock.createQuery(anyString(), eq(Product.class))).thenReturn(query);
        when(query.getResultList()).thenReturn(products);

        List<Product> allProducts = productDao.getAllProductsWithCategory(Category.kitchen.name());
        int allProdcutsWithCategory = 0;
        for(Product p : allProducts) {
            if (p.getCategory().equals(Category.kitchen)) {
                allProdcutsWithCategory++;
            }
        }
        assertThat(allProdcutsWithCategory).isEqualTo(2);
    }

    @Test
    void TestFindAllProductsFromCertainType() {
        when(emMock.createQuery(anyString(), eq(Product.class))).thenReturn(query);
        when(query.getResultList()).thenReturn(products);

        List<Product> allProducts = productDao.getAllProductsWithType(Type.service.name());
        int allProdcutsWithType = 0;
        for(Product p : allProducts) {
            if (p.getType().equals(Type.service)) {
                allProdcutsWithType++;
            }
        }
        assertThat(allProdcutsWithType).isEqualTo(1);
    }
}
