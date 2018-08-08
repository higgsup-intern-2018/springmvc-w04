package com.higgsup.intern.ebshop.jpa.repo;

import com.higgsup.intern.ebshop.jpa.entity.Author;
import com.higgsup.intern.ebshop.jpa.entity.Ebook;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAuthorRepository extends CrudRepository<Author, Long> {

    @Query("SELECT a, COUNT(a) AS countOfBooks " +
            "FROM Ebook e " +
            "LEFT JOIN e.author a " +
            "WHERE a.id = :id")
    Author getById(@Param("id") Long id);

    @Query("SELECT e " +
            "FROM OrderDetails o " +
            "JOIN o.ebook e " +
            "JOIN e.author a " +
            "WHERE a.id = :id " +
            "GROUP BY e.id " +
            "ORDER BY SUM(o.quantity) DESC ")
    List<Ebook> getTop3BooksOfAuthor(@Param("id") Long id);


    @Query("SELECT COUNT(a) " +
            "FROM Ebook e " +
            "INNER JOIN e.author a " +
            "WHERE a.id = :author_id")
    Integer countEbooksOfAnAuthor(@Param("author_id") Long author_id);

    @Query("SELECT a " +
            "FROM OrderDetails o " +
            "JOIN o.ebook e " +
            "JOIN e.author a " +
            "GROUP BY a.id " +
            "ORDER BY SUM(o.quantity) DESC ")
    List<Author> findTop5BestSellingAuthors();

    @Query("SELECT SUM(od.quantity) " +
            "FROM OrderDetails od " +
            "JOIN od.ebook e " +
            "JOIN e.author a " +
            "WHERE a.id = :id")
    Integer countOfBooks(@Param("id") Long id);

    @Query("SELECT SUM(od.quantity) " +
            "FROM OrderDetails od " +
            "JOIN od.ebook e " +
            "JOIN e.publisher p " +
            "WHERE p.id = :id")
    Integer countOfBooksByPublisherId(@Param("id") Long id);
}
