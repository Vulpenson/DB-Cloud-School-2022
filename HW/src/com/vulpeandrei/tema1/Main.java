package com.vulpeandrei.tema1;

public class Main {
    public static void main(String[] args) throws Account.NotEnoughMoneyException, Account.InvalidNationalIdException {
        Account account = new Account();
        account.deposit(100);

        // Uncomment line 7 for checking how NotEnoughMoneyException works
//         account.withdraw(200);
        account.deposit(200);
        account.withdraw(200);

        account.linkToNationalId(1990101409143L);

        // Uncomment line 14 for checking how InvalidNationaIdException works
//        account.linkToNationalId(2990101409143L);
    }
}
