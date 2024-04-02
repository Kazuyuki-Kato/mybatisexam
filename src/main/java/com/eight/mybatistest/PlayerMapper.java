package com.eight.mybatistest;

import com.eight.mybatistest.Player;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PlayerMapper {
    @Select("SELECT * FROM players ")
    List<Player> findAll();

    @Select("SELECT * FROM players WHERE id = #{id}")
    Optional<Player> findById(Integer id);

    @Insert("INSERT INTO players (name, position, uniform_number, prefecture) VALUES (#{name}, #{position}, #{uniformNumber}, #{prefecture})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Player player);

    @Update("UPDATE player SET name = #{name}, position = #{position}, uniformNumber = #{uniformNumber}, prefecture = #{prefecture} WHERE id = #{id}")
    void update(Player player);
}
