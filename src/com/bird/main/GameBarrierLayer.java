package com.bird.main;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//障碍物层
public class GameBarrierLayer {
    private boolean state=false;
    private GameTime gameTime;
    private Random random=new Random();
    private List<Barrier>barriers;

    public GameBarrierLayer(){
        barriers = new ArrayList<>();
        gameTime=new GameTime();
    }
    //绘制障碍物
    public void draw(Graphics g,Bird bird,GameState gamestate){
        for(int i=0;i<barriers.size();i++){
            Barrier barrier=barriers.get(i);
            if(barrier.isVisible()){
                barrier.draw(g);
            }
            else{
                Barrier remove=barriers.remove(i);
                Barrierpool.setPool(remove);
                i--;
            }
        }
        collideBird(bird,gamestate);
        logic(g);
    }


    public void logic(Graphics g){
        if(barriers.size()==0){
            ran();
            gameTime.begin();
            insert(550,0,numberTop,0);
            insert(550,670-numberDown,numberDown,2);
        }else{
            //判断最后一个障碍物是否进入到屏幕内
            long differ=gameTime.differ();
            g.setColor(Color.BLACK);
            g.setFont(new Font("微软雅黑",1,20));
            g.drawString("坚持了："+differ+"秒",30,50);
            Barrier last=barriers.get(barriers.size()-1);
            if(last.isInFrame()){
                ran();

                insert(550,0,numberTop,0);
                insert(550,670-numberDown,numberDown,2);
            }
        }
    }
    //用于从池中获取对象，并把参数封装成barrier，存入barriers数组中
    public void insert (int x,int y,int num,int type){
        Barrier top =Barrierpool.getPool();
        top.setX(x);
        top.setY(y);
        top.setHeight(num);
        top.setType(type);
        top.setVisible(true);
        barriers.add(top);
    }
    //上方障碍物高度
    private int numberTop;
    //下方障碍物高度
    private int numberDown;
    //中间的
    public void ran(){
        numberTop=random.nextInt(500)+100;
        numberDown=random.nextInt(500)+100;
        //如果管道重合重新随机
        if(numberTop+numberDown>=580){
            ran();
        }
    }
    //判断障碍物和小鸟发生碰撞
    public boolean collideBird(Bird bird,GameState gamestate){
        for (int i=0;i<barriers.size();i++){
            Barrier barrier=barriers.get(i);
            //判断矩形是否相交
            if(barrier.getRect().intersects(bird.getRect())){
                System.out.println("相撞");
                gamestate.setState(1);
            }
        }
        return false;
    }
//清空池子
    public void restant(){
        barriers.clear();
    }
}
