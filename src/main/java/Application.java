import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) throws SQLException {

        // Создаем переменные с данными для подключения к базе
        final String user = "postgres";
        final String password = "G4#f^K@s7a";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            // Создаем объект класса BookDAOImpl
            EmployeeDAO employeeDAO = new EmployeeDAOImpl(connection);

            City city = new City(2, "TEST");
            Employee employee = new Employee(16, "Han", "Bot", "man", 46, city);

            // Вызываем метод добавления объекта
            employeeDAO.create(employee);

            // Создаем список наполняя его объектами, которые получаем
            // путем вызова метода для получения всех элементов таблицы
            List<Employee> list = new ArrayList<>(employeeDAO.readAll());

            // Выведем список в консоль
            for (Employee employees : list) {
                System.out.println(employees);
            }

            System.out.println();

            // Изменение city по id
            employeeDAO.updateCityById(7, 2);

            // Удаление по id
            employeeDAO.deleteById(14);

            List<Employee> list1 = new ArrayList<>(employeeDAO.readAll());

            // Выведем список в консоль
            for (Employee employees : list1) {
                System.out.println(employees);
            }
        }
    }
}

