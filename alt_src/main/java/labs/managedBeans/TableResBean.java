package labs.managedBeans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import labs.database.DatabaseManager;
import labs.model.Point;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.Serializable;
import java.util.ArrayList;

@Named("tableResBean")
@ApplicationScoped
@Getter
public class TableResBean implements Serializable {
    private DatabaseManager dbManager;
    private ArrayList<Point> resultList;
    private static final Logger logger = LogManager.getLogger(TableResBean.class);
    @PostConstruct
    public void init(){
        logger.info("tableResBean.init() started");
        dbManager  = DatabaseManager.getInstance();
        resultList = dbManager.getPoints();
        logger.info("tableResBean.init() successfully finished");
    }

    public void clearTable(){
        logger.info("tableResBean.clearTable() started");
        dbManager.clearAll();
        resultList.clear();
        logger.info("tableResBean.clearTable() successfully finished");
    }

    public void updateResultList(){
        logger.info("tableResBean.getResultList() started");
        resultList = dbManager.getPoints();
        logger.info("tableResBean.getResultList() successfully finished");
    }

}
