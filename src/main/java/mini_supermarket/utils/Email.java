package mini_supermarket.utils;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class Email {
    public static boolean sendEmail(String toEmail, String emailSubject, String emailBody) {
        Properties properties = Resource.loadProperties(Settings.EMAIL_FILE);
        int times = 5;
        String email = properties.getProperty("email.email");
        String password = properties.getProperty("email.password");
        String host = properties.getProperty("email.host");
        String port = properties.getProperty("email.port");

        properties.clear();
        properties.put("mail.smtp.host", Password.decrypt(host, email, times));
        properties.put("mail.smtp.port", Password.decrypt(port, email, times));
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, Password.decrypt(password, email, times));
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(emailSubject, "utf-8");
            message.setText(emailBody, "utf-8");
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            Log.error(Email.class, "Error while sending an email to " + toEmail + ": " + e);
            return false;
        }
    }
}
