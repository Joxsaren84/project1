package ru.joxaren.project1.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.joxaren.project1.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();
        book.setBookId(resultSet.getInt("book_id"));
        book.setBookName(resultSet.getString("book_name"));
        book.setBookAuthor(resultSet.getString("book_autor"));
        book.setBookYear(resultSet.getInt("book_year"));
        Integer integer = resultSet.getObject("book_reader_id", Integer.class);
        book.setBookReaderId(integer);
        return book;
    }
}
