package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.EbookDTO;
import com.higgsup.intern.ebshop.dto.EbookOrderDTO;
import com.higgsup.intern.ebshop.dto.EbookOrderListDTO;
import com.higgsup.intern.ebshop.exception.ResourceNotFoundException;
import com.higgsup.intern.ebshop.exception.ServiceException;
import com.higgsup.intern.ebshop.jpa.entity.Ebook;
import com.higgsup.intern.ebshop.jpa.repo.EbookRepository;
import com.higgsup.intern.ebshop.jpa.repo.IEbookRepository;
import com.higgsup.intern.ebshop.service.IEbookService;
import ma.glasnost.orika.MapperFacade;
import com.higgsup.intern.ebshop.dto.GenericResponseDTO;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EbookService implements IEbookService {

    private final EbookRepository ebookRepository;
    private final MapperFacade mapper;
    private final IEbookRepository iEbookRepository;

    public EbookService(EbookRepository ebookRepository, MapperFacade mapper, IEbookRepository iEbookRepository) {
        this.ebookRepository = ebookRepository;
        this.mapper = mapper;
        this.iEbookRepository = iEbookRepository;
    }


    @Override
    public EbookDTO findById(Long id) {
        Ebook ebook = ebookRepository.findOne(id);
        if (ebook == null) {
            throw new ResourceNotFoundException(String.format("Ebook with id = %d does not exist!", id));
        }
        return mapper.map(ebook, EbookDTO.class);
    }

    @Override
    public void update(EbookDTO ebookDTO) {
        Long id = ebookDTO.getId();
        if (ebookRepository.findOne(id) == null) {
            throw new ServiceException(String.format("Ebook with id = %d does not exist!", id));
        }

        Ebook ebook = mapper.map(ebookDTO, Ebook.class);
        ebookRepository.save(ebook);
    }

    @Override
    public GenericResponseDTO create(EbookDTO ebookDTO) {
        String isbn = ebookDTO.getIsbn();
        Integer newQuantity = ebookDTO.getQuantity();
        Ebook ebook = mapper.map(ebookDTO, Ebook.class);
        if (ebookRepository.findOne(Long.valueOf(isbn)) == null) {
            ebookRepository.save(ebook);
            return GenericResponseDTO.created();
        } else {
            Ebook originalEbook = ebookRepository.findOne(Long.valueOf(isbn));
            ebook.setQuantity(newQuantity + originalEbook.getQuantity());
            ebookRepository.save(ebook);
            return GenericResponseDTO.updated()
                    .addAdditionalInfo(String.format("Ebook with isbn = %s exists! The ebook is updated. ", isbn));
        }
    }

    @Override
    public void delete(Long id) {
        if (ebookRepository.findOne(id) == null) {
            throw new ServiceException(String.format("Ebook with id = %d does not exist!", id));
        }
        ebookRepository.delete(id);
    }

    @Override
    public EbookOrderListDTO top10BestSellers() {
        List<EbookOrderDTO> ebookOrderDTOS = iEbookRepository.top10BestSeller();

        EbookOrderListDTO ebookOrderListDTO = new EbookOrderListDTO();
        ebookOrderListDTO.setEbookOrderDTOs(ebookOrderDTOS);

        return ebookOrderListDTO;
    }
}
