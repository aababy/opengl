package com.airhockey.android.util;

import static android.opengl.GLES20.*;
import android.util.Log;

public class ShaderHelper {
    private static final String TAG = "ShaderHelper";

    public static int compileVertexShader(String shaderCode) {
        return compileShader(GL_VERTEX_SHADER, shaderCode);
    }

    public static int compileFragmentShader(String shaderCode) {
        return compileShader(GL_FRAGMENT_SHADER, shaderCode);
    }

    private static int compileShader(int type, String shaderCode) {
        final int shaderObjectId = glCreateShader(type);
        if (shaderObjectId == 0) {
            if (LoggerConfig.ON) {
                Log.w(TAG, "error");
            }   
            return 0;
        }
        
        glShaderSource(shaderObjectId, shaderCode);
        glCompileShader(shaderObjectId);
        
        final int[] compileStatus = new int[1];
        glGetShaderiv(shaderObjectId, GL_COMPILE_STATUS, compileStatus, 0);
        
        if (compileStatus[0] == 0) {
            glDeleteShader(shaderObjectId);
            Log.w("shader error", shaderCode);
            
            return 0;
        }
        
        return shaderObjectId;
    }
    
    public static int linkProgram(int vertexShaderId, int fragmentShaderId) {
        final int programObjectId = glCreateProgram();
        
        if (programObjectId == 0) {
            Log.w(TAG, "error");
        }
        
        glAttachShader(programObjectId, vertexShaderId);
        glAttachShader(programObjectId, fragmentShaderId); 
        
        glLinkProgram(programObjectId);
        
        final int[] linkStatus = new int[1];
        glGetProgramiv(programObjectId, GL_LINK_STATUS, linkStatus, 0);
        
        if (LoggerConfig.ON) {
            Log.v(TAG, "link \n" + glGetProgramInfoLog(programObjectId));
        }
        
        if (linkStatus[0] == 0) {
            glDeleteShader(programObjectId);
            Log.w(TAG, "link error");
            
            return 0;
        }
        
        return programObjectId;
    }
    
    public static boolean validateProgram(int programObjectId) {
        glValidateProgram(programObjectId);
        
        final int[] validateStatus = new int[1];
        glGetProgramiv(programObjectId, GL_VALIDATE_STATUS, validateStatus, 0);
        Log.v(TAG, "Results: " + validateStatus[0] + "\nLog: " + glGetProgramInfoLog(programObjectId));
        return validateStatus[0] != 0;
    }
}


