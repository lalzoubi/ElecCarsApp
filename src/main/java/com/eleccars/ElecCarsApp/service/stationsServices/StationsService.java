package com.eleccars.ElecCarsApp.service.stationsServices;


import com.eleccars.ElecCarsApp.model.dto.stationsDTOs.StationsInfoDto;
import com.eleccars.ElecCarsApp.model.mapper.stationsMappers.StationsMapper;
import com.eleccars.ElecCarsApp.model.entities.stationsModels.StationInfo;
import com.eleccars.ElecCarsApp.repository.stationsRepositories.StationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StationsService {

    @Autowired
    StationsRepository stationsRepository;

    @Autowired
    StationsMapper stationsMapper;

    @Transactional
    public void saveStationDetails(StationInfo stationInfo) {
        stationsRepository.save(stationInfo);
    }

    @Transactional(readOnly = true)
    public StationsInfoDto findStationById(Long id) {
        Optional <StationInfo> info = stationsRepository.findById(id);
        return stationsMapper.toDto(info.get());

        //return stationsRepository.findById(id).stream().map(stationsMapper).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<StationsInfoDto> getAllStations(int pageNum, int pageSize, String sortCol, Boolean isAsc) {

        Pageable pageable = PageRequest.of(pageNum,pageSize, Sort.by(isAsc ? Sort.Direction.ASC : Sort.Direction.DESC, sortCol));
        Page<StationInfo> info = stationsRepository.findAllStations(pageable);
        return stationsMapper.toPageDto(info);
    }

    @Transactional
    public void deleteStationById(Long id) {
        StationInfo station = stationsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Station not found"));
        station.setStationActive(false);
        stationsRepository.save(station);
    }

    @Transactional
    public void reactiveStationById(Long id) {
        StationInfo station = stationsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Station not found"));
        station.setStationActive(true);
        stationsRepository.save(station);
    }
}
