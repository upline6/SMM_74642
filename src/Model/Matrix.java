package Model;

public class Matrix {

    private int matrixSize;
    private double[][] matrixContent;

    public Matrix(int size) {
        matrixSize = size;
        matrixContent = new double[matrixSize][matrixSize];
    }

    /**
     * This method takes a number i and computes the next bigger integer that
     * is 2^n.
     *
     * @param i: input number
     * @return next bigger integer to the base 2
     */
    private static int nextPowerOfTwo(int i) {
        return (int) Math.pow(2, Math.ceil(Math.log(i) / Math.log
                (2)));
    }

    public int getMatrixSize() {
        return matrixSize;
    }

    private double getValue(int row, int column) {
        return matrixContent[row][column];
    }

    public void setValue(int row, int column, double value) {
        matrixContent[row][column] = value;
    }

    private void addToValue(int row, int column, double value) {
        matrixContent[row][column] += value;
    }


    /**
     * @return
     */
    private Matrix getQuarterMatrix(int row, int column) {
        Matrix result = new Matrix(this.getMatrixSize() / 2);
        for (int i = 0; i < result.getMatrixSize(); i++) {
            for (int j = 0; j < result.getMatrixSize(); j++) {
                result.setValue(i, j, this.getValue
                        (i + (row * result.getMatrixSize()), j + (column *
                                result.getMatrixSize())));
            }
        }
        return result;
    }

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
     * @param m
     * @return
     */
    private Matrix multSch(Matrix m, boolean verbose) {
        Matrix result = new Matrix(this.getMatrixSize());
        if (this.getMatrixSize() != m.getMatrixSize()) {
            return null;
        }
        for (int i = 0; i < result.getMatrixSize(); i++) {
            for (int j = 0; j < result.getMatrixSize(); j++)
                for (int k = 0; k < result.getMatrixSize(); k++) {
                    result.addToValue
                            (i, j, this.getValue(i, k) * m.getValue(k, j));
                }
        }
        if (verbose) {
            result.printMatrix();
            System.out.println();
        }
        return result;
    }

    /**
     * @param m
     * @return
     */
    public Matrix multStr(int limit, Matrix m, boolean verbose) {
        Matrix m1 = expandMatrix(this);
        Matrix m2 = expandMatrix(m);
        Matrix result = new Matrix(m1.getMatrixSize());
        if (m1.getMatrixSize() != m2.getMatrixSize()) {
            return null;
        } else if (limit == result.getMatrixSize()) {
            return m1.multSch(m2, verbose);
        } else {
            Matrix inter1 = m1.getQuarterMatrix(0, 1).subtr
                    (m1.getQuarterMatrix(1, 1)).multStr
                    (limit, m2.getQuarterMatrix(1, 0).add(m2.getQuarterMatrix
                            (1, 1)), verbose);
            Matrix inter2 = m1.getQuarterMatrix(0, 0).add
                    (m1.getQuarterMatrix(1, 1)).multStr
                    (limit, m2.getQuarterMatrix(0, 0).add(m2.getQuarterMatrix
                            (1, 1)), verbose);
            Matrix inter3 = m1.getQuarterMatrix(0, 0).subtr
                    (m1.getQuarterMatrix(1, 0)).multStr
                    (limit, m2.getQuarterMatrix(0, 0).add(m2.getQuarterMatrix
                            (0, 1)), verbose);
            Matrix inter4 = m1.getQuarterMatrix(0, 0).add
                    (m1.getQuarterMatrix(0, 1)).multStr
                    (limit, m2.getQuarterMatrix(1, 1), verbose);
            Matrix inter5 = m1.getQuarterMatrix(0, 0).multStr(limit,
                    m2.getQuarterMatrix(0, 1).subtr(m2.getQuarterMatrix(1,
                            1)), verbose);
            Matrix inter6 = m1.getQuarterMatrix(1, 1).multStr(limit,
                    m2.getQuarterMatrix(1, 0).subtr(m2.getQuarterMatrix(0,
                            0)), verbose);
            Matrix inter7 = m1.getQuarterMatrix(1, 0).add
                    (m1.getQuarterMatrix(1, 1)).multStr
                    (limit, m2.getQuarterMatrix(0, 0), verbose);

            Matrix resultQuarter00 = inter1.add(inter2).subtr(inter4).add
                    (inter6);
            Matrix resultQuarter01 = inter4.add(inter5);
            Matrix resultQuarter10 = inter6.add(inter7);
            Matrix resultQuarter11 = inter2.subtr(inter3).add(inter5).subtr
                    (inter7);

            int halfSize = result.getMatrixSize() / 2;
            for (int i = 0; i < halfSize; i++) {
                for (int j = 0; j < halfSize; j++) {
                    result.setValue(i, j, resultQuarter00.getValue(i, j));
                    result.setValue
                            (i, j + halfSize, resultQuarter01.getValue(i, j));
                    result.setValue
                            (i + halfSize, j, resultQuarter10.getValue(i, j));
                    result.setValue
                            (i + halfSize, j + halfSize,
                                    resultQuarter11.getValue(i, j));
                }
            }
            result = trimMatrix(result, result.getMatrixSize() -
                    this.getMatrixSize());
            if (verbose) {
                result.printMatrix();
                System.out.println();
            }
        }
        return result;
    }

    private Matrix add(Matrix m) {
        Matrix result = new Matrix(this.getMatrixSize());
        for (int i = 0; i < result.getMatrixSize(); i++) {
            for (int j = 0; j < result.getMatrixSize(); j++) {
                result.setValue(i, j, this.getValue(i, j) + m.getValue(i, j));
            }
        }
        return result;
    }

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
     * power of two. The content of the input matrix is filled into the return
     * matrix with any new cells containing zeros.
     *
     * @param m the input matrix with any size
     * @return a matrix object with the size to the power of two
     */
    private Matrix expandMatrix(Matrix m) {
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