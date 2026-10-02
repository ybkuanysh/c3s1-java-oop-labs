package com.ybkuanysh.lab4.task1;

import java.util.Objects;

public class CreditAccount extends Account {
    private final double limit;

    public CreditAccount(String number, double balance, double limit) {
        super(number, balance);
        this.limit = limit;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > balance + limit) return false;
        balance -= amount;
        return true;
    }

    @Override
    public void monthEnd() {
        if (balance < 0) balance *= 1.02;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" лимит=%.2f", limit);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o) && limit == ((CreditAccount) o).limit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), limit);
    }
}
