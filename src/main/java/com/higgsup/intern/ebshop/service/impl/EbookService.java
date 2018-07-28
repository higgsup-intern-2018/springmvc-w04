package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.EbookDTO;
import com.higgsup.intern.ebshop.dto.EbookOrderDTO;
import com.higgsup.intern.ebshop.exception.ResourceNotFoundException;
import com.higgsup.intern.ebshop.exception.ServiceException;
import com.higgsup.intern.ebshop.jdbc.dao.EbookDAO;
import com.higgsup.intern.ebshop.jdbc.model.Ebook;
import com.higgsup.intern.ebshop.service.IEbookService;
import ma.glasnost.orika.MapperFacade;
import com.higgsup.intern.ebshop.dto.GenericResponseDTO;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EbookService implements IEbookService {

    private final EbookDAO ebookDAO;
    private final MapperFacade mapper;

    public EbookService(EbookDAO ebookDAO, MapperFacade mapper) {
        this.ebookDAO = ebookDAO;
        this.mapper = mapper;
    }

    @Override
    public EbookDTO findById(Long id) {     //check is book existed
        Ebook ebook = ebookDAO.findById(id);
        if (ebook == null) {    //if this book not existed => throw exception
            throw new ResourceNotFoundException(String.format("Ebook with id = %d does not exist!", id));
        }
        return mapper.map(ebook, EbookDTO.class);
    }

    @Override
    public void update(EbookDTO ebookDTO) {
        Long id = ebookDTO.getId();     //get id of book
        if (ebookDAO.findById(id) == null) {    //if book isnot existed in db
            throw new ServiceException(String.format("Ebook with id = %d does not exist!", id));
        }

        Ebook ebook = mapper.map(ebookDTO, Ebook.class);
        ebookDAO.update(ebook);
    }

    @Override
    public GenericResponseDTO create(EbookDTO ebookDTO) {
        String isbn = ebookDTO.getIsbn();
        Integer newQuantity = ebookDTO.getQuantity();
        Ebook ebook = mapper.map(ebookDTO, Ebook.class);
        if (ebookDAO.findByIsbn(isbn)== null){
            ebookDAO.create(ebook);
            return GenericResponseDTO.created();
        }else{
            Ebook originalEbook = ebookDAO.findByIsbn(isbn);
            ebook.setQuantity(newQuantity + originalEbook.getQuantity());
            ebookDAO.updateAddedEbook(ebook);
            return GenericResponseDTO.updated()
                    .addAdditionalInfo(String.format("Ebook with isbn = %s exists! The ebook is updated. ", isbn));
        }
    }

    @Override
    public void delete(Long id) {
        if (ebookDAO.findById(id) == null) {
            throw new ServiceException(String.format("Ebook with id = %d does not exist!", id));
        }
        ebookDAO.delete(id);
    }


    public List<EbookOrderDTO> findTop10BestSellers() {
        List<EbookOrderDTO> top10BestSellerEbooks = ebookDAO.findTop10BestSellerEbooks();

        return top10BestSellerEbooks;
    }
}
