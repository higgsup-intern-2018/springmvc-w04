package com.higgsup.intern.ebshop.jpa.repo;

import com.higgsup.intern.ebshop.jpa.entity.Ebook;
import com.higgsup.intern.ebshop.jpa.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPublisherRepository extends JpaRepository<Publisher, Long> {
    @Query( "SELECT publisher.id, publisher.name, publisher.website, publisher.founder, publisher.founded_year, publisher.address, " +
            "SUM(order_details.quantity) AS countOfBook FROM publisher " +
            "LEFT JOIN ebook ON publisher.id = ebook.publisher_id " +
            "LEFT JOIN order_details ON ebook.id = order_details.ebook_id " +
            "WHERE publisher.id = :id")
    Publisher findById(Long id);

    @Query( "SELECT COUNT(publisher_id) " +
            "FROM ebook " +
            "LEFT JOIN publisher ON publisher.id = ebook.publisher_id " +
            "WHERE publisher.id = :id")
    Integer countBookOfPublisher(Long id);

    @Query( "SELECT ebook.id, ebook.title, author.firstname, author.lastname, publisher.name, ebook.price " +
            "FROM ebook " +
            "LEFT JOIN publisher ON ebook.publisher_id = publisher.id " +
            "LEFT JOIN order_details ON ebook.id = order_details.ebook_id " +
            "where publisher.id = :id " +
            "GROUP BY ebook_id " +
            "ORDER by COUNT(ebook_id) DESC ")
    List<Ebook> top5BookOfPublisher(Long id);


    @Query( "SELECT publisher.id, publisher.name, publisher.website, publisher.founder, publisher.founded_year, publisher.address," +
            "SUM(order_details.quantity) AS countOfBook " +
            "FROM publisher " +
            "JOIN ebook ON publisher.id = ebook.publisher_id " +
            "JOIN order_details ON ebook.id = order_details.ebook_id " +
            "GROUP BY publisher.id " +
            "ORDER by countOfBook DESC ")
    List<Publisher> findTop5BestSellingPublishers();
}
