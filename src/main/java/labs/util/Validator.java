package labs.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Validator {

    public boolean isHit(float x, float y, float r) {
        return ((x >= 0) && (x <= r/2) && (y >= 0) && (y <= r) || //in rectangle
                (x>=0) && (y >= x * r/2) && (y <= 0) || //in triangle
                (x * x + y * y <= (r/2) * (r/2)) && (x <= 0) && (y <= 0) //in circle
        );
    }

//    public boolean validateParams(LinkedHashMap<String, Float> params) {
//        return validateY(params.get("y")) && validateR(params.get("r"));
//    }
//
//    private boolean validateX(float x) {
//        return x<5 && x>-3;
//    }
//
//    private boolean validateY(float y) {
//        List<Float> yValues = new ArrayList<>(8);
//        float i = -2f;
//        while (i<1.5f){
//            yValues.add(i);
//            i += 0.5f;
//        }
//        return yValues.contains(y);
//    }
//
//    private boolean validateR(float r) {
//        List<Float> rValues = new ArrayList<>(31);
//        float i = 2f;
//        while (i<5f){
//            rValues.add(i);
//            i += 0.1f;
//        }
//        return rValues.contains(r);
//    }
}
