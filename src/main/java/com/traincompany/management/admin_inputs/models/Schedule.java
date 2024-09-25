package com.traincompany.management.admin_inputs.models;

import java.util.Date;
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
@Table(name="Schedules")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Schedule_ID")
    private Integer id;

    @Column(name = "Train_ID", insertable = false, updatable = false)
    private Integer trainId;

    @Column(name = "Route_ID", insertable = false, updatable = false)
    private Integer routeId;

    @Column(name = "Status_ID", insertable = false, updatable = false)
    private Integer statusId;

    @Column(name = "Departure_Time")
    private Date departureTime;

    @Column(name = "Arrival_Time")
    private Date arrivalTime;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="Train_ID")
    private Train train;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="Route_ID")
    private Route route;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="Status_ID")
    private Status status;
}
