package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.EbookDTO;
import com.higgsup.intern.ebshop.dto.EbookOrderDTO;
import com.higgsup.intern.ebshop.dto.EbookOrderListDTO;
import com.higgsup.intern.ebshop.exception.ResourceNotFoundException;
import com.higgsup.intern.ebshop.exception.ServiceException;
import com.higgsup.intern.ebshop.jpa.entity.Ebook;
import com.higgsup.intern.ebshop.jpa.repo.IEbookRepository;
import com.higgsup.intern.ebshop.service.IEbookService;
import ma.glasnost.orika.MapperFacade;
import com.higgsup.intern.ebshop.dto.GenericResponseDTO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EbookService implements IEbookService {

    private final IEbookRepository iEbookRepository;
    private final MapperFacade mapper;

    public EbookService(IEbookRepository iEbookRepository, MapperFacade mapper) {
        this.iEbookRepository = iEbookRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void update(EbookDTO ebookDTO) {
        Long id = ebookDTO.getId();
        if (iEbookRepository.findOne(id) == null) {
            throw new ServiceException(String.format("Ebook with id = %d does not exist!", id));
        }

        Ebook ebook = mapper.map(ebookDTO, Ebook.class);
        iEbookRepository.save(ebook);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public GenericResponseDTO create(EbookDTO ebookDTO) {
        String isbn = ebookDTO.getIsbn();
        Integer newQuantity = ebookDTO.getQuantity();
        Ebook ebook = mapper.map(ebookDTO, Ebook.class);
        if (iEbookRepository.findByIsbn(isbn)== null) {
            iEbookRepository.save(ebook);
            return GenericResponseDTO.created();
        } else {
            Ebook originalEbook = iEbookRepository.findByIsbn(isbn);
            ebook.setId(originalEbook.getId());
            ebook.setQuantity(newQuantity + originalEbook.getQuantity());
            iEbookRepository.save(ebook);
            return GenericResponseDTO.updated()
                    .addAdditionalInfo(String.format("Ebook with isbn = %s exists! The ebook is updated. ", isbn));
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void delete(Long id) {
        if (iEbookRepository.findOne(id) == null) {
            throw new ServiceException(String.format("Ebook with id = %d does not exist!", id));
        }else{
        Ebook ebook = iEbookRepository.findOne(id);
        ebook.setDeleted(true);
        iEbookRepository.save(ebook);}
    }


    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public EbookOrderListDTO top10BestSellers() {
        List<EbookOrderDTO> ebookOrderDTOS = iEbookRepository.top10BestSeller().subList(0, 9);

        EbookOrderListDTO ebookOrderListDTO = new EbookOrderListDTO();
        ebookOrderListDTO.setEbookOrderDTOs(ebookOrderDTOS);

        return ebookOrderListDTO;
    }

}
