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
@Table(name="Trains")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Train_ID")
    private Integer id;

    @Column(name = "Train_Type", length = 60)
    private String type;

    @Column(name = "Capacity")
    private Integer capacity;

    @Column(name = "Max_Speed")
    private Float maxSpeed;
}
