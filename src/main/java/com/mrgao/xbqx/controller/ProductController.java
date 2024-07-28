package com.mrgao.xbqx.controller;

import com.mrgao.xbqx.service.ProductService;
import com.mrgao.xbqx.entity.Product;
import com.mrgao.xbqx.mapper.StorkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
    @Autowired
    private ProductService productService;

    /**
     * 获取单个商品
     *
     * @return
     */
    @GetMapping("/product/testEnv")
    public Integer testEnv() {
        return productService.judgeExist();
    }

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
     * 获取单个商品(在无事务的前提下，对比一级缓存和二级缓存的工作原理)
     *
     * @param id
     * @param prdName
     * @return
     */
    @GetMapping("/product/single/testByNoTransactional")
    public Product testByNoTransactional(@RequestParam Long id, @RequestParam(required = false, name = "pname") String prdName) {
        Product detail1 = storkMapper.getStorkDetailByPid(id, prdName);
        System.out.println("-----------(一级缓存)基于SQLSession，无@Transactional注解----------");
        Product detail2 = storkMapper.getStorkDetailByPid(id, prdName);
        return detail2;
    }

    /**
     * 获取单个商品(在无事务的前提下，对比一级缓存和二级缓存的工作原理)
     *
     * @param id
     * @param prdName
     * @return
     */
    @Transactional
    @GetMapping("/product/single/testByTransactional")
    public Product testByTransactional(@RequestParam Long id, @RequestParam(required = false, name = "pname") String prdName) {
        Product detail1 = storkMapper.getStorkDetailByPid(id, prdName);
        System.out.println("-----------(一级缓存)基于SQLSession，@Transactional注解----------");
        Product detail2 = storkMapper.getStorkDetailByPid(id, prdName);
        return detail2;
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
