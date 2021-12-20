package session7.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import session7.model.entity.Account;
import session7.model.entity.User;
import session7.model.enumaration.TransactionType;


import java.util.Date;
import java.util.List;
import java.util.Set;

public class AccountDao extends BaseDao {
    public void add(Account account) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.load(User.class, account.getUser().getId());
        user.getAccount().add(account);
        session.save(user);
        transaction.commit();
        session.close();
    }

    public void deposit(int id, double amount) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session7.model.entity.Transaction dTransaction = new session7.model.entity.Transaction();
        Account account = session.load(Account.class, id);
        dTransaction.setAccount(account);
        dTransaction.setAmount(amount);
        dTransaction.setTransactionType(TransactionType.DEPOSIT);
        dTransaction.setDate(new Date());
        dTransaction.deposit();
        session.persist(dTransaction); //void
        transaction.commit();
        session.close();
    }

    public void withdraw(int id, double amount) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Account account = session.load(Account.class, id);
        session7.model.entity.Transaction wTransaction = new session7.model.entity.Transaction();
        wTransaction.setAccount(account);
        wTransaction.setAmount(amount);
        wTransaction.setTransactionType(TransactionType.WITHDRAW);
        wTransaction.setDate(new Date());
        wTransaction.withdraw();
        session.persist(wTransaction);
        transaction.commit();
        session.close();
    }
}
