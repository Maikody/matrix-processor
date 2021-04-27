package com.company.transpose;

import com.company.Matrix;

public class HorizontalLineTranspose implements TransposeMethod {
    @Override
    public Matrix transpose(Matrix matrix) {
        Matrix transposedMatrix = new Matrix();
        String[][] transposedMatrixData = new String[matrix.getRows()][matrix.getColumns()];
        int originalRowMaxIndex = matrix.getRows() - 1;

        for (int row = 0; row < matrix.getRows(); row++) {
            for (int column = 0; column < matrix.getColumns(); column++) {
                transposedMatrixData[row][column] = matrix.getMatrixData()[originalRowMaxIndex][column];
            }
            originalRowMaxIndex--;
        }

        transposedMatrix.setMatrixData(transposedMatrixData);
        return transposedMatrix;
    }
}
