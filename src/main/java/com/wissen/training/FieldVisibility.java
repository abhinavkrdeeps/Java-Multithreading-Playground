package com.wissen.training;

/**
 * FieldVisibility: According to the java memory model, We have L1 cache and L2 (Shared Cache between the cores)
 *
 *  -------------------------------------
 *  Core 1          |        Core 2
 *  L1 Cache        |        L1 Cache
 *       L2 Cache (shared cache)
 *  -------------------------------------
 *
 * After knowing this model, We can see the field Visibility Problem.
 * Let say a variable x and two methods we have .
 * First One is write() method which sets the value of x to 1;
 * Second One is read() method which reads the value of x.
 * Because of local cache value of x gets stored in L1 cache and not
 * visible to other thread.
 *
 *Solution:  Using Volatile KeyWord. When a variable is declared volatile
 * the value is not been read/written from local cache instead it is pulled
 * from main memory and written to main memory.
 *
 *
 *
 */
public class FieldVisibility {

    volatile int x=0;

    public void write(){
       // System.out.println("Writer Thread Executing");
        this.x=1;
    }
    public void read(){
        //System.out.println("Reader Thread Executing");
        int r2=this.x;
        System.out.println(r2);
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(()-> new FieldVisibility().write()).start();
        Thread.sleep(400);
        new Thread(()-> new FieldVisibility().read()).start();

    }
}
