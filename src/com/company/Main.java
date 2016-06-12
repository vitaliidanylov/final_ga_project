package com.company;

import java.text.DecimalFormat;

public class Main {
    //matrix
    public static double[][] matrix = MatrixFromFile.readMatrixFromFile("K:\\GoogleDrive\\GitHub_project\\ga_optimized\\src\\datasets\\matrix30x30.txt");

    public static void main(String[] args) {
        int maxIterations = 100;
        final long startTime = System.nanoTime();
        //variables
        Population pop = new Population(30,30);
        //generation of population
        for (int i = 0; i < maxIterations; i++) {
            pop = additionFunctions.selectionPop(pop);
//            System.out.println("Selection done");
            pop = additionFunctions.crossoverPop(pop);
//            System.out.println("Cross done");
            pop = additionFunctions.mutate(pop);
//            System.out.println("Mutation done");
        }
        //time
        calculateFitness.getAllFitness(pop);

        System.out.println("Shortest distance is: " + calculateFitness.maxFitness);
        System.out.println("Max chromosome: " + calculateFitness.maxChromosome);
        //end time
        final double estimatedTime  = (System.nanoTime() - startTime)/1000000000.0;
//        double seconds = (double)estimatedTime / 1000000000.0;
//        double seconds = (double)estimatedTime;
//        System.out.println("Time of calculations is: " + seconds);

        DecimalFormat df = new DecimalFormat("#.#########");
        df.setMaximumFractionDigits(8);
        System.out.println(df.format(estimatedTime));

    }

}
