package Vartual_ATM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {
    String pin;
    String card;
    TextField textField;
    JButton b1, b2;

    // Method to set up frame design
    private void setupFrameDesign() {
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 1550, 830);
        add(l3);

        JLabel label1 = new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("System", Font.BOLD, 16));
        label1.setBounds(460, 180, 400, 35);
        l3.add(label1);

        textField = new TextField();
        textField.setBackground(new Color(65, 125, 128));
        textField.setForeground(Color.WHITE);
        textField.setBounds(460, 230, 320, 25);
        textField.setFont(new Font("Raleway", Font.BOLD, 22));
        l3.add(textField);

        b1 = new JButton("DEPOSIT");
        b1.setBounds(700, 362, 150, 35);
        b1.setBackground(new Color(65, 125, 128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        l3.add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(700, 406, 150, 35);
        b2.setBackground(new Color(65, 125, 128));
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        l3.add(b2);

        setLayout(null);
        setSize(1550, 1080);
        setLocation(0, 0);
        setVisible(true);
    }

    // Updated constructor to accept pin and card parameters
    Deposit(String pin, String card) {
        this.pin = pin;
        this.card = card;
        setupFrameDesign(); // Call the frame design setup method
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String amount = textField.getText();
            Date date = new Date();
            if (e.getSource() == b1) {
                if (textField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter the amount you want to deposit");
                } else {
                    // Save deposit details to a file
                    BufferedWriter writer = new BufferedWriter(new FileWriter(card + "_deposit.txt", true));
                    writer.write("Date: " + date + ", Amount: " + amount);
                    writer.newLine();
                    writer.close();

                    // Display card number and deposit amount in a dialog box
                    String message = "Card Number: " + card + "\nDeposit Amount: Tk. " + amount;
                    JOptionPane.showMessageDialog(null, message, "Deposit Details", JOptionPane.INFORMATION_MESSAGE);

                    setVisible(false);

                }
            } else if (e.getSource() == b2) {
                setVisible(false);
                // Navigate back to Signup3 class with pin and card parameters
                new Signup3(pin); // Ensure Signup3 constructor accepts pin and card parameters
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Deposit("", ""); // Pass empty strings for pin and card, or provide actual values if needed
    }
}
