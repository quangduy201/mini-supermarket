package mini_supermarket.GUI;

import mini_supermarket.DTO.Account;
import mini_supermarket.main.MiniSupermarket;
import mini_supermarket.utils.DateTime;
import mini_supermarket.utils.I18n;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class OTP extends JDialog {
    private static final int OTP_LENGTH = 6;
    private final Account account;
    private final String email;
    private final JPanel otpEnterEmail;
    private final JPanel otpConfirmPanel;
    private final JPanel otpChangePassword;
    private final long seconds = 180;
    private int step;
    private Thread currentCountDownThread;

    public OTP() {
        super((Frame) null, I18n.get("frame", "forgotten_password"), true);

        otpEnterEmail = new JPanel(new MigLayout());
        otpEnterEmail.setPreferredSize(new Dimension(500, 300));

        otpConfirmPanel = new JPanel(new MigLayout());
        otpConfirmPanel.setPreferredSize(new Dimension(500, 300));

        otpChangePassword = new JPanel(new MigLayout());
        otpChangePassword.setPreferredSize(new Dimension(500, 300));

        account = new Account();
        email = "";
        toStep(step = 1);
        setSize(500, 300);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(MiniSupermarket.login);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit();
            }
        });
        setVisible(true);
    }

    private void showEnterEmail() {
        otpEnterEmail.removeAll();

        JLabel lbEnterEmail = new JLabel(I18n.get("frame", "forgotten_password.email"), SwingConstants.CENTER);
        lbEnterEmail.setFont(new Font("Arial", Font.BOLD, 20));
        lbEnterEmail.setPreferredSize(new Dimension(500, 50));
        otpEnterEmail.add(lbEnterEmail, "span, center");

        JTextField txtEnterEmail = new JTextField();
        txtEnterEmail.setPreferredSize(new Dimension(350, 40));
        otpEnterEmail.add(txtEnterEmail, "span, center, wrap");

        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(new EmptyBorder(15, 0, 0, 0));
        otpEnterEmail.add(panel, "wrap,center");

        JButton[] buttons = new JButton[2];
        buttons[0] = new JButton(I18n.get("dialog", "cancel"));
        buttons[0].setBackground(new Color(0x018847));
        buttons[0].setForeground(new Color(0xFFFFFF));
        buttons[0].setFont(new Font("Arial", Font.BOLD, 12));
        buttons[0].addActionListener(e -> dispose());
        panel.add(buttons[0]);

        buttons[1] = new JButton(I18n.get("dialog", "next"));
        buttons[1].setBackground(new Color(0x018847));
        buttons[1].setForeground(new Color(0xFFFFFF));
        buttons[1].setFont(new Font("Arial", Font.BOLD, 12));
        buttons[1].addActionListener(e -> validateStep1(txtEnterEmail.getText()));
        panel.add(buttons[1]);
    }

    private void showConfirmPanel() {
        otpConfirmPanel.removeAll();

        JLabel username = new JLabel(account.getUsername(), SwingConstants.CENTER);
        username.setFont(new Font("Arial", Font.BOLD, 20));
        username.setPreferredSize(new Dimension(500, 32));
        otpConfirmPanel.add(username, "span,center");

        JLabel label2 = new JLabel(I18n.get("frame", "forgotten_password.otp"), SwingConstants.CENTER);
        label2.setFont(new Font("Arial", Font.BOLD, 14));
        label2.setPreferredSize(new Dimension(500, 32));
        otpConfirmPanel.add(label2, "span,center,wrap");

        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.BOLD, 20));
        textField.setPreferredSize(new Dimension(90, 40));
        textField.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                JTextField textField = (JTextField) input;
                String text = textField.getText();
                return text.matches("\\d{1,6}"); // Only allow 1 to 6 digits
            }
        });
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                currentText = currentText.substring(0, offset) + text + currentText.substring(offset + length);

                if (currentText.matches("\\d{0,6}")) { // Only allow 0 to 6 digits
                    super.replace(fb, offset, length, text, attrs);
                    if (currentText.length() == 6)
                        validateStep2(currentText);
                }
            }
        });
        otpConfirmPanel.add(textField, "wrap,center,span");

        JLabel nothing = new JLabel();
        nothing.setPreferredSize(new Dimension(20, 10));
        otpConfirmPanel.add(nothing, "wrap,center");

        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(new EmptyBorder(15, 0, 0, 0));
        otpConfirmPanel.add(panel, "wrap,center");

        JButton[] buttons = new JButton[3];
        buttons[0] = new JButton();
        buttons[0].setText(I18n.get("dialog", "resend"));
        buttons[0].setFont(new Font("Arial", Font.PLAIN, 12));
        buttons[0].setBackground(new Color(0x018847));
        buttons[0].setForeground(new Color(0xFFFFFF));
        buttons[0].addActionListener(e -> {
            sendOTP(nothing);
        });
        panel.add(buttons[0]);

        buttons[1] = new JButton();
        buttons[1].setText(I18n.get("dialog", "back"));
        buttons[1].setFont(new Font("Arial", Font.PLAIN, 12));
        buttons[1].setBackground(new Color(0x018847));
        buttons[1].setForeground(new Color(0xFFFFFF));
        buttons[1].addActionListener(e -> toStep(--step));
        panel.add(buttons[1]);

        buttons[2] = new JButton();
        buttons[2].setText(I18n.get("dialog", "cancel"));
        buttons[2].setFont(new Font("Arial", Font.PLAIN, 12));
        buttons[2].setBackground(new Color(0x018847));
        buttons[2].setForeground(new Color(0xFFFFFF));
        buttons[2].addActionListener(e -> this.dispose());
        panel.add(buttons[2]);

        sendOTP(nothing);
    }

    private void showChangePassword() {
        JLabel title = new JLabel(I18n.get("frame", "forgotten_password.change_password"), SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setPreferredSize(new Dimension(500, 50));
        otpChangePassword.add(title, "span,center");

        JLabel label1 = new JLabel(I18n.get("frame", "forgotten_password.new_password"), SwingConstants.CENTER);
        label1.setFont(new Font("Arial", Font.BOLD, 14));
        label1.setPreferredSize(new Dimension(200, 32));
        otpChangePassword.add(label1, "span,center,wrap");

        JPasswordField passwordField1 = new JPasswordField();
        JPasswordField passwordField2 = new JPasswordField();

        passwordField1.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField1.setPreferredSize(new Dimension(200, 32));
        passwordField1.addActionListener(e -> {
            String password = new String(passwordField1.getPassword());
            String confirm = new String(passwordField2.getPassword());
            validateStep3(password, confirm);
        });
        otpChangePassword.add(passwordField1, "span,center,wrap");

        JLabel label2 = new JLabel(I18n.get("frame", "forgotten_password.reenter"), SwingConstants.CENTER);
        label2.setFont(new Font("Arial", Font.BOLD, 14));
        label2.setPreferredSize(new Dimension(200, 32));
        otpChangePassword.add(label2, "span,center,wrap");

        passwordField2.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField2.setPreferredSize(new Dimension(200, 32));
        passwordField2.addActionListener(e -> {
            String password = new String(passwordField1.getPassword());
            String confirm = new String(passwordField2.getPassword());
            validateStep3(password, confirm);
        });
        otpChangePassword.add(passwordField2, "span,center,wrap");

        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(new EmptyBorder(15, 0, 0, 0));
        otpChangePassword.add(panel, "span,center,wrap");

        JButton[] buttons = new JButton[2];
        buttons[0] = new JButton();
        buttons[0].setText(I18n.get("dialog", "confirm"));
        buttons[0].setFont(new Font("Arial", Font.PLAIN, 12));
        buttons[0].setBackground(new Color(0x018847));
        buttons[0].setForeground(new Color(0xFFFFFF));
        buttons[0].addActionListener(e -> {
            String password = new String(passwordField1.getPassword());
            String confirm = new String(passwordField2.getPassword());
            validateStep3(password, confirm);
        });
        panel.add(buttons[0]);

        buttons[1] = new JButton();
        buttons[1].setText(I18n.get("dialog", "cancel"));
        buttons[1].setFont(new Font("Arial", Font.PLAIN, 12));
        buttons[1].setBackground(new Color(0x018847));
        buttons[1].setForeground(new Color(0xFFFFFF));
        buttons[1].addActionListener(e -> this.dispose());
        panel.add(buttons[1]);
    }

    private void exit() {
        String message = I18n.get("frame", "exit.forgotten_password");
        String title = I18n.get("dialog", "title.warning");
        String[] options = new String[]{
            I18n.get("dialog", "back"),
            I18n.get("dialog", "exit")
        };
        int choice = JOptionPane.showOptionDialog(null, message, title,
            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
        if (choice == 1)
            dispose();
    }

    private void toStep(int step) {
        JPanel panel = new JPanel();
        switch (step) {
            case 1 -> {
                showEnterEmail();
                panel = otpEnterEmail;
            }
            case 2 -> {
                showConfirmPanel();
                panel = otpConfirmPanel;
            }
            case 3 -> {
                showChangePassword();
                panel = otpChangePassword;
            }
        }
        setContentPane(panel);
        repaint();
        revalidate();
    }

    private void sendOTP(JLabel nothing) {
        if (currentCountDownThread != null)
            currentCountDownThread.interrupt();
        currentCountDownThread = new Thread(() -> {
//            activeOtp = Email.getOTP();
            nothing.setText(I18n.get("frame", "forgotten_password.sending_otp"));
//            Email.sendOTP(email, "Đặt lại mật khẩu Bách Hoá Xanh", activeOtp);
            DateTime start = new DateTime();
            long temp = 0;
            while (seconds - temp > 0 && !Thread.currentThread().isInterrupted()) {
                temp = DateTime.calculateTime(start, new DateTime());
                nothing.setText("(" + (seconds - temp) + "s)");
            }
//            activeOtp = "";
            nothing.setText(I18n.get("frame", "forgotten_password.expired"));
        });
        currentCountDownThread.start();
    }

    private void validateStep1(String text) {
        toStep(++step);
    }

    private void validateStep2(String currentText) {
        toStep(++step);
    }

    private void validateStep3(String password, String confirm) {
        this.dispose();
    }
}