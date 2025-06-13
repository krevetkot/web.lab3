package labs.mbeans;

import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import labs.util.MBeanRegister;

import java.io.Serializable;

@Named("hitPercentageCounter")
@SessionScoped
public class HitPercentageCounter implements HitPercentageCounterMBean, Serializable {
    @Inject
    private PointCounter pointCounter;

    public void init(@Observes @Initialized(SessionScoped.class) Object unused) {
        MBeanRegister.registerHitPercentageCounterMBean(this);
    }

    @Override
    public double getProportion() {
        return (double) pointCounter.getMissedPoints() / pointCounter.getTotalPoints();
    }
}
