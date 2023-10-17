package mini_supermarket.GUI;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import mini_supermarket.BLL.AccountBLL;
import mini_supermarket.main.MiniSupermarket;
import mini_supermarket.utils.DateTime;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;


public class Login extends JFrame {
    private JPanel contentPane;
    private JPanel header;
    private JPanel login;
    private JPanel formLogin;
    private JLabel lbBanner_Header;
    private JLabel lbLogin;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JLabel lbForgetPasswd;
    private JTextField jTextFieldUserName;
    private JPasswordField jTextFieldPassword;
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
                cancel();
            }
        });

        contentPane = new JPanel(new BorderLayout());
//        contentPane.setBackground(new Color(0x02723A));
        setContentPane(contentPane);

        header = new JPanel(new BorderLayout());
        header.setPreferredSize(new Dimension(700, 110));
        header.setBackground(new Color(0x028948));
        header.setBorder(BorderFactory.createMatteBorder(0, -60, 5, 0, new Color(0xF0F0F0FF)));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(header, BorderLayout.NORTH);

        lbBanner_Header = new JLabel();
        lbBanner_Header.setSize(new Dimension(700, 100));
        lbBanner_Header.setIcon(new FlatSVGIcon("img/banner_header.svg"));
        lbBanner_Header.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(lbBanner_Header, BorderLayout.CENTER);

        lbLogin = new JLabel("Đăng Nhập", SwingConstants.CENTER);
        lbLogin.setBackground(new Color(0xF0F0F0FF));
        lbLogin.setForeground(new Color(0x028948));
        lbLogin.setFont(new Font("Lexend", Font.BOLD, 30));
        lbLogin.setBorder(BorderFactory.createMatteBorder(5, 0, 0, 0, new Color(0x028948)));
        contentPane.add(lbLogin, BorderLayout.CENTER);

        login = new JPanel(new FlowLayout());
        contentPane.add(login, BorderLayout.SOUTH);

        formLogin = new JPanel(new MigLayout());
        formLogin.setPreferredSize(new Dimension(500, 270));
        login.add(formLogin);

        lbUsername = new JLabel("Tài khoản:", JLabel.LEFT);
        lbUsername.setForeground(new Color(0x018847));
        lbUsername.setPreferredSize(new Dimension(150, 50));
        lbUsername.setFont(new Font("Lexend", Font.BOLD, 15));
        formLogin.add(lbUsername);

        jTextFieldUserName = new JTextField();
        jTextFieldUserName.setBackground(new Color(211,211,211));
        jTextFieldUserName.setPreferredSize(new Dimension(350, 40));
        jTextFieldUserName.setFont(new Font("open sans", Font.PLAIN, 15));
        jTextFieldUserName.setText("Nhập tài khoản");
        jTextFieldUserName.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent ignoredEvt) {
                jTextFieldUserNameFocusGained(ignoredEvt);
            }

            public void focusLost(FocusEvent ignoredEvt) {
                jTextFieldUserNameFocusLost(ignoredEvt);
            }
        });
        jTextFieldUserName.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER)
                    login();
            }
        });
        formLogin.add(jTextFieldUserName, "wrap,growx");

        lbPassword = new JLabel("Mật khẩu:", JLabel.LEFT);
        lbPassword.setForeground(new Color(0x018847));
        lbPassword.setPreferredSize(new Dimension(150, 50));
        lbPassword.setFont(new Font("Lexend", Font.BOLD, 15));
        formLogin.add(lbPassword);

        jTextFieldPassword = new JPasswordField();
        jTextFieldPassword.setBackground(new Color(211,211,211));
        jTextFieldPassword.setPreferredSize(new Dimension(350, 40));
        jTextFieldPassword.setText("Password");
        jTextFieldPassword.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent ignoredEvt) {
                jTextFieldPasswordFocusGained(ignoredEvt);
            }

            public void focusLost(FocusEvent ignoredEvt) {
                jTextFieldPasswordFocusLost(ignoredEvt);
            }
        });
        jTextFieldPassword.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER)
                    login();
            }
        });
        formLogin.add(jTextFieldPassword, "wrap,growx");

        lbForgetPasswd = new JLabel("Quên mật khẩu?");
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

        btLogin = new JButton("Đăng nhập");
        btLogin.setBackground(new Color(0x018847));;
        btLogin.setForeground(new Color(0xFFFFFF));
        btLogin.setFont(new Font("Lexend", Font.BOLD, 15));
        btLogin.setPreferredSize(new Dimension(80, 50));
        btLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                login();
            }
        });
        btLogin.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    login();
            }
        });
        formLogin.add(btLogin, "span, center, wrap");
    }

    private void forgotPassword() {
        new OTP();
    }

    private void jTextFieldUserNameFocusLost(FocusEvent ignoredEvt) {
        if (jTextFieldUserName.getText().isEmpty()) {
            jTextFieldUserName.setText("Nhập tài khoản");
        }
    }

    private void jTextFieldUserNameFocusGained(FocusEvent ignoredEvt) {
        if (jTextFieldUserName.getText().equals("Nhập tài khoản")) {
            jTextFieldUserName.setText("");
        }
    }

    private void jTextFieldPasswordFocusGained(FocusEvent ignoredEvt) {
        String password = new String(jTextFieldPassword.getPassword());
        if (password.equals("Password")) {
            jTextFieldPassword.setText("");
        }
    }

    private void jTextFieldPasswordFocusLost(FocusEvent ignoredEvt) {
        String password = new String(jTextFieldPassword.getPassword());
        if (password.isEmpty()) {
            jTextFieldPassword.setText("Password");
        }
    }

    public void login() {
        String userName, passWord;
        userName = jTextFieldUserName.getText();
        passWord = new String(jTextFieldPassword.getPassword());
        AccountBLL accountBLL = new AccountBLL();
//        List<Account> accountList = accountBLL.findAccounts("username", userName);
//        if (accountList.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Tài khoản không tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//        if (!accountList.get(0).getPassword().equals(passWord)) {
//            JOptionPane.showMessageDialog(this, "Mật khẩu không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//        Account account = accountList.get(0);
        try {
            DateTime now = new DateTime(LocalDateTime.now());
            //updateAccountLast_signed_in
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        JOptionPane.showMessageDialog(this, "Đăng nhập thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//        try {
//            Thread thread = new Thread(() -> MiniSupermarket.homeGUI.setAccount(account));
//            thread.start();
//            thread.join();
//        } catch (Exception ignored) {
//
//        }
        dispose();

    }

    private void cancel() {
        String[] options = new String[]{"Huỷ", "Thoát chương trình"};
        int choice = JOptionPane.showOptionDialog(null, "Bạn có muốn thoát chương trình?",
            "Lỗi", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
        if (choice == 1)
            MiniSupermarket.exit(1);
    }
}
