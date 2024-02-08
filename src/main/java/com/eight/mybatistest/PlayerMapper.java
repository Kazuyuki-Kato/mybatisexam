package com.eight.mybatistest;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PlayerMapper {
    @Select("SELECT * FROM players ")
    List<Player> findAll();

    @Select("SELECT * FROM players WHERE number LIKE CONCAT(#{id}, '%')")
    List<Player> findByNumberStartingWith(int prefix);
}
