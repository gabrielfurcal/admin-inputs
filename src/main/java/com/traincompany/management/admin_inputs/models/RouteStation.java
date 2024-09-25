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
@Table(name="Route_Station")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Route_Station_ID")
    private Integer id;

    @Column(name = "Route_ID", insertable = false, updatable = false)
    private Integer routeId;

    @Column(name = "Station_ID", insertable = false, updatable = false)
    private Integer stationId;

    @Column(name = "Station_Order")
    private Integer order;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="Route_ID")
    private Route route;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="Station_ID")
    private Station station;
}
