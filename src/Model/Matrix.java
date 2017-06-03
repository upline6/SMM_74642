package Model;

public class Matrix {

    private int matrixSize;
    private double[][] matrixContent;

    public Matrix(int size) {
        matrixSize = size;
        matrixContent = new double[matrixSize][matrixSize];
    }

    public int getMatrixSize() {
        return matrixSize;
    }

    public double getValue(int row, int column) {
        return matrixContent[row][column];
    }

    public void setValue(int row, int column, double value) {
        matrixContent[row][column] = value;
    }

    private void addToValue(int row, int column, double value) {
        matrixContent[row][column] += value;
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
     * if condition noch einbauen
     * @param m
     * @return
     */
    public Matrix multSchoolMethod(Matrix m) {
        Matrix result = new Matrix(this.getMatrixSize());
//        if (this.getMatrixSize() != m.getMatrixSize()) {
//
//        }
        for (int i = 0; i < result.getMatrixSize(); i++) {
            for (int j = 0; j < result.getMatrixSize(); j++)
                for (int k = 0; k < result.getMatrixSize(); k++) {
                    result.addToValue
                            (i, j, this.getValue(i, k) * m.getValue(k, j));
                }
        }
        return result;
    }
    /**
     * This method fills the whole Matrix with zeros.
     */
    public void initializeMatrix() {
        for(int i = 0; i < matrixSize; i++) {
            for(int j = 0; j < matrixSize; j++) {
                matrixContent[i][j] = 0;
            }
        }
    }

    /**
     * This method takes a number i and computes the next bigger integer that
     * is 2^n.
     * @param i
     * @return
     */
    private int raiseToPowerOfTwo(int i) {
        int powerTwo = 2;
        while (i > powerTwo) {
            powerTwo *= 2;
        }
        return powerTwo;
    }

    /**
     *
     * @return
     */
    public Matrix getQuarterMatrix(int row, int column) {
        Matrix result = new Matrix(this.getMatrixSize() / 2);
        for(int i = 0; i < result.getMatrixSize(); i++) {
            for (int j = 0; j < result.getMatrixSize(); j++) {
                result.setValue(i, j, this.getValue
                        (i + (row * result.getMatrixSize()), j + (column *
                                result.getMatrixSize())));
                System.out.print(result.getValue(i, j) + " ");
            }
        }
        return result;
    }
}
