package com.eleccars.ElecCarsApp.service.stationsServices.Impl;


import com.eleccars.ElecCarsApp.model.dto.stationsDTOs.StationsInfoDto;
import com.eleccars.ElecCarsApp.model.mapper.stationsMappers.StationsMapper;
import com.eleccars.ElecCarsApp.model.entities.stationsEntities.StationInfo;
import com.eleccars.ElecCarsApp.repository.stationsRepositories.StationsRepository;
import com.eleccars.ElecCarsApp.service.stationsServices.StationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StationsServiceImpl implements StationsService {

    final StationsRepository stationsRepository;
    final StationsMapper stationsMapper;

    @Transactional
    @Override
    public void saveStationDetails(StationsInfoDto StationsInfoDto) {
        StationInfo entity = stationsMapper.toEntity(StationsInfoDto);
        stationsRepository.save(entity);
    }

    @Override
    public void updateStationDetails(StationsInfoDto StationsInfoDto) {
        StationInfo entity = stationsMapper.toEntity(StationsInfoDto);
        stationsRepository.save(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public StationsInfoDto findStationById(Long id) {
        Optional <StationInfo> info = stationsRepository.findById(id);
        return stationsMapper.toDto(info.get());

        //return stationsRepository.findById(id).stream().map(stationsMapper).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public Page<StationsInfoDto> getAllStations(int pageNum, int pageSize, String sortCol, Boolean isAsc) {

        Pageable pageable = PageRequest.of(pageNum,pageSize, Sort.by(isAsc ? Sort.Direction.ASC : Sort.Direction.DESC, sortCol));
        Page<StationInfo> info = stationsRepository.findAll(pageable);
        return stationsMapper.toPageDto(info);
    }

    @Transactional
    @Override
    public void deleteStationById(Long id) {
        StationInfo station = stationsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Station not found"));
        station.setStationActive(false);
        stationsRepository.save(station);
    }

    @Transactional
    @Override
    public void reactiveStationById(Long id) {
        StationInfo station = stationsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Station not found"));
        station.setStationActive(true);
        stationsRepository.save(station);
    }
}
