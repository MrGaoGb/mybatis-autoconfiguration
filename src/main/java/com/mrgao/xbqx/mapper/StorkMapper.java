package com.mrgao.xbqx.mapper;


import com.mrgao.xbqx.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Mr.Gao
 * @date 2024/1/8 15:39
 * @apiNote:
 */
public interface StorkMapper {

    /**
     * 获取商品库存明细
     *
     * @param id
     * @param prdName
     * @return
     */
    Product getStorkDetailByPid(@Param("id") Long id, @Param("prdName") String prdName);

    /**
     * 获取所有商品
     *
     * @param prdName
     * @return
     */
    List<Product> listAll(@Param("prdName") String prdName);
}
