package com.company;

import sun.security.util.BitArray;

import java.text.DecimalFormat;

/**
 * Created by vit on 5/19/2016.
 */
public class additionFunctions {
    //population init
    static Population newPop;

    //checking rout e function
//    public static boolean isConnected(double[][] arr, BitArray ch) {
//        boolean res = false;
//        int point = 0;
//        for (int i = 1; i < arr.length; i++) {
//            if (ch.get(i) && (arr[point][i] > 0) && !Double.isInfinite(arr[point][i])) {
//                point = i;
//                continue;
//            }
//            if (ch.get(i) && Double.isInfinite(arr[point][i])) {
//                res = false;
//                break;
//            }
//            res = true;
//        }
//        return res;
//    }

    //selection
    public static Population selectionPop(Population oldPop) {
        //counter to track how many chromosome in new population
        int     ctrNew = 0,
                ctrOld = 0;
        double  random,
                sumOfFit;

        //fitness sum
        sumOfFit = calculateFitness.getAllFitness(oldPop);

        //creation of new population
        newPop = new Population(oldPop.length());

        while (ctrNew < newPop.length()) {
            random = Math.random()*sumOfFit;

            if (random > calculateFitness.arrOfFitneses[ctrOld]) {
                newPop.ba[ctrNew] = oldPop.ba[ctrOld];
                ctrNew++;
            } else {
                ctrOld++;
                if (ctrOld == oldPop.length()) ctrOld = 0;
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
        boolean  tmpArray;

        //crate new population
        Population newPop = new Population(oldPop.length());

        while (newCtr != oldPop.length()) {
            //crossover point
            cutPoint = (int) (Math.random() * ((point - 2) + 1)) + 2;

            //choose two chromosome randomly from old population
            p1 = oldPop.ba[(int) (Math.random() * oldPop.length())];
            p2 = oldPop.ba[(int) (Math.random() * oldPop.length())];

            for (int i = 0; i < cutPoint; i++) {
                tmpArray = p1.get(i);
                p1.set(i, p2.get(i));
                p2.set(i, tmpArray);
            }
                newPop.ba[newCtr] = p1;
                newCtr++;
                newPop.ba[newCtr] = p2;
                newCtr++;
        }
        return newPop;
    }


    //mutation function
    public static Population mutate(Population oldPop) {
        //counters
        int ctr = 0;
        //mutation level
        double a = Math.ceil(0.5 * oldPop.length());
//        System.out.println(a);
        //random variables
        int     randChromo,
                randGene;
        //tmp chromosome
        BitArray tmpChromo;

        while (a != ctr) {
            randChromo = (int) (Math.random() * oldPop.length());
            randGene = 1 + (int) (Math.random() * (oldPop.ba[0].length() - 2));
            tmpChromo = oldPop.ba[randChromo];
            tmpChromo.set(randGene, !oldPop.ba[randChromo].get(randGene));

            oldPop.ba[randChromo] = tmpChromo;
            ctr++;
        }
        return oldPop;
    }

    public static void getTime(long startTime){
        final double estimatedTime = (System.nanoTime() - startTime) / 1000000000.0;
        DecimalFormat df = new DecimalFormat("#.#########");
        df.setMaximumFractionDigits(8);
        System.out.println(df.format(estimatedTime));
    }
}