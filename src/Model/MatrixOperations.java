//package Model;
//
//public class MatrixOperations {
//
////    public Matrix multStrMethod1 (Matrix a, Matrix b, int limit) {
////        Matrix result = new Matrix(a.getMatrixSize());
////        while
////        return result;
////    }
//
//    /**
//     * Interim result
//     * @param a
//     * @param b
//     * @param limit
//     * @return
//     */
//    public Matrix multStrMethod (Matrix a, Matrix b, int limit) {
//        Matrix result = new Matrix(a.getMatrixSize());
//        if (limit == result.getMatrixSize()){ //evtl +1
//            multSchoolMethod(a, b);
//        } else {
//            Matrix inter1 = multStrMethod(subtract(a.getQuarterMatrix(0, 1),
//                    a.getQuarterMatrix(1, 1)), add(b.getQuarterMatrix(1,
//                    0), b.getQuarterMatrix(1, 1)), limit);
//            Matrix inter2 = multStrMethod(add(a.getQuarterMatrix(0, 0), a
//                            .getQuarterMatrix(1, 1)), add(b.getQuarterMatrix
//                            (0, 0), b.getQuarterMatrix(1, 1)),
//                    limit);
//            Matrix inter3 = multStrMethod(subtract(a.getQuarterMatrix(0, 0)
//                    , a.getQuarterMatrix(1, 0)), add(b.getQuarterMatrix(0,
//                    0), b.getQuarterMatrix(0, 1)), limit);
//            Matrix inter4 = multStrMethod(add(a.getQuarterMatrix(0, 0), a
//                    .getQuarterMatrix(0, 1)),b.getQuarterMatrix(1, 1),
//                    limit);
//            Matrix inter5 = multStrMethod(a.getQuarterMatrix(0, 0),
//                    subtract(b.getQuarterMatrix(0, 1), b.getQuarterMatrix
//                            (1, 1)),limit);
//            Matrix inter6 = multStrMethod(a.getQuarterMatrix(1, 1),
//                    subtract(b.getQuarterMatrix(1, 0), b.getQuarterMatrix
//                            (0, 0)), limit);
//            Matrix inter7 = multStrMethod(add(a.getQuarterMatrix(1, 0), a
//                    .getQuarterMatrix(1, 1)), b.getQuarterMatrix(0, 0),
//                    limit);
//            Matrix resultQuarter00 = subtract(add(add(inter1, inter2),inter6)
//                    , inter4);
//            Matrix resultQuarter01 = add(inter4, inter5);
//            Matrix resultQuarter10 = add(inter6, inter7);
//            Matrix resultQuarter11 = subtract(subtract(add(inter2, inter5),
//                    inter3), inter7);
//            int halfSize = result.getMatrixSize()/2;
//            for(int j = 0; j < halfSize; j++) {
//                for (int k = 0; k < halfSize; k++) {
//                    result.setValue(j, k, resultQuarter00.getValue(j, k));
//                    result.setValue(j + halfSize, k, resultQuarter00.getValue
//                            (j, k));
//                    result.setValue(j, k + halfSize, resultQuarter00.getValue(j, k));
//                    result.setValue(j + halfSize, k + halfSize,
//                            resultQuarter00.getValue(j, k));
//                }
//            }
//        }
//        return result;
//    }
//
//    public Matrix multSchoolMethod(Matrix a, Matrix b) {
//        Matrix result = new Matrix(a.getMatrixSize());
//        for (int i = 0; i < result.getMatrixSize(); i++) {
//            for (int j = 0; j < result.getMatrixSize(); j++)
//                for (int k = 0; k < result.getMatrixSize(); k++) {
//                    result.addToValue
//                            (i, j, a.getValue(i, k) * b.getValue(k, j));
//                }
//        }
//        return result;
//    }
//
//    public Matrix add(Matrix a, Matrix b) {
//        Matrix result = new Matrix(a.getMatrixSize());
//        for (int i = 0; i < result.getMatrixSize(); i++) {
//            for (int j = 0; j < result.getMatrixSize(); j++) {
//                result.setValue(i, j, a.getValue(i, j) + b.getValue(i, j));
//            }
//        }
//        return result;
//    }
//
//    public Matrix subtract(Matrix a, Matrix b) {
//        Matrix result = new Matrix(a.getMatrixSize());
//        for (int i = 0; i < result.getMatrixSize(); i++) {
//            for (int j = 0; j < result.getMatrixSize(); j++) {
//                result.setValue(i, j, a.getValue(i, j) - b.getValue(i, j));
//            }
//        }
//        return result;
//    }
//
//}