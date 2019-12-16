package server.database;

import client.ui.model.FileInfo;

import java.sql.*;

import static server.Constant.*;

public class SQLiteDataBase {
    private static Connection connection;
    private static boolean hasData = false;

    public ResultSet display() throws SQLException, ClassNotFoundException {
        if(connection == null){
            getConnection();
        }

        Statement state = connection.createStatement();
        ResultSet res = state.executeQuery("SELECT * FROM file");
        return res;

    }

    public void deleteFile(String file_name) throws SQLException, ClassNotFoundException {
        if(connection == null){
            getConnection();
        }
        if(file_name.contains(".txt")) {
            file_name = file_name.replace(".txt", "");
        }
        Statement state = connection.createStatement();
        state.execute("DELETE FROM file\n" +
                "WHERE "+ FILE_NAME +"='" + file_name +"'");
    }

    public void updateFile(FileInfo fileInfo, String oldname) throws SQLException, ClassNotFoundException {
        if(connection == null){
            getConnection();
        }
        String filename = fileInfo.getName();
        if(filename.contains(".txt")) {
            filename = filename.replace(".txt", "");
        }
        if(oldname.contains(".txt")) {
            oldname = oldname.replace(".txt", "");
        }
        Statement state = connection.createStatement();
        state.execute("UPDATE file \n" +
                "SET "+ FILE_NAME + "='" + filename +"',\n" +
                FILE_CONTENT + "='" + fileInfo.getContent() +"'\n" +
                "WHERE " + FILE_NAME +"='"+ oldname +"'");
        System.out.println("UPDATE file \n" +
                "SET "+ FILE_NAME + "='" + filename +"',\n" +
                FILE_CONTENT + "='" + fileInfo.getContent() +"'\n" +
                "WHERE " + FILE_NAME +"='"+ oldname +"'");
    }

    private void getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:File.db");
        initialise();
    }

    private void initialise() throws SQLException, ClassNotFoundException {
        if(!hasData){
            hasData = true;
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT name  FROM sqlite_master WHERE type='table' AND name='file'");
            if(!res.next()){
                System.out.println("Building the table");
                Statement statement1 = connection.createStatement();
                statement1.execute("CREATE TABLE file (\n" +
                        "    id           INTEGER      PRIMARY KEY AUTOINCREMENT,\n" +
                        "    file_name    VARCHAR (60) NOT NULL,\n" +
                        "    file_content VARCHAR\n" +
                        ");\n");

                addFile("file1","Đây là file số 1");
                addFile("file2","Đây là file só 2");


            }
        }
    }

    public void addFile(String file_name, String file_content) throws SQLException, ClassNotFoundException {
        if(connection == null){
            getConnection();
        }
        PreparedStatement prep = connection.prepareStatement("INSERT INTO file(file_name,file_content) values(?,?)");
        prep.setString(1,file_name);
        prep.setString(2,file_content);
        prep.execute();
    }
}
