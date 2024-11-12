package labs.managedBeans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import labs.database.DatabaseManager;
import labs.model.Point;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

@Named("tableResBean")
@ApplicationScoped
public class TableResBean implements Serializable {
    private DatabaseManager dbManager;
    private ArrayList<Point> resultList;
    @PostConstruct
    public void init(){
        dbManager  = new DatabaseManager();
        try {
            dbManager.connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resultList = dbManager.getPoints();
        if (resultList == null){
            resultList = new ArrayList<>();
        }

    }


}
