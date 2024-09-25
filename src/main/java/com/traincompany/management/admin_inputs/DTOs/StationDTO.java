package com.traincompany.management.admin_inputs.DTOs;

import java.util.List;

public record StationDTO(Integer id, String name, String countryCode, String phone, String postalCode,
                        Float latitude, Float longitude, Integer cityId, String imageUrl, List<RouteDTO> routes) { }
