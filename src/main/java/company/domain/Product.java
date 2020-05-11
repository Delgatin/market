package company.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Entity
public class Product extends AbstractEntity {

    @NotNull
    String name;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    Category category;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    Type type;

    public Product() {}

    public Product(String name, Category category, Type type) {
        this.name = name;
        this.category = category;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", type=" + type +
                '}';
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public Type getType() {
        return type;
    }
}
