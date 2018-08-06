package com.higgsup.intern.ebshop.jpa.repo;

import com.higgsup.intern.ebshop.dto.EbookOrderDTO;
import com.higgsup.intern.ebshop.dto.EbookOrderListDTO;
import com.higgsup.intern.ebshop.jpa.entity.Author;
import com.higgsup.intern.ebshop.jpa.entity.Ebook;
import com.higgsup.intern.ebshop.jpa.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEbookRepository extends JpaRepository<Ebook, Long> {

    @Query("UPDATE ebook SET deleted = TRUE where id = :id")
    void deleteEbook(@Param("id") Long id);

    @Query( "SELECT ebook.id, ebook.title, author.firstname, author.lastname, publisher.name, ebook.price, " +
            "SUM(order_details.quantity) AS copies_sold " +
            "FROM order_details JOIN ebook ON order_details.ebook_id = ebook.id " +
            "JOIN author ON ebook.author_id = author.id " +
            "JOIN publisher ON ebook.publisher_id = publisher.id " +
            "GROUP BY(ebook_id) " +
            "ORDER BY copies_sold DESC")
    List<EbookOrderDTO> top10BestSeller();

    @Query( "SELECT author.id, author.firstname, author.lastname, author.year_of_birth, author.description, author.website, author.organization, " +
            "COUNT(ebook.author_id) AS countOfBooks " +
            "FROM ebook " +
            "JOIN author ON ebook.author_id = author.id " +
            "WHERE ebook.id = :id")
    Author infoOfAuthor(Long id);

    @Query( "SELECT publisher.id, publisher.name, publisher.website, publisher.founde, publisher.founded_year, publisher.address, " +
            "COUNT(ebook.publisher_id) AS countOfBook FROM ebook " +
            "JOIN publisher " +
            "ON ebook.publisher_id = publisher.id " +
            "WHERE ebook.id = :id")
    Publisher getPublisherByEbookId(Long id);
}
