package com.example.restapi.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 상품속성 Domain
 */
@Entity
@Table(name="goods")
@Data
public class Goods extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_no")
    private int id;

    @Column(name = "goods_nm")
    private String goodsNm;

    @Lob
    @Column(name = "goods_cont")
    private String goodsCont;

    @Column(name = "com_id")
    private String comId;

}
