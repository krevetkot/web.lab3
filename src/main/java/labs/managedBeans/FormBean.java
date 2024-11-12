package labs.managedBeans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
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

    @PostConstruct
    public void init(){
        x = 0;
        y = 0;
        r = 1;
    }

    public void addPoint(){

    }
}
