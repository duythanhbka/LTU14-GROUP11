package server.ui.controller;

import client.ui.view.Profile;
import server.ui.model.InfoUpdate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class ProfileControllerSv {
    private Profile profile;
    private JButton saveButton;
    private JTextField textFieldName;
    private JRadioButton maleRadioButton;
    private JTextField textFieldAge;
    private JTextField textFieldClass;
    private JTextField textFieldHome;
    private JRadioButton femaleRadioButton;
    private JTextField textFieldAdress;
    private JTextField textFieldId;
    private JLabel title;
    private JLabel nameLabel;
    private JLabel sexLabel;
    private JLabel ageLabel;
    private JLabel classLabel;
    private JLabel homeLabel;
    private JLabel addressLabel;
    private JLabel idLabel;
    private JPanel avatarLabel;
    private JPanel blank;
    private JTextField textFieldEmail;
    private JTextField textFieldPhone;
    private JLabel emailLabel;
    private JLabel phoneLabel;
    private InfoUpdate newInfoUpdate = new InfoUpdate();
    private String titles;

    public ProfileControllerSv(String titles) throws RemoteException {
        this.titles = titles;
        initComponent();
        initListener();
        setUp();
    }

    public ProfileControllerSv() {
    }

    private void initComponent() throws RemoteException {
        profile = new Profile(titles);
        saveButton = profile.getSaveButton();
        textFieldName = profile.getTextFieldName();
        textFieldAdress = profile.getTextFieldAdress();
        textFieldAge = profile.getTextFieldAge();
        textFieldClass = profile.getTextFieldClass();
        textFieldHome = profile.getTextFieldHome();
        textFieldId = profile.getTextFieldId();
        maleRadioButton = profile.getMaleRadioButton();
        femaleRadioButton = profile.getFemaleRadioButton();
        title = profile.getTitleLabel();
        nameLabel = profile.getNameLabel();
        sexLabel = profile.getSexLabel();
        ageLabel = profile.getAgeLabel();
        classLabel = profile.getClassLabel();
        homeLabel = profile.getHomeLabel();
        addressLabel = profile.getAddressLabel();
        idLabel = profile.getIdLabel();
        avatarLabel = profile.getAvatarLabel();
        blank = profile.getBlank();
        textFieldEmail = profile.getTextFieldEmail();
        textFieldPhone = profile.getTextFieldPhone();
        emailLabel = profile.getEmailLabel();
        phoneLabel = profile.getPhoneLabel();

    }

    private void setUp(){
        title.setText("THÔNG TIN SINH VIÊN");
        nameLabel.setText("Họ và tên :");
        sexLabel.setText("Giới tính :");
        ageLabel.setText("Tuổi :");
        classLabel.setText("Lớp :");
        homeLabel.setText("Quê quán :");
        addressLabel.setText("Địa chỉ :");
        idLabel.setText("Mã số sinh viên :");
        emailLabel.setText("Email :");
        phoneLabel.setText("Số điện thoại :");
        maleRadioButton.setSelected(true);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(maleRadioButton);
        buttonGroup.add(femaleRadioButton);

    }

    private void initListener() {
        saveButton.addActionListener(new ProfileControllerSv.SaveProfile());
    }
    private class SaveProfile implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.out.println("Thông tin :" + newInfoUpdate.getName() + "\n"
                    + newInfoUpdate.getAge() + "\n"
                    + newInfoUpdate.getAddress() + "\n"
                    + newInfoUpdate.getClasses() + "\n"
                    + newInfoUpdate.getHome() + "\n"
                    + newInfoUpdate.getId() + "\n"
                    + newInfoUpdate.getPhone() + "\n"
                    + newInfoUpdate.getEmail() + "\n"
                    + newInfoUpdate.isSex() + "\n"
            );

        }
    }

    public void showProfile(){
        textFieldName.setText(newInfoUpdate.getName());
        textFieldEmail.setText(newInfoUpdate.getEmail());
        textFieldAdress.setText(newInfoUpdate.getAddress());
        textFieldAge.setText(newInfoUpdate.getAge());
        textFieldClass.setText(newInfoUpdate.getClasses());
        textFieldHome.setText(newInfoUpdate.getHome());
        textFieldId.setText(newInfoUpdate.getId());
        textFieldPhone.setText(newInfoUpdate.getPhone());
        if(newInfoUpdate.isSex()){
            maleRadioButton.setSelected(true);
        }else{
            femaleRadioButton.setSelected(true);
        }
        profile.setVisible(true);
    }
    public void upDateInfo(InfoUpdate infoUpdate){
        newInfoUpdate.setName(infoUpdate.getName());
        newInfoUpdate.setEmail(infoUpdate.getEmail());
        newInfoUpdate.setAddress(infoUpdate.getAddress());
        newInfoUpdate.setAge(infoUpdate.getAge());
        newInfoUpdate.setClasses(infoUpdate.getClasses());
        newInfoUpdate.setHome(infoUpdate.getHome());
        newInfoUpdate.setId(infoUpdate.getId());
        newInfoUpdate.setPhone(infoUpdate.getPhone());
        newInfoUpdate.setSex(infoUpdate.isSex());

    }

}
