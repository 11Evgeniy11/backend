package Lesson6.db.dao;

import Lesson6.db.model.Products;
import Lesson6.db.model.ProductsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductsMapper {

    long countByExample(ProductsExample example);


    int deleteByExample(ProductsExample example);


    int deleteByPrimaryKey(Long id);


    int insert(Products record);


    int insertSelective(Products record);


    List<Products> selectByExample(ProductsExample example);


    Products selectByPrimaryKey(Long id);


    int updateByExampleSelective(@Param("record") Products record, @Param("example") ProductsExample example);


    int updateByExample(@Param("record") Products record, @Param("example") ProductsExample example);


    int updateByPrimaryKeySelective(Products record);


    int updateByPrimaryKey(Products record);
}