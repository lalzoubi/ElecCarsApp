package com.eleccars.ElecCarsApp.service.stationsServices;


import com.eleccars.ElecCarsApp.dto.stationsDTOs.StationsInfoDto;
import com.eleccars.ElecCarsApp.mapper.stationsMappers.StationsMapper;
import com.eleccars.ElecCarsApp.model.stationsModels.StationInfo;
import com.eleccars.ElecCarsApp.repository.stationsRepositories.StationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StationsService {

    @Autowired
    StationsRepository stationsRepository;

    StationsMapper stationsMapper = new StationsMapper();

    @Transactional
    public void saveStationDetails(StationInfo stationInfo) {
        stationsRepository.save(stationInfo);
    }

    @Transactional(readOnly = true)
    public List<StationsInfoDto> findStationById(Long id) {
        return stationsRepository.findById(id).stream().map(stationsMapper).collect(Collectors.toList());
    }

    @Transactional
    public void deleteStationById(Long id) {
        StationInfo station = stationsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Station not found"));
        station.setIs_active(0);
        stationsRepository.save(station);
    }

    @Transactional
    public void reactiveStationById(Long id) {
        StationInfo station = stationsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Station not found"));
        station.setIs_active(1);
        stationsRepository.save(station);
    }
}
