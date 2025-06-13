package labs.mbeans;

public interface PointCounterMBean {
    int getTotalPoints();
    int getMissedPoints();
    void incrementPoints(boolean isHit);
}
