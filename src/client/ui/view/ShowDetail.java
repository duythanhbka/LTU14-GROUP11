package client.ui.view;

import javax.swing.*;

public class ShowDetail extends JFrame {
    private JButton getFile;
    private JButton createNewButton;
    private JButton editButton;
    private JPanel mPanel;

    public ShowDetail(String title){
        setSize(500,200);
        setTitle(title);
        setContentPane(mPanel);
        setLocationRelativeTo(null);
    }

    public JPanel getmPanel() {
        return mPanel;
    }

    public void setmPanel(JPanel mPanel) {
        this.mPanel = mPanel;
    }

    public JButton getGetFile() {
        return getFile;
    }

    public void setGetFile(JButton getFile) {
        this.getFile = getFile;
    }


    public JButton getCreateNewButton() {
        return createNewButton;
    }

    public void setCreateNewButton(JButton createNewButton) {
        this.createNewButton = createNewButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public void setEditButton(JButton editButton) {
        this.editButton = editButton;
    }
}
