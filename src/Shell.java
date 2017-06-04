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
////        m3 = mo.multStr(m1, m2, 2);
////        m3.printMatrix();
//        // test end

        BufferedReader inRead =
                new BufferedReader(new InputStreamReader(System.in));
        execute(inRead);
    }

    private static void execute(BufferedReader inRead) throws IOException{
        boolean quit = false;
        boolean verbose = false;
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
                    case 'v':
                        if (scanner.hasNext()) {
                            String nextParam = scanner.next().toLowerCase();
                            switch (nextParam) {
                                case "on":
                                    verbose = true;
                                    break;
                                case "off":
                                    verbose = false;
                                    break;
                                default:
                                    errorMessage("Please enter either on or " +
                                            "off.");
                            }
                        } else {
                            errorMessage("Please enter either on or off.");
                        }
                        break;
                    case 'm':
                        int matrixSizes;
                        int limit;
                        int xa;
                        int xb;
                        int ya;
                        int yb;
                        if (scanner.hasNextInt()) {
                            matrixSizes = scanner.nextInt();
                        } else {
                            errorMessage("This function needs 6 integers to " +
                                    "work with.");
                            break;
                        } if (scanner.hasNextInt()) {
                            limit = scanner.nextInt();
                        } else {
                            errorMessage("This function needs 6 integers to " +
                                    "work with.");
                            break;
                        } if (scanner.hasNextInt()) {
                            xa = scanner.nextInt();
                        } else {
                            errorMessage("This function needs 6 integers to " +
                                    "work with.");
                            break;
                        } if (scanner.hasNextInt()) {
                            xb = scanner.nextInt();
                        } else {
                            errorMessage("This function needs 6 integers to " +
                                    "work with.");
                            break;
                        } if (scanner.hasNextInt()) {
                            ya = scanner.nextInt();
                        } else {
                            errorMessage("This function needs 6 integers to " +
                                    "work with.");
                            break;
                        } if (scanner.hasNextInt()) {
                            yb = scanner.nextInt();
                        } else {
                            errorMessage("This function needs 6 integers to " +
                                    "work with.");
                            break;
                        }
                        Matrix matrixA = new Matrix(matrixSizes);
                        for (int i = 0; i < matrixA.getMatrixSize(); i++) {
                            for (int j = 0; j < matrixA.getMatrixSize(); j++) {
                                matrixA.setValue(i, j, i * xa + j * xb);
                            }
                        }
                        Matrix matrixB = new Matrix(matrixSizes);
                        for (int i = 0; i < matrixB.getMatrixSize(); i++) {
                            for (int j = 0; j < matrixB.getMatrixSize(); j++) {
                                matrixB.setValue(i, j, i * ya + j * yb);
                            }
                        }
                        Matrix matrixC = matrixA.multStr(limit, matrixB);
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