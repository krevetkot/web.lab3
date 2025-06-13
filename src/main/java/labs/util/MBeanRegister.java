package labs.util;

import labs.mbeans.*;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class MBeanRegister {
    private static ObjectName pointsCounterName;
    private static ObjectName hitPercentageCounterName;

    static {
        try {
            pointsCounterName = new ObjectName("labs.mbeans:type=PointCounter");
            hitPercentageCounterName = new ObjectName("labs.mbeans:type=hitPercentageCounter");
        } catch (MalformedObjectNameException e) {
            throw new RuntimeException(e);
        }
    }

    public static void registerMBeans() {
        try {
            MBeanServer mbeanServer = ManagementFactory.getPlatformMBeanServer();

            // Регистрация PointsCounter
            PointCounterMBean pointsCounter = new PointCounter();
            mbeanServer.registerMBean(pointsCounter, pointsCounterName);

            // Регистрация HitPercentageCounter
            HitPercentageCounterMBean hitPercentage = new HitPercentageCounter();
            mbeanServer.registerMBean(hitPercentage, hitPercentageCounterName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ObjectName getPointCounterInstance() {
        return pointsCounterName;
    }

    public static ObjectName getHitPercentageCounterInstance() {
        return hitPercentageCounterName;
    }
}
