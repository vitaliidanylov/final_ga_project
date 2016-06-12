package com.company;

import sun.security.util.BitArray;

/**
 * Created by vit on 6/10/2016.
 */
public class calculateFitness {

    public static int maxFitness = Integer.MAX_VALUE;
    public static BitArray maxChromosome;

    /*Public methods*/
    //get fitness of chromosome
    static int getFitness(double[][] matrix, BitArray ch){
        int     fitness = 0,
                tmpInd = 0;

        for (int i = 0; i < ch.length(); i++) {
            if (ch.get(i)){
                fitness += matrix[tmpInd][i];
                tmpInd = i;
            }
        }
        return fitness;
    }

    //get general fitness
    public static double getAllFitness(Population pop){
        double sum = 0;
        int currentFitness;

        for (int i = 0; i < pop.length(); i++) {
//            System.out.println(sum);
            currentFitness = calculateFitness.getFitness(Main.matrix, pop.ba[i]);
            sum += currentFitness;

            if(maxFitness > currentFitness){
                maxFitness = currentFitness;
                maxChromosome = pop.ba[i];
            }
        }
        return sum;
    }

}
