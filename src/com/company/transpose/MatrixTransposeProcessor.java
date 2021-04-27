package com.company.transpose;

import com.company.Matrix;

public class MatrixTransposeProcessor {

    private TransposeMethod transposeMethod;

    public void setTransposeMethod(TransposeMethod transposeMethod) {
        this.transposeMethod = transposeMethod;
    }

    public Matrix transposeMatrix(Matrix matrix) {
        return this.transposeMethod.transpose(matrix);
    }

}
