package session7.model.entity;

import lombok.Data;
import session7.model.enumaration.AccountType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int accountNumber;
    private String cartNumber;
    @Temporal(TemporalType.TIMESTAMP)
    private Date openingDate;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    private double balance;
    @Column(length = 4)
    private String cvv2;

    private Date expirationDate;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private List<Transaction> transaction = new ArrayList<>();

    public static Date generateExpirationDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 5);
        return cal.getTime();
    }

    @Override
    public String toString() {
        return "model.Account{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", cartNumber='" + cartNumber + '\'' +
                ", type=" + type +
                ", openingDate=" + openingDate +
                ", balance=" + balance +
                ", cvv2='" + cvv2 + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }
}

