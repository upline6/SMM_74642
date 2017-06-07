import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * This class handles the users input to multiply two matrices whit the
 * Strassen algorithm using the Matrix class and outputs results, help text
 * and error messages.
 */
public final class Shell {

    /**
     * Constructor set private, so nothing can instantiate this class.
     */
    private Shell() { }

    /**
     * The main method of the program is instantiating a BufferedReader to parse
     * any user input. It also starts the 'game loop' by calling the execute
     * method.
     * @param args
     * @throws IOException
     */
    public static void main(final String[] args) throws IOException {
        BufferedReader inRead =
                new BufferedReader(new InputStreamReader(System.in));
        execute(inRead);
    }

    /**
     * This method is the backbone of the communication between the user
     * input and the information display. As long as this method runs
     * commands will be read and hand over to the respective method. It also
     * handles wrong user inputs.
     * @param inRead the object to get the users inputs from
     * @throws IOException
     */
    private static void execute(final BufferedReader inRead) throws
            IOException {
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
                                    errorMessage("Please enter either on or "
                                            + "off.");
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
                            errorMessage("This function needs 6 integers to "
                                    + "work with.");
                            break;
                        }
                        if (scanner.hasNextInt()) {
                            limit = scanner.nextInt();
                        } else {
                            errorMessage("This function needs 6 integers to "
                                    + "work with.");
                            break;
                        }
                        if (scanner.hasNextInt()) {
                            xa = scanner.nextInt();
                        } else {
                            errorMessage("This function needs 6 integers to "
                                    + "work with.");
                            break;
                        }
                        if (scanner.hasNextInt()) {
                            xb = scanner.nextInt();
                        } else {
                            errorMessage("This function needs 6 integers to "
                                    + "work with.");
                            break;
                        }
                        if (scanner.hasNextInt()) {
                            ya = scanner.nextInt();
                        } else {
                            errorMessage("This function needs 6 integers to "
                                    + "work with.");
                            break;
                        }
                        if (scanner.hasNextInt()) {
                            yb = scanner.nextInt();
                        } else {
                            errorMessage("This function needs 6 integers to "
                                    + "work with.");
                            break;
                        }

                        if (limit < 2) {
                            errorMessage("Limit needs to be greater than 2.");
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
                        errorMessage("Unknown command. Try the help command!");
                }
            } else {
                errorMessage("No command.");
            }
        }
    }

    /**
     * This method is always called when printing an error text to the
     * console. It unifies the error text in that all of them begin with the
     * phrase "Error!_".
     * @param errorCause an aiding String generated by the specific context
     */
    private static void errorMessage(final String errorCause) {
        System.out.println("Error! " + errorCause);
    }

    /**
     * This method prints out the state of the boolean value verbose into the
     * string ON or OFF. It is only used in the execute method.
     * @param verb takes the verbose variable
     */
    private static void printVerbose(final boolean verb) {
        String onOrOff = "OFF";

        if (verb) {
            onOrOff = "ON";
        }
        System.out.println("VERBOSE is set to: " + onOrOff);
    }

    /**
     * This method provides the help text which is called by the execute
     * method when the user types the help command.
     */
    private static void helpMessage() {
        System.out.println("This program is used to multiply two matrices "
                + "with the algorithm developed by\nVolker Strassen. "
                + "Available commands are:\n\n"
                + "HELP\tdisplays an overview of the program and the "
                + "commands.\n"
                + "QUIT\texits the program\n"
                + "MULT\ttakes 6 integers to perform a Strassen-matrix "
                + "multiplication on two\n\t\tmatrices. n: the size of the "
                + "two matrices to be created; n(0) the limit\n\t\tat which "
                + "the common school method will be used; x(a), x(b), y(a), y"
                + "(b)\n\t\tvariables to create two matrices. The first "
                + "matrix is built as follows:\n\t\ta(i,j) = x(a)*i +x(b)*j ."
                + " The second matrix: b(i,j) = y(a)*i + y(b)*j .\n"
                + "VERBOSE\t"
                + "prints out the state of this option. Adding ON or OFF to "
                + "the command\n\t\tchanges its state. When turned on, the "
                + "MULT method prints out its\n\t\tinterim results.");
    }
}
