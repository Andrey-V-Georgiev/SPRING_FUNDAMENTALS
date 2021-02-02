package softuni.andreys.models.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import java.math.BigDecimal;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String username;
    private String password;
    private String email;
    private BigDecimal budget;

    public User() {
    }

    public User(String username, String password, String email, BigDecimal budget) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.budget = budget;
    }

    @Length(min = 2, message = "Username must be at least 2 symbols")
    @Column(name = "username", unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Length(min = 2, message = "Password must be at least 2 symbols")
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Email(message = "Must contain `@`!")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @DecimalMin(value = "0", message = "Budget must be positive number")
    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }
}
