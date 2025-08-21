package com.eleccars.ElecCarsApp.model.dto.stationsDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StationsInfoDto {
    private long id;
    private String station_name_ar;
    private String station_name_en;
    private String station_address_desc;
    private String latitude;
    private String longitude;
    private Boolean stationActive;
    private Boolean stationAvailable;
    private Integer sockets_count;
    private String deletedBy;
    private String deletedDate;
    private long countryIdRef;
}
