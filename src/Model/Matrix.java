package Model;

import static javafx.scene.input.KeyCode.M;

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
     * if condition noch einbauen, private machen
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
        System.out.println("School Method used!");
        return result;
    }

    /**
     * if Methode einbauen
     * @param m
     * @return
     */
//    public Matrix multStrassenMethod(int useSchoolMethod, Matrix m) {
//        Matrix result = new Matrix(this.getMatrixSize());
//        this = new M
//
//    }
    /**
     * This method fills the whole Matrix with zeros.
     */
    public Matrix expandMatrix(Matrix m) {
        Matrix result = new Matrix(nextPowerOfTwo(m.getMatrixSize()));
        for(int i = 0; i < result.getMatrixSize(); i++) {
            for(int j = 0; j < result.getMatrixSize(); j++) {
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
     * This method takes a number i and computes the next bigger integer that
     * is 2^n.
     * @param i
     * @return
     */

    public int nextPowerOfTwo(int i) {
        int powerTwo = (int) Math.pow(2, Math.ceil(Math.log(i) / Math.log
                (2)));
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
