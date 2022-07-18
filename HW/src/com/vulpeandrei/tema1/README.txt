Vulpe Andrei - Tema 1 - 20.07.2022
-------------------------------------------------------------------------------

The goal of this homework is to solve the "Account" problem.

-------------------------------------------------------------------------------
Define an Account class containing an accountNo, amount and nationalId property.
Requirements:
• Create an empty constructor
• Implement method public void deposit(int amount)
• Define a NotEnoughMoneyException
• Implement method public void withdraw(int amount) which throws an
NotEnoughMoneyException if there is not enough money into the account
• Define an InvalidNationalIdException
• Implement method public void linkToNationalId(int nationalId) which throws an
InvalidNationalIdException if there the national id is not valid
• Test the functions inside a main class
