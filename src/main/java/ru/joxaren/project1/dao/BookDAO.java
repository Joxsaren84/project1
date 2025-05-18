package ru.joxaren.project1.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.joxaren.project1.models.Book;
import ru.joxaren.project1.models.Person;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*
    public List<Book> index(){
        String sql = "SELECT * FROM book";
        return jdbcTemplate.query(sql, new BookMapper());
    }*/

    public List<Book> index() {
        String sql = "SELECT * FROM book";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
    }

    public void save(Book book) {
        String sql = "INSERT INTO book (book_name, book_author, book_year) values (?, ?, ?)";
        jdbcTemplate.update(sql, book.getBookName(), book.getBookAuthor(), book.getBookYear());
    }

    public Book show(int id) {
        String sql = "SELECT * FROM book WHERE book_id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public Book show(String bookName) {
        String sql = "SELECT * FROM book WHERE book_name = ?";
        return jdbcTemplate.query(sql, new Object[]{bookName}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void setReader(int bookId, Integer personId) {
        String sql = "UPDATE book SET book_reader_id = ? WHERE book_id = ?";
        jdbcTemplate.update(sql, personId, bookId);
    }

    public Person getReader(int bookId) {
        String sql = "SELECT * FROM person WHERE person_id = " +
                "(SELECT book_reader_id FROM book WHERE book_id = ?)";
        return jdbcTemplate.query(sql, new Object[]{bookId}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void deleteBook(int bookId) {
        String sql = "DELETE FROM book WHERE book_id = ?";
        jdbcTemplate.update(sql, bookId);
    }

    public void updateBook(int id, Book book) {
        String sql = "UPDATE book SET book_name = ?, book_author = ?, book_year = ? WHERE book_id = ?";
        jdbcTemplate.update(sql, book.getBookName(), book.getBookAuthor(), book.getBookYear(), id);
    }

    public List<Book> getReadersBooks(int readerId) {
        String sql = "SELECT * FROM book WHERE book_reader_id = ?";
        return jdbcTemplate.query(sql, new Object[]{readerId}, new BeanPropertyRowMapper<>(Book.class));
    }

    public void setNull(Integer personId) {
        String sql = "UPDATE book SET book_reader_id = ? WHERE book_reader_id = ?";
        jdbcTemplate.update(sql, null, personId);
    }
}
