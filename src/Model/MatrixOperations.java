//package Model;
//
//public class MatrixOperations {
//
//    /**
//     * Interim result
//     * @param a
//     * @param b
//     * @param limit
//     * @return
//     */
//    public Matrix multStr (Matrix a, Matrix b, int limit) {
//        Matrix result = new Matrix(a.getMatrixSize());
//        if (limit == result.getMatrixSize()){ //evtl +1
//            multSch(a, b);
//        } else {
//            Matrix inter1 = multStr(subtract(a.getQuarterMatrix(0, 1),
//                    a.getQuarterMatrix(1, 1)), add(b.getQuarterMatrix(1,
//                    0), b.getQuarterMatrix(1, 1)), limit);
//            Matrix inter2 = multStr(add(a.getQuarterMatrix(0, 0), a
//                            .getQuarterMatrix(1, 1)), add(b.getQuarterMatrix
//                            (0, 0), b.getQuarterMatrix(1, 1)),
//                    limit);
//            Matrix inter3 = multStr(subtract(a.getQuarterMatrix(0, 0)
//                    , a.getQuarterMatrix(1, 0)), add(b.getQuarterMatrix(0,
//                    0), b.getQuarterMatrix(0, 1)), limit);
//            Matrix inter4 = multStr(add(a.getQuarterMatrix(0, 0), a
//                    .getQuarterMatrix(0, 1)),b.getQuarterMatrix(1, 1),
//                    limit);
//            Matrix inter5 = multStr(a.getQuarterMatrix(0, 0),
//                    subtract(b.getQuarterMatrix(0, 1), b.getQuarterMatrix
//                            (1, 1)),limit);
//            Matrix inter6 = multStr(a.getQuarterMatrix(1, 1),
//                    subtract(b.getQuarterMatrix(1, 0), b.getQuarterMatrix
//                            (0, 0)), limit);
//            Matrix inter7 = multStr(add(a.getQuarterMatrix(1, 0), a
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