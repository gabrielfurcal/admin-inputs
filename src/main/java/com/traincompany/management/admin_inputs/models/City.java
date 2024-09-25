package com.traincompany.management.admin_inputs.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Cities")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "City_ID")
    private Integer id;

    @Column(name = "City", length = 60)
    private String city;

    @Column(name = "Province", length = 60)
    private String province;

    @Column(name = "Country", length = 60)
    private String country;
}
