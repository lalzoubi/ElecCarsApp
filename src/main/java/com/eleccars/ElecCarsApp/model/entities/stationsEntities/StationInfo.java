package com.eleccars.ElecCarsApp.model.entities.stationsEntities;

import com.eleccars.ElecCarsApp.model.base.BaseEntity;
import com.eleccars.ElecCarsApp.model.entities.commonEntities.GeneralLookupsDtl;
import com.eleccars.ElecCarsApp.model.entities.securityEntities.UserInfo;

import com.eleccars.ElecCarsApp.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Where(clause = "id != -1")
@Table(name = "station_info")
public class StationInfo extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "station_name_ar")
    private String stationNameAr;
    @Column(name = "station_name_en")
    private String stationNameEn;
    @Column(name = "station_address_desc")
    private String stationAddressDesc;
    private String latitude;
    private String longitude;
    @Column(name = "is_station_active")
    private Boolean stationActive;
    @Column(name = "is_station_available")
    private Boolean stationAvailable;
    @Column(name = "sockets_count")
    private Integer socketsCount;
    @Column(name = "deleted_by")
    private String deletedBy;
    @Column(name = "deleted_date")
    private String deletedDate;

    //ManyToOne Relations
    @ManyToOne()
    @JoinColumn(name = "country_id_ref")
    private GeneralLookupsDtl country;

    @OneToMany(mappedBy = "users_station")
    private List<UserInfo> userInfoList;
}
