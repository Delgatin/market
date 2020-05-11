package company.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Account extends AbstractEntity {

    @NotNull
    private String name;

    @NotNull
    private String password;

    public Account() {

    }

    public Account(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
