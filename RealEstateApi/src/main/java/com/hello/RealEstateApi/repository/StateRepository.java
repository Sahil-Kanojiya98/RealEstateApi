package com.hello.RealEstateApi.repository;

import com.hello.RealEstateApi.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StateRepository extends JpaRepository<State ,Long> {

    @Query(value = "SELECT AVG(sale_price) FROM state", nativeQuery = true)
    double findAverageSalePriceOverall();

    @Query(value = "SELECT location, AVG(sale_price) AS avgSalePrice FROM state GROUP BY location", nativeQuery = true)
    List<Map<String, Double>> findAverageSalePricePerLocation();

    @Query(value = "SELECT MAX(sale_price) AS maxSalePrice FROM state", nativeQuery = true)
    Integer findMaxSalePrice();

    @Query(value = "SELECT MIN(sale_price) AS minSalePrice FROM state", nativeQuery = true)
    Integer findMinSalePrice();

    @Query(value = "SELECT * FROM state WHERE sale_price BETWEEN :minPrice AND :maxPrice", nativeQuery = true)
    List<State> findByPriceRange(@Param("minPrice") int minPrice, @Param("maxPrice") int maxPrice);

    @Query(value = "SELECT * FROM state WHERE location = :location", nativeQuery = true)
    List<State> findByLocation(@Param("location") String location);

    @Query(value = "SELECT * FROM state WHERE bedrooms >= :minBedrooms AND bathrooms >= :minBathrooms AND square_footage >= :minSquareFootage", nativeQuery = true)
    List<State> findByFeatures(@Param("minBedrooms") int minBedrooms, @Param("minBathrooms") double minBathrooms, @Param("minSquareFootage") int minSquareFootage);

    @Query(value = """
            SELECT * FROM state
            WHERE (:location IS NULL OR location = :location)
            AND (:minPrice IS NULL OR sale_price >= :minPrice)
            AND (:maxPrice IS NULL OR sale_price <= :maxPrice)
            AND (:minBedrooms IS NULL OR bedrooms >= :minBedrooms)
            AND (:minBathrooms IS NULL OR bathrooms >= :minBathrooms)
            AND (:minSquareFootage IS NULL OR square_footage >= :minSquareFootage)
            """, nativeQuery = true)
    List<State> combinedSearch(
            @Param("location") String location,
            @Param("minPrice") Integer minPrice,
            @Param("maxPrice") Integer maxPrice,
            @Param("minBedrooms") Integer minBedrooms,
            @Param("minBathrooms") Double minBathrooms,
            @Param("minSquareFootage") Integer minSquareFootage
    );

}
