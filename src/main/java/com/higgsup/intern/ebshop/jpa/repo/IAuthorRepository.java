package com.higgsup.intern.ebshop.jpa.repo;

import com.higgsup.intern.ebshop.dto.AuthorDTO;
import com.higgsup.intern.ebshop.jpa.entity.Author;
import com.higgsup.intern.ebshop.jpa.entity.Ebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAuthorRepository extends JpaRepository<Author, Long> {

    //Tìm thông tin tác giả
    @Query( "SELECT a, " +
            "COUNT(a) AS countOfBooks " +
            "FROM Ebook e " +
            "LEFT JOIN e.author a " +
            "WHERE a.id = :id")
    Author getById(@Param("id") Long id);

    //Top 3 sach ban chay cua tac gia
    @Query( "SELECT e " +
            "FROM OrderDetails od " +
            "JOIN od.ebook e "+
            "JOIN e.author a "+
            "WHERE a.id = :id " +
            "GROUP BY e.id "+
            "ORDER BY SUM(od.quantity) DESC ")
    List<Ebook> getTop3BooksOfAuthor(@Param("id") Long id);

    //Tong so sach cua tac gia
    @Query( "SELECT COUNT(e.id) " +
            "FROM Ebook e INNER JOIN e.author a  " +
            "WHERE a.id = :author_id" )
    Integer countEbooksOfAnAuthor(@Param("author_id") Long author_id);

    //Top 5 sach ban chay nhat cua tac gia
    @Query( "SELECT a ," +
            "SUM(od.quantity) AS countOfBooks " +
            "FROM OrderDetails od " +
            "JOIN od.ebook e " +
            "JOIN e.author a " +
            "GROUP BY a.id " +
            "ORDER BY countOfBooks DESC ")
    List<Author> findTop5BestSellingAuthors();


}
