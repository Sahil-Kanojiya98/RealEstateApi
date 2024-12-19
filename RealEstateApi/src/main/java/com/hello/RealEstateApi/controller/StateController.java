package com.hello.RealEstateApi.controller;

import com.hello.RealEstateApi.model.State;
import com.hello.RealEstateApi.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/real-estate")
public class StateController {

    @Autowired
    StateService stateService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody List<State> stateData){
        try {
            stateService.saveAll(stateData);
            return ResponseEntity.ok().body("Data Saved Successfully");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/average-sale-price")
    public ResponseEntity<Double> getAverageSalePriceOverall() {
        double averageSalePrice = stateService.getAverageSalePriceOverall();
        return ResponseEntity.ok(averageSalePrice);
    }

    @GetMapping("/average-sale-price-per-location")
    public ResponseEntity<List<Map<String, Double>>> getAverageSalePricePerLocation() {
        List<Map<String, Double>> averageSalePricePerLocation = stateService.getAverageSalePricePerLocation();
        return ResponseEntity.ok(averageSalePricePerLocation);
    }

    @GetMapping("/max-sale-price")
    public ResponseEntity<Integer> getMaxSalePrice() {
        int maxSalePrice = stateService.getMaxSalePrice();
        return ResponseEntity.ok(maxSalePrice);
    }

    @GetMapping("/min-sale-price")
    public ResponseEntity<Integer> getMinSalePrice() {
        int minSalePrice = stateService.getMinSalePrice();
        return ResponseEntity.ok(minSalePrice);
    }

    // Advanced Search APIs
    @GetMapping("/search-by-price-range")
    public ResponseEntity<List<State>> searchByPriceRange(
            @RequestParam int minPrice,
            @RequestParam int maxPrice) {
        List<State> properties = stateService.searchByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(properties);
    }

    @GetMapping("/search-by-location")
    public ResponseEntity<List<State>> searchByLocation(
            @RequestParam String location) {
        List<State> properties = stateService.searchByLocation(location);
        return ResponseEntity.ok(properties);
    }

    @GetMapping("/search-by-features")
    public ResponseEntity<List<State>> searchByFeatures(
            @RequestParam int minBedrooms,
            @RequestParam double minBathrooms,
            @RequestParam int minSquareFootage) {
        List<State> properties = stateService.searchByFeatures(minBedrooms, minBathrooms, minSquareFootage);
        return ResponseEntity.ok(properties);
    }

    @GetMapping("/search-combined")
    public ResponseEntity<List<State>> combinedSearch(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) Integer minBedrooms,
            @RequestParam(required = false) Double minBathrooms,
            @RequestParam(required = false) Integer minSquareFootage) {
        List<State> properties = stateService.combinedSearch(location, minPrice, maxPrice, minBedrooms, minBathrooms, minSquareFootage);
        return ResponseEntity.ok(properties);
    }

}
