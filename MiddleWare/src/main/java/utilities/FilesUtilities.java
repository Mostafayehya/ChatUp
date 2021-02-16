package utilities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import javafx.scene.image.Image;

public class FilesUtilities {
    public static byte[] convertImageFileToByteArray(File file, String extension) {
        byte[] bytes = null;
        BufferedImage bi = null;
        ByteArrayOutputStream baos = null;
        try {
            //read image from file system
            bi = ImageIO.read(file);
            baos = new ByteArrayOutputStream();
            ImageIO.write(bi, extension, baos);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        // convert BufferedImage to byte[]
        if (baos != null) {
            bytes = baos.toByteArray();
        }
        return bytes;
    }

    public static BufferedImage convertImageByteArrayToBufferedImage(byte[] imageBytes) {
        InputStream is = new ByteArrayInputStream(imageBytes);
        try {
            return ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Image convertByteArrayToImage(byte[]imageBytes){
        InputStream is =new ByteArrayInputStream(imageBytes);
        return new Image(is);
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
