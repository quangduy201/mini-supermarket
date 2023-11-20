package mini_supermarket.GUI.component;

import com.formdev.flatlaf.icons.FlatCheckBoxIcon;
import mini_supermarket.BLL.DecentralizationBLL;
import mini_supermarket.BLL.FunctionBLL;
import mini_supermarket.BLL.ModuleBLL;
import mini_supermarket.BLL.RoleBLL;
import mini_supermarket.DTO.*;
import mini_supermarket.DTO.Module;
import mini_supermarket.utils.I18n;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.__;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DecentralizationTable extends JScrollPane {
    protected final RoundPanel panel;
    protected final RoleBLL roleBLL;
    protected final ModuleBLL moduleBLL;
    protected final FunctionBLL functionBLL;
    protected final DecentralizationBLL decentralizationBLL;
    protected final List<Module> modules;
    protected final List<Function> functions;
    protected final Map<Pair<Integer, Integer>, Pair<Module, Function>> table;
    protected final JCheckBox[][] checkboxes;
    protected Role role;

    public DecentralizationTable() {
        panel = new RoundPanel();
        roleBLL = new RoleBLL();
        moduleBLL = new ModuleBLL();
        functionBLL = new FunctionBLL();
        decentralizationBLL = new DecentralizationBLL();
        modules = moduleBLL.findAll();
        functions = functionBLL.findAll();
        table = new HashMap<>();
        checkboxes = new JCheckBox[modules.size()][functions.size()];

        panel.setLayout(new GridLayout(modules.size() + 1, functions.size() + 1));

        int i, j;
        panel.add(new JLabel());
        for (i = 0; i < functions.size(); i++) {
            String functionName = I18n.get("frame", "function." + functions.get(i).getName());
            panel.add(new JLabel(functionName));
        }
        for (i = 0; i < modules.size(); i++) {
            String moduleName = I18n.get("frame", "main.module." + modules.get(i).getName());
            panel.add(new JLabel(moduleName));
            for (j = 0; j < functions.size(); j++) {
                checkboxes[i][j] = new JCheckBox();
                checkboxes[i][j].setVisible(false);
                checkboxes[i][j].setEnabled(false);
                panel.add(checkboxes[i][j]);
                table.put(new Pair<>(i, j), new Pair<>(modules.get(i), functions.get(j)));
            }
        }
        Role role = roleBLL.doSomethingOn(0L, r -> r);
        List<Decentralization> decentralizations = decentralizationBLL.findBy(__.DECENTRALIZATION.ROLE, role);
        for (Decentralization decentralization : decentralizations) {
            Long moduleId = decentralization.getId().getModule().getId();
            Long functionId = decentralization.getId().getFunction().getId();
            checkboxes[(int) (moduleId - 1)][(int) (functionId - 1)].setVisible(true);
        }
        handleCheckBoxes();

        this.setViewportView(panel);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
        List<Decentralization> decentralizations = decentralizationBLL.findBy(__.DECENTRALIZATION.ROLE, role);
        for (Decentralization decentralization : decentralizations) {
            Long moduleId = decentralization.getId().getModule().getId();
            Long functionId = decentralization.getId().getFunction().getId();
            checkboxes[(int) (moduleId - 1)][(int) (functionId - 1)].setSelected(true);
        }
        for (int i = 0; i < modules.size(); i++) {
            boolean isSelected = checkboxes[i][0].isSelected();
            checkboxes[i][0].setEnabled(true);
            for (int j = 1; j < functions.size(); j++) {
                if (isSelected)
                    checkboxes[i][j].setEnabled(true);
            }
        }
        checkboxes[14][0].setVisible(role.getId() != 1);
    }

    public void refreshTable() {
        for (JCheckBox[] row : checkboxes) {
            for (JCheckBox checkBox : row) {
                checkBox.setSelected(false);
                checkBox.setEnabled(false);
            }
        }
    }

    public void handleCheckBoxes() {
        for (int i = 0; i < modules.size(); i++) {
            int rowIndex = i;
            checkboxes[i][0].addActionListener(e -> {
                boolean isSelected = checkboxes[rowIndex][0].isSelected();
                for (int j = 1; j < functions.size(); j++)
                    checkboxes[rowIndex][j].setEnabled(isSelected);
            });
            for (int j = 0; j < functions.size(); j++) {
                int colIndex = j;
                checkboxes[i][j].addActionListener(e -> {
                    Pair<Module, Function> ids = table.get(new Pair<>(rowIndex, colIndex));
                    DecentralizationId id = new DecentralizationId(role, ids.getFirst(), ids.getSecond());
                    Decentralization decentralization = new Decentralization(id);
                    if (checkboxes[rowIndex][colIndex].isSelected())
                        decentralizationBLL.add(decentralization);
                    else
                        decentralizationBLL.delete(decentralization);
                });
            }
        }
    }
}
