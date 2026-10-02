package com.ybkuanysh.lab4.task1;

import java.util.Objects;

public class DepositAccount extends Account {
    private final double rate;

    public DepositAccount(String number, double balance, double rate) {
        super(number, balance);
        this.rate = rate;
    }

    @Override
    public void monthEnd() {
        balance += balance * rate / 12;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" ставка=%.0f%%", rate * 100);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o) && rate == ((DepositAccount) o).rate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rate);
    }
}
