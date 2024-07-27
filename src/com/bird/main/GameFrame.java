package com.bird.main;

import static com.bird.util.Constant.*;//静态导包

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;


public class GameFrame extends Frame {
    //gameover
    private GameState gamestate;
    //实例化背景类
    private GameBackGround gameBackGround;
    //实例化鸟类
    private Bird bird;
    //实例化GameBarrierLayer；
    private GameBarrierLayer gameBarrierLayer;
    //存放图片
    private BufferedImage buffimg=new BufferedImage(FRAM_WIDTH,FRAM_HEIGTH,BufferedImage.TYPE_4BYTE_ABGR);//8 位 RGBA 颜色分量的图像
    //初始化参数
    public GameFrame(){
        //窗口
     //   JFrame frame=new JFrame(FRAM_TITLE);
        setVisible(true);
        setSize(FRAM_WIDTH,FRAM_HEIGTH);
        setLocation(FRAM_x,FRAM_y);
        setResizable(false);
        setTitle(FRAM_TITLE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }//窗口关闭
        });
        //画鸟
        initGmeg();
        new run().start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                add(e);//按下未释放
            }

            @Override
            public void keyReleased(KeyEvent e) {
                minu(e);//抬起
            }
        });
    }
    //初始化对象
    public void initGmeg(){
        gameBackGround=new GameBackGround();
        bird=new Bird();
        gameBarrierLayer=new GameBarrierLayer();
        gamestate =new GameState();
    }

    class run extends Thread{
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(33);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
//绘制

    public void update(Graphics g){
        Graphics graphics = buffimg.getGraphics();
        if(gamestate.getState()==0) {
            gameBackGround.draw(graphics);
            bird.draw(graphics);
            gamestate.draw(graphics);
            gameBackGround.setState(false);
        }
        else if(gamestate.getState()==2){
            gameBackGround.draw(graphics);
            bird.draw(graphics);
            gameBarrierLayer.draw(graphics, bird,gamestate);
        }
        else if(gamestate.getState()==1)
        {
            gamestate.draw(graphics);
        }

        g.drawImage(buffimg, 0, 0, null);


    }
//按键-键盘监听器
    public void add(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:

                bird.fly(1);
                break;

            case KeyEvent.VK_SPACE:
                if(gamestate.getState()==3) {
                    gameBackGround.setState(true);
                    bird.setLife(true);
                    gamestate.setState(2);
                }
                else if(gamestate.getState()==4){
                    restart();
                }
                break;

        }
    }
    //重置游戏

    public void restart(){
        gameBarrierLayer.restant();
        bird.restartDraw();
        gamestate.restant();
    }
    public void minu(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                bird.fly(5);
                break;

        }
    }

}
