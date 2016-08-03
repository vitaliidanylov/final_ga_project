package com.company;

import sun.security.util.BitArray;

public class Population {

    BitArray[] ba;
    BitArray chromosome;

    public Population(int popSize, int chromosomeSize) {
        int ctr = 0;
        //initialization
        ba = new BitArray[popSize];
        chromosome = new BitArray(chromosomeSize);
        chromosome.set(0,true);
        chromosome.set(chromosomeSize-1,true);

        while(ctr < popSize){
            for (int i = 1; i < chromosomeSize-2; i++) {
                chromosome.set(i,Math.random()<0.5);
            }
            if(additionFunctions.isConnected(Main.matrix, chromosome)){
                ba[ctr] = chromosome;
                ctr++;
            }
        }
    }

    public Population(int populationSize) {
        ba = new BitArray[populationSize];
    }

    public int length(){
        return ba.length;
    }

//    public boolean randomBoolean(){
//        return Math.random() < 0.5;
//    }

    public void printOut(){
        for (int i = 0; i < this.ba.length; i++) {
            System.out.println(this.ba[i]);
        }
    }
}
