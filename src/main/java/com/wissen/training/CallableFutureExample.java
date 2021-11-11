package com.wissen.training;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Callable -> It is similar to Runnable but with a difference that we can return something after
 * executing the task.
 * A generic type.
 *
 * Future -> A placeholder for the callable ot return the value in future
 *
 *
 */
public class CallableFutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        List<Future<Integer>> futures = new ArrayList<>();
        for(int i=0;i<100;i++) {
            Future<Integer> future = executorService.submit(() -> new Random().nextInt());
            futures.add(future);
        }

        // Retrieving the value
        for(Future<Integer> future: futures){
            System.out.println("Value : " +future.get()); // A Blocking Operation. 
        }
    }
}
