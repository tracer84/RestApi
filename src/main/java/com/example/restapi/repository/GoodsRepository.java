package com.example.restapi.repository;

import com.example.restapi.domain.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Goods, Integer> {
    //Page<Goods> findAll(Pageable pageable);
/*
    // 단건 조회
    Goods findByid(int id);

    // 이름으로 조회
    Goods findByName(String goodsNm);

    Page<Goods> findAll(Pageable pageable);*/
}
