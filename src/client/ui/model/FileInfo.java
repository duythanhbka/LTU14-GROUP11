package client.ui.model;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class FileInfo implements Serializable {
    String name, content;
    int id;

    public FileInfo() {
    }

    public FileInfo(String name, String content) {
        this.name = name;
        this.content = content;
    }


    public FileInfo(String name, String content, int id) {
        this.name = name;
        this.content = content;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
