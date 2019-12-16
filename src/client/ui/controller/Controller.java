package client.ui.controller;

import client.ui.model.FileInfo;
import client.ui.view.CreateFile;
import client.ui.view.EditFile;
import client.ui.view.ShowDetail;
import interfaces.FileInterface;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import static server.Constant.PATH_CLIENT;

public class Controller {

    private static final String HOST = "localHost";
    private static final int PORT = 1097;
    private static Registry registry;

    private CreateFile createFile;
    private JFormattedTextField textField_name;
    private JFormattedTextField textField_content;
    private JButton btn_ok;

    private ShowDetail showDetail;
    private JButton getFile;
    private JButton createNewButton;
    private JButton editButton;

    private EditFile editFile;
    private JFormattedTextField editText_name;
    private JFormattedTextField editText_content;
    private JButton btn_update;
    private JButton btn_delete;

    String oldname,oldcontent;

    public Controller() throws RemoteException {

        initComponent();
        initListener();
    }

    private void initComponent() throws RemoteException {
        showDetail = new ShowDetail("Menu");
        getFile = showDetail.getGetFile();
        createNewButton = showDetail.getCreateNewButton();
        editButton = showDetail.getEditButton();

        createFile = new CreateFile("Tạo file mới");
        textField_name = createFile.getTextField_name();
        textField_content = createFile.getTextField_content();
        btn_ok  = createFile.getBtn_ok();

        editFile = new EditFile("Chỉnh sửa file");
        editText_name = editFile.getEditText_name();
        editText_content = editFile.getEditText_content();
        btn_update = editFile.getUpdateButton();
        btn_delete = editFile.getDeleteButton();

        registry = LocateRegistry.getRegistry(HOST, PORT);


    }

    private void setUp(){

    }

    private void initListener(){
        getFile.addActionListener(new GetInfo());
        btn_ok.addActionListener(new SendNewFile());
        createNewButton.addActionListener(new CreateNewFile());
        editButton.addActionListener(new EditingFile());
        btn_update.addActionListener(new SendEdit());
        btn_delete.addActionListener(new DeleteFile());
    }

    private class GetInfo implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                FileInterface service = (FileInterface) registry.lookup(FileInterface.class.getSimpleName());
                List<FileInfo> infoList = service.getInfo();
                getInfo(infoList);

            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                showFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private class CreateNewFile implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            showCreate();
        }
    }
    private class SendNewFile implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            FileInfo fileInfo = new FileInfo();
            fileInfo.setName(textField_name.getText());
            fileInfo.setContent(textField_content.getText());
            String path = PATH_CLIENT + fileInfo.getName();
            File file = new File(path);
            file.delete();
            try {
                FileInterface service = (FileInterface) registry.lookup(FileInterface.class.getSimpleName());
                service.createInfo(fileInfo);
                String message =  service.showMessage();
                List<FileInfo> infoList = service.getInfo();
                getInfo(infoList);
                int input = JOptionPane.showConfirmDialog(null,message, "Thông báo",JOptionPane.CLOSED_OPTION);
                if(input == JOptionPane.OK_OPTION){
                    showFile();
                    hideCreate();
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    private class EditingFile implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                showFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private class SendEdit implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            FileInfo fileInfo = new FileInfo();
            fileInfo.setName(editText_name.getText());
            fileInfo.setContent(editText_content.getText());
            String path = PATH_CLIENT + oldname;
            File file = new File(path);
            file.delete();
            try {
                FileInterface service = (FileInterface) registry.lookup(FileInterface.class.getSimpleName());
                service.updateInfo(fileInfo,oldname);
                List<FileInfo> infoList = service.getInfo();
                String message = service.showMessage();
                getInfo(infoList);
                int input = JOptionPane.showConfirmDialog(null,message, "Thông báo",JOptionPane.CLOSED_OPTION);
                if(input == JOptionPane.OK_OPTION){
                    showFile();
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private class DeleteFile implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            FileInfo fileInfo = new FileInfo();
            fileInfo.setName(editText_name.getText());
            fileInfo.setContent(editText_content.getText());
            String path = PATH_CLIENT + fileInfo.getName();
            File file = new File(path);
            file.delete();

            System.out.println(path);

            try {
                FileInterface service = (FileInterface) registry.lookup(FileInterface.class.getSimpleName());
                service.deleteInfo(fileInfo);
                List<FileInfo> infoList = service.getInfo();
                String message = service.showMessage();
                getInfo(infoList);
                int input = JOptionPane.showConfirmDialog(null,message, "Thông báo",JOptionPane.CLOSED_OPTION);
                if(input == JOptionPane.OK_OPTION){
                    showFile();
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void showDetail(){
        showDetail.setVisible(true);
    }

    public void showEditingFile(){
        editFile.setVisible(true);
    }

    public void showCreate(){
        textField_name.setText("");
        textField_content.setText("");
        createFile.setVisible(true);
    }

    public void hideCreate(){
        createFile.setVisible(false);
    }

    public void hideEdting(){
        editFile.setVisible(false);
    }

    public void showFile() throws IOException {
        final JFileChooser fileChooser = new JFileChooser(new File(PATH_CLIENT));
        int returnVal =  fileChooser.showOpenDialog(showDetail);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            showEditingFile();
            File file = fileChooser.getSelectedFile();
            oldname = file.getName();
            editText_name.setText(file.getName());
            List<String> content = Files.readAllLines(Paths.get(file.getPath()), StandardCharsets.UTF_8);
            StringBuilder stringBuilder = new StringBuilder();
            for(String value : content){
                stringBuilder.append(value);
            }
            editText_content.setText(stringBuilder.toString());
        }
    }

    public void getInfo(List<FileInfo> infoList) throws IOException {
        for(int i = 0; i<infoList.size();i++){
            File file = new File(PATH_CLIENT + infoList.get(i).getName()+ ".txt");
            file.createNewFile();
            Files.write(Paths.get(PATH_CLIENT + file.getName()),
                    Collections.singleton(infoList.get(i).getContent()),
                    StandardCharsets.UTF_8);
        }
    }
}
