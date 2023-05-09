package com.softeen.ikun.tools;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {


    public static BufferedImage loading(String imgUrl){

        //读取图片的资源
        URL url = Utils.class.getResource("/img/"+imgUrl);

        BufferedImage image = null;

        try {
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;

    }


    public static FileInputStream getFileStream(String fileName) throws IOException {
        return new FileInputStream(Utils.class.getResource("/music/"+fileName).getFile());
    }

}
