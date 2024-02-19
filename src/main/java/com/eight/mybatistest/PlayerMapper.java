package com.eight.mybatistest;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import java.util.Optional;
@Mapper
public interface PlayerMapper {
    @Select("SELECT * FROM players ")
    List<Player> findAll();
    @Select("SELECT * FROM players WHERE id = #{id}")
    Optional<Player> findById(int id);
}