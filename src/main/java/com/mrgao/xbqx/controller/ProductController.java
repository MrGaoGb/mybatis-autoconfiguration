package com.mrgao.xbqx.controller;

import com.mrgao.xbqx.entity.Product;
import com.mrgao.xbqx.mapper.StorkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Mr.Gao
 * @date 2024/4/9 15:44
 * @apiNote:
 */
@RestController
public class ProductController {

    @Autowired
    private StorkMapper storkMapper;

    /**
     * 获取单个商品
     *
     * @param id
     * @param prdName
     * @return
     */
    @GetMapping("/product/single")
    public Product single(@RequestParam Long id, @RequestParam(required = false, name = "pname") String prdName) {
        return storkMapper.getStorkDetailByPid(id, prdName);
    }

    /**
     * 获取所有商品
     *
     * @return
     */
    @GetMapping("/product/listAll")
    public List<Product> listAll(@RequestParam(required = false, name = "pname") String prdName) {
        return storkMapper.listAll(prdName);
    }
}
