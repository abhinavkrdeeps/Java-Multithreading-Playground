package com.wissen.training;

import java.sql.Time;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *  In Java ,
 *  1 Java Thread is equal to 1 os (Operating System) Thread. So Creating very large number
 *  of threads is not feasible as well as gives poor performance.
 *  As a Solution we can Use ExecutorService which is a kind of thread Pool maintains a limited number
 *  of thread to do multiple Tasks.
 *
 *   Let say we have a 1000 task . Create a Thread pool with 10 threads. All the 1000 tasks are stored
 *   in a blocking queue. Each Thread fetch a task from blocking queue , executes it and then fetch
 *   another task.
 *
 *
 *                                                  Thread Pool
 *                        -----------------------------------------------------------------------
 *                        |           Blocking Queue (Thread Safe )
 *                        |      -----------------------------------------------------------------
 *                        |             Task 1 |   Task 2               |
 *                        |                    |                        |
 *                        |       ----------------------------------------------------------------
 *                        |
 *                        |        thread 0   |   Thread 1 | thread 2 | thread 3 | ...  thread 10
 *                        |        (Fetch Task From Queue and
 *                        |         Execute It )
 *                        |
 *                        |
 *                        |
 *                        |-----------------------------------------------------------------------
 *
 *        Get the Number of available Processors'
 *        int numberOfCores = Runtime.getRuntime().availableProcessors();
 *        No Matter How many threads you are running. Number of threads/tasks which can run in parallel
 *        is equal to the number of cores/processors available . Else we have a time based splitting done by
 *        scheduler.
 *
 *        How to choose ideal pool size.
 *        ---------------------------------------------------------------------------------------------------------
 *        -- Depends on use case.
 *        If task is IO intensive (Calls to db), then pool size should be large as multiple
 *        threads goes in waiting condition.
 *        If task is CPU intensive (Calculating hash) , then it should be equal to the number of cores/ processors
 *        available.
 *
 *        ----------------------------------------------------------------------------------------------------------
 *        Cached Thread Pool
 *
 *        In Cached Thread Pool, we do not have control on the number of threads.
 *        Blocking queue is replaced with a synchronous queue which just hold one task.
 *        When a task is submitted, first checks if any thread is free, if free execute the
 *        task, else create a new thread.
 *        Also, If a thread is idle for 60s , kill the thread to limit the number of thread and
 *        pool size.
 *
 *        -----------------------------------------------------------------------------------------------------------
 *
 *
 *
 */
public class ExecutorServiceExample {
    public static void main(String[] args) {
        int numberOfCores = Runtime.getRuntime().availableProcessors();
        System.out.println("number of processors: "+numberOfCores);
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfCores);
//        for(int i=0;i<1000;i++){
//            //executorService.execute();
//            executorService.submit(()->  System.out.println(Thread.currentThread().getName()));
//        }

        // Cached Thread Pool
        ExecutorService cachedService = Executors.newCachedThreadPool();
        for(int i=0;i<1000;i++){
            //executorService.execute();
            cachedService.submit(()->  System.out.println(Thread.currentThread().getName()));
        }

        // Scheduled Executor (Scheduled Thread Pool)

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

        // Run a Task after 10 seconds delay
        scheduledExecutorService.schedule(()-> System.out.println(Thread.currentThread().getName()),10, TimeUnit.SECONDS);
        // Run a Task Repeatedly every 10 sec. First Wait For 10 sec (Initial Delay) then schedule it for every 10 sec
        scheduledExecutorService.scheduleAtFixedRate(()->System.out.println(new Date()),10,10,TimeUnit.SECONDS);
    }


}
