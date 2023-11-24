package mini_supermarket.GUI.component.Statisic.blankchart.main;

import mini_supermarket.DTO.Account;
import mini_supermarket.GUI.component.RoundPanel;
import mini_supermarket.GUI.layout.LeftRightLayout;
import mini_supermarket.utils.Resource;

import javax.swing.*;

public class MainUserInfo extends RoundPanel {
    private final LeftRightLayout infoLayout;
    private final JLabel avatar;
    private final JLabel staffName;
    private final JLabel roleName;

    public MainUserInfo(Account account) {
        infoLayout = new LeftRightLayout(75, true);
        Icon icon = Resource.loadSmoothIcon("img/avatar/bell-boy.png", 75);
        avatar = new JLabel(icon);
        staffName = new JLabel(account.getStaff().getName());
        roleName = new JLabel(account.getRole().getName());

        setBackground(null);

        this.add(avatar);
        this.add(staffName);
        this.add(roleName);
    }

    public JLabel getAvatar() {
        return avatar;
    }

    public JLabel getStaffName() {
        return staffName;
    }

    public JLabel getRoleName() {
        return roleName;
    }
}
