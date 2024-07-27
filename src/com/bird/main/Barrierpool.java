package com.bird.main;

import java.util.ArrayList;
import java.util.List;

//障碍物对象池
public class Barrierpool {
    //管理池中所有对象的容器
    private  static List<Barrier>pool=new ArrayList<>();
    //池中初始的对象个数
    public static final int initCount=16;
    //对象池中最大个数
    public static final int maxCount=20;

    static {
        //初始化池中对象
        for(int i=0;i<initCount;i++){
            pool.add(new Barrier());

        }
    }
    //池中获取对象
    public static Barrier getPool(){
        int size = pool.size();
        //池中有对象可拿
        if(size>0){
            //移除并返回对象
            System.out.println("拿走一个");
            return pool.remove(size-1);

        }
        else{
            //池中没有对象了，new
            System.out.println("新的对象");
            return new Barrier();
        }
    }

    //将对象归还容器
    public static void setPool(Barrier barrier){
        if(pool.size()<maxCount){
            pool.add(barrier);
            System.out.println("容器归还了");
        }
    }
}
