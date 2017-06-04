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
     * if condition noch einbauen, private machen
     * @param m
     * @return
     */
    public Matrix multSch(Matrix m) {
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
    public Matrix multStr(int limit, Matrix m) {
        Matrix result = new Matrix(this.getMatrixSize());
        Matrix m1 = expandMatrix(this);
        Matrix m2 = expandMatrix(m);
        if (m1.getMatrixSize() != m2.getMatrixSize()) {
            return null;
        } else if (limit == result.getMatrixSize()) {
            return m1.multSch(m2);
        } else {
//            Matrix inter1 = multStr(subtract(a.getQuarterMatrix(0, 1),
//                    a.getQuarterMatrix(1, 1)), add(b.getQuarterMatrix(1,
//                    0), b.getQuarterMatrix(1, 1)), limit);
//            Matrix inter1 = m1.multStr(limit,    m1.getQuarterMatrix(0,1))

        }
        return result;
    }

    public Matrix add(Matrix m) {
        Matrix result = new Matrix(this.getMatrixSize());
        for (int i = 0; i < result.getMatrixSize(); i++) {
            for (int j = 0; j < result.getMatrixSize(); j++) {
                result.setValue(i, j, this.getValue(i, j) + m.getValue(i, j));
            }
        }
        return result;
    }

    /**
     * This method takes a matrix and returns a matrix that has a size to the
     * power of two. The content of the input matrix is filled into the return
     * matrix with any new cells containing zeros.
     * @param m the input matrix with any size
     * @return a matrix object with the size to the power of two
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
     * @param i: input number
     * @return next bigger integer to the base 2
     */
    public static int nextPowerOfTwo(int i) {
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
//                System.out.print(result.getValue(i, j) + " ");
            }
        }
        return result;
    }
}
