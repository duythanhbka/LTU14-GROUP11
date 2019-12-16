package interfaces;

import client.ui.model.FileInfo;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface FileInterface extends Remote {
    List<FileInfo> getInfo() throws RemoteException, SQLException, ClassNotFoundException;
    void updateInfo(FileInfo info, String oldname) throws IOException, SQLException, ClassNotFoundException;
    void createInfo(FileInfo info)  throws RemoteException, SQLException, ClassNotFoundException;
    void deleteInfo(FileInfo info) throws RemoteException, SQLException, ClassNotFoundException;
    String showMessage() throws RemoteException, SQLException, ClassNotFoundException;
}
