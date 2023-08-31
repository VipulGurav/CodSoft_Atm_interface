package atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMGUI {
    private ATM atm;
    private JFrame frame;

    public ATMGUI(double initialBalance) {
        atm = new ATM(initialBalance);

        frame = new JFrame("ATM Machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(4, 1));

        
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton balanceButton = new JButton("Check Balance");

        JLabel resultLabel = new JLabel("", JLabel.CENTER);
        
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String amountStr = JOptionPane.showInputDialog(frame, "Enter amount to deposit:");
                double amount = Double.parseDouble(amountStr);
                atm.deposit(amount);
                resultLabel.setText("Deposit successful");
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String amountStr = JOptionPane.showInputDialog(frame, "Enter amount to withdraw:");
                double amount = Double.parseDouble(amountStr);
                if (atm.withdraw(amount)) {
                    resultLabel.setText("Withdrawal successful");
                } else {
                    resultLabel.setText("Insufficient balance");
                }
            }
        });

        balanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double balance = atm.checkBalance();
                resultLabel.setText("Balance: " + balance);
            }
        });

        frame.add(depositButton);
        frame.add(withdrawButton);
        frame.add(balanceButton);
        frame.add(resultLabel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ATMGUI(1000.0); // Initial account balance
            }
        });
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}

class ATM {
    private BankAccount bankAccount;

    public ATM(double initialBalance) {
        bankAccount = new BankAccount(initialBalance);
    }

    public void deposit(double amount) {
        bankAccount.deposit(amount);
    }

    public boolean withdraw(double amount) {
        return bankAccount.withdraw(amount);
    }

    public double checkBalance() {
        return bankAccount.getBalance();
    }
}
