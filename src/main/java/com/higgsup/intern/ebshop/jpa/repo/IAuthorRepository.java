package com.higgsup.intern.ebshop.jpa.repo;

import com.higgsup.intern.ebshop.dto.AuthorDTO;
import com.higgsup.intern.ebshop.jpa.entity.Author;
import com.higgsup.intern.ebshop.jpa.entity.Ebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAuthorRepository extends JpaRepository<Author, Long> {

    @Query( "SELECT a, COUNT(a) AS countOfBooks " +
            "FROM Ebook e " +
            "LEFT JOIN e.author a " +
            "WHERE a.id = :id")
    AuthorDTO findById(Long id);

    @Query( "SELECT e " +
            "FROM OrderDetails.ebook e, e.author a " +
            "WHERE a.id = :id " +
            "GROUP BY e " +
            "ORDER BY SUM(OrderDetails.quantity) DESC ")
    List<Ebook> getTop3BooksOfAuthor(Long id);


    @Query( "SELECT COUNT(a) " +
            "FROM Ebook e INNER JOIN e.author a " +
            "WHERE a.id = :author_id" )
    Integer countEbooksOfAnAuthor(Long author_id);

    @Query( "SELECT a, SUM(OrderDetails.quantity) AS countOfBooks FROM Ebook " +
            "JOIN Ebook.author a " +
            "JOIN OrderDetails.ebook e " +
            "GROUP BY a " +
            "ORDER BY countOfBooks DESC ")
    List<Author> findTop5BestSellingAuthors();

}
