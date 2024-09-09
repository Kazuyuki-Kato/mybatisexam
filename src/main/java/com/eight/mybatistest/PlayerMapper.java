package com.eight.mybatistest;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PlayerMapper {
    @Select("SELECT * FROM players ")
    List<Player> findAll();

    @Select("SELECT * FROM players WHERE id = #{id}")
    Optional<Player> findById(Integer id);

    @Insert("INSERT INTO players (last_Name, first_Name, position, uniform_number, prefecture) VALUES (#{lastName}, #{firstName}, #{position}, #{uniformNumber}, #{prefecture})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Player player);

    @Update("UPDATE players SET last_Name = #{lastName}, first_Name = #{firstName}, position = #{position}, uniform_number = #{uniformNumber}, prefecture = #{prefecture} WHERE id = #{id}")
    void update(Player player);

    @Delete("DELETE FROM players WHERE id = #{id}")
    void delete(@Param("id") Integer id);
}
