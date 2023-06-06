import java.util.List;

public interface BookDAO {

    // Добавление объекта
    void create(Book book);
    // Получение объекта по id
    Book readById(int id);
    // Получение всех объектов
    List<Book> readAll();
    // Изменение объекта по id
    void updateAmountById(int id, int amount);
    // Удаление объекта по id
    void deleteById(int id);

}