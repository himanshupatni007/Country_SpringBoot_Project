package com.example.demo.service;

import com.example.demo.payload.CountryDTO;
import com.example.demo.payload.CountryDTOResponse;

public interface CountryService {

    CountryDTO createCountry(CountryDTO countryDTO);
    CountryDTO  updateCountry(CountryDTO countryDTO,Integer countryId);

    CountryDTO  getCountryById(Integer countryId);
    CountryDTOResponse getAllCountry(Integer pageNumber, Integer pageSize, String sortBy);

    void deleteCountry(Integer CountryId);
}
