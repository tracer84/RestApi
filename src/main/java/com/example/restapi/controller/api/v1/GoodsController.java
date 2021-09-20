package com.example.restapi.controller.api.v1;

import com.example.restapi.domain.Goods;
import com.example.restapi.domain.GoodsDTO;
import com.example.restapi.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value = "${demo.api}/goods")
public class GoodsController {

    @Autowired
    GoodService goodService;

    /**
     * 상품 리스트 조회
     * @param pageable
     * @param goods
     * @return
     */
    @GetMapping("")
    public ResponseEntity<Page<Goods>> list(@PageableDefault Pageable pageable, Goods goods){

        Page<Goods> GoodsList = goodService.findAll(pageable);

        return new ResponseEntity<Page<Goods>>(GoodsList, HttpStatus.OK);
    }

    /**
     * 상품 상세 조회
     * @param id
     * @return
     */
    @GetMapping("/{id}}")
    public ResponseEntity<Goods> detail(@PathVariable int id){

        Optional<Goods> goods = goodService.findById(id);

        if(goods.isPresent()) {
            return new ResponseEntity<Goods>(goods.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * 상품 저장
     * @param goodsDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<Goods> save(@RequestBody GoodsDTO goodsDTO) {
        return new ResponseEntity<Goods>(goodService.save(goodsDTO), HttpStatus.OK);
    }

}
