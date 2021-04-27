package com.company.transpose;

import com.company.Matrix;

public class SideDiagonalTranspose implements TransposeMethod {
    @Override
    public Matrix transpose(Matrix matrix) {
        Matrix transposedMatrix = new Matrix();
        String[][] transposedMatrixData = new String[matrix.getColumns()][matrix.getRows()];
        int originalRowMaxIndex = matrix.getRows() - 1;
        int originalColumnMaxIndex = matrix.getColumns() - 1;

        for (int row = 0; row < matrix.getColumns(); row++) {
            for (int column = 0; column < matrix.getRows(); column++) {
                transposedMatrixData[row][column] = matrix.getMatrixData()[originalRowMaxIndex][originalColumnMaxIndex];
                originalRowMaxIndex--;
            }
            originalColumnMaxIndex--;
            originalRowMaxIndex = matrix.getRows() - 1;
        }

        transposedMatrix.setMatrixData(transposedMatrixData);
        return transposedMatrix;
    }
}
