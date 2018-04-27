package com.gruppe3;

public class Main {

    public static void main(String[] args) {
        System.out.println("MoSi - Hausaufgabe 1");
        System.out.println();

        // implementation task 1.2.1
        FieldState[][] ex0cfg = {
            {FieldState.FIRE,null,null},
            {null,null,FieldState.WOODS},
            {null,FieldState.WOODS,null}
        };
        Experiment ex0 = new Experiment(3, ex0cfg, 4, true);

        ex0.runExp();
    }

}