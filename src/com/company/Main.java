package com.company;

public class Main {
    //load matrix from file
    public static double[][] matrix = MatrixFromFile.readMatrixFromFile("src/datasets/matrix10x10.txt");

//    public static void main(String[] args) {
//        double startTime = System.nanoTime();
//        Population p = new Population(10,matrix.length);
//        System.out.println("Initial: "+(System.nanoTime()-startTime)/1000000000);
//        double selTime = System.nanoTime();
//        additionFunctions.selectionPop(p);
//        System.out.println("Selection: "+(System.nanoTime()-selTime)/1000000000);
//        double crosTime = System.nanoTime();
//        additionFunctions.crossoverPop(p);
//        System.out.println("Crossover: "+(System.nanoTime()-crosTime)/1000000000);
//        double mutTime = System.nanoTime();

//        additionFunctions.mutate(p);
//        System.out.println("Mutation: "+(System.nanoTime()-mutTime)/1000000000);
//        double fitnessTime = System.nanoTime();
//        calculateFitness.getAllFitness(p);
//        System.out.println("Fitness:"+(System.nanoTime()-fitnessTime)/1000000000);
//    }

    public static void main(String[] args) {

        int maxIterations = 4;
        //number of algorithm starts
        for (int i = 0; i < 31; i++) {
            //number of iterations in algorithm
            final long startTime = System.nanoTime();
            Population pop = new Population(14, matrix.length);
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
           additionFunctions.getTime(startTime);
            //result output
//            System.out.println("Shortest distance is: " + calculateFitness.maxFitness + " ");
//            System.out.println("Max chromosome: " + calculateFitness.maxChromosome);
        }
    }
}
