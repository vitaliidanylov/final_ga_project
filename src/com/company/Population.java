package com.company;

import sun.security.util.BitArray;

/**
 * Created by vit on 6/10/2016.
 */
public class Population {

    BitArray ba[];

    public Population(int popSize, int chromosomeSize) {
        //initialization
        ba = new BitArray[popSize];
        BitArray bCh;
        int ctr = 0;

        while(ctr < popSize){
            bCh = new BitArray(chromosomeSize);
            bCh.set(0,true);
            bCh.set(chromosomeSize-1,true);
            for (int j = 1; j < chromosomeSize - 1; j++) {
                bCh.set(j,randomBoolean());
            }
            if(additionFunctions.isCon(Main.matrix, bCh)){
                ba[ctr] = bCh;
                ctr++;
//                System.out.println("Chromosome created :"+ctr);
            }
        }
    }

    public Population(int populationSIze) {
        ba = new BitArray[populationSIze];
    }

    public int length(){
        return ba.length;
    }

    public static boolean randomBoolean(){
        return Math.random() < 0.5;
    }
}
