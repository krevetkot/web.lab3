package labs.mbeans;

import jakarta.enterprise.context.*;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("pointCounter")
@SessionScoped
public class PointCounter implements PointCounterMBean, Serializable {
    private int totalScore;
    private int missedScore;

    @Override
    public int getTotalPoints() {
        return totalScore;
    }

    @Override
    public int getMissedPoints() {
        return missedScore;
    }

    @Override
    public void incrementPoints(boolean isHit) {
        totalScore++;
        if (!isHit){
            missedScore++;
            if (missedScore % 15 == 0){
                System.out.println("попа");
            }
        }
    }
}
