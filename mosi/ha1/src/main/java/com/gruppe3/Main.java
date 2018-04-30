package com.gruppe3;

public class Main {

    public static void main(String[] args) {
        System.out.println("MoSi - Hausaufgabe 1");
        System.out.println();

        Field.printSymbolLegend();

        // implementation task 1.2.1
        FieldState[][] ex0cfg = {
            {FieldState.FIRE,null,null},
            {null,null,FieldState.FOREST},
            {null,FieldState.FOREST,null}
        };
        Experiment ex0 = new Experiment(3, "Aufgabe 1.2.1:\n", ex0cfg);

        ex0.runExpStep(10, true);

        // experiment task 1.2.2
        Experiment ex1 = new Experiment(21, "Aufgabe 1.2.2:\n");

        ex1.simulateAllFirePos(FieldState.GRASSLAND, FieldState.FOREST);
        ex1.printHistogram();

        // experiment tasl 1.2.3
        Experiment ex2 = new Experiment(21, 318, 118, 5);
    }

}