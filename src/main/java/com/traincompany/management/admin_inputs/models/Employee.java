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
@Table(name="Employees")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Employee_ID")
    private Integer id;

    @Column(name = "Fist_Name", length = 100)
    private String firstName;

    @Column(name = "Last_Name", length = 100)
    private String lastName;

    @Column(name = "Position", length = 50)
    private String position;

    @Column(name = "Phone_Number", length = 20)
    private String phoneNumber;

    @Column(name = "Email", length = 60)
    private String email;
}
