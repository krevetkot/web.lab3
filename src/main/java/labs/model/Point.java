package labs.model;

public class Point {
    private float x;
    private float y;
    private float r;
    private boolean isHit;

    public Point(float x, float y, float r, boolean isHit){
        this.x = x;
        this.y = y;
        this.r = r;
        this.isHit = isHit;
    }

    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public float getR(){
        return r;
    }
    public boolean getIsHit(){
        return isHit;
    }
}
