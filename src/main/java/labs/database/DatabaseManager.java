package labs.database;

import labs.model.Point;
import lombok.Getter;
import lombok.Setter;

import java.sql.*;
import java.util.ArrayList;

@Getter
@Setter
public class DatabaseManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/studs";
    private static final String USER = "s409577";
    private static final String PASSWORD = "7Tpx3iO5o2XLp7ja";
    private Connection connection;

    public void connect() throws SQLException {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void createPointsTable(Connection connection) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS points ("
                + "ID SERIAL PRIMARY KEY, "
                + "x REAL NOT NULL, "
                + "y REAL NOT NULL,"
                + "r REAL NOT NULL, "
                + "result BOOLEAN NOT NULL"
                + ");";
        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertPoint(Point point) {
        String insertSQL = "INSERT INTO points (x, y, r, result) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setDouble(1, point.getX());
            preparedStatement.setDouble(2, point.getY());
            preparedStatement.setDouble(3, point.getR());
            preparedStatement.setBoolean(4, point.getIsHit());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Point> getPoints() {
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
            e.printStackTrace();
        }
        return points;
    }
}
