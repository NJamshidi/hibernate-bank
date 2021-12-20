package session7.service;

import session7.dao.AccountDao;
import session7.model.entity.Account;

public class AccountServiceImpl implements AccountService {
    AccountDao accountDao = new AccountDao();

    @Override
    public void add(Account account) {
        accountDao.add(account);
    }

    @Override
    public void deposit(int id, double amount) {
        accountDao.deposit(id, amount);
    }

    @Override
    public void withdraw(int id, double amount) {
        accountDao.withdraw(id, amount);
    }
}
