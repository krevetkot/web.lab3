package labs.managedBeans;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;


@Named("startPageBean")
@ApplicationScoped
public class StartPageBean implements Serializable {
    private float initX;
    private float initY;
    private float initR;

}
