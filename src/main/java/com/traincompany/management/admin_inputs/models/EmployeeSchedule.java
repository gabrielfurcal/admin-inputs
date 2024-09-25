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
@Table(name="Employee_Schedule")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Emp_Schdl_ID")
    private Integer id;

    @Column(name = "Employee_ID", insertable = false, updatable = false)
    private Integer employeeId;

    @Column(name = "Schedule_ID", insertable = false, updatable = false)
    private Integer scheduleId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="Employee_ID")
    private Employee employee;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="Schedule_ID")
    private Schedule schedule;
}
