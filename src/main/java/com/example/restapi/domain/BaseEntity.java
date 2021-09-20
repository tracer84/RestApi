//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.restapi.domain;

import lombok.Data;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Data
@MappedSuperclass
public class BaseEntity {

    @Column(name = "reg_dm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDm;

    @Column(name = "upd_dm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updDm;
}
