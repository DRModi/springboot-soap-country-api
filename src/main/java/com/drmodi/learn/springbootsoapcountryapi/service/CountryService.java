package com.drmodi.learn.springbootsoapcountryapi.service;

import com.learn.drmodi.model.country.Country;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class CountryService {

    private static final Map<String, Country> countryDetails = new HashMap<>();


    @PostConstruct
    public void initializeCountryDetails(){

        Country countryIndia = new Country();
        countryIndia.setCountryFullName("INDIA");
        countryIndia.setCountryCapitalName("NEW DELHI");
        countryIndia.setCountryAlpha3Code("IND");
        countryIndia.setCountryPopulation((long) 1295210000);

        Country countryUSA = new Country();
        countryUSA.setCountryFullName("UNITED STATES OF AMERICA");
        countryUSA.setCountryCapitalName("WASHINGTON, D.C");
        countryUSA.setCountryAlpha3Code("USA");
        countryUSA.setCountryPopulation((long) 323947000);

        Country countrySwitz = new Country();
        countrySwitz.setCountryFullName("SWITZERLAND");
        countrySwitz.setCountryCapitalName("BERN");
        countrySwitz.setCountryAlpha3Code("CHE");
        countrySwitz.setCountryPopulation((long) 8341600);

        countryDetails.put("in", countryIndia);
        countryDetails.put("us", countryUSA);
        countryDetails.put("ch", countrySwitz);

    }


    public Country getCountryDeatails(String countryCode){
        return countryDetails.get(countryCode);

    }
}
