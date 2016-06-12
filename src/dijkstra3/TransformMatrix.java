package dijkstra3;

import com.company.MatrixFromFile;

import java.util.ArrayList;

/**
 * Created by vit on 6/13/2016.
 */
public class TransformMatrix {
    public static void main(String[] args) {
        double[][] matrix = MatrixFromFile.readMatrixFromFile("K:\\GoogleDrive\\GitHub_project\\ga_optimized\\src\\datasets\\matrix10x10.txt");
        convertMatrix(matrix);

    }
    public static ArrayList convertMatrix(double[][] matrix){
        ArrayList formated = new ArrayList();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[i].length; j++) {
                if (!Double.isInfinite(matrix[i][j])){
                    if (i == j) continue;
                    else if(i == 0) {
                        System.out.println("new Graph.Edge(\"a\", \""+j+"\", "+matrix[i][j]+"),");
                    } else if(j == matrix.length-1){
                        System.out.println("new Graph.Edge(\""+i+"\", \"e\", "+matrix[i][j]+"),");
                    } else {
                        System.out.println("new Graph.Edge(\""+i+"\", \""+j+"\", "+ (int)matrix[i][j]+"),");
                    }
                }
            }
        }

        return formated;
    }
}
