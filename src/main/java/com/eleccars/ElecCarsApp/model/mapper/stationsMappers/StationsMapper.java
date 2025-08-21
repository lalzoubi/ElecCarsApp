package com.eleccars.ElecCarsApp.model.mapper.stationsMappers;

import com.eleccars.ElecCarsApp.model.dto.stationsDTOs.StationsInfoDto;
import com.eleccars.ElecCarsApp.model.entities.stationsModels.StationInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface StationsMapper {

    @Mappings({
            @Mapping(source = "countryIdRef", target = "country.id")})
    StationInfo toEntity(StationsInfoDto dto);

    @Mappings({
            @Mapping(source = "country.id", target = "countryIdRef")})
    StationsInfoDto toDto(StationInfo entity);

    @Mappings({
            @Mapping(source = "country.id", target = "countryIdRef")})
    default Page<StationsInfoDto> toPageDto(Page<StationInfo> stationsPage) {
        return stationsPage.map(this::toDto);
    }
}
