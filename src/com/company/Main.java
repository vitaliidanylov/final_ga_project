package com.company;

import java.text.DecimalFormat;

public class Main {
    //загрузка матриці из файла
    public static double[][] matrix = MatrixFromFile.readMatrixFromFile("K:\\GoogleDrive\\GitHub_project\\ga_optimized\\src\\datasets\\matrix10x10.txt");

    public static void main(String[] args) {
        //количество итераций для каждой популяции
        int maxIterations = 10;
        //количество запусков алгоритма
        for (int i = 0; i < 30; i++) {
            //начало
            final long startTime = System.nanoTime();
            Population pop = new Population(6, 10);
            for (int j = 0; j < maxIterations; j++) {
                //основные этапы алгоитма
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
            //вывод результата
            System.out.println("Shortest distance is: " + calculateFitness.maxFitness + " ");
            System.out.println("Max chromosome: " + calculateFitness.maxChromosome);
        }
    }
}
