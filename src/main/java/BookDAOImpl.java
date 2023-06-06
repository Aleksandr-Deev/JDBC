import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {

    private final Connection connection;


    public BookDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Book book) {

        // Формируем запрос к базе с помощью PreparedStatement
        try(PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO book (title, author_id, amount) VALUES ((?), (?), (?))")) {

            // Подставляем значение вместо wildcard
            // первым параметром указываем порядковый номер wildcard
            // вторым параметром передаем значение
            statement.setString(1, book.getTitle());
            statement.setInt(2, book.getAuthor().getId());
            statement.setInt(3, book.getAmount());

            // С помощью метода executeQuery отправляем запрос к базе
            statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Book readById(int id) {

        Book book = new Book();
        // Формируем запрос к базе с помощью PreparedStatement
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM book INNER JOIN author ON book.author_id=author.author_id AND book_id=(?)")) {

            // Подставляем значение вместо wildcard
            statement.setInt(1, id);

            // Делаем запрос к базе и результат кладем в ResultSet
            ResultSet resultSet = statement.executeQuery();

            // Методом next проверяем есть ли следующий элемент в resultSet
            // и одновременно переходим к нему, если таковой есть
            while(resultSet.next()) {

                // С помощью методов getInt и getString получаем данные из resultSet
                // и присваиваем их полим объекта
                book.setId(Integer.parseInt(resultSet.getString("book_id")));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(new Author(resultSet.getInt("author_id"),
                        resultSet.getString("name_author")));
                book.setAmount(Integer.parseInt(resultSet.getString("amount")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public List<Book> readAll() {

        // Создаем список, в который будем укладывать объекты
        List<Book> bookList = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM book INNER JOIN author ON book.author_id=author.author_id")) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                int id = Integer.parseInt(resultSet.getString("book_id"));
                String title = resultSet.getString("title");
                Author author = new Author(resultSet.getInt("author_id"),
                        resultSet.getString("name_author"));
                int amount = Integer.parseInt(resultSet.getString("amount"));


                // Создаем объекты на основе полученных данных
                // и укладываем их в итоговый список
                bookList.add(new Book(id, title, author, amount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList;
    }

    @Override
    public void updateAmountById(int id, int amount) {

        try(PreparedStatement statement = connection.prepareStatement(
                "UPDATE book SET amount=(?) WHERE book_id=(?)")) {

            statement.setInt(1, amount);
            statement.setInt(2, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {

        try(PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM book WHERE book_id=(?)")) {

            statement.setInt(1, id);
            statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
