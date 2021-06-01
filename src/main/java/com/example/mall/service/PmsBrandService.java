package com.example.mall.service;

import com.example.mall.mbg.model.PmsBrand;

import java.util.List;

/**
 * @author linxinying
 * @version 1.0.0
 * @ClassName PmsBrandService.java
 * @Description TODO
 * @createTime 2021-05-31 20:51:00
 */
public interface PmsBrandService {

    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrand brand);

    int updateBrand(Long id, PmsBrand brand);

    int deleteBrand(long id);

    List<PmsBrand> listBrand(int pageNum, int pageSize);

    PmsBrand getBrand(long id);
}
