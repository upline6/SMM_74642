import Model.Matrix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public final class Shell {

    public static void main(String[] args) throws IOException{
//        // test
//        m1.printMatrix();
////        m1.printMatrix();
////        m1.getQuarterMatrix(0, 0).printMatrix();
////        m1.getQuarterMatrix(0, 1).printMatrix();
////        m1.getQuarterMatrix(1, 0).printMatrix();
////        m1.getQuarterMatrix(1, 1).printMatrix();
//        Matrix m2 = new Matrix(4, 2, 3);
//        m2.printMatrix();
////        m2.printMatrix();
//        Matrix m3;
////
////        MatrixOperations mo = new MatrixOperations();
////        m3 = mo.multStrMethod(m1, m2, 2);
////        m3.printMatrix();
//        // test end

        BufferedReader inRead =
                new BufferedReader(new InputStreamReader(System.in));
        execute(inRead);
    }

    private static void execute(BufferedReader inRead) throws IOException{
        boolean quit = false;
        while (!quit) {
            System.out.print("smm> ");
            String input = inRead.readLine();
            Scanner scanner = new Scanner(input);
            scanner.useDelimiter("\\s+");
            if (scanner.hasNext()) {
                String command = scanner.next().toLowerCase();
                switch (command.charAt(0)) {
                    case 'q':
                        quit = !quit;
                        break;
                    case 'h':
                        helpMessage();
                        break;
                    case 'm':
                        int matrixSizes = scanner.nextInt();
                        int limit = scanner.nextInt();
                        int xa = scanner.nextInt();
                        int xb = scanner.nextInt();
                        int ya = scanner.nextInt();
                        int yb = scanner.nextInt();
                        Matrix matrixA = new Matrix(matrixSizes);
                        for(int i = 0; i < matrixA.getMatrixSize(); i++) {
                            for (int j = 0; j < matrixA.getMatrixSize(); j++) {
                                matrixA.setValue(i, j, i*xa + j*xb);
                            }
                        }
                        matrixA.printMatrix();
                        Matrix matrixB = new Matrix(matrixSizes);
                        for(int i = 0; i < matrixB.getMatrixSize(); i++) {
                            for (int j = 0; j < matrixB.getMatrixSize(); j++) {
                                matrixB.setValue(i, j, i*ya + j*yb);
                            }
                        }
                        System.out.println();
                        matrixB.printMatrix();
                        System.out.println();
                        Matrix matrixC = matrixA.expandMatrix(matrixA);
                        matrixC.printMatrix();
                        break;
                    default: errorMessage("Unknown command.");
                }
            } else {
                errorMessage("No command.");
            }
        }
    }

    private static void errorMessage(String errorCause){
        System.out.println("Error! " + errorCause);
    }

    private static void helpMessage(){
        System.out.println("Help text here!");
    }
}