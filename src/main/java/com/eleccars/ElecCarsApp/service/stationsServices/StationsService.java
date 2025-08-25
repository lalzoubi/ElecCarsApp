package com.eleccars.ElecCarsApp.service.stationsServices;


import com.eleccars.ElecCarsApp.model.dto.stationsDTOs.StationsInfoDto;
import com.eleccars.ElecCarsApp.model.entities.stationsEntities.StationInfo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface StationsService {

    void saveStationDetails(StationsInfoDto StationsInfoDto);

    void updateStationDetails(StationsInfoDto StationsInfoDto);

    StationsInfoDto findStationById(Long id);

    Page<StationsInfoDto> getAllStations(int pageNum, int pageSize, String sortCol, Boolean isAsc);

    void deleteStationById(Long id);

    void reactiveStationById(Long id);

}
