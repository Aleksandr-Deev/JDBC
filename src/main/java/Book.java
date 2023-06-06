import java.util.Objects;

public class Book {

    private int id;
    private String title;
    private Author author;
    private int amount;

    public Book() {
    }

    public Book(String title, Author author, int amount) {
        this.title = title;
        this.author = author;
        this.amount = amount;
    }

    public Book(int id, String title, Author author, int amount) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && amount == book.amount && Objects.equals(title, book.title) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, amount);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", amount=" + amount +
                '}';
    }
}