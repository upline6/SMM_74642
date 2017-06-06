import Model.Matrix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public final class Shell {

    public static void main(String[] args) throws IOException {
        BufferedReader inRead =
                new BufferedReader(new InputStreamReader(System.in));
        execute(inRead);
    }

    private static void execute(BufferedReader inRead) throws IOException {
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
                                    printVerbose(verbose);
                                    break;
                                case "off":
                                    verbose = false;
                                    printVerbose(verbose);
                                    break;
                                default:
                                    errorMessage("Please enter either on or " +
                                            "off.");
                            }
                        } else {
                            printVerbose(verbose);
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
                        }
                        if (scanner.hasNextInt()) {
                            limit = scanner.nextInt();
                        } else {
                            errorMessage("This function needs 6 integers to " +
                                    "work with.");
                            break;
                        }
                        if (scanner.hasNextInt()) {
                            xa = scanner.nextInt();
                        } else {
                            errorMessage("This function needs 6 integers to " +
                                    "work with.");
                            break;
                        }
                        if (scanner.hasNextInt()) {
                            xb = scanner.nextInt();
                        } else {
                            errorMessage("This function needs 6 integers to " +
                                    "work with.");
                            break;
                        }
                        if (scanner.hasNextInt()) {
                            ya = scanner.nextInt();
                        } else {
                            errorMessage("This function needs 6 integers to " +
                                    "work with.");
                            break;
                        }
                        if (scanner.hasNextInt()) {
                            yb = scanner.nextInt();
                        } else {
                            errorMessage("This function needs 6 integers to " +
                                    "work with.");
                            break;
                        }

                        if (limit < 2) {
                            errorMessage("Limit needs do be greater than 2.");
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
                        Matrix matrixC = matrixA.multStr(limit, matrixB,
                                verbose);

                        if (!verbose) {
                            matrixC.printMatrix();
                        }
                        break;
                    default:
                        errorMessage("Unknown command. Try the help " +
                                "command!");
                }
            } else {
                errorMessage("No command.");
            }
        }
    }

    private static void errorMessage(String errorCause) {
        System.out.println("Error! " + errorCause);
    }

    private static void printVerbose(boolean verb) {
        String onOrOff = "OFF";

        if (verb) {
            onOrOff = "ON";
        }
        System.out.println("VERBOSE is set to: " + onOrOff);
    }

    private static void helpMessage() {
        System.out.println("This program is used to multiply two matrices " +
                "with the algorithm developed by Volker Strassen. Available " +
                "commands are:");
        System.out.println();
        System.out.println("HELP displays an overview of the program and the" +
                " commands.");
        System.out.println("QUIT exits the program");
        System.out.println("MULT takes 6 integers to perform a Strassen-" +
                "matrix multiplication on two matrices: 1. the size of the two" +
                "matrices to be created, 2. the limit at which the common " +
                "school method should be used, 3+4: variables for the first " +
                "matrix, created with n(i, j) = i*variable of 3 + j+variable " +
                "of 4");
        System.out.println("VERBOSE prints out the state of this option. " +
                "Adding ON or OFF to the command changes its state. When " +
                "turned on, the MULT method prints out its interim results.");
    }
}