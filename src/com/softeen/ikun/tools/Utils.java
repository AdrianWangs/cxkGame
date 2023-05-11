package com.softeen.ikun.tools;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

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

    public static ImageIcon loadGif(String gifUrl){

        URL url = Utils.class.getResource("/img/"+gifUrl);

        ImageIcon imageIcon = new ImageIcon(url);

        return imageIcon;
    }



    public static FileInputStream getFileStream(String fileName) throws IOException {
        return new FileInputStream(Utils.class.getResource("/music/"+fileName).getFile());
    }


    public static int randNum(int min, int max){
        Random random = new Random();
        return random.nextInt(max-min+1)+min;
    }

    public static String getVideoUrl(String videoUrl){
        URL url = Utils.class.getResource("/video/"+videoUrl);
        return url.toString();
    }


}
