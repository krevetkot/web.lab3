package labs.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import labs.managedBeans.FormBean;
import labs.model.Point;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

@Getter
@Setter
public class DatabaseManager implements Serializable {
    private static volatile DatabaseManager instance;
    @PersistenceContext
    private EntityManager manager;

    public DatabaseManager() {
        manager = Persistence.createEntityManagerFactory("default").createEntityManager();
        //метод, который создает фабрику объектов EntityManager на основе конфигурации, заданной в файле persistence.xml
    }

    public static DatabaseManager getInstance(){
        if (instance==null){
            return new DatabaseManager();
        }
        return instance;
    }

    public void insertPoint(Point point) {
        //если таблица не создана, она будет создана при первой транзакции
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.persist(point);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()){
                transaction.rollback();
            }
        }
    }

    public ArrayList<Point> getPoints() {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            ArrayList<Point> points = new ArrayList<>(manager.createQuery("select p from Point p", Point.class).getResultList());
            transaction.commit();
            return points;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return new ArrayList<>();
        }
    }

    public void clearAll() {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.createQuery("delete from Point p").executeUpdate();
            transaction.commit();
        } catch (Exception e){
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }

    }
}
