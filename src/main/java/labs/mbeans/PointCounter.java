package labs.mbeans;

import jakarta.enterprise.context.*;
import jakarta.inject.Named;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import java.io.Serializable;

@Named("pointCounter")
@SessionScoped
public class PointCounter extends NotificationBroadcasterSupport implements PointCounterMBean, Serializable {
    private int totalScore;
    private int missedScore;
    private long msgNumber = 1;

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
        }
        if (totalScore % 15 == 0){
            Notification notification = new Notification(
                    "points.multiple_of_15",
                    this,
                    msgNumber++,
                    System.currentTimeMillis(),
                    "Total points reached: " + totalScore
            );

            sendNotification(notification);
        }
    }
}
