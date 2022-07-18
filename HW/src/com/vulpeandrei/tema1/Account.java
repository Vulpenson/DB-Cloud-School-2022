package com.vulpeandrei.tema1;

import java.util.ArrayList;
import java.util.Collections;

public class Account extends Exception{
    // Exception thrown when there are not enough money in the account
    // in case of withdrawal
    public static class NotEnoughMoneyException extends Throwable {
        public NotEnoughMoneyException(String errorMessage, Throwable err) {
            super(errorMessage, err);
        }
    }

    // Exception thrown when the inserted National ID is invalid
    public static class InvalidNationalIdException extends Throwable {
        public InvalidNationalIdException(String errorMessage, Throwable err) {
            super(errorMessage, err);
        }
    }

    private int accountNo; // Number of the account
    private int amount; // The amount of money in the account
    private long nationalId; // The National ID linked to the account

    // Generator of a default account
    public Account() {
        this.accountNo = 1;
        this.amount = 0;
    }

    // Getters and Setters
    public int getAccountNo() {
        return accountNo;
    }
    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public long getNationalId() {
        return nationalId;
    }

    // Function that deposits money to personal account
    public void deposit(int amount) {
        this.amount = this.amount + amount;
    }

    // Function that withdraws money from personal account
    public void withdraw(int amount) throws NotEnoughMoneyException{
        if(amount > this.amount) {
            throw new NotEnoughMoneyException("Not enough money in the account", new Error());
        } else {
            this.amount = this.amount - amount;
        }
    }

    // Function that checks if the given National ID is valid
    // National ID has the format SAALLZZJJNNNC
    public boolean isNationalIdValid(long nationalId) {
        // Transforming the National ID into an array for easier use
        ArrayList<Long> id = new ArrayList<>();
        long tmp = nationalId;
        while(tmp != 0) {
            id.add(tmp % 10);
            tmp /= 10;
        }
        Collections.reverse(id);
        // Checking if the length of nationalId is valid
        if (id.size() != 13) {
            return false;
        }

        // Checking if the sex is valid
        if (!(id.get(0) == 1 || id.get(0) == 2 || id.get(0) == 5 || id.get(0) == 6)) {
            return false;
        }

        // Checking if the year of birth format is correct
        int year = (int) ((id.get(1) * 10) + id.get(2));
        if (year > 22 && (id.get(0) == 5 || id.get(0) == 6)) {
            return false;
        }

        // Checking if the month of birth is valid
        int month = (int) ((id.get(3) * 10) + id.get(4));
        if (month > 12) {
            return false;
        }

        // Checking if the day of birth is valid
        int day = (int) ((id.get(5) * 10) + id.get(6));
        if (day > 31 && (month == 1 || month == 3 || month == 5 || month == 7 ||
                        month == 8 || month == 10 || month == 12)) {
            return false;
        }
        if (month == 2) {
            if(year % 4 == 0 && day > 29) {
                return false;
            }
            if(year % 4 != 0 && day > 28) {
                return false;
            }
        }
        if (day > 30 && (month == 4 || month == 6 || month == 9 || month == 11)) {
            return false;
        }

        // Checking if the province code is valid
        int provinceCode = (int) ((id.get(7) * 10) + id.get(8));
        if (provinceCode > 52) {
            return false;
        }

        // Checking if the CheckSum value corresponds with the one calculated
        // based on the given National ID
        long checkAux = 279146358279L;
        int checkSum = Math.toIntExact(id.get(12));
        int Sum = 0;

        // Transforming the checkAux into an array for easier use
        ArrayList<Long> check = new ArrayList<>();
        long tmp_check = checkAux;
        while(tmp_check != 0) {
            check.add(tmp_check % 10);
            tmp_check = tmp_check / 10;
        }
        Collections.reverse(check);

        for (int i = 0; i <= 11; i++) {
            Sum += (id.get(i) * check.get(i));
        }
        if (Sum % 11 == 10 && checkSum != 1) {
            return false;
        }
        if (Sum % 11 != checkSum) {
            return false;
        }

        // If all the checks are passed then the given National ID is valid
        return true;
    }

    // Function that links the current account to a National ID
    public void linkToNationalId(long nationalId) throws InvalidNationalIdException {
        if (!isNationalIdValid(nationalId)) {
            throw new InvalidNationalIdException("The given National ID is invalid", new Error());
        } else {
            this.nationalId = nationalId;
        }
    }
}
