/*package com.ironhack.stolen_name_trucking_company_homework_3.dao;

import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.NoSuchValueException;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login implements ActionListener {

    private static JLabel title;
    private static JPanel panel;
    private static JLabel user;
    private static JTextField userText;
    private static JLabel password;
    private static JPasswordField passwordText;
    private static JButton submit;
    private static JLabel success;
    private static JLabel success2;
    private static JLabel hint;
    private static JFrame frame;
    private static String username;


    //public static MainMenu menu = new MainMenu(leadRepository, accountRepository, contactRepository, opportunityRepository, salesRepRepository);

    public static void login2() {

        frame = new JFrame("LBL CRM SYSTEM LOGIN");
        frame.setUndecorated(true);
        frame.setSize(400, 230);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel(new BorderLayout());
        panel.setBorder(new LineBorder(Color.BLACK, 2, true));

        title = new JLabel("LBL CRM SYSTEM LOGIN");
        title.setBounds(40, 10, 300, 25);
        title.setFont(new Font("Serif", Font.BOLD, 20));
        panel.add(title);

        user = new JLabel("Username");
        user.setFont(new Font("Serif", Font.BOLD, 16));
        user.setBounds(50, 60, 120, 25);
        panel.add(user);

        userText = new JTextField(20);
        userText.setBounds(140, 60, 165, 25);
        panel.add(userText);

        password = new JLabel("Password");
        password.setFont(new Font("Serif", Font.BOLD, 16));
        password.setBounds(50, 90, 120, 25);
        panel.add(password);

        passwordText = new JPasswordField();
        passwordText.setBounds(140, 90, 165, 25);
        panel.add(passwordText);

        submit = new JButton("Login");
        submit.setBounds(223, 140, 80, 25);
        submit.addActionListener(new Login());
        panel.add(submit);

        success = new JLabel();
        success.setBounds(30, 140, 300, 25);
        panel.add(success);

        hint = new JLabel("Make your console full screen for a better experience");
        hint.setFont(new Font("Serif", Font.BOLD, 16));
        hint.setForeground(Color.red);
        hint.setBounds(20, 190, 380, 25);
        panel.add(hint);

        success2 = new JLabel();
        panel.add(success2);

        frame.add(panel);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.getRootPane().setDefaultButton(submit); // Let's you press "Submit" button using ENTER

    }

    public static String getUsername() {
        username = userText.getText() ;
        return username;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //username = userText.getText();
        char[] password = passwordText.getPassword();
        String pass = String.valueOf(password);

        if (getUsername().equals("Lee") && pass.equals("lee")
        || getUsername().equals("Sebastian") && pass.equals("sebastian")
        || getUsername().equals("Mariana") && pass.equals("mariana")
        || getUsername().equals("NataliaS") && pass.equals("natalias")
        || getUsername().equals("Nathan") && pass.equals("nathan")
        || getUsername().equals("NataliaN") && pass.equals("natalian")
        || getUsername().equals("Katarzyna") && pass.equals("katarzyna")
        || getUsername().equals("Urszula") && pass.equals("urszula")
        || getUsername().equals("Anna") && pass.equals("anna")
        || getUsername().equals("Admin") && pass.equals("admin")) {

            frame.dispose();
            try {
                menu.OS();
            } catch (RuntimeException | AWTException | NoSuchValueException ex) {
            }
        } else if (getUsername().equals("Guest") && pass.equals("guest")) {
            frame.dispose();
            try {
                menu.OSGuest();
            } catch (RuntimeException | AWTException ex) {
            }
        } else {
            success.setText("Wrong username or password!");
        }

    }
}
*/