package Model;

public class Matrix {

    private int matrixSize;
    private double[][] matrixContent;

    public Matrix(int size) {
        matrixSize = size;
        matrixContent = new double[matrixSize][matrixSize];
    }

    public Matrix(int size, int xa, int xb){
        matrixSize = raiseToPowerOfTwo(size);
        matrixContent = new double[matrixSize][matrixSize];
        initializeMatrix();
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrixContent[i][j] = i*xa + j*xb;
            }
        }
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
        System.out.println("");
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

    public void addToValue(int row, int column, int value) {
        matrixContent[row][column] += value;
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
