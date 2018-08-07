package com.higgsup.intern.ebshop.jpa.repo;

import com.higgsup.intern.ebshop.jpa.entity.Ebook;
import com.higgsup.intern.ebshop.jpa.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPublisherReposiory extends JpaRepository<Publisher, Long> {
//Tim thong tin NXB
    @Query ("SELECT p, " +
            "SUM(od.quantity) AS countOfBook FROM OrderDetails od " +
            "LEFT JOIN od.ebook e " +
            "LEFT JOIN e.publisher p " +
            "WHERE p.id = :id")
    Publisher getById(@Param("id") Long id);
//Tong so sach cua nxb
    @Query( "SELECT COUNT(p.id) " +
            "FROM Ebook e " +
            "LEFT JOIN e.publisher p " +
            "WHERE p.id = :id")
    Integer countBookOfPublisher(@Param("id") Long id);

    @Query( "SELECT e, p " +
            "FROM OrderDetails od " +
            "LEFT JOIN od.ebook e  " +
            "LEFT JOIN e.publisher p " +
            "WHERE p.id = :id " +
            "GROUP BY e.id " +
            "ORDER by COUNT(e.id) DESC ")
    List<Ebook> top5BookOfPublisher(@Param("id") Long id);

//Top 5 dau sach ban nhieu nhat cua NXB
    @Query( "SELECT p," +
            "SUM(od.quantity) AS countOfBook " +
            "FROM OrderDetails od " +
            "JOIN od.ebook e  " +
            "JOIN e.publisher p " +
            "GROUP BY p.id " +
            "ORDER by countOfBook DESC ")
    List<Publisher> findTop5BestSellingPublishers();
}
