package com.higgsup.intern.ebshop.jpa.repo;

import com.higgsup.intern.ebshop.jpa.entity.Author;
import com.higgsup.intern.ebshop.jpa.entity.Ebook;
import com.higgsup.intern.ebshop.jpa.entity.Publisher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEbookRepository extends CrudRepository<Ebook, Long> {

    Ebook findByIsbn(String isbn);

    @Query("SELECT a, " +
            "COUNT(a.id) AS countOfBooks " +
            "FROM Ebook e " +
            "JOIN e.author a  " +
            "WHERE e.id = :id")
    Author infoOfAuthor(@Param("id") Long id);

    @Query("SELECT e " +
            "FROM OrderDetails o " +
            "JOIN o.ebook e " +
            "GROUP BY(o.ebook) " +
            "ORDER BY SUM(o.quantity) DESC")
    List<Ebook> top10BestSellingEbooks();

    @Query("SELECT a " +
            "FROM Ebook e " +
            "JOIN e.author a  " +
            "WHERE e.id = :id")
    Author getAuthorByEbookId(@Param("id") Long id);

    @Query("SELECT p " +
            "FROM Ebook e " +
            "JOIN e.publisher p " +
            "WHERE e.id = :id")
    Publisher getPublisherByEbookId(@Param("id") Long id);

    @Query("SELECT SUM(o.quantity) " +
            "FROM OrderDetails o " +
            "JOIN o.ebook e " +
            "WHERE e.id = :id")
    Integer copiesSold(@Param("id") Long id);
}


