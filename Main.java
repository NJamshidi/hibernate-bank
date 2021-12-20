package session7;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import session7.model.entity.Account;
import session7.model.entity.Update;
import session7.model.entity.User;
import session7.model.enumaration.AccountType;
import session7.service.AccountServiceImpl;
import session7.service.UserServiceImpl;


import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    static UserServiceImpl userServiceImpl = new UserServiceImpl();
    static AccountServiceImpl accountServiceImpl = new AccountServiceImpl();
    static Scanner input = new Scanner(System.in);
    private static Random random = new Random();


    public static void main(String[] args) {
        boolean quit = false;


        while (!quit) {
            showPage();
            int selection = input.nextInt();
            switch (selection) {
                case 1: {
                    createAccount();
                }
                break;
                case 2: {
                    deposite();
                }
                break;
                case 3: {
                    withDraw();
                }
                break;
                case 4: {
                    showLast3Transactions();
                }
                break;
                case 5:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid selection!");

            }
        }
    }


    public static void showPage() {
        System.out.println("1. Add new account\n" +
                " 2. Withdraw\n" +
                " 3. Deposit\n" +
                " 4. View Last 3 Transactions\n" +
                " 5. Exit");
        System.out.print("> ");

    }

    public static void createAccount() {
        User user = new User();
        System.out.print("Do You Have Any account ?(yes/no) ");
        input.nextLine();
        String answer = input.nextLine();
        if (answer.equals("no")) {
            System.out.println("your name: ");
//            input.nextLine();
            String name = input.nextLine();
            System.out.println("your family: ");
            String family = input.nextLine();
            System.out.println("your nationalCode: ");
            String nationalCode = input.nextLine();


            user.setName(name);
            user.setFamily(family);
            user.setNationalCode(nationalCode);
            user.setCreationDate(new Date());

            addUser(user);
        } else {
            System.out.println("your name: ");
            input.nextLine();
            String name = input.nextLine();
            System.out.println("your family: ");
            String family = input.nextLine();
            System.out.println("your nationalCode: ");
            String nationalCode = input.nextLine();
            user = userServiceImpl.findUserByNameAndFamily(name, family, nationalCode);

            addUser(user);
        }

    }

    private static void addUser(User user) {
        Account account = new Account();
        int temp = random.nextInt(1000000);
        account.setAccountNumber(10000 + temp);
//        account.setAccountNumber(1);
        account.setCartNumber("1");
        account.setCvv2("1111");
        account.setOpeningDate(new Date());
        System.out.println("Enter balance: ");
        double balance = input.nextDouble();
        account.setBalance(balance);
        account.setExpirationDate(Account.generateExpirationDate());
        account.setUser(user);
        System.out.print("what kind of account do you want to open? please enter 's' for SHORT_TERM," +
                " 'l' for LONG_TERM, 'c' for CURRENT: ,  'g' for GOOD_LOAN: ");
        String typeAccount = input.next();

        switch (typeAccount) {
            case "s":
                account.setType(AccountType.SHORT_TERM);
                break;
            case "l":
                account.setType(AccountType.LONG_TERM);
                break;
            case "c":
                account.setType(AccountType.CURRENT);
                break;
            default:
            case "g":
                account.setType(AccountType.GOOD_LOAN);
                break;
        }
        userServiceImpl.add(user);
        accountServiceImpl.add(account);
    }


    private static void deposite() {
        System.out.print("enter your name: ");
        input.nextLine();
        String name = input.nextLine();
        System.out.println("enter your family: ");
        String family = input.nextLine();
        System.out.println("enter your natiomnalCode: ");
        String nationalCode = input.nextLine();
        User user = userServiceImpl.findUserByNameAndFamily(name, family, nationalCode);
        List<Account> accounts = user.getAccount();
        accounts.forEach(System.out::println);
        System.out.print("enter accountNumber: ");
        int number = input.nextInt();
        System.out.println("Enter Amount U Want to Deposit : ");
        double amount = input.nextDouble();
        for(Account account: accounts){
            if (account.getAccountNumber()==number){
                accountServiceImpl.deposit(account.getId(), amount);
            }
        }

    }

    private static void withDraw() {
        System.out.print("enter your name: ");
        input.nextLine();
        String name = input.nextLine();
        System.out.println("enter your family: ");
        String family = input.nextLine();
        System.out.println("enter your natiomnalCode: ");
        String nationalCode = input.nextLine();
        User user = userServiceImpl.findUserByNameAndFamily(name, family, nationalCode);
        List<Account> accounts = user.getAccount();
        accounts.forEach(System.out::println);
        System.out.print("enter accountNumber: ");
        int number = input.nextInt();
        System.out.println("Enter Amount U Want to WithDraw : ");
        double amount = input.nextDouble();
        for(Account account: accounts) {
            if (account.getAccountNumber() == number) {
                accountServiceImpl.withdraw(account.getId(), amount);
            }
        }}
    private static void showLast3Transactions() {
        System.out.print("enter your name: ");
        input.nextLine();
        String name = input.nextLine();
        System.out.println("enter your family: ");
        String family = input.nextLine();
        System.out.println("enter your natiomnalCode: ");
        String nationalCode = input.nextLine();
        User user;
        user = userServiceImpl.findUserByNameAndFamily(name, family, nationalCode);
        List<Update> updates = userServiceImpl.getUpdates(user);
        int c = 3;
        int updateSize = updates.size();
        if (updateSize > c)
            while (c > 0) {
                System.out.println(updates.get(updateSize - c));
                c--;
            }
        else
            IntStream.iterate(updateSize - 1, i -> i >= 0, i -> i - 1).mapToObj(updates::get).forEach(System.out::println);
    }


}