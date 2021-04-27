package com.company.transpose;

import com.company.Matrix;

public class MainDiagonalTranspose implements TransposeMethod {
    @Override
    public Matrix transpose(Matrix matrix) {
        Matrix transposedMatrix = new Matrix();
        int rows = matrix.getRows();
        int columns = matrix.getColumns();
        String[][] transposedMatrixData = new String[matrix.getColumns()][matrix.getRows()];

        for (int row = 0; row < matrix.getColumns(); row++) {
            for (int column = 0; column < matrix.getRows(); column++) {
                transposedMatrixData[row][column] = matrix.getMatrixData()[column][row];
            }
        }

        transposedMatrix.setMatrixData(transposedMatrixData);
        transposedMatrix.setRows(rows);
        transposedMatrix.setColumns(columns);

        return transposedMatrix;
    }
}
