package com.eleccars.ElecCarsApp.repository.stationsRepositories;


import com.eleccars.ElecCarsApp.model.stationsModels.StationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationsRepository extends JpaRepository<StationInfo, Long> {
}
