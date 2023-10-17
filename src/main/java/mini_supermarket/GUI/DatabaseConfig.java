package mini_supermarket.GUI;

import mini_supermarket.main.MiniSupermarket;
import mini_supermarket.utils.Log;
import mini_supermarket.utils.Password;
import mini_supermarket.utils.Resource;
import mini_supermarket.utils.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Properties;

public class DatabaseConfig extends JDialog {
    private Properties properties;
    private JPanel panel;
    private JLabel[] labels;
    private JTextField[] textFields;
    private JButton btnConfirm;
    private JButton btnCancel;

    public DatabaseConfig(Properties properties) {
        super((Frame) null, "Cấu hình database", true);
        this.properties = properties;
        initComponents();
    }

    public void initComponents() {
        panel = new JPanel();
        labels = new JLabel[4];
        Arrays.setAll(labels, i -> new JLabel());
        textFields = new JTextField[4];
        Arrays.setAll(textFields, i -> new JTextField());
        textFields[3] = new JPasswordField();
        btnConfirm = new JButton();
        btnCancel = new JButton();

        Font fontLabel = new Font("Roboto", Font.BOLD, 14);
        Font fontTextField = new Font("Roboto", Font.PLAIN, 14);

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        labels[0].setText("Port:");
        labels[1].setText("Database:");
        labels[2].setText("Username:");
        labels[3].setText("Password:");
        textFields[0].setText(properties.getProperty("db.port"));
        textFields[1].setText(properties.getProperty("db.database"));
        textFields[2].setText(properties.getProperty("db.username"));
        for (int i = 0; i < 4; i++) {
            gbc.gridy = i;
            gbc.gridx = 0;
            gbc.weightx = 0.25;
            gbc.gridwidth = 1;
            labels[i].setHorizontalAlignment(JLabel.LEFT);
            labels[i].setLabelFor(textFields[i]);
            labels[i].setFont(fontLabel);
            panel.add(labels[i], gbc);

            gbc.gridx = 1;
            gbc.weightx = 0.75;
            gbc.gridwidth = 3;
            textFields[i].setFont(fontTextField);
            textFields[i].addActionListener(e -> confirm());
            panel.add(textFields[i], gbc);
        }

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(10, 0, 10, 0);
        c.gridx = 0;
        btnConfirm.setText("Xác nhận");
        btnConfirm.addActionListener(e -> confirm());
        buttonPanel.add(btnConfirm, c);

        c.gridx = 1;
        btnCancel.setText("Hủy");
        btnCancel.addActionListener(e -> cancel());
        buttonPanel.add(btnCancel, c);

        setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        setMinimumSize(new Dimension(400, 350));
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cancel();
            }
        });
        pack();
        setLocationRelativeTo(MiniSupermarket.splashScreen);
        setVisible(true);
    }

    public void confirm() {
        try {
            Files.createDirectories(Resource.getPathFromResource("settings", false));
        } catch (Exception e) {
            Log.error(DatabaseConfig.class, e.toString());
            return;
        }
        Path path = Resource.getPathFromResource(Settings.DATABASE_FILE, false);
        Resource.setReadOnly(path, false);
        try (OutputStream outputStream = new FileOutputStream(path.toFile())) {
            properties.setProperty("db.port", textFields[0].getText());
            properties.setProperty("db.database", textFields[1].getText());
            properties.setProperty("db.username", textFields[2].getText());
            String encryptedPassword = Password.encrypt(textFields[3].getText(), textFields[1].getText());
            properties.setProperty("db.password", encryptedPassword);
            properties.store(outputStream, "Database configuration");
            Resource.setReadOnly(path, true);
            setEnabled(false);
            dispose();
        } catch (Exception e) {
            Log.error(DatabaseConfig.class, e.toString());
        }
    }

    public void cancel() {
        String[] options = new String[]{"Cấu hình lại", "Thoát chương trình"};
        int choice = JOptionPane.showOptionDialog(null, "Chương trình không thể chạy nếu không thể kết nối được cơ sở dữ liệu.",
            "Lỗi", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
        if (choice == 1)
            MiniSupermarket.exit(1);
    }
}

