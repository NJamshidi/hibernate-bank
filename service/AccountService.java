package session7.service;

import session7.model.entity.Account;

public interface AccountService {
     void add(Account account);
    void deposit(int id, double amount);
    void withdraw(int id, double amount);

}
