package com.company;

import sun.security.util.BitArray;

/**
 * Created by vit on 5/19/2016.
 */
public class additionFunctions {
    //population for operations
    static Population newPop;

    //check path for validation
    public static boolean isCon(double[][] arr, BitArray ch) {
        boolean res = false;
        int point = 0;

        for (int i = 1; i < arr.length; i++) {
            if ((ch.get(i) == true) && (arr[point][i] > 0) && !Double.isInfinite(arr[point][i])) {
                point = i;
                continue;
            }
            if ((ch.get(i) == true) && Double.isInfinite(arr[point][i])) {
                res = false;
                break;
            }
            res = true;
        }

        return res;
    }

    //selection function
    public static Population selectionPop(Population oldPop) {
        //counter to track how many chromosome in new population
        int ctrNew = 0,
                ctrOld = 0;
        double  random,
                sumOfFit = 0;

        //get sum of all fitness
        sumOfFit = calculateFitness.getAllFitness(oldPop);

        //array of fitness of each chromosome
        double[] arrOfFit = new double[oldPop.length()];

        //create new population
        newPop = new Population(oldPop.length());

        //count each fitness of population
        for (int i = 0; i < oldPop.length(); i++) {
            arrOfFit[i] = sumOfFit / calculateFitness.getFitness(Main.matrix, oldPop.ba[i]) * 100;
        }

        while (ctrNew < newPop.length()) {
            random = Math.random();
            if (random < arrOfFit[ctrOld]) {
                newPop.ba[ctrNew] = oldPop.ba[ctrOld];
                ctrNew++;
            } else {
                ctrOld++;
                if (ctrOld == oldPop.length()) {
                    ctrOld = 0;
                }
            }
        }

        return newPop;
    }

    //crossover function
    public static Population crossoverPop(Population oldPop) {
        //counters
        int     newCtr = 0,
                point = oldPop.ba[0].length(),
                cutPoint;

        //parents an intermediate array
        BitArray p1,
                 p2;
        boolean tmpArray;

        //crate new population
        Population newPop = new Population(oldPop.length());

        while(newCtr != oldPop.length()){
            //crossover point
            cutPoint = (int)(Math.random() * ((point - 2) + 1)) + 2;
//          System.out.println("Cut point: " + cutPoint);

            //choose two chromosome randomly from old population
            p1 = oldPop.ba[(int)Math.random()*oldPop.length()];
            p2 = oldPop.ba[(int)Math.random()*oldPop.length()];

            //output Old
//            System.out.println("Old output: ");
//            System.out.println(p1.toString());
//            System.out.println(p2.toString());

            //initialize tmp array
//            System.out.println("Point: " + point);
//            System.out.println(cutPoint);
//            System.out.println(oldPop.length());
            //change genes between two parents
            for (int i = 0; i < cutPoint; i++) {
                tmpArray = p1.get(i);
                p1.set(i,p2.get(i));
                p2.set(i,tmpArray);
            }

            if (additionFunctions.isCon(Main.matrix,p1)){
                newPop.ba[newCtr] = p1;
                newCtr++;
            } else if (additionFunctions.isCon(Main.matrix,p2)){
                newPop.ba[newCtr] = p2;
                newCtr++;
            }

            //output New
//            System.out.println("New output: ");
//            System.out.println(p1.toString());
//            System.out.println(p2.toString());
        }
        return newPop;
    }

    //mutation function
    public static Population mutate(Population oldPop) {
        //counter
        int ctr = 0;
        //mutation rate
        int a = (int) (0.1*oldPop.length());
        //random number
        int     randChromo,
                randGene;
        //tmp chromosome
        BitArray tmpChromo;

        while(a != ctr){
            randChromo = (int) (Math.random()*oldPop.length());
//            System.out.println("Random chromosome :" + randChromo);

            randGene = 1 + (int)(Math.random() * (oldPop.ba[0].length() - 2));
//            System.out.println("Random gene :"+randGene);

            tmpChromo = oldPop.ba[randChromo];
//            System.out.println("Temporary chromosome :" + tmpChromo);
//            System.out.println(" ");

            tmpChromo.set(randGene, !oldPop.ba[randChromo].get(randGene));

            if (additionFunctions.isCon(Main.matrix, tmpChromo)){
                oldPop.ba[randChromo] = tmpChromo;
                ctr++;
            }

        }
        return oldPop;
    }
}