package com.eleccars.ElecCarsApp.model.stationsModels;

import com.eleccars.ElecCarsApp.base.BaseEntity;
import com.eleccars.ElecCarsApp.model.commonModels.GeneralLookupsDtl;
import com.eleccars.ElecCarsApp.model.securityModels.UserInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StationInfo extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String station_name_ar;
    private String station_name_en;
    private String station_address_desc;
    private String latitude;
    private String longitude;
    @Column(name = "is_station_active")
    private Boolean stationActive;
    @Column(name = "is_station_available")
    private Boolean stationAvailable;
    private Integer sockets_count;
    private String deletedBy;
    private String deletedDate;

    //ManyToOne Relations
    @ManyToOne()
    @JoinColumn(name = "countryIdRef")
    private GeneralLookupsDtl country;

    @OneToMany(mappedBy = "users_station")
    private List<UserInfo> userInfoList;
}
