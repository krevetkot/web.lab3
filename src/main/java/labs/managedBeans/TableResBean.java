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
import java.sql.SQLException;
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
        dbManager  = DatabaseManager.getInstance();
        try {
            dbManager.connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resultList = dbManager.getPoints();
        if (resultList == null){
            resultList = new ArrayList<>();
        }
        dbManager.createPointsTable();
    }

    public void clearTable(){
        logger.info("clearTable() started");
        dbManager.clearAll();
        logger.info("clearTable() successfully finished");
    }

}
