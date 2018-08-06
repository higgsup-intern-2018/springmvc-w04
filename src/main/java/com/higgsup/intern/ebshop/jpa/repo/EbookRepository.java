package com.higgsup.intern.ebshop.jpa.repo;

import com.higgsup.intern.ebshop.jpa.entity.Ebook;
import org.springframework.data.repository.CrudRepository;

public interface EbookRepository extends CrudRepository<Ebook, Long> {
}
