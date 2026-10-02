package com.ybkuanysh.lab4.task1;

public class Main {
    public static void main(String[] args) {
        Account[] accounts = {
                new Account("KZ01", 1000),
                new DepositAccount("KZ02", 1000, 0.12),
                new CreditAccount("KZ03", 1000, 5000)
        };

        for (Account a : accounts) {
            System.out.println(a);
            System.out.println("  снять 3000: " + (a.withdraw(3000) ? "успешно" : "отказ"));
            a.monthEnd();
            System.out.println("  после конца месяца: " + a);
        }

        Account x = new DepositAccount("KZ09", 500, 0.1);
        Account y = new DepositAccount("KZ09", 500, 0.1);
        Account z = new Account("KZ09", 500);
        System.out.println("x.equals(y): " + x.equals(y) + ", hash равны: " + (x.hashCode() == y.hashCode()));
        System.out.println("x.equals(z): " + x.equals(z));
    }
}
