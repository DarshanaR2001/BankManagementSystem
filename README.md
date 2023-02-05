In a Bank, the maximum limit of account balance is ₹1,00,000. The balance cannot exceed this limit. The bank wants to put in some conditions for withdrawals and deposits to an account. 
The conditions are as follows:
      ● Account balance cannot be less than ₹0
      ● The minimum deposit amount is ₹500 per transaction
      ● The maximum deposit amount is ₹50,000 per transaction
      ● The minimum withdrawal amount is ₹1,000 per transaction
      ● The maximum withdrawal amount is ₹25,000 per transaction
      ● No more than 3 deposits are allowed in a day
      ● No more than 3 withdrawals are allowed in a day
      ● Account number entered during deposit or withdrawal should be valid
      ● Account has sufficient balance during withdrawals
      
Below are the commands that need to be supported along with the above description, input parameters and the expected output for each command.

      ● Create - Takes 1 parameter that is the full name of the holder. Creates a new account and returns the account number.
      ● Deposit - Takes 2 parameters as input. First is the account number and the second is the deposit amount. Returns the balance post deposit.
      ● Withdraw - Takes 2 parameters as input. First is the account number and the second is the withdrawal amount. Returns the balance post withdrawal.
      ● Balance - Takes 1 parameter that is the account number. Returns current balance.
      ● Transfer - Takes 3 parameters. First is the source account number, second is the target account number and the last one is the amount to transfer. Returns status as successful or failure.
              ○ All the deposit and withdrawal rules are applicable for transfer operation as well.
              
Sample Input and Output:

      Account creation:
          ● Input: Create “Amy”
            Output: 1001
          ● Input: Create “Aurora”
             Output: 1002
             
      Deposit:
          ● Input: Deposit 1001 10000
            Output: 10000
          ● Input: Deposit 1001 100
            Output: Minimum deposit amount is 500
          ● Input: Deposit 1001 60000
            Output: Maximum deposit amount is 50000
          ● Input: Deposit 1001 10000
            Output: Only 3 deposit are allowed in a day
            
      Balance:
          ● Input: Balance 1001
            Output: 10000
            
      Withdrawal:
          ● Input: Withdraw 1001 500
            Output: Minimum withdrawal amount is 1000
          ● Input: Withdraw 1001 20000
            Output: Insufficient balance
          ● Input: Withdraw 1001 1000
            Output: 9000
          ● Input: Withdraw 1001 2000
            Output: 7000
          ● Input: Withdraw 1001 1000
             Output: 6000
          ● Input: Withdraw 1001 5000
            Output: Only 3 withdrawal are allowed in a day
            
      Transfer:
          ● Input: Transfer 1001 1002 5000
            Output: Successful
          ● Input: Transfer 1002 1004 500
            Output: Minimum withdrawal amount is 1000
          ● Input: Transfer 1002 1004 30000
            Output: Maximum withdrawal amount is 30000
