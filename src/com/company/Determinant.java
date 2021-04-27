package com.company;

import com.company.transpose.MainDiagonalTranspose;
import com.company.transpose.TransposeMethod;

import java.util.ArrayList;
import java.util.List;

public class Determinant {

    public int detInt(Matrix matrix, int n) {
        if (n == 2) {
            return Integer.parseInt(matrix.getMatrixData()[0][0]) * Integer.parseInt(matrix.getMatrixData()[1][1]) -
                    Integer.parseInt(matrix.getMatrixData()[0][1]) * Integer.parseInt(matrix.getMatrixData()[1][0]);
        }

        int sum = 0;

        for (int j = 0; j < n; j++) {

            Matrix newMatrix = new Matrix();
            newMatrix.setMatrixData(new String[n - 1][n - 1]);
            newMatrix.setRows(n - 1);
            newMatrix.setColumns(n - 1);

            int rowIterator = 0;
            int columnIterator = 0;

            for (int row = 1; row < matrix.getRows(); row++) {
                for (int column = 0; column < matrix.getColumns(); column++) {
                    if (j != column) {
                        newMatrix.getMatrixData()[rowIterator][columnIterator] = matrix.getMatrixData()[row][column];
                        columnIterator++;
                    }
                }
                rowIterator++;
                columnIterator = 0;
            }

            int a = (int) Math.pow(-1, 1 + j + 1) * Integer.parseInt(matrix.getMatrixData()[0][j]);

            sum += a * detInt(newMatrix, n - 1);
        }

        return sum;
    }

    public double detDouble(Matrix matrix, int n) {
        if (n == 2) {
            double result = Double.parseDouble(matrix.getMatrixData()[0][0]) * Double.parseDouble(matrix.getMatrixData()[1][1]) -
                    Double.parseDouble(matrix.getMatrixData()[0][1]) * Double.parseDouble(matrix.getMatrixData()[1][0]);
            if(result == -0.0) {
                result = 0.0;
            }
            return result;
        }

        double sum = 0;

        for (int j = 0; j < n; j++) {
            Matrix newMatrix = new Matrix();
            newMatrix.setMatrixData(new String[n - 1][n - 1]);
            newMatrix.setRows(n - 1);
            newMatrix.setColumns(n - 1);

            int rowIterator = 0;
            int columnIterator = 0;

            for (int row = 1; row < matrix.getRows(); row++) {
                for (int column = 0; column < matrix.getColumns(); column++) {
                    if (j != column) {
                        newMatrix.getMatrixData()[rowIterator][columnIterator] = matrix.getMatrixData()[row][column];
                        columnIterator++;
                    }
                }
                rowIterator++;
                columnIterator = 0;
            }

            double a = Math.pow(-1, 1 + j + 1) * Double.parseDouble(matrix.getMatrixData()[0][j]);
            sum += a * detDouble(newMatrix, n - 1);
        }


        return sum;
    }


    public Matrix cofactorMatrix(Matrix matrix, int n, boolean isFactorial) {
        Matrix cofactorMatrix = new Matrix();
        cofactorMatrix.setMatrixData(new String[matrix.getRows()][matrix.getColumns()]);
        cofactorMatrix.setRows(matrix.getRows());
        cofactorMatrix.setColumns(matrix.getColumns());

        TransposeMethod tm = new MainDiagonalTranspose();

        if (n == 2) {
            for (int row = 0; row < cofactorMatrix.getRows(); row++) {
                for (int column = 0; column < cofactorMatrix.getColumns(); column++) {
                    cofactorMatrix.getMatrixData()[row][column] = String.valueOf(
                            Math.pow(-1, row + 1 + column + 1) * Double.parseDouble(matrix.getMatrixData()[row == 0 ? 1 : 0][column == 0 ? 1 : 0]));
                }
            }
            return tm.transpose(cofactorMatrix);
        } else {
            int iterator = 0;

            List<Double> cofactors = new ArrayList<>();

            if (isFactorial) {
                getCofactorDouble(matrix, cofactorMatrix.getRows(), cofactors);
            } else {
                getCofactorInt(matrix, cofactorMatrix.getRows(), cofactors);
            }

            for (int row = 0; row < cofactorMatrix.getRows(); row++) {
                for (int column = 0; column < cofactorMatrix.getColumns(); column++) {
                    cofactorMatrix.getMatrixData()[row][column] =
                            String.valueOf(cofactors.get(iterator));
                    iterator ++;
                }
            }
        }

        return tm.transpose(cofactorMatrix);
    }

    public void getCofactorDouble(Matrix matrix, int n, List<Double> cofactors) {

        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j++) {
                Matrix newMatrix = new Matrix();
                newMatrix.setMatrixData(new String[n - 1][n - 1]);
                newMatrix.setRows(n - 1);
                newMatrix.setColumns(n - 1);
                int rowIterator = 0;
                int columnIterator = 0;

                for (int row = 0; row < matrix.getRows(); row++) {
                    if (i != row) {
                        for (int column = 0; column < matrix.getColumns(); column++) {
                            if (j != column) {
                                newMatrix.getMatrixData()[rowIterator][columnIterator] = matrix.getMatrixData()[row][column];
                                columnIterator++;
                            }
                        }
                        rowIterator++;
                        columnIterator = 0;
                    }
                }

                double cofactor = Math.pow(-1, i + 1 + j + 1) * detDouble(newMatrix, newMatrix.getRows());
                cofactors.add(cofactor);
            }
        }

    }

    public void getCofactorInt(Matrix matrix, int n, List<Double> cofactors) {

        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j++) {
                Matrix newMatrix = new Matrix();
                newMatrix.setMatrixData(new String[n - 1][n - 1]);
                newMatrix.setRows(n - 1);
                newMatrix.setColumns(n - 1);

                int rowIterator = 0;
                int columnIterator = 0;

                for (int row = 0; row < matrix.getRows(); row++) {
                    if (i != row) {
                        for (int column = 0; column < matrix.getColumns(); column++) {
                            if (j != column) {
                                newMatrix.getMatrixData()[rowIterator][columnIterator] = matrix.getMatrixData()[row][column];
                                columnIterator++;
                            }
                        }
                        rowIterator++;
                        columnIterator = 0;
                    }
                }

                double cofactor = Math.pow(-1, i + 1 + j + 1) * detInt(newMatrix, n - 1);
                cofactors.add(cofactor);
            }
        }

    }
}
