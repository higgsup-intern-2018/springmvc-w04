package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.*;
import com.higgsup.intern.ebshop.exception.ResourceNotFoundException;
import com.higgsup.intern.ebshop.exception.ServiceException;
import com.higgsup.intern.ebshop.jdbc.dao.EbookDAO;
import com.higgsup.intern.ebshop.jdbc.model.Author;
import com.higgsup.intern.ebshop.jdbc.model.Ebook;
import com.higgsup.intern.ebshop.service.IEbookService;
import ma.glasnost.orika.MapperFacade;

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
        Long id = ebookDTO.getId();
        if (ebookDAO.findById(id) == null) {
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

    @Override
    public EbookOrderListDTO top10BestSellers() {
        List<EbookOrderDTO> ebookOrderDTOS = ebookDAO.top10BestSeller();

        EbookOrderListDTO ebookOrderListDTO = new EbookOrderListDTO();
        ebookOrderListDTO.setEbookOrderDTOs(ebookOrderDTOS);

        return ebookOrderListDTO;
    }

    @Override
    public EbookListDTO searchEbook(String name, Long authorId, Long publisherId, Double priceFrom, Double priceTo, String isbn) {
        List<Ebook> ebooks = ebookDAO.find(name, authorId, publisherId, priceFrom, priceTo, isbn);
        List<EbookDTO> ebookDTOS = mapper.mapAsList(ebooks, EbookDTO.class);
        EbookListDTO ebookListDTO = new EbookListDTO();
        ebookListDTO.setEbookDTOList(ebookDTOS);
        return ebookListDTO;
    }
}
