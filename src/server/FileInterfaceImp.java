package server;

import client.ui.model.FileInfo;
import interfaces.FileInterface;
import server.database.SQLiteDataBase;
import server.model.FileInfoSever;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static server.Constant.*;

public class FileInterfaceImp extends UnicastRemoteObject implements FileInterface {

    String showMessage = "";

    protected FileInterfaceImp() throws RemoteException, SQLException, ClassNotFoundException {
    }

    @Override
    public synchronized List<FileInfo> getInfo() throws RemoteException, SQLException, ClassNotFoundException {
        List<FileInfo> infoList = new ArrayList<>();
        SQLiteDataBase sqLiteDataBase = new SQLiteDataBase();
        ResultSet rs = sqLiteDataBase.display();
        while (rs.next()){
            FileInfo fileInfo = new FileInfo(rs.getString(FILE_NAME),rs.getString(FILE_CONTENT),rs.getInt(FILE_ID));
            infoList.add(fileInfo);
        }
        return infoList;
    }

    @Override
    public void updateInfo(FileInfo info,String oldname) throws IOException, SQLException, ClassNotFoundException {
        SQLiteDataBase sqLiteDataBase = new SQLiteDataBase();
        sqLiteDataBase.updateFile(info,oldname);
        String filename = info.getName();
        if(filename.contains(".txt")) {
            filename = filename.replace(".txt", "");
        }
        if(oldname.contains(".txt")) {
            oldname = oldname.replace(".txt", "");
        }

        String path = PATH + oldname+".txt";
        String pathnew = PATH + filename+".txt";
        File file = new File(path);
        File newfile = new File(pathnew);
        file.delete();
        newfile.createNewFile();
        Files.write(Paths.get(PATH +newfile.getName()),
                Collections.singleton(info.getContent()),
                StandardCharsets.UTF_8);
        showMessage = "Cập nhật thành công";
    }

    @Override
    public void createInfo(FileInfo info) throws RemoteException, SQLException, ClassNotFoundException {
        List<FileInfo> infoList = new ArrayList<>();
        SQLiteDataBase sqLiteDataBase = new SQLiteDataBase();
        ResultSet rs = sqLiteDataBase.display();
        while (rs.next()){
            FileInfo fileInfo = new FileInfo(rs.getString(FILE_NAME),rs.getString(FILE_CONTENT),rs.getInt(FILE_ID));
            infoList.add(fileInfo);
        }
        for(int i =0; i<infoList.size(); i++){
            if(infoList.get(i).getName().equals(info.getName())){
               showMessage = "File này đã tồn tại";
            }else{
                try {
                    File file = new File(PATH+info.getName()+".txt");
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                    Files.write(Paths.get(PATH +file.getName()),
                            Collections.singleton(info.getContent()),
                            StandardCharsets.UTF_8);
                    showMessage = "Tạo file mới thành công";
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        if(showMessage.equals("Tạo file mới thành công")) {
            sqLiteDataBase.addFile(info.getName(), info.getContent());
        }
    }

    @Override
    public void deleteInfo(FileInfo info) throws RemoteException, SQLException, ClassNotFoundException {
        SQLiteDataBase sqLiteDataBase = new SQLiteDataBase();
        sqLiteDataBase.deleteFile(info.getName());
        String filename = info.getName();
        if(filename.contains(".txt")) {
            filename = filename.replace(".txt", "");
        }
        String path = PATH + filename+".txt";
        File file = new File(path);
        file.delete();
        showMessage = "Đã xóa file thành công";
    }

    @Override
    public String showMessage() throws RemoteException, SQLException, ClassNotFoundException {
        return showMessage;
    }

}
