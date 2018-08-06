package com.higgsup.intern.ebshop.jpa.repo;

import com.higgsup.intern.ebshop.dto.EbookOrderDTO;
import com.higgsup.intern.ebshop.jpa.entity.Author;
import com.higgsup.intern.ebshop.jpa.entity.Ebook;
import com.higgsup.intern.ebshop.jpa.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEbookRepository extends JpaRepository<Ebook,Long>{
    //Top 10 sach ban chay nhat
    @Query( "SELECT e.id, e.title, a.firstname, a.lastname, p.name, e.price, " +
            "SUM(od.quantity) AS copies_sold " +
            "FROM OrderDetails od "+
            "JOIN od.ebook e  " +
            "JOIN e.author a  " +
            "JOIN e.publisher p " +
            "GROUP BY(e.id) " +
            "ORDER BY copies_sold DESC")
    List<EbookOrderDTO> top10BestSeller();
    //Thong tin cua tac gia va tong so sach cua tac gia trong he thong
    @Query( "SELECT a, " +
            "COUNT(a.id) AS countOfBooks " +
            "FROM Ebook  e " +
            "JOIN e.author a " +
            "WHERE e.id = :id")
    Author infoOfAuthor(@Param("id") Long id);
    //Thong tin cua nha xuat ban va so sach cua nxb trong he thong
    @Query( "SELECT p, " +
            "COUNT(p.id) AS countOfBook FROM Ebook e " +
            "JOIN e.publisher p " +
            "WHERE e.id = :id")
    Publisher getPublisherByEbookId(@Param("id") Long id);
}
