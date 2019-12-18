package server;

import java.io.File;

public interface Constant {

     String PATH = System.getProperty("user.dir") + File.separator+"src"+File.separator+"server"+File.separator+"MyFileServer"+File.separator;
     String PATH_CLIENT = System.getProperty("user.dir")+ File.separator+"src"+File.separator+"client"+File.separator+"MyFileClient"+File.separator;
     String FILE_NAME = "file_name";
     String FILE_CONTENT = "file_content";
     String FILE_ID = "id";
}
