package com.example.restapi;

import com.example.restapi.domain.Goods;
import com.example.restapi.domain.GoodsDTO;
import com.example.restapi.service.GoodService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class RestApiApplicationTests {

    @Autowired
    GoodService goodService;

    @Test
    void contextLoads() {
    }

    @Test
    void getList(){
        int page = 10;
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC,"id"));

        Page<Goods> goodsList = goodService.findAll(pageable);

        System.out.println(goodsList.get());
    }

    @Test
    void insert(){

        GoodsDTO goods = new GoodsDTO();
        goods.setGoodsNm("테스트");
        goods.setGoodsCont("테스트설명");
        goods.setComId("업체아이디");

        goodService.save(goods);

    }
}
