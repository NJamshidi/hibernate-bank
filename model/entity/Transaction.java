package session7.model.entity;

import lombok.Data;
import session7.model.enumaration.TransactionType;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private double amount;
    private Date date;
    @ManyToOne
    private Account account;

    public double withdraw(){
    double balance = account.getBalance();

        if (balance > amount) {
            balance -= amount;
            return account.getBalance();
        } else
            throw new RuntimeException("balance not enough!");
    }

    public double deposit() {
        account.setBalance(account.getBalance() + amount);
        return account.getBalance();
    }
}
