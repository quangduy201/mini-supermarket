package mini_supermarket.utils;

import mini_supermarket.DTO.Account;
import mini_supermarket.main.MiniSupermarket;

import javax.swing.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class OTP {
    public static final List<Pair<Account, String>> activeOTPs = new ArrayList<>();
    public static final int OTP_LENGTH = 6;

    public OTP() {

    }

    public static String generateNumericOTP() {
        String numericChars = "0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < OTP_LENGTH; i++) {
            int index = random.nextInt(numericChars.length());
            otp.append(numericChars.charAt(index));
        }
        return otp.toString();
    }

    public static int getIndexOTP(Account account) {
        for (int i = 0; i < activeOTPs.size(); ++i) {
            Account activeAccount = activeOTPs.get(i).getFirst();
            if (activeAccount.getId().equals(account.getId()))
                return i;
        }
        return -1;
    }

    public static void setOTP(Account account, String otp) {
        int index = getIndexOTP(account);
        if (index == -1) {
            activeOTPs.add(new Pair<>(account, otp));
        } else {
            activeOTPs.set(index, new Pair<>(account, otp));
        }
    }

    public static void removeOTP(Account account) {
        int index = getIndexOTP(account);
        if (index != -1)
            activeOTPs.remove(index);
    }

    public static void sendOTP(String toEmail, String otp) {
        String emailSubject = "Đặt lại mật khẩu King Cafe";
        String emailBody = "Mã OTP để thiết lập lại mật khẩu của bạn là: " + otp;
        Email.sendEmail(toEmail, emailSubject, emailBody);
    }

    public static void sendPassword(String toEmail, String password) {
        String emailSubject = "Mật khẩu mặc định King Cafe";
        String emailBody = "Không được cung cấp mật khẩu này cho bất cứ ai: " + password;
        Email.sendEmail(toEmail, emailSubject, emailBody);
    }

    public static void countdown(String otp, int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        for (int i = 0; i < activeOTPs.size(); ++i) {
            if (activeOTPs.get(i).getSecond().equals(otp)) {
                activeOTPs.remove(i);
                String[] options = new String[]{"Gửi lại", "Hủy"};
                JOptionPane.showOptionDialog(MiniSupermarket.login, "Gửi lại mã OTP khác?", "Mã OTP đã hết hạn",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                break;
            }
        }
    }
}
