package com.airhockey.android.programs;

import static android.opengl.GLES20.*;
import static android.opengl.Matrix.*;

public class ColorShaderProgram {
    
    // Uniform constants
    protected static final String U_MATRIX = "u_Matrix";
    protected static final String U_TEXTURE_UNIT = "u_TextureUnit";
    // Attribute constants
    protected static final String A_POSITION = "a_Position";
    protected static final String A_COLOR = "a_Color";
    protected static final String A_TEXTURE_COORDINATES = "a_TextureCoordinates";
    
    private final int uMatrixLocation;
    
    private final int aPositionLocation;
    private final int aColorLocation;

    public ColorShaderProgram(Context context) {
        super(context, R.raw.simple_vertex_shader, 
            R.raw.simple_fragment_shader);
        
        uMatrixLocation = glGetUniformLocation(program, U_MATRIX);       
        
        aPositionLocation = glGetAttribLocation(program, A_POSITION);
        aColorLocation = glGetAttribLocation(program, A_COLOR);
    }
    
    public void setUniforms(float[] matrix) {
        glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);
    }
    
    public int getPositionAttributeLocation() {
        return aPositionLocation;
    }
    
    public int getColorAttributeLocation() {
        return aColorLocation;
    }
}
