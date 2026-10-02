package com.ybkuanysh.lab4.task1;

import java.util.Objects;

public class Account {
    protected final String number;
    protected double balance;

    public Account(String number, double balance) {
        this.number = number;
        this.balance = balance;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) return false;
        balance -= amount;
        return true;
    }

    public void monthEnd() {
    }

    @Override
    public String toString() {
        return String.format("%s[%s, баланс=%.2f]", getClass().getSimpleName(), number, balance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account a = (Account) o;
        return number.equals(a.number) && balance == a.balance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, balance);
    }
}
