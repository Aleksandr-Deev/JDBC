import java.util.List;

public interface EmployeeDAO {

    // метод доьавления(создания)
    void create(Employee employee);

    // Метод получения объекта по id
    Employee readById(int id);

    // Получение всех объектов из базы
    List<Employee> readAll();

    // Метод обновления данных в базе
    void updateCityById(int id, int city_id);

    // Метод удаления данных из базы
    void deleteById(int id);
}
