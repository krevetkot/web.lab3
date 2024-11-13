package labs.managedBeans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import labs.database.DatabaseManager;
import labs.model.Point;
import labs.util.Validator;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Named("formBean")
@ApplicationScoped
@Getter
@Setter
public class FormBean implements Serializable {
    private  double x;
    private double y;
    private double r;
    private DatabaseManager dbManager;

    @PostConstruct
    public void init(){
        x = 0;
        y = 0;
        r = 1;
        dbManager = DatabaseManager.getInstance();
    }

    public void addPoint(float x, float y, float r){
        Validator validator = new Validator();
        boolean isHit = validator.isHit(x, y, r);
        dbManager.insertPoint(new Point(x, y, r, isHit));
    }
}
