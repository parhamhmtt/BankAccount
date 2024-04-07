public class usersAccount {
    private String ownerName;
    private int accountNumber;
    private long balance;

    public usersAccount(String ownerName, int accountNumber, long balance, String password) {
        this.ownerName = ownerName;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.password = password;
    }

    private String password;

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public boolean checkPassword(String password){
        return(password.equals(this.password));
    }
}
