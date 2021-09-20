package com.example.restapi.component;

import com.example.restapi.domain.GoodsDTO;
import com.example.restapi.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AppInitializer {

    @Autowired
    GoodService goodService;

    @PostConstruct
    private void init(){

        GoodsDTO goods = new GoodsDTO();
        goods.setGoodsNm("테스트");
        goods.setGoodsCont("테스트설명");
        goods.setComId("업체아이디");

        goodService.save(goods);
    }
}
