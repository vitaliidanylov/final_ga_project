package com.company;

import sun.security.util.BitArray;

/**
 * Created by vit on 5/19/2016.
 */
public class additionFunctions {
    //population init
    static Population newPop;

    //checking rout e function
    public static boolean isConnected(double[][] arr, BitArray ch) {
        boolean res = false;
        int point = 0;
        for (int i = 1; i < arr.length; i++) {
            if (ch.get(i) && (arr[point][i] > 0) && !Double.isInfinite(arr[point][i])) {
                point = i;
                continue;
            }
            if (ch.get(i) && Double.isInfinite(arr[point][i])) {
                res = false;
                break;
            }
            res = true;
        }
        return res;
    }

    //selection
    public static Population selectionPop(Population oldPop) {
        //counter to track how many chromosome in new population
        int ctrNew = 0,
                ctrOld = 0;
        double random,
                sumOfFit = 0;

        //fitness sum
        sumOfFit = calculateFitness.getAllFitness(oldPop);
        double[] arrOfFit = new double[oldPop.length()];

        //creation of new population
        newPop = new Population(oldPop.length());

        //fitness function calcucation
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
        int newCtr = 0,
                point = oldPop.ba[0].length(),
                cutPoint;

        //parents an intermediate array
        BitArray p1,
                p2;
        boolean tmpArray;

        //crate new population
        Population newPop = new Population(oldPop.length());

        while (newCtr != oldPop.length()) {
            //crossover point
            cutPoint = (int) (Math.random() * ((point - 2) + 1)) + 2;

            //choose two chromosome randomly from old population
            p1 = oldPop.ba[(int) Math.random() * oldPop.length()];
            p2 = oldPop.ba[(int) Math.random() * oldPop.length()];

            for (int i = 0; i < cutPoint; i++) {
                tmpArray = p1.get(i);
                p1.set(i, p2.get(i));
                p2.set(i, tmpArray);
            }

            if (additionFunctions.isConnected(Main.matrix, p1)) {
                newPop.ba[newCtr] = p1;
                newCtr++;
            } else if (additionFunctions.isConnected(Main.matrix, p2)) {
                newPop.ba[newCtr] = p2;
                newCtr++;
            }
        }
        return newPop;
    }

    //mutation function
    public static Population mutate(Population oldPop) {
        //counters
        int ctr = 0;
        //mutation level
        int a = (int) (0.07 * oldPop.length());
        //random variables
        int randChromo,
                randGene;
        //tmp chromosome
        BitArray tmpChromo;

        while (a != ctr) {
            randChromo = (int) (Math.random() * oldPop.length());
            randGene = 1 + (int) (Math.random() * (oldPop.ba[0].length() - 2));
            tmpChromo = oldPop.ba[randChromo];
            tmpChromo.set(randGene, !oldPop.ba[randChromo].get(randGene));

            if (additionFunctions.isConnected(Main.matrix, tmpChromo)) {
                oldPop.ba[randChromo] = tmpChromo;
                ctr++;
            }
        }
        return oldPop;
    }

//    //modified, create edges aarray (test function)
//    public static ArrayList<ArrayList> getEdges(double[][] arrMatrix) {
//        ArrayList<ArrayList> edges = new ArrayList<>();
//        ArrayList tmp;
//        //loops for filter edges
//        for (int i = 0; i < arrMatrix.length - 1; i++) {
//            tmp = new ArrayList();
//            for (int j = i + 1; j < arrMatrix[i].length; j++) {
//                if (!Double.isInfinite(arrMatrix[i][j])) {
//                    tmp.add(j);
//                }
//            }
//            edges.add(tmp);
//        }
//        return edges;
//    }
//
//    //check if connected (test function)
//    public static boolean isConnectedMod(ArrayList<ArrayList> arrList, BitArray ch) {
//        //how many iterations do we need
//        int numOfIter = 0;
//        for (int i = 0; i < ch.length(); i++) {
//            if (ch.get(i)){
//                numOfIter++;
//            }
//        }
//
//        //counter variables
//        int arrCount = 0;
//        int chCounter = 1;
//        boolean res = false;
//        //checking function
//        for (int i = 0; i < ch.length()-1; i++) {
//            if (ch.get(chCounter) && arrList.get(arrCount).contains(chCounter)){
//                arrCount = chCounter;
//                res = true;
//            } else if(ch.get(chCounter) && !arrList.get(arrCount).contains(chCounter)){
//                res = false;
//                break;
//            }
//        }
//
//        return res;
//    }
}