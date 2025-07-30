package com.eleccars.ElecCarsApp.model.commonModels;

import com.eleccars.ElecCarsApp.model.stationsModels.StationInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GeneralLookupsDtl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String lookup_name_ar;
    private String lookup_name_en;
    private String lookup_desc;

    //ManyToOne Relations
    @ManyToOne()
    @JoinColumn(name = "LkIdRef")
    private GeneralLookups generalLookups;

    @OneToMany(mappedBy = "country")
    private List<StationInfo> stationInfoList;
}
