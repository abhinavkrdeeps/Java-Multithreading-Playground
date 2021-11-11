package com.wissen.training;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class Multithreading {

    // Parallelism -> requires > 1 cpu cores

    // Concurrency -> Shared Variables between multiple threads at same time.
    // Let say we have 4 threads . T1, T2, T3, T4 and cpu has one core. It means Parallelism cannot be implemented.
    /*
    *  Thread 1
    *  new Thread(()-> if(ticket > 0 ) {
    *   book();
    *   ticket--;
    * })
    * Thread 2
     *  new Thread(()-> if(ticket > 0 ) {
     *   book();
     *   ticket--;
     * })
    * What happens behind the scene is all done by scheduler. scheduler schedules a thread
    * for a particular allocated time.
    * scheduler interleaves the thread one after another.
    *
    *  Concurrency -> Shared Resource needs to accessed or updated.
    *                 Or
    *                 Multiple tasks(Thread) needs to coordinate between them.
    *                 Only 1 Core Cpu.
    *
    *
    *
    *  */

    // Each Thread has its own independent initialized copy and hence making it thread safe.
    public static ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>(){

        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }

        @Override
        public SimpleDateFormat get() {
            return super.get();
        }
    };

    public String birthDate(int id){
        System.out.println(id);
        return Multithreading.simpleDateFormatThreadLocal.get().format(new Date());
    }


    public static void main(String[] args) {

        // Create 100 threads each performing birthDate
        // This will fire up 100 threads . Each creating its new object of SimpleDateFormat class which is not efficient.
//        for(int i=0;i<=100;i++){
//            int id=i;
//            new Thread(()->new Multithreading().birthDate(id)).start();
//        }

        // Using Executor Service to create a thread Pool.
        // This will create a thread Pool with fix number of threads(10).
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for(int i=0;i<=1000;i++){
            int id=i;
            executorService.submit(()-> new Multithreading().birthDate(id));
        }
    }
}
