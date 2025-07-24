package com.eleccars.ElecCarsApp.mapper.stationsMappers;

import com.eleccars.ElecCarsApp.dto.stationsDTOs.StationsInfoDto;
import com.eleccars.ElecCarsApp.model.stationsModels.StationInfo;

import java.util.function.Function;

public class StationsMapper implements Function<StationInfo, StationsInfoDto> {
    @Override
    public StationsInfoDto apply(StationInfo stationInfo) {
        return new StationsInfoDto(stationInfo.getId(),
                stationInfo.getStation_name_ar(),
                stationInfo.getStation_name_en(),
                stationInfo.getStation_address_desc(),
                stationInfo.getLatitude(),
                stationInfo.getLongitude(),
                stationInfo.getIs_active(),
                stationInfo.getIs_available(),
                stationInfo.getSockets_count(),
                stationInfo.getDeletedBy(),
                stationInfo.getDeletedDate(),
                stationInfo.getCountry().getId());
    }
}
