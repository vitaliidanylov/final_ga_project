package com.company;

import java.text.DecimalFormat;

public class Main {
    //load matrix from file
    public static double[][] matrix = MatrixFromFile.readMatrixFromFile("/home/vit/IdeaProjects/final_ga_project/src/datasets/matrix10x10.txt");

    public static void main(String[] args) {

        //number of iterations in algorithm
        int maxIterations = 10;
        //number of algorithm starts
        for (int i = 0; i < 30; i++) {
            //начало
            final long startTime = System.nanoTime();
            Population pop = new Population(6, 10);
//            System.out.println(Arrays.toString(pop.ba));
            for (int j = 0; j < maxIterations; j++) {
                //main
                pop = additionFunctions.selectionPop(pop);
                pop = additionFunctions.crossoverPop(pop);
                pop = additionFunctions.mutate(pop);
                //time
                calculateFitness.getAllFitness(pop);
                //end time
            }
            //перевод времени калькуляции в удобный формат
            final double estimatedTime = (System.nanoTime() - startTime) / 1000000000.0;
            DecimalFormat df = new DecimalFormat("#.#########");
            df.setMaximumFractionDigits(8);
            System.out.println(df.format(estimatedTime));
            //result output
            System.out.println("Shortest distance is: " + calculateFitness.maxFitness + " ");
            System.out.println("Max chromosome: " + calculateFitness.maxChromosome);
        }
    }
}
