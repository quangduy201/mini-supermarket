package mini_supermarket.GUI;

import mini_supermarket.BLL.AccountBLL;
import mini_supermarket.DTO.Account;
import mini_supermarket.main.MiniSupermarket;
import mini_supermarket.utils.*;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class Login extends JFrame {
    private JPanel contentPane;
    private JPanel header;
    private JPanel login;
    private JPanel formLogin;
    private JLabel lbFullLogo;
    private JLabel lbLogin;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JLabel lbForgetPasswd;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btLogin;

    public Login() {
        initComponents();
    }

    private void initComponents() {
        setSize(700, 500);
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit();
            }
        });

        contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        header = new JPanel(new BorderLayout());
        header.setPreferredSize(new Dimension(700, 110));
        header.setBackground(new Color(0x028948));
        header.setBorder(BorderFactory.createMatteBorder(0, -60, 5, 0, new Color(0xF0F0F0FF)));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(header, BorderLayout.NORTH);

        lbFullLogo = new JLabel();
        lbFullLogo.setIcon(Resource.loadSVGIcon("img/logo_full.svg", 650));
        lbFullLogo.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(lbFullLogo, BorderLayout.CENTER);

        lbLogin = new JLabel(I18n.get("frame", "login.form"), SwingConstants.CENTER);
        lbLogin.setForeground(new Color(0x028948));
        lbLogin.setFont(new Font("Lexend", Font.BOLD, 30));
        lbLogin.setBorder(BorderFactory.createMatteBorder(5, 0, 0, 0, new Color(0x028948)));
        contentPane.add(lbLogin, BorderLayout.CENTER);

        login = new JPanel(new FlowLayout());
        contentPane.add(login, BorderLayout.SOUTH);

        formLogin = new JPanel(new MigLayout());
        formLogin.setPreferredSize(new Dimension(500, 270));
        login.add(formLogin);

        lbUsername = new JLabel(I18n.get("frame", "login.username"), JLabel.LEFT);
        lbUsername.setForeground(new Color(0x018847));
        lbUsername.setPreferredSize(new Dimension(150, 50));
        lbUsername.setFont(new Font("Lexend", Font.BOLD, 15));
        formLogin.add(lbUsername);

        txtUsername = new JTextField();
        txtUsername.setPreferredSize(new Dimension(350, 40));
        txtUsername.setFont(new Font("open sans", Font.PLAIN, 15));
        txtUsername.putClientProperty("JTextField.placeholderText", I18n.get("frame", "login.username.placeholder"));
        txtUsername.addActionListener(e -> login());
        formLogin.add(txtUsername, "wrap,growx");

        lbPassword = new JLabel(I18n.get("frame", "login.password"), JLabel.LEFT);
        lbPassword.setForeground(new Color(0x018847));
        lbPassword.setPreferredSize(new Dimension(150, 50));
        lbPassword.setFont(new Font("Lexend", Font.BOLD, 15));
        formLogin.add(lbPassword);

        txtPassword = new JPasswordField();
        txtPassword.setPreferredSize(new Dimension(350, 40));
        txtPassword.setFont(new Font("open sans", Font.PLAIN, 15));
        txtPassword.putClientProperty("JTextField.placeholderText", I18n.get("frame", "login.password.placeholder"));
        txtPassword.addActionListener(e -> login());
        formLogin.add(txtPassword, "wrap,growx");

        lbForgetPasswd = new JLabel(I18n.get("frame", "login.forgotten_password"));
        lbForgetPasswd.setFont(new Font("Lexend", Font.PLAIN, 12));
        lbForgetPasswd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lbForgetPasswd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lbForgetPasswd.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0x018847)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lbForgetPasswd.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            }

            public void mousePressed(MouseEvent e) {
                forgotPassword();
            }
        });
        formLogin.add(lbForgetPasswd, "span, right, wrap");

        btLogin = new JButton(I18n.get("frame", "login.form"));
        btLogin.setBackground(new Color(0x018847));
        btLogin.setForeground(new Color(0xFFFFFF));
        btLogin.setFont(new Font("Lexend", Font.BOLD, 15));
        btLogin.setPreferredSize(new Dimension(80, 50));
        btLogin.addActionListener(e -> login());
        formLogin.add(btLogin, "span, center, wrap");
    }

    private void forgotPassword() {
        new ForgottenPassword().setVisible(true);
    }

    public void login() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());
        Pair<Account, String> result = AccountBLL.validateLogin(username, password);
        Account account = result.getFirst();
        String message = result.getSecond();
        if (account == null) {
            String title = I18n.get("dialog", "title.error");
            JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
            return;
        }
        account.setLastSignedIn(DateTime.now());
        new AccountBLL().update(account);

        String title = I18n.get("dialog", "title.info");
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
        MiniSupermarket.main = new Main(account);
        dispose();
        MiniSupermarket.main.setVisible(true);
        if (account.getPassword().startsWith("first")) {
            ForgottenPassword forgottenPassword = new ForgottenPassword();
            forgottenPassword.setAccount(account);
            forgottenPassword.toStep(3);
            forgottenPassword.setVisible(true);
        }
        MiniSupermarket.login = null;
        System.gc();
    }

    private void exit() {
        String message = I18n.get("frame", "exit");
        String title = I18n.get("dialog", "title.exit");
        String[] options = new String[]{
            I18n.get("dialog", "cancel"),
            I18n.get("dialog", "exit")
        };
        int choice = JOptionPane.showOptionDialog(this, message, title,
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (choice == 1)
            MiniSupermarket.exit(1);
    }
}
