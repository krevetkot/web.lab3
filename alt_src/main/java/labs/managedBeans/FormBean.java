package labs.managedBeans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import labs.database.DatabaseManager;
import labs.model.Point;
import labs.util.Checker;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;

@Named("formBean")
@ApplicationScoped
@Getter
@Setter
public class FormBean implements Serializable {
    private  double x;
    private double y;
    private double r;

    private double anotherX;
    private double anotherY;
    private double otherR;

    private DatabaseManager dbManager;
    private static final Logger logger = LogManager.getLogger(FormBean.class);
    private Checker checker;
    @Inject
    private TableResBean tableResBean;

    @PostConstruct
    public void init(){
        logger.info("formBean.init() started");
        x = 0;
        y = 0;
        r = 2;
        dbManager = DatabaseManager.getInstance();
        checker = new Checker();
        logger.info("formBean.init() successfully finished");
    }

    public void addPoint(float x, float y, float r){
        logger.info("formBean.addPoint() started");
        boolean isHit = checker.isHit(x, y, r);
        dbManager.insertPoint(new Point(x, y, r, isHit));
        tableResBean.updateResultList();
        logger.info("formBean.addPoint() successfully finished");
    }
}
