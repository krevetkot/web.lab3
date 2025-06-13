package labs.mbeans;

import jakarta.enterprise.context.*;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Named;
import labs.util.MBeanRegister;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import java.io.Serializable;

@Named("pointCounter")
@SessionScoped
public class PointCounter extends NotificationBroadcasterSupport implements PointCounterMBean, Serializable {
    private int totalScore;
    private int missedScore;
    private long msgNumber = 1;

    public void init(@Observes @Initialized(SessionScoped.class) Object unused) {
        MBeanRegister.registerPointCounterMBean(this);
    }

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
