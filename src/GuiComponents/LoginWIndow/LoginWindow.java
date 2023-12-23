package GuiComponents.LoginWIndow;

import Database.Database;
import GuiComponents.CustomComponents.RoundedButton;
import GuiComponents.Resources.Fonts;
import GuiComponents.Resources.Images;
import GuiComponents.Resources.StoreColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class LoginWindow extends JFrame {

    final private static int FRAME_HEIGHT = 300;
    final private static int FRAME_WIDTH = 400;
    final private static int USERNAME_FIELD_Y = 120;
    final private static int PASSWORD_FIELD_Y = 160;
    final private static String TITLE = "Tindahan";
    final private static int UPPER_PANEL_HEIGHT = 120;
    final private static Dimension BUTTON_SIZE = new Dimension(200, 50);
    final private static Point BUTTON_LOCATION = new Point(100, 200);
    final private static Color BUTTON_COLOR = StoreColors.LIGHT_MAROON;
    final private static Color BUTTON_TEXT_COLOR = Color.white;
    final private static Color BUTTON_COLOR_HOVER = StoreColors.DARK_MAROON;
    final private static Color FRAME_COLOR = StoreColors.BACKGROUND_COLOR;

    public LoginWindow(JFrame nextFrame) throws NoSuchAlgorithmException {
        JPanel upperPanel = new JPanel();

        InputFields usernameField = new InputFields("Username:", 0, USERNAME_FIELD_Y, FRAME_WIDTH, false, FRAME_COLOR, StoreColors.FONT_COLOR_DARK);
        InputFields passwordField = new InputFields("Password: ", 0, PASSWORD_FIELD_Y, FRAME_WIDTH, true, FRAME_COLOR, StoreColors.FONT_COLOR_DARK);

        JLabel logoLabel = new JLabel();
        RoundedButton loginButton = new RoundedButton("LOGIN", BUTTON_COLOR_HOVER);
        ImageIcon logo = Images.STORE_LOGO;
        ImageIcon appIcon = Images.APP_ICON;

        this.getContentPane().setBackground(FRAME_COLOR);
        this.setIconImage(appIcon.getImage());

        upperPanel.setLayout(new FlowLayout());
        upperPanel.setSize(FRAME_WIDTH, UPPER_PANEL_HEIGHT);
        upperPanel.setOpaque(false);

        loginButton.setSize(BUTTON_SIZE);
        loginButton.setLocation(BUTTON_LOCATION);
        loginButton.setText("LOGIN");
        loginButton.setFont(Fonts.FONT_BOLD);
        loginButton.setFocusable(false);
        loginButton.setBackground(BUTTON_COLOR);
        loginButton.setForeground(BUTTON_TEXT_COLOR);
        loginButton.setFocusPainted(false);

        logoLabel.setIcon(logo);
        logoLabel.setVisible(true);

        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setTitle(TITLE);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        upperPanel.add(logoLabel);

        this.add(upperPanel);
        this.add(usernameField);
        this.add(passwordField);
        this.add(loginButton);

        usernameField.getTextField().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    login(usernameField, passwordField, nextFrame);
                }
            }
        });

        passwordField.getPasswordField().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    login(usernameField, passwordField, nextFrame);
                }
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login(usernameField, passwordField, nextFrame);
            }
        });

        this.setVisible(true);

    }

    public void login(InputFields usernameField, InputFields passwordField, JFrame nextFrame) {
        String username = usernameField.getUsernameInput();
        String passHash;
        try {
            passHash = passwordField.getPasswordHash();
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }

        try {
            if (Database.verifyLogin(username, passHash)) {
                System.out.println("Login Successful");
                setVisible(false);
                nextFrame.setVisible(true);
            } else {
                System.out.println("Login Failed");
            }
        } catch (SQLException ex) {

        }
    }

}
