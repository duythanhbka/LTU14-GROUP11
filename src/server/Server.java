package server;

import client.ui.model.FileInfo;
import interfaces.FileInterface;
import server.database.SQLiteDataBase;
import server.model.FileInfoSever;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import static server.Constant.*;

public class Server {
    private static final int PORT = 1097;
    private static Registry registry;


    public static void startRegistry() throws RemoteException {

        // Tạo một bộ đăng ký (Registry) tại Server.
        registry =  LocateRegistry.createRegistry(PORT);
    }
    public static void registerObject(String name, Remote remoteObj)
            throws RemoteException, AlreadyBoundException {
        // Đăng ký đối tượng vào bộ đăng ký.
        // Nó được gắn với cái tên nào đó.
        // Client sẽ tìm trên bộ đăng ký với tên này để có thể gọi đối tượng.


        registry.bind(name, remoteObj);
        System.out.println("Registered: " + name + " -> "
                + remoteObj.getClass().getName() + "[" + remoteObj + "]");

    }
    public static void main(String[] args) throws Exception {
        System.out.println("Server starting...");
        preparedData();

        startRegistry();
        registerObject(FileInterface.class.getSimpleName(), new FileInterfaceImp());

        // Server đã được start, và đang lắng nghe các request từ Client.
        System.out.println("Server started!");

    }

    private static void preparedData() throws SQLException, ClassNotFoundException, IOException {
        SQLiteDataBase sqLiteDataBase = new SQLiteDataBase();
        ResultSet rs;

        List<FileInfo> infoList = new ArrayList<>();
        rs = sqLiteDataBase.display();
        while (rs.next()){
            FileInfo fileInfo = new FileInfo(rs.getString(FILE_NAME),rs.getString(FILE_CONTENT));
            infoList.add(fileInfo);
        }
        for(int i = 0; i<infoList.size(); i++) {
            File file = new File(PATH + infoList.get(i).getName()+ ".txt");
            file.createNewFile();
            Files.write(Paths.get(PATH + file.getName()),
                    Collections.singleton(infoList.get(i).getContent()),
                    StandardCharsets.UTF_8);
        }
    }
}
