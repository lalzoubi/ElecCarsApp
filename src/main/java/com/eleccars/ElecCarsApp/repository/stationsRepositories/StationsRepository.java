package com.eleccars.ElecCarsApp.repository.stationsRepositories;


import com.eleccars.ElecCarsApp.model.entities.stationsEntities.StationInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StationsRepository extends JpaRepository<StationInfo, Long> {

}
