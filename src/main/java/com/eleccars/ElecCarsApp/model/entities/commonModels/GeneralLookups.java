package com.eleccars.ElecCarsApp.model.entities.commonModels;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GeneralLookups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String lookup_name_ar;
    private String lookup_name_en;
    private String lookup_desc;

    //@JsonManagedReference
    @OneToMany(mappedBy = "generalLookups")
    private List<GeneralLookupsDtl> generalLookupsDtlList;


}
