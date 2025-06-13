package labs.util;

import labs.mbeans.*;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class MBeanRegister {
    private static final ObjectName pointsCounterName;
    private static final ObjectName hitPercentageCounterName;
    private static final MBeanServer mbeanServer = ManagementFactory.getPlatformMBeanServer();

    static {
        try {
            pointsCounterName = new ObjectName("labs.mbeans:type=pointCounter");
            hitPercentageCounterName = new ObjectName("labs.mbeans:type=hitPercentageCounter");
        } catch (MalformedObjectNameException e) {
            throw new RuntimeException(e);
        }
    }

    public static void registerPointCounterMBean(Object bean) {
        try {
            mbeanServer.registerMBean(bean, pointsCounterName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void registerHitPercentageCounterMBean(Object bean) {
        try {
            mbeanServer.registerMBean(bean, hitPercentageCounterName);
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
