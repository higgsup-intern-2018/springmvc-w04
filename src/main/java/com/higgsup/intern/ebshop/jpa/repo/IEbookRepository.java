package com.higgsup.intern.ebshop.jpa.repo;

import com.higgsup.intern.ebshop.dto.EbookOrderDTO;
import com.higgsup.intern.ebshop.jpa.entity.Author;
import com.higgsup.intern.ebshop.jpa.entity.Ebook;
import com.higgsup.intern.ebshop.jpa.entity.Publisher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface IEbookRepository extends CrudRepository<Ebook, Long> {
    Ebook findEbookByIsbn(String isbn);

    @Transactional
    @Modifying
    @Query("UPDATE Ebook ebook SET ebook.deleted = TRUE where id = :id")
    public void delete(@Param("id") Long id);

    @Query("select p, count(ebook) as countOfBook from Ebook ebook " +
            "join ebook.publisher p " +
            "where ebook.id = :id")
    Publisher publisherOfEbooks(@Param("id") Long id);

    @Query("select a, count(ebook) as countOfBook from Ebook ebook " +
            "join ebook.author a " +
            "where ebook.id = :id")
    Author authorOfEbooks(@Param("id") Long id);

    @Query("SELECT e " +
            "FROM OrderDetails o " +
            "JOIN o.ebook e " +
            "GROUP BY (e.id) " +
            "ORDER BY SUM(o.quantity) DESC ")
    List<Ebook> top10BestSeller(Pageable pageable);

    @Query(" select SUM(o.quantity) AS copies_sold " +
            "FROM OrderDetails o " +
            "JOIN o.ebook e " +
            "WHERE e.id = :id")
    Integer countCopySold(@Param("id") Long id);
}
