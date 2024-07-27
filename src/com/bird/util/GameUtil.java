package com.bird.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class GameUtil {
    public static BufferedImage loadBufferedImage(String imagPath){//图片载入内存
        try {
            return ImageIO.read(new FileInputStream(imagPath));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
