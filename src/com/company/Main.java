package com.company;

import com.company.transpose.*;

import java.util.Scanner;

public class Main {

    static String fileName = "in.txt";
    static Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        printMenu();
        programLoop(Integer.parseInt(scanner.nextLine()));
    }

    public static void printMenu() {
        System.out.println("1. Add matrices");
        System.out.println("2. Multiply matrix by constant");
        System.out.println("3. Multiply matrices");
        System.out.println("4. Transpose matrix");
        System.out.println("5. Calculate determinant");
        System.out.println("6. Inverse matrix");
        System.out.println("0. Exit");
        System.out.println("Your choice:");
    }

    public static void programLoop(int choice) {
        while (choice != 0) {
            chooseOption(choice);
            printMenu();
            choice = Integer.parseInt(scanner.nextLine());
        }
    }

    public static void chooseOption(int userChoice) {
        Matrix matrixA;
        Matrix matrixB;

        switch (userChoice) {
            case 1:
                try {
                    matrixA = initializeMatrix();
                    matrixB = initializeMatrix();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("The operation cannot be performed.\n");
                    break;
                }

                Matrix sumOfMatrices = matrixA.sumMatrices(matrixB);
                printResult(sumOfMatrices);
                break;
            case 2:
                try {
                    matrixA = initializeMatrix();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("The operation cannot be performed.\n");
                    break;
                }

                System.out.println("Enter constant:");
                Matrix multipliedMatrixByConstant = matrixA.multiplyByNumber(scanner);

                printResult(multipliedMatrixByConstant);
                break;
            case 3:
                try {
                    matrixA = initializeMatrix();
                    matrixB = initializeMatrix();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("The operation cannot be performed.\n");
                    break;
                }

                Matrix multipliedMatrixByMatrix = matrixA.multiplyByMatrix(matrixB);
                printResult(multipliedMatrixByMatrix);
                break;
            case 4:
                printTransposeMenuOptions();
                MatrixTransposeProcessor transposeProcessor = new MatrixTransposeProcessor();
                Matrix transposedMatrix;

                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        transposeProcessor.setTransposeMethod(new MainDiagonalTranspose());
                        break;
                    case 2:
                        transposeProcessor.setTransposeMethod(new SideDiagonalTranspose());
                        break;
                    case 3:
                        transposeProcessor.setTransposeMethod(new VerticalLineTranspose());
                        break;
                    default:
                        transposeProcessor.setTransposeMethod(new HorizontalLineTranspose());
                        break;
                }

                try {
                    matrixA = initializeMatrix();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("The operation cannot be performed.\n");
                    break;
                }

                transposedMatrix = transposeProcessor.transposeMatrix(matrixA);
                printResult(transposedMatrix);
                break;
            case 5:
                try {
                    matrixA = initializeMatrix();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("The operation cannot be performed.\n");
                    break;
                }

                Determinant determinant = new Determinant();
                System.out.println("The result is: \n" +
                        (matrixA.isFractional(matrixA.getMatrixData()[0][0]) ? determinant.detDouble(matrixA, matrixA.getColumns()) :
                                determinant.detInt(matrixA, matrixA.getColumns())) + "\n");
                break;
            case 6:
                try {
                    matrixA = initializeMatrix();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("The operation cannot be performed.\n");
                    break;
                }

                System.out.println("The result is: \n");
                printResult(matrixA.inverseMatrix());
                break;
            default:
                break;
        }
    }

    public static Matrix initializeMatrix() {
        System.out.println("Enter size of matrix:");
        Matrix matrix = new Matrix(scanner);
        System.out.println("Enter matrix:");
        matrix.fillMatrixWithUserInput(scanner);
        return matrix;
    }

    public static void printResult(Matrix result) {
        if (result != null) {
            for (String[] array : result.getMatrixData()) {
                for (String value : array) {
                    System.out.print(value + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("ERROR");
        }
        System.out.println();
    }

    public static void printTransposeMenuOptions() {
        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
        System.out.println("Your choice:");
    }

}
