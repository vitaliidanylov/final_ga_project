package com.company;

import sun.security.util.BitArray;

public class calculateFitness {

    public static int maxFitness = Integer.MAX_VALUE;
    public static BitArray maxChromosome;

    //Функция получения целевого значения хромосомы
    static int getFitness(double[][] matrix, BitArray ch){
        int     fitness = 0,
                tmpInd = 0;

        for (int i = 0; i < ch.length(); i++) {
            if (ch.get(i)){
                fitness += matrix[tmpInd][i];
                tmpInd = i;
            }
        }
        System.out.println("Fitness: "+fitness);
        return fitness;
    }

    //Функция подсчёта суммы всех целевых функций
    public static double getAllFitness(Population pop){
        double sum = 0;
        int currentFitness;

        for (int i = 0; i < pop.length(); i++) {
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
