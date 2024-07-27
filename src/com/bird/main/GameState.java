package com.bird.main;

import com.bird.util.Constant;
import com.bird.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameState {
    private BufferedImage gameover;
    private BufferedImage start;
    //4个状态：未开始 死亡 进行 准备开始 重置（0 1 2 3 4


    private int state=0;
    public GameState(){
        gameover= GameUtil.loadBufferedImage(Constant.DEAD_IMG);
        start=GameUtil.loadBufferedImage(Constant.START_IMG);
    }
    public void draw(Graphics g){
        switch (state) {
            case 0:
                g.drawImage(start,20,50,null);
                System.out.println("开始");
                state=3;
                break;
            case 1:
                g.drawImage(gameover, 20, 50, null);
                System.out.println("嗝儿屁");
                state=4;
                break;

        }
    }
    public void restant(){
        state=2;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
