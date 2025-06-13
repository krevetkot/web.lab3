package labs.mbeans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("hitPercentageCounter")
@SessionScoped
public class HitPercentageCounter implements HitPercentageCounterMBean, Serializable {
    @Inject
    private PointCounter pointCounter;

    @Override
    public double getProportion() {
        return (double) pointCounter.getMissedPoints() / pointCounter.getTotalPoints();
    }
}
