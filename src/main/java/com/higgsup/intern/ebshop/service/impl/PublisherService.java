package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.AuthorDTO;
import com.higgsup.intern.ebshop.dto.EbookDTO;
import com.higgsup.intern.ebshop.dto.EbookListDTO;
import com.higgsup.intern.ebshop.dto.PublisherDTO;
import com.higgsup.intern.ebshop.exception.ResourceNotFoundException;
import com.higgsup.intern.ebshop.exception.ServiceException;
import com.higgsup.intern.ebshop.jdbc.dao.EbookDAO;
import com.higgsup.intern.ebshop.jdbc.dao.PublisherDAO;
import com.higgsup.intern.ebshop.jdbc.model.Author;
import com.higgsup.intern.ebshop.jdbc.model.Ebook;
import com.higgsup.intern.ebshop.jdbc.model.Publisher;
import com.higgsup.intern.ebshop.service.IPublisherService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService implements IPublisherService{
    private final PublisherDAO publisherDAO;
    private final EbookDAO ebookDAO;
    private final MapperFacade mapper;

    public PublisherService(PublisherDAO publisherDAO, EbookDAO ebookDAO, MapperFacade mapper) {
        this.publisherDAO = publisherDAO;
        this.ebookDAO = ebookDAO;
        this.mapper = mapper;
    }
    @Override
    public void create(PublisherDTO publisherDTO) {
        Publisher publisher = mapper.map(publisherDTO, Publisher.class);
        publisherDAO.create(publisher);
    }

    @Override
    public void delete(Long id) {
        if (publisherDAO.findById(id) == null) {
            throw new ServiceException(String.format("Publisher with id = %d does not exist!", id));
        }
        if(publisherDAO.countBookOfPublisher(id) > 0)
        {
            throw new ServiceException(String.format("Publisher with id = %d still have book in it!", id));
        }
        publisherDAO.delete(id);
    }

    @Override
    public void update(PublisherDTO publisherDTO) {
        Long id = publisherDTO.getId();
        if (publisherDAO.findById(id)==null) {
            throw new ServiceException(String.format("Person with id = %d does not exist!", id));
        }

        Publisher publisher = mapper.map(publisherDTO,Publisher.class);
        publisherDAO.update(publisher);
    }
    @Override
    public PublisherDTO findById(Long id) {
        Publisher publisher = publisherDAO.findById(id);
        if (publisher == null) {
            throw new ResourceNotFoundException(String.format("Publisher with id = %d does not exist!", id));
        }

        //gọi hàm
        List<Ebook> ebooks = publisherDAO.top5BookOfPublisher(id);
        // tạo list ebookDTO
        List<EbookDTO> ebookDTOs = mapper.mapAsList(ebooks,EbookDTO.class);
        //Tạo vòng for
        for (EbookDTO item : ebookDTOs) {
            Author authors = ebookDAO.infoOfAuthor(item.getId());
            AuthorDTO authorDTO = mapper.map(authors, AuthorDTO.class);
            item.setAuthorDTO(authorDTO);
        }
        //tạo 1 đối tượng ebook list mới
        EbookListDTO ebookListDTO = new EbookListDTO();
        // set giá trị cho ebookListDTO
        ebookListDTO.setEbookDTOList(ebookDTOs);

        PublisherDTO publisherDTO = mapper.map(publisher , PublisherDTO.class);
        publisherDTO.setEbookListDTO(ebookListDTO);

        //Tìm tổng số đầu sách của NXB
        publisherDTO.setAllBookOfPublisher(publisherDAO.countBookOfPublisher(id));
        return publisherDTO;
    }
}
