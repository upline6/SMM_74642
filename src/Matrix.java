/**
 * This class is used to create and calculate with a matrix. The main
 * purpose of it is to call the multStr method which multiplies two
 * matrices. The class itself is a wrapper for a double array.
 */
public class Matrix {

    private int matrixSize;
    private double[][] matrixContent;

    /**
     * The constructor initializes an empty matrix with a given size.
     *
     * @param size of the new matrix
     */
    public Matrix(int size) {
        matrixSize = size;
        matrixContent = new double[matrixSize][matrixSize];
    }

    /**
     * This method takes a number i and computes the next bigger integer that
     * is 2^n.
     *
     * @param i input number
     * @return next bigger integer to the power of two, returns the same as the
     * input if it was already to the power of two
     */
    private static int nextPowerOfTwo(int i) {
        return (int) Math.pow(2, Math.ceil(Math.log(i) / Math.log(2)));
    }

    /**
     * Calling this method returns the size of the array current matrix
     * object. The field is always quadratic so this information is valid for
     * rows and columns of this matrix.
     *
     * @return the length of the array in both dimensions as an integer
     */
    public int getMatrixSize() {
        return matrixSize;
    }

    /**
     * Returns the value of a cell in the matrix. When given the row and the
     * column.
     *
     * @param row    first row starting at 0
     * @param column first column starting at 0
     * @return the double value of the cell
     */
    public double getValue(int row, int column) {
        return matrixContent[row][column];
    }

    /**
     * This method sets the value for a single specific cell in the matrix.
     *
     * @param row    first row starting at 0
     * @param column first column starting at 0
     * @param value  the desired value of the cell
     */
    public void setValue(int row, int column, double value) {
        matrixContent[row][column] = value;
    }

    /**
     * This method adds a value to an existing value in a cell in the matrix.
     *
     * @param row    first row starting at 0
     * @param column first column starting at 0
     * @param value  the desired value which should be added to the cell
     */
    private void addToValue(int row, int column, double value) {
        matrixContent[row][column] += value;
    }

    /**
     * This method returns a matrix which is a quadrant of the matrix. It is
     * divided by two in both dimensions. The method is called only by
     * matrices that are to the power of two, hence the division is always
     * symmetrical.
     * The quadrants are named as follows:
     * - upper left: 0/0
     * - upper right: 0/1
     * - lower left: 1/0
     * - lower right: 1/1
     *
     * @param row    input coordinate for the desired quarter martix; see
     *               description above
     * @param column input coordinate for the desired quarter martix; see
     *               description above
     * @return the quarter of the matrix based on the coordinates, if wrong
     * the parameters are not 0 or 1 the method returns null.
     */
    private Matrix getQuarterMatrix(int row, int column) {

        if (row < 0 || row > 1 || column < 0 || column > 1) {
            return null;
        }
        Matrix result = new Matrix(this.getMatrixSize() / 2);

        for (int i = 0; i < result.getMatrixSize(); i++) {
            for (int j = 0; j < result.getMatrixSize(); j++) {
                result.setValue(i, j, this.getValue(
                        i + (row * result.getMatrixSize()), j + (column
                                * result.getMatrixSize())));
            }
        }
        return result;
    }

