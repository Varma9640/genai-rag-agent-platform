package com.varma.vector;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SimilarityUtil {
    public double cosineSimilarity(float[] vector1, float[] vector2) {

        double dotProduct = 0.0;
        double normVector1 = 0.0;
        double normVector2 = 0.0;

        for (int index = 0; index < vector1.length; index++) {
            dotProduct += vector1[index] * vector2[index];
            normVector1 += Math.pow(vector1[index], 2);
            normVector2 += Math.pow(vector2[index], 2);
        }

        return dotProduct /
                (
                        Math.sqrt(normVector1)
                                * Math.sqrt(normVector2)
                );
    }
}
