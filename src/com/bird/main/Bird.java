package com.bird.main;

import static com.bird.util.Constant.*;
import com.bird.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bird {
    //小鸟加速度
    private int acceleration;
    //小鸟矩形对象
    private Rectangle rect;
    //小鸟的生命
    public boolean life=false;
    //存放小鸟图片
    private BufferedImage[] images;
    public static final int BIRD_IMG_COUNT=3;
    //鸟的状态
    private int state;
    public static final int STATE_NORMAR=0;
    public static final int STATE_UP=1;
    public static final int STATE_DOWN=2;
    //小鸟的位置
    private int x=200,y=200;
    //小鸟移动方向 上下
    private boolean up=false,down=false;
    //小鸟移动速度
    private int speed=4;
    //构造方法中对资源初始化
    public Bird(){
        images=new BufferedImage[BIRD_IMG_COUNT];
        for(int i=0;i<BIRD_IMG_COUNT;i++){
            images[i]=GameUtil.loadBufferedImage(BIRD_IMG[i]);
        }
        int w=images[0].getWidth();
        int h=images[0].getHeight();
        rect=new Rectangle(w,h);
    }

    //描绘小鸟
    public void draw(Graphics g){
        if(life) {
            flyLogic();
            g.drawImage(images[state], x, y, null);
            //绘制小鸟矩形
           // g.drawRect(x, y, (int) rect.getWidth(), rect.height);
            rect.x = this.x;
            rect.y = this.y;
        }
    }

    //控制移动方向

    public void flyLogic(){
        if(up){
            acceleration--;
            y+=acceleration;
            if(acceleration<-10){
                acceleration=-10;
            }
            if(y<30){
                y=30;
                acceleration=0;
            }
        }
        if(!up){
            acceleration++;
            y+=acceleration;
            if(acceleration>10){
                acceleration=10;
            }
            if(y>630){
                y=630;
                acceleration=0;
            }

        }
    }
    public void fly(int fly)
    {
        switch(fly){
            case 1:
                state=1;
                up=true;
                break;
            case 5:
                state=2;
                up=false;
                break;
        }
    }
    public Rectangle getRect() {
        return rect;
    }
    //重新绘制小鸟的位置
    public void restartDraw(){
        life=true;
        x=200;
        y=200;
    }

    public void setLife(boolean life) {
        this.life = life;
    }
}
