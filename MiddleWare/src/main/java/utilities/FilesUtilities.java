package utilities;

import java.io.*;
import org.apache.commons.io.FileUtils;

public class FilesUtilities {
    public static byte[] convertFileToByteArray(File file, String extension) {
        byte[] bytes = null;
        try {
            bytes = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public static void writeByteArrayToFile(File file,byte[]fileBytes){
        try {
            FileUtils.writeByteArrayToFile(file,fileBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFileExtension(File file){
        String extension="";
        int i = file.getName().lastIndexOf('.');
        if (i > 0) {
            extension = file.getName().substring(i+1);
        }
        return extension;
    }
}
