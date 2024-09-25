package com.traincompany.management.admin_inputs.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Stations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Station_ID")
    private Integer id;

    @Column(name = "Station_Name", length = 100)
    private String name;

    @Column(name = "Station_Country_Code", length = 3)
    private String countryCode;

    @Column(name = "Station_Phone", length = 20)
    private String phone;

    @Column(name = "Station_Postal_Code", length = 10)
    private String postalCode;

    @Column(name = "Station_Lat")
    private Float latitude;

    @Column(name = "Station_Long")
    private Float longitude;

    @Column(name = "City_ID", insertable = false, updatable = false)
    private Integer cityId;

    @Column(name = "Station_Img_Url", length = 200)
    private String imageUrl;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="City_ID")
    private City city;
}
