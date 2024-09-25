package com.traincompany.management.admin_inputs.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;
import com.traincompany.management.admin_inputs.DTOs.CityDTO;
import com.traincompany.management.admin_inputs.services.CityService;

@GraphQlTest(CityController.class)
public class CityControllerTest {
    
    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private CityService cityService;

    @Test
     void canGetCities() {
        var x = graphQlTester.documentName("cities").execute().path("cities").entityList(CityDTO.class);
        x.hasSize(0);
     }
}
