package client.ui.view;

import javax.swing.*;

public class CreateFile extends JFrame {
    private JPanel myPanel;
    private JFormattedTextField textField_name;
    private JFormattedTextField textField_content;
    private JButton btn_ok;

    public CreateFile(String title){
        setSize(500,300);
        setTitle(title);
        setContentPane(myPanel);
        setLocationRelativeTo(null);
    }

    public JPanel getMyPanel() {
        return myPanel;
    }

    public void setMyPanel(JPanel myPanel) {
        this.myPanel = myPanel;
    }

    public JFormattedTextField getTextField_name() {
        return textField_name;
    }

    public void setTextField_name(JFormattedTextField textField_name) {
        this.textField_name = textField_name;
    }

    public JFormattedTextField getTextField_content() {
        return textField_content;
    }

    public void setTextField_content(JFormattedTextField textField_content) {
        this.textField_content = textField_content;
    }

    public JButton getBtn_ok() {
        return btn_ok;
    }

    public void setBtn_ok(JButton btn_ok) {
        this.btn_ok = btn_ok;
    }
}
