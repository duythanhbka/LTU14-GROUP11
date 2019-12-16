package client.ui.view;

import javax.swing.*;

public class EditFile extends JFrame {
    private JPanel myPanel;
    private JFormattedTextField editText_name;
    private JFormattedTextField editText_content;
    private JButton updateButton;
    private JButton deleteButton;

    public EditFile(String title){
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

    public JFormattedTextField getEditText_name() {
        return editText_name;
    }

    public void setEditText_name(JFormattedTextField editText_name) {
        this.editText_name = editText_name;
    }

    public JFormattedTextField getEditText_content() {
        return editText_content;
    }

    public void setEditText_content(JFormattedTextField editText_content) {
        this.editText_content = editText_content;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public void setUpdateButton(JButton updateButton) {
        this.updateButton = updateButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }
}
