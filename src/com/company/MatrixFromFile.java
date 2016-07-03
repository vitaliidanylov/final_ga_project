package com.company;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This method will take a string parameter representing the name of the file. It
 * will copy the contents into an array of strings.
 *
 * @param String file
 * @return double[]
 */

public class MatrixFromFile {
    //read matrix
    public static double[][] readMatrixFromFile(String filename) {
        //estimate how many lines in the file
        int ctr = 0;
        try {
            Scanner s1 = new Scanner(new FileReader(filename));
            while (s1.hasNextLine()) {
                ctr += 1;
                s1.nextLine();
            }
            s1.close();
            //create matrix to store elements from the file
            double[][] matrix = new double[ctr][ctr];

            Scanner s2 = new Scanner(new FileReader(filename));
            for (int i = 0; i < ctr; i++) {
                for (int j = 0; j < ctr; j++) {
                    matrix[i][j] = Double.parseDouble(s2.next());
                }
            }
            s2.close();
            return matrix;
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return null;
    }

    //matrix printing
    public static void printMatrix(double[][] matrix) {
        System.out.println(Arrays.deepToString(matrix));
    }
}