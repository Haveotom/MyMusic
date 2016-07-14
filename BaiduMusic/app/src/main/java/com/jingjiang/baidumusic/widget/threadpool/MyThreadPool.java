package com.jingjiang.baidumusic.widget.threadpool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by dllo on 16/7/7.
 */
public class MyThreadPool {
    private static MyThreadPool ourInstance;

    //线程池对象
    private ThreadPoolExecutor threadPoolExecutor;

    public static MyThreadPool getOurInstance() {
        if (ourInstance == null) {
            synchronized (MyThreadPool.class) {
                if (ourInstance == null) {
                    ourInstance = new MyThreadPool();
                }
            }
        }
        return ourInstance;
    }

    public MyThreadPool() {
        //对线程池对象初始化
        //使用5个参数的构造方法
        //int corePoolSize:核心线程数量
        //核心线程会随着线程池的创建,同时创建出来
        //如果没有任务,核心线程就会等待,并不会销毁
        //只有核心线程,才能执行任务
        //通常,核心线程数 是CPU核心数 + 1
        //int maximumPoolSize : 最大线程数
        //线程池中所能容纳的线程数量的上线
        //它一定大于等于核心线程数,
        //超过核心线程数的线程 叫做工作线程
        //工作线程并不能执行任务,它们会等待核心线程执行完毕
        //再开始执行,所有的工作线程,满足FIFO的策略
        //工作线程是有可能被销毁的
        //当工作线程没有任务可做的时候,同时
        //时间超过了keepAliveTime 所规定的时间
        //就会把该工作线程销毁掉
        //long keepAliveTime : 保持活动时间
        //确定工作线程 没有工作时还能存活的最大时间
        //TimeUnit unit : 用来确定keepAliveTime的单位
        //最大的是天,最小的是纳秒
        //BlockingQueue<Runnable> workQueue
        //任务队列,当线程池内的线程数达到线程池规定的最大
        //线程数时,还继续向线程池中提交任务
        //这些任务就会放进任务队列,BlockingQueue是一个
        //接口对象,我们在使用的时候,需要使用实现该接口的
        //实现类,Android为我们封装了几个常用的任务队列
        //常用的任务队列有:
        //1.LinkedBlockingQueue:无界任务队列
        //2.SynchronousQueue:直接提交的任务队列
        //  该任务队列不能储存任务,所以一般配合线程池
        //  的最大线程数 无上限(int的最大值)
        //  的这种情况使用
        //3.DelayQueue:等待队列,它会让工作队列里的任务
        //  等待一会(自己定义的时间)再进入线程池
        //4.ArrayBlockingQueue:有界任务队列
        //5.PriorityBlockingQueue:优先级任务队列
        //  可以指定每个任务的优先级,优先级高的,先进入
        //  线程池
        //CPU 核心数:
        int CPUCores = Runtime.getRuntime().availableProcessors();
        threadPoolExecutor = new ThreadPoolExecutor(CPUCores + 1,
                CPUCores * 2 + 1, 60l, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>());
    }

    //获得线程池对象
    public ThreadPoolExecutor getThreadPoolExecutor() {
        return threadPoolExecutor;
    }


//        //使用自定义的线程池
//        ThreadPoolExecutor threadPool =
//                MyThreadPool
//                        .getInstance()
//                        .getThreadPoolExecutor();
//        //向线程池中添加新的任务
//        //调用threadPool的execute方法,参数是
//        //Runnable
//        threadPool.execute(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
//        //关闭线程池,线程池会完成现有的工作,然后关闭
//        threadPool.shutdown();
//        //线程池会放弃现有的工作,立刻关闭
//        threadPool.shutdownNow();

}
