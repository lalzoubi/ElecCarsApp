package com.eleccars.ElecCarsApp.mapper.stationsMappers;

import com.eleccars.ElecCarsApp.dto.stationsDTOs.StationsInfoDto;
import com.eleccars.ElecCarsApp.model.stationsModels.StationInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface StationsMapper {

    @Mappings({
            @Mapping(source = "countryIdRef", target = "country.id")})
    StationInfo toEntity(StationsInfoDto dto);

    @Mappings({
            @Mapping(source = "country.id", target = "countryIdRef")})
    StationsInfoDto toDto(StationInfo entity);
}
