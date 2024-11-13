package labs.util;

import labs.model.Point;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Validator {

    public boolean isHit(float x, float y, float r) {
        return ((x >= 0) && (x <= r/2) && (y >= 0) && (y <= r) || //in rectangle
                (x <= 0) && (y <= x + r) && (y >= 0) || //in triangle
                (x * x + y * y <= (r/2) * (r/2)) && (x <= 0) && (y <= 0) //in circle
        );
    }

    public boolean validateParams(LinkedHashMap<String, Float> params) {
        return validateY(params.get("y")) && validateR(params.get("r"));
    }

//    private boolean validateX(float x) {
//        List<Float> xValues = new ArrayList<>(9);
//        xValues.add(-2f);
//        xValues.add(-1.5f);
//        xValues.add(-1f);
//        xValues.add(-0.5f);
//        xValues.add(0f);
//        xValues.add(0.5f);
//        xValues.add(1f);
//        xValues.add(1.5f);
//        xValues.add(2f);
//        return xValues.contains(x);
//    }

    private boolean validateY(float y) {
        return (y < 3 && y > -5);
    }

    private boolean validateR(float r) {
        List<Float> rValues = new ArrayList<>(5);
        rValues.add(1f);
        rValues.add(1.5f);
        rValues.add(2f);
        rValues.add(2.5f);
        rValues.add(3f);
        return rValues.contains(r);
    }


}
