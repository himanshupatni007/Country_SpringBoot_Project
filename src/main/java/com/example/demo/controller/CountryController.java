package com.example.demo.controller;


import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.CountryDTO;
import com.example.demo.payload.CountryDTOResponse;
import com.example.demo.service.CountryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/country")
public class CountryController {

     @Autowired
     private CountryService countryService;

   @PostMapping("/")
     public ResponseEntity<CountryDTO> createCountry(@Valid @RequestBody CountryDTO countryDTO){
       CountryDTO country = this.countryService.createCountry(countryDTO);
       return new ResponseEntity<>(country, HttpStatus.CREATED);
   }


    @PutMapping("/{countryId}")
    public ResponseEntity<CountryDTO> updateCountry(@Valid @RequestBody CountryDTO countryDTO,@PathVariable("countryId") Integer countryId)
    {
        CountryDTO countryDTO1 = this.countryService.updateCountry(countryDTO, countryId);
       return new ResponseEntity<>(countryDTO1,HttpStatus.OK);
    }

    @DeleteMapping("/{countryId}")
   public ResponseEntity<ApiResponse> deleteCountry(@PathVariable ("countryId") Integer countryId)
    {
        this.countryService.deleteCountry(countryId);
   return  new ResponseEntity<>(new ApiResponse("Country deleted Successfully",true),HttpStatus.OK);
    }

    @GetMapping("/{countryId}")
    public ResponseEntity<CountryDTO> getCountry(@PathVariable("countryId") Integer countryId){
        CountryDTO countryById = this.countryService.getCountryById(countryId);
       return  new ResponseEntity<>(countryById,HttpStatus.FOUND);
    }

    @GetMapping("/")
    public ResponseEntity<CountryDTOResponse> getAllCountry(@RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
                                                            @RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize,
                                                            @RequestParam(value = "sortBy",defaultValue = "id",required = false ) String sortBy){

          return  new ResponseEntity<>(this.countryService.getAllCountry(pageNumber,pageSize,sortBy),HttpStatus.OK);
    }
}
