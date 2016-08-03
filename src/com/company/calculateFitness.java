package com.company;

import sun.security.util.BitArray;

public class calculateFitness {

    static int maxFitness = Integer.MAX_VALUE;
    static BitArray maxChromosome;
    static double[] arrOfFitneses;

    //fitness function calculatio for chromosome
    static int getFitness(double[][] matrix, BitArray ch){
        int     fitness = 0,
                tmpInd = 0;

        for (int i = 0; i < ch.length(); i++) {
            if (ch.get(i)){
                fitness += matrix[tmpInd][i];
                tmpInd = i;
            }
        }
//        System.out.println("Fitness: "+fitness);
        return fitness;
    }

    //sum of fitness functions
    public static double getAllFitness(Population pop){
        double sum = 0;
        int currentFitness;
        arrOfFitneses = new double[pop.length()];

        for (int i = 0; i < pop.length(); i++) {
            currentFitness = calculateFitness.getFitness(Main.matrix, pop.ba[i]);
            arrOfFitneses[i] = currentFitness;
            sum += currentFitness;

            if(maxFitness >currentFitness){
                maxFitness = currentFitness;
                maxChromosome = pop.ba[i];
            }
        }
        return sum;
    }
}
