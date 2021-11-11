package com.wissen.training;


/**
 *  JavaMemoryModel (JMM) is a specification which guarantees visibility of fields
 *  amidst reordering of instructions.
 *
 *
 *
 */
public class JavaMemoryModel {

    public static void outOfOrderExecution(){
        /*
        * A program is a series of instructions .
        * It might happen that either jvm, cpu , compiler.. the order of execution
        * might change to enhance some performance issues, while maintaining the semantics same
        * to make sure output of the program is always same.
        *
        * Example
        * a=3; (Load a; Set a to 3; Load back to a;)
        * b=1; (Load b ; Set b to 1; Load b;)
        * a=a+1; (Load a ; set a to 4; Load back a);
        *
        * some changes can be done as a has been loaded twice;
        * a=3;
        * a=a+1;
        * b=1;
        *
        *
        *
        * */
    }

    public static void main(String[] args) {

    }
}
