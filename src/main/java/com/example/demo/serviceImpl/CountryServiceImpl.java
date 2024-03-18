package com.example.demo.serviceImpl;

import com.example.demo.entity.Country;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.payload.CountryDTO;
import com.example.demo.payload.CountryDTOResponse;
import com.example.demo.repositories.CountryRepo;
import com.example.demo.service.CountryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    CountryRepo countryRepo;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public CountryDTO createCountry(CountryDTO countryDTO) {
        Country map = this.modelMapper.map(countryDTO, Country.class);
        Country save = this.countryRepo.save(map);
        return  this.modelMapper.map(save,CountryDTO.class);
    }

    @Override
    public CountryDTO updateCountry(CountryDTO countryDTO, Integer countryId) {
        Country country = this.countryRepo.findById(countryId).orElseThrow(() -> new ResourceNotFoundException("country", "id", countryId));
        country.setCountryName(countryDTO.getCountryName());
        country.setLanguage(countryDTO.getLanguage());
        country.setPopulation(countryDTO.getPopulation());
        Country save = this.countryRepo.save(country);
        return this.modelMapper.map(save,CountryDTO.class);

    }

    @Override
    public CountryDTO getCountryById(Integer countryId) {
        Country country = this.countryRepo.findById(countryId).orElseThrow(() -> new ResourceNotFoundException("country", "id", countryId));
       return this.modelMapper.map(country,CountryDTO.class);
    }

    @Override
    public CountryDTOResponse getAllCountry(Integer pageNumber, Integer pageSize, String sortBy) {
        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize, Sort.by(sortBy).ascending());
        Page<Country> all1 = this.countryRepo.findAll(pageRequest);
        List<Country> all = all1.getContent();
        List<CountryDTO> collect = all.stream().map(user -> this.modelMapper.map(user,CountryDTO.class)).collect(Collectors.toList());
        CountryDTOResponse userResponse = new CountryDTOResponse();
        userResponse.setContent(collect);
        userResponse.setPageNumber(all1.getNumber());
        userResponse.setPageSize(all1.getSize());
        userResponse.setTotalElements(all1.getTotalElements());
        userResponse.setTotalPages(all1.getTotalPages());
        userResponse.setLastPage(all1.isLast());
        return userResponse;
    }

    @Override
    public void deleteCountry(Integer countryId) {
        Country country = this.countryRepo.findById(countryId).orElseThrow(() -> new ResourceNotFoundException("country", "id", countryId));
           this.countryRepo.delete(country);
    }
}
