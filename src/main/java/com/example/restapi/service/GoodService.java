package com.example.restapi.service;

import com.example.restapi.domain.Goods;
import com.example.restapi.domain.GoodsDTO;
import com.example.restapi.repository.GoodsRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Log
@Service
@Transactional
public class GoodService {

    @Autowired
    GoodsRepository goodsRepository;

    public Page<Goods> findAll(Pageable pageable){
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber()-1);
        pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC,"id"));
        return goodsRepository.findAll(pageable);
    }

    public Optional<Goods> findById(int id){
        return goodsRepository.findById(id);
    }

    @Transactional
    public Goods save(GoodsDTO goodsDTO) {

        Goods goods = new Goods();
        goods.setGoodsNm(goodsDTO.getGoodsNm());
        goods.setGoodsCont(goodsDTO.getGoodsCont());
        goods.setComId(goodsDTO.getComId());
        goods.setRegDm(new Date());
        goods.setUpdDm(new Date());

        return goodsRepository.save(goods);
    }

}