    /**
     * This method prints the matrix into the console.
     */
    public void printMatrix() {

        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {

                if (j != matrixSize - 1) { //check if is last in row
                    System.out.print(matrixContent[i][j] + " ");
                } else {
                    System.out.print(matrixContent[i][j]);
                }
            }
            System.out.println("");
        }
    }

    /**
     * This method takes another matrix object and returns the
     * product of the two. It uses the common algorithm taught in schools.
     * The verbose parameter allows result to be directly printed into the
     * console.
     *
     * @param m       the second matrix to multiply the matrix with
     * @param verbose binary value to determine if the result should be printed
     * @return the result of the product as a matrix object, if the matrices
     * do not have the same size it returns null.
     */
    private Matrix multSch(Matrix m, boolean verbose) {
        Matrix result = new Matrix(this.getMatrixSize());

        if (this.getMatrixSize() != m.getMatrixSize()) {
            return null;
        }

        for (int i = 0; i < result.getMatrixSize(); i++) {
            for (int j = 0; j < result.getMatrixSize(); j++) {
                for (int k = 0; k < result.getMatrixSize(); k++) {
                    result.addToValue(
                            i, j, this.getValue(i, k) * m.getValue(k, j));
                }
            }
        }

        if (verbose) {
            result.printMatrix();
            System.out.println();
        }
        return result;
    }

    /**
     * This method takes another matrix object and returns the
     * product of the two. It uses the algorithm by Volker Strassen which is
     * faster than the school algorithm but requires to have two quadratic
     * matrices which sizes are to the same power of two. The algorithm calls
     * itself when solving a multiplication within the algorithm. Therefore
     * it needs a limit at which multiplication is solved with the school
     * method.
     *
     * @param limit   at which size of the matrix the multiplication should
     *                call the school method to solve it
     * @param m       the second matrix to multiply the matrix with
     * @param verbose binary value to determine if the result should be printed
     * @return the result of the product as a matrix object, if the matrices
     * do not have the same size it returns null.
     */
    public Matrix multStr(int limit, Matrix m, boolean verbose) {
        Matrix m1 = expandMatrix(this);
        Matrix m2 = expandMatrix(m);
        Matrix result = new Matrix(m1.getMatrixSize());

        if (m1.getMatrixSize() != m2.getMatrixSize()) {
            return null;
        } else if (limit >= result.getMatrixSize()) {
            return m1.multSch(m2, verbose);
        } else {
            Matrix inter1 = m1.getQuarterMatrix(0, 1).subtr(
                    m1.getQuarterMatrix(1, 1)).multStr(
                            limit, m2.getQuarterMatrix(1, 0).add(
                                    m2.getQuarterMatrix(1, 1)), verbose);
            Matrix inter2 = m1.getQuarterMatrix(0, 0).add(
                    m1.getQuarterMatrix(1, 1)).multStr(
                            limit, m2.getQuarterMatrix(0, 0).add(
                                    m2.getQuarterMatrix(1, 1)), verbose);
            Matrix inter3 = m1.getQuarterMatrix(0, 0).subtr(
                    m1.getQuarterMatrix(1, 0)).multStr(
                            limit, m2.getQuarterMatrix(0, 0).add(
                                    m2.getQuarterMatrix(0, 1)), verbose);
            Matrix inter4 = m1.getQuarterMatrix(0, 0).add(
                    m1.getQuarterMatrix(0, 1)).multStr(
                            limit, m2.getQuarterMatrix(1, 1), verbose);
            Matrix inter5 = m1.getQuarterMatrix(0, 0).multStr(limit,
                    m2.getQuarterMatrix(0, 1).subtr(m2.getQuarterMatrix(1,
                            1)), verbose);
            Matrix inter6 = m1.getQuarterMatrix(1, 1).multStr(limit,
                    m2.getQuarterMatrix(1, 0).subtr(m2.getQuarterMatrix(0,
                            0)), verbose);
            Matrix inter7 = m1.getQuarterMatrix(1, 0).add(
                    m1.getQuarterMatrix(1, 1)).multStr(
                            limit, m2.getQuarterMatrix(0, 0), verbose);

            Matrix resultQuarter00 = inter1.add(inter2).subtr(
                    inter4).add(inter6);
            Matrix resultQuarter01 = inter4.add(inter5);
            Matrix resultQuarter10 = inter6.add(inter7);
            Matrix resultQuarter11 = inter2.subtr(inter3).add(
                    inter5).subtr(inter7);

            int halfSize = result.getMatrixSize() / 2;

            for (int i = 0; i < halfSize; i++) {
                for (int j = 0; j < halfSize; j++) {
                    result.setValue(i, j, resultQuarter00.getValue(i, j));
                    result.setValue(i, j + halfSize,
                            resultQuarter01.getValue(i, j));
                    result.setValue(i + halfSize, j,
                            resultQuarter10.getValue(i, j));
                    result.setValue(i + halfSize, j + halfSize,
                                    resultQuarter11.getValue(i, j));
                }
            }
            result = trimMatrix(result, result.getMatrixSize()
                    - this.getMatrixSize());

            if (verbose) {
                result.printMatrix();
                System.out.println();
            }
        }
        return result;
    }

    /**
     * This method takes another matrix object and adds it to this object.
     *
     * @param m the matrix object to be added
     * @return a matrix object which has the input matrix added from the
     * matrix calling this method
     */
    private Matrix add(Matrix m) {
        Matrix result = new Matrix(this.getMatrixSize());

        for (int i = 0; i < result.getMatrixSize(); i++) {
            for (int j = 0; j < result.getMatrixSize(); j++) {
                result.setValue(i, j, this.getValue(i, j) + m.getValue(i, j));
            }
        }
        return result;
    }

    /**
     * This method takes another matrix object and subtracts it from the this
     * object.
     *
     * @param m the matrix object to be subtracted
     * @return a matrix object which has the input matrix subtracted from the
     * matrix calling this method
     */
    private Matrix subtr(Matrix m) {
        Matrix result = new Matrix(this.getMatrixSize());

        for (int i = 0; i < result.getMatrixSize(); i++) {
            for (int j = 0; j < result.getMatrixSize(); j++) {
                result.setValue(i, j, this.getValue(i, j) - m.getValue(i, j));
            }
        }
        return result;
    }

    /**
     * This method takes a matrix and returns a matrix that has a size to the
     * power of two. The content of the input matrix is copied into the return
     * matrix with any new cells containing zeros.
     *
     * @param m the matrix object to be inflated
     * @return a matrix object with the size to the power of two or the input
     * matrix if it was already of a size to the power of two
     */
    private Matrix expandMatrix(Matrix m) {

        if (m.getMatrixSize() == nextPowerOfTwo(m.getMatrixSize())) {
            return m;
        }
        Matrix result = new Matrix(nextPowerOfTwo(m.getMatrixSize()));

        for (int i = 0; i < result.getMatrixSize(); i++) {
            for (int j = 0; j < result.getMatrixSize(); j++) {
                if (j < m.getMatrixSize() && i < m.getMatrixSize()) {
                    result.setValue(i, j, m.getValue(i, j));
                } else {
                    result.setValue(i, j, 0);
                }
            }
        }
        return result;
    }

    /**
     * This method takes a matrix object and cuts the right side and the
     * bottom by a give size. It is used to resize matrices that were
     * previously inflated to a size of a power of two to be able to perform
     * a Strassen Multiplication on them.
     *
     * @param m       the matrix object to be trimmed
     * @param cutSize the amount of rows and columns to be cut
     * @return a quadratic matrix object with a smaller size than the input
     */
    private Matrix trimMatrix(Matrix m, int cutSize) {
        Matrix result = new Matrix(m.getMatrixSize() - cutSize);

        for (int i = 0; i < result.getMatrixSize(); i++) {
            for (int j = 0; j < result.getMatrixSize(); j++) {
                result.setValue(i, j, m.getValue(i, j));
            }
        }
        return result;
    }
}
