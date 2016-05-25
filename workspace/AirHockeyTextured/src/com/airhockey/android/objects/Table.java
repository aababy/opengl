package com.airhockey.android.objects;

import com.airhockey.android.data.VertexArray;
import com.airhockey.android.*;
import static android.opengl.GLES20.*;
import static android.opengl.Matrix.*;

public class Table {
    private final VertexArray vertexArray;
    
    private static final int POSITION_COMPONENT_COUNT = 4;    
    private static final int COLOR_COMPONENT_COUNT = 3;    
    private static final int TEXTURE_COORDINATES_COMPONENT_COUNT = 2;    
    
    private static final int BYTES_PER_FLOAT = 4;
    
    private static final int STRIDE = 
        (POSITION_COMPONENT_COUNT + COLOR_COMPONENT_COUNT) * BYTES_PER_FLOAT;    
    
    private static final float[] VERTEX_DATA = {        
        0f, 0f, 0.5f, 0.5f,
        -0.5f, -0.8f, 0f, 0.9f,
        0.5f, -0.8f, 1f, 0.9f,
        0.5f, 0.8f, 1f, 0.1f,
        -0.5f, 0.8f, 0f, 0.1f,
        -0.5f, -0.8f, 0f, 0.9f };
    
    public Table() {
        vertexArray = new VertexArray(VERTEX_DATA);
    }
    
    public void bindData(TextureShaderProgram textureProgram) {
        vertexArray.setVertexAttribPointer(
            0, 
            textureProgram.getPositionAttributeLocation(), 
            POSITION_COMPONENT_COUNT, 
            STRIDE); 
        
        vertexArray.setVertexAttribPointer(
            POSITION_COMPONENT_COUNT, textureProgram.getTextureCoordinatesAttributeLocation(), TEXTURE_COORDINATES_COMPONENT_COUNT, STRIDE);
    }
    
    public void draw() {
        glDrawArrays(GL_TRIANGLE_FAN, 0, 6);        
    }
}


