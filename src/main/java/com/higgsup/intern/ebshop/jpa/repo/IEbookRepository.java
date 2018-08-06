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

    @Query("UPDATE Ebook e SET e.deleted = TRUE where e.id = :id")
    void deleteEbook(@Param("id") Long id);

    @Query( "SELECT e, a, p, " +
            "SUM(o.quantity) AS copies_sold " +
            "FROM OrderDetails o " +
            "JOIN o.ebook e " +
            "JOIN e.author a " +
            "JOIN e.publisher p " +
            "GROUP BY(e.id) " +
            "ORDER BY copies_sold DESC")
    List<EbookOrderDTO> top10BestSeller();

    @Query( "SELECT a, " +
            "COUNT(a.id) AS countOfBooks " +
            "FROM Ebook e " +
            "JOIN e.author a  " +
            "WHERE e.id = :id")
    Author infoOfAuthor(Long id);

    @Query( "SELECT p, " +
            "COUNT(p.id) AS countOfBook FROM Ebook e " +
            "JOIN e.publisher p " +
            "WHERE e.id = :id")
    Publisher getPublisherByEbookId(Long id);
}
