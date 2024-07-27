package com.bird.main;

import com.bird.util.Constant;
import com.bird.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameBackGround {
    //背景图
    private BufferedImage bkimg;//带缓冲区图像类
    private boolean state=true;

    public GameBackGround(){
        bkimg= GameUtil.loadBufferedImage(Constant.BK_IMG_OATH);
    }

    //自定义绘制方法
    public void draw (Graphics g) {

        if (state) {
            int height = bkimg.getHeight();
            int weight = bkimg.getWidth();
            g.drawImage(bkimg, 0, 0, null);
            //循环次数
            int count = Constant.FRAM_WIDTH / weight + 1;
            for (int i = 0; i < count; i++) {
                g.drawImage(bkimg, weight * i, Constant.FRAM_HEIGTH - height, null);
            }
        }
    }
    public void setState(boolean state) {
        this.state = state;
    }
}
