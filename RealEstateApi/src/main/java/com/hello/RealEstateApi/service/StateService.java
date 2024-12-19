package com.hello.RealEstateApi.service;

import com.hello.RealEstateApi.model.State;
import com.hello.RealEstateApi.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

@Service
public class StateService {

    @Autowired
    StateRepository stateRepository;

    public void saveAll(List<State> stateData) throws Exception {
        if (stateData.size()==0){
            throw new Exception("No housing data provided");
        }
        stateRepository.saveAll(stateData);
    }


    public Double getAverageSalePriceOverall() {
        System.out.println(stateRepository.findAverageSalePriceOverall());
        return stateRepository.findAverageSalePriceOverall();
    }

    public List<Map<String, Double>> getAverageSalePricePerLocation() {
        return stateRepository.findAverageSalePricePerLocation();
    }

    public Integer getMaxSalePrice() {
        return stateRepository.findMaxSalePrice();
    }

    public Integer getMinSalePrice() {
        return stateRepository.findMinSalePrice();
    }


    public List<State> searchByPriceRange(int minPrice, int maxPrice) {
        return stateRepository.findByPriceRange(minPrice, maxPrice);
    }

    public List<State> searchByLocation(String location) {
        return stateRepository.findByLocation(location);
    }

    public List<State> searchByFeatures(int minBedrooms, double minBathrooms, int minSquareFootage) {
        return stateRepository.findByFeatures(minBedrooms, minBathrooms, minSquareFootage);
    }

    public List<State> combinedSearch(String location, Integer minPrice, Integer maxPrice, Integer minBedrooms, Double minBathrooms, Integer minSquareFootage) {
        return stateRepository.combinedSearch(location, minPrice, maxPrice, minBedrooms, minBathrooms, minSquareFootage);
    }

}
