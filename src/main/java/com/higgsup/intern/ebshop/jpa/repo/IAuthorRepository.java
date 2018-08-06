package com.higgsup.intern.ebshop.jpa.repo;

import com.higgsup.intern.ebshop.dto.AuthorDTO;
import com.higgsup.intern.ebshop.jpa.entity.Author;
import com.higgsup.intern.ebshop.jpa.entity.Ebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAuthorRepository extends JpaRepository<Author, Long> {

    @Query( "SELECT author.id, author.firstname, author.lastname, author.year_of_birth, author.description, author.website, author.organization, " +
            "COUNT(ebook.author_id) AS countOfBooks " +
            "FROM author " +
            "LEFT JOIN ebook ON ebook.author_id = author.id " +
            "WHERE author.id = :id")
    AuthorDTO findById(Long id);

    @Query( "SELECT ebook.id, ebook.isbn, ebook.title, ebook.description, ebook.author_id," +
            "ebook.publisher_id, ebook.publication_date, ebook.pages, ebook.price, ebook.quantity, ebook.deleted " +
            "FROM ebook, author, order_details " +
            "WHERE author.id = :id " +
            "AND author.id = ebook.author_id " +
            "AND ebook.id = order_details.ebook_id " +
            "GROUP BY order_details.ebook_id " +
            "ORDER BY SUM(order_details.quantity) DESC ")
    List<Ebook> getTop3BooksOfAuthor(Long id);


    @Query( "SELECT COUNT(ebook.id) " +
            "FROM ebook INNER JOIN author ON ebook.author_id = author.id " +
            "WHERE author.id = :author_id" )
    Integer countEbooksOfAnAuthor(Long author_id);

    @Query( "SELECT author.id, author.firstname, author.lastname, author.year_of_birth, author.description, author.website, author.organization, " +
            "SUM(order_details.quantity) AS countOfBooks " +
            "FROM author " +
            "JOIN ebook " +
            "ON author.id = ebook.author_id " +
            "JOIN order_details " +
            "ON ebook.id = order_details.ebook_id " +
            "GROUP BY author.id " +
            "ORDER BY countOfBooks DESC ")
    List<Author> findTop5BestSellingAuthors();

}
