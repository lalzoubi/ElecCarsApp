package com.eleccars.ElecCarsApp.model.base;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity<ID> {

    @Column(name = "created_by", updatable = false)
    @CreatedBy
    private String createdBy;
    @Column(name = "created_date", updatable = false)
    @CreatedDate
    private Date createdDate;
    @LastModifiedBy
    @Column(name = "modified_by")
    private String modifiedBy;
    @LastModifiedDate
    @Column(name = "modified_date")
    private Date modifiedDate;

}
