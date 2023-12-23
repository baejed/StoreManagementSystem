package GuiComponents.LoginWIndow;

import GuiComponents.CustomComponents.RoundedPasswordField;
import GuiComponents.CustomComponents.RoundedTextField;
import GuiComponents.Resources.StoreColors;

import javax.swing.*;
import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class InputFields extends JPanel {

    final private static int LABEL_WIDTH = 120;
    final private static int LABEL_HEIGHT = 40;
    final private static int TEXT_FIELD_HEIGHT = 35;
    final private static int TEXT_FIELD_WIDTH = 200;
    final private static int TEXT_FIELD_X = 150;
    final private static int TEXT_FIELD_Y = 0;
    final private static Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 22);
    final private RoundedTextField textField;
    final private RoundedPasswordField passwordField;
    final private JLabel label = new JLabel();

    InputFields(String labelText, int XLocation, int YLocation, int frameWidth, boolean isPassword, Color frameColor, Color fontColor) {

        textField = new RoundedTextField(StoreColors.TEXTFIELD_COLOR, StoreColors.MAROON, frameColor);
        passwordField = new RoundedPasswordField(StoreColors.TEXTFIELD_COLOR, StoreColors.MAROON, frameColor);

        label.setSize(LABEL_WIDTH, LABEL_HEIGHT);
        label.setText(labelText);
        label.setVisible(true);
        label.setLocation(40, 0);
        label.setFont(font);
        label.setForeground(fontColor);

        textField.setSize(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
        textField.setLocation(TEXT_FIELD_X, TEXT_FIELD_Y);
        textField.setFont(font);

        passwordField.setSize(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
        passwordField.setLocation(TEXT_FIELD_X, TEXT_FIELD_Y);
        passwordField.setFont(font);

        this.setLayout(null);
        this.setSize(frameWidth, 40);
        this.setLocation(XLocation, YLocation);
        this.setOpaque(false);

        if (isPassword) this.add(passwordField);
        else this.add(textField);
        this.add(label);

        this.setVisible(true);
    }

    private String arrToStr(char[] arr) {
        StringBuilder builder = new StringBuilder();
        for (char i : arr) {
            builder.append(i);
        }
        return builder.toString();
    }

    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public String getPasswordHash() throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(arrToStr(passwordField.getPassword()).getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encodedHash);
    }

    public String getUsernameInput(){
        return textField.getText();
    }
    public RoundedTextField getTextField(){
        return textField;
    }

    public RoundedPasswordField getPasswordField(){
        return passwordField;
    }

}
