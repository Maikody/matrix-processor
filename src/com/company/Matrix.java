package com.company;

import java.util.Scanner;

public class Matrix {

    private String[][] matrixData;
    private int rows;
    private int columns;

    public Matrix() {
    }

    public Matrix(Scanner scanner) {
        setMatrixDimensions(getUserMatrixDimensions(scanner));
        this.matrixData = new String[rows][columns];
    }

    public String[][] getMatrixData() {
        return matrixData;
    }

    public void setMatrixData(String[][] matrixData) {
        this.matrixData = matrixData;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public String[] getUserMatrixDimensions(Scanner scanner) {
        return scanner.nextLine().trim().split("\\s+");
    }

    public void setMatrixDimensions(String[] userInputDimensions) throws ArrayIndexOutOfBoundsException {
        this.rows = Integer.parseInt(userInputDimensions[0]);
        this.columns = Integer.parseInt(userInputDimensions[1]);
    }

    public void fillMatrixWithUserInput(Scanner scanner) {
        if (matrixData != null) {
            for (int i = 0; i < matrixData.length; i++) {
                String[] matrixRow = getMatrixRowElements(scanner);
                matrixData[i] = matrixRow;
            }
        }
    }

    public String[] getMatrixRowElements(Scanner scanner) {
        return scanner.nextLine().trim().split("\\s+");
    }

    public boolean isFractional(String num) {
        return num.matches("-*\\w*\\.\\w+");
    }

//    private int[] transformStringRowToIntegerRow(String[] rowElements) {
//        int[] matrixRowIntegers = new int[rowElements.length];
//        for (int n = 0; n < rowElements.length; n++) {
//            matrixRowIntegers[n] = Integer.parseInt(rowElements[n]);
//        }
//        return matrixRowIntegers;
//    }
//
//    private double[] transformStringRowToDoubleRow(String[] rowElements) {
//        double[] matrixRowDoubles = new double[rowElements.length];
//        for (int n = 0; n < rowElements.length; n++) {
//            matrixRowDoubles[n] = Double.parseDouble(rowElements[n]);
//        }
//        return matrixRowDoubles;
//    }

    public Matrix sumMatrices(Matrix matrix) {
        if (checkIfMatricesAreTheSame(matrix)) {
            Matrix result = new Matrix();
            result.matrixData = new String[matrixData.length][matrixData[0].length];
            for (int row = 0; row < matrixData.length; row++) {
                for (int column = 0; column < matrixData[row].length; column++) {
                    if (isFractional(matrixData[0][0]) || isFractional(matrix.matrixData[0][0])) {
                        result.matrixData[row][column] = String.valueOf(Double.parseDouble(matrixData[row][column])
                                + Double.parseDouble(matrix.matrixData[row][column]));
                    } else {
                        result.matrixData[row][column] = String.valueOf(Integer.parseInt(matrixData[row][column])
                                + Integer.parseInt(matrix.matrixData[row][column]));
                    }
                }
            }
            return result;
        }
        return null;
    }

    public boolean checkIfMatricesAreTheSame(Matrix matrix) {
        return this.rows == matrix.rows && this.columns == matrix.columns;
    }

    public Matrix multiplyByNumber(Scanner scanner) {
        Matrix multipliedMatrix = new Matrix();
        int multiplicationFactor = getMultiplicationFactor(scanner);
        multipliedMatrix.matrixData = matrixData;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (isFractional(matrixData[0][0])) {
                    multipliedMatrix.matrixData[row][column] =
                            String.valueOf(multiplicationFactor * Double.parseDouble(matrixData[row][column]));
                } else {
                    multipliedMatrix.matrixData[row][column] =
                            String.valueOf(multiplicationFactor * Integer.parseInt(matrixData[row][column]));
                }
            }
        }
        return multipliedMatrix;
    }

    public Matrix multiplyByNumber(double number) {
        Matrix multipliedMatrix = new Matrix();
        multipliedMatrix.matrixData = matrixData;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (isFractional(matrixData[0][0])) {
                    double result = number * Double.parseDouble(matrixData[row][column]);
                    if(result == -0.0) {
                        result = 0.0;
                    }
                    multipliedMatrix.matrixData[row][column] = String.valueOf(result);
                } else {
                    multipliedMatrix.matrixData[row][column] =
                            String.valueOf(number * Integer.parseInt(matrixData[row][column]));
                }
            }
        }
        return multipliedMatrix;
    }

    private int getMultiplicationFactor(Scanner scanner) {
        return Integer.parseInt(scanner.nextLine().trim());
    }

    public Matrix multiplyByMatrix (Matrix matrix) {
        if (checkIfMatricesCanBeMultiplied(matrix)) {
            Matrix multipliedMatrix = new Matrix();
            multipliedMatrix.matrixData = new String[rows][matrix.columns];
            int iterator = 0;
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < matrix.columns; column++) {
                    double dotProductInFractions = 0.0;
                    int dotProduct = 0;
                    while (iterator < columns) {
                        if (isFractional(this.matrixData[0][0]) || isFractional((matrix.matrixData[0][0]))) {
                            dotProductInFractions += Double.parseDouble(this.matrixData[row][iterator])
                                                                    * Double.parseDouble(matrix.matrixData[iterator][column]);
                            multipliedMatrix.matrixData[row][column] = String.valueOf(dotProductInFractions);
                        } else {
                            dotProduct += Integer.parseInt(this.matrixData[row][iterator])
                                    * Integer.parseInt(matrix.matrixData[iterator][column]);
                            multipliedMatrix.matrixData[row][column] = String.valueOf(dotProduct);
                        }
                        iterator++;
                    }
                    iterator = 0;
                }
            }
            return multipliedMatrix;
        }
        return null;
    }

    public boolean checkIfMatricesCanBeMultiplied(Matrix matrix) {
        return this.columns == matrix.rows;
    }

    public Matrix inverseMatrix() {
        Matrix inverseMatrix = new Matrix();
        inverseMatrix.setMatrixData(matrixData);
        inverseMatrix.setRows(rows);
        inverseMatrix.setColumns(columns);

        boolean isMatrixFractional = inverseMatrix.isFractional(inverseMatrix.getMatrixData()[0][0]);

        Determinant determinant = new Determinant();
        double det;
        if (isMatrixFractional) {
            det = determinant.detDouble(inverseMatrix, rows);
        } else {
            det = determinant.detInt(inverseMatrix, rows);
        }
        if (det == 0) {
            System.out.println("The matrix doesn't have an inverse.");
            return null;
        }

        Matrix cofactorMatrix = determinant.cofactorMatrix(inverseMatrix, rows, isMatrixFractional);
        return cofactorMatrix.multiplyByNumber(1 / det);
    }
}