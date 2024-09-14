package com.mrgao.xbqx.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Mr.Gao
 * @date 2024/4/9 16:04
 * @apiNote:
 */
@Data
public class Product implements Serializable {

    private Long id;

    private String pid;

    private Integer storkCount;

    private String prdName;

    private List<String> orgNoList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(pid, product.pid) && Objects.equals(storkCount, product.storkCount) && Objects.equals(prdName, product.prdName) && Objects.equals(orgNoList, product.orgNoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pid, storkCount, prdName, orgNoList);
    }
}
