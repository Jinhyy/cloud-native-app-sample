package com.elevenst.mapper;

import com.elevenst.model.MyTableModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MyTableMapper {

    @Select("SELECT * FROM MY_TABLE")
    List<MyTableModel> findMyTableModelAll();
}
