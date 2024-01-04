package Camerarentalaapp;

public class User {
	private double walletBalance;

    public User() {
        this.walletBalance = 0.0;
    }

    public double getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }

    public void depositToWallet(double amount) {
        try {
            if (amount <= 0) {
                throw new IllegalArgumentException("Invalid deposit amount. Amount must be greater than 0.");
            }
            setWalletBalance(getWalletBalance() + amount);
            System.out.println("Deposit successful. New wallet balance: $" + getWalletBalance());
        } catch (IllegalArgumentException e) {
            System.out.println("Deposit failed. " + e.getMessage());
        }
    }
}
