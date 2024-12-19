package com.hello.RealEstateApi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("ID")
    private Long id;

    @Column(name = "bedrooms")
    @JsonProperty("Bedrooms")
    private int bedrooms;

    @Column(name = "bathrooms")
    @JsonProperty("Bathrooms")
    private double bathrooms;

    @Column(name = "square_footage")
    @JsonProperty("SquareFootage")
    private int squareFootage;

    @Column(name = "location")
    @JsonProperty("Location")
    private String location;

    @Column(name = "sale_price")
    @JsonProperty("SalePrice")
    private int salePrice;

}