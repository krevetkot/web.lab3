package labs.database;

import labs.managedBeans.FormBean;
import labs.model.Point;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

@Getter
@Setter
public class DatabaseManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/studs";
    private static final String USER = "s409577";
    private static final String PASSWORD = "7Tpx3iO5o2XLp7ja";
    private Connection connection;

    private static volatile DatabaseManager instance;
    private static final Logger logger = LogManager.getLogger(DatabaseManager.class);

    public void connect() throws SQLException {
        logger.info("Создание соединения...");
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        logger.info("Соединение создано.");
    }

    public static DatabaseManager getInstance(){
        if (instance==null){
            return new DatabaseManager();
        }
        return instance;
    }

    public void createPointsTable() {
        logger.info("Создание таблицы результатов...");
        String createTableSQL = "CREATE TABLE IF NOT EXISTS points ("
                + "ID SERIAL PRIMARY KEY, "
                + "x REAL NOT NULL, "
                + "y REAL NOT NULL,"
                + "r REAL NOT NULL, "
                + "result BOOLEAN NOT NULL"
                + ");";
        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
            logger.info("Таблица результатов создана.");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public void insertPoint(Point point) {
        logger.info("Добавление точки...");
        String insertSQL = "INSERT INTO points (x, y, r, result) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setDouble(1, point.getX());
            preparedStatement.setDouble(2, point.getY());
            preparedStatement.setDouble(3, point.getR());
            preparedStatement.setBoolean(4, point.getIsHit());
            preparedStatement.executeUpdate();
            logger.info("Точка добавлена.");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public ArrayList<Point> getPoints() {
        logger.info("Получение таблицы результатов...");
        ArrayList<Point> points = new ArrayList<Point>();
        String query = "SELECT * FROM points ORDER BY id DESC";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                float x = resultSet.getFloat("x");
                float y = resultSet.getFloat("y");
                float r = resultSet.getFloat("r");
                boolean result = resultSet.getBoolean("result");

                Point point = new Point(x, y, r, result);
                points.add(point);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        logger.info("Получение таблицы результатов прошло успешно.");
        return points;
    }

    public void clearAll(){
        logger.info("Удаление таблицы результатов...");
        String query = "DELETE * FROM points";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            logger.info("Удаление таблицы результатов прошло успешно.");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
