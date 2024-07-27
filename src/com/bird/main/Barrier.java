package com.bird.main;

import com.bird.util.Constant;
import com.bird.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.security.PublicKey;

//障碍物
public class Barrier {
    //矩形参数
    private Rectangle rect;
    //障碍物移动速度
    private int speed = 3;
    //障碍物图
    private static BufferedImage[] imgs;
    //障碍物状态
    private boolean visible;

    static{
        final int COUNT =3;
        //类加载将图片初始化
        imgs=new BufferedImage[COUNT];
        for(int i=0;i<COUNT;i++){
            imgs[i]= GameUtil.loadBufferedImage(Constant.BARRIER_IMG_PATH[i]);
        }
    }
    //位置
    private int x,y;
    private  int width,height;
    //类型
    private int type;
    public static final int TYPE_TOP_NOMAL=0;
    public static final int TYPE_BOTTOM_NOMAL=2;
    public static final int TYPE_HOVER_NOMAL=4;
//获得障碍物宽度高度
    public  static final int BARRIRE_WIDTH=imgs[0].getWidth();
    public  static final int BARRIRE_HEIGHT=imgs[0].getHeight();
    public  static final int BARRIRE_HEAD_WIDTH=imgs[1].getWidth();
    public  static final int BARRIRE_HEAD_HEIGHT=imgs[1].getHeight();

    public Barrier(){
        rect=new Rectangle();
    }

    public Barrier(int x, int y,int height, int type) {
        this.x = x;
        this.y = y;
        this.width = BARRIRE_WIDTH;
        this.height = height;
        this.type = type;
    }
    //根基不同的类型绘制障碍物
    public void draw(Graphics g) {
        switch (type) {
            case TYPE_TOP_NOMAL: drawTopMormal(g);break;
            case TYPE_BOTTOM_NOMAL: drawNomalTop(g);break;

        }
    }
    //绘制从上向下的障碍物
    private  void drawTopMormal(Graphics g){
        //求出所需的障碍物的块数
        int count =(height-BARRIRE_HEAD_HEIGHT)/BARRIRE_HEIGHT+1;
        //for循环绘制障碍物
        for(int i=0;i<count;i++){
            g.drawImage(imgs[0],x,y+i*BARRIRE_HEIGHT,null);

        }
        //绘制头
        int y=height-BARRIRE_HEAD_HEIGHT;
        g.drawImage(imgs[2], x-(BARRIRE_HEAD_WIDTH-BARRIRE_WIDTH)/2,y,null);
        x-=speed;
        //出屏幕
        if(x<-70){
            visible=false;
        }
        rect(g);
    }
    //从下向上的障碍物
    private void drawNomalTop(Graphics g){
        //求出所需要的障碍物的块数
        int count =height/BARRIRE_HEIGHT+1;
        //for循环绘制障碍物
        for(int i=0;i<count;i++){
            g.drawImage(imgs[0],x,Constant.FRAM_HEIGTH-i*BARRIRE_HEIGHT,null);
        }
      //  //头
        int y=Constant.FRAM_HEIGTH-height;
        g.drawImage(imgs[1],x-(BARRIRE_HEAD_WIDTH-BARRIRE_WIDTH)/2,y,null);
        x-=speed;

        if(x<-70){
            visible=false;
        }
        rect(g);
    }
    //绘制障碍物碰撞矩形
    public void rect(Graphics g){
        int x1=this.x;
        int y1=this.y;
        int w1=imgs[0].getWidth();
        g.setColor(Color.BLUE);
      //  g.drawRect(x1,y1,w1,height);

        setRecyangle(x1,y1,w1,height);
    }
    //障碍物矩形参数
    public void setRecyangle(int x,int y,int width,int height){
        rect.x=x;
        rect.y=y;
        rect.width=width;
        rect.height=height;
    }
    //判断什么时候绘制下一组障碍物
    public boolean isInFrame(){
        return 500-x>150;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Rectangle getRect() {
        return rect;
    }
}
