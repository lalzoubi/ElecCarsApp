package com.eleccars.ElecCarsApp.dto.stationsDTOs;

public record StationsInfoDto(
        long id,
        String station_name_ar,
        String station_name_en,
        String station_address_desc,
        String latitude,
        String longitude,
        Integer is_active,
        Integer is_available,
        Integer sockets_count,
        String deletedBy,
        String deletedDate,
        long countryIdRef
) {
}
