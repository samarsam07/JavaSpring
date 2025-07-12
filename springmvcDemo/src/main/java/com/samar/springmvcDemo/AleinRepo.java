package com.samar.springmvcDemo;

import com.samar.springmvcDemo.model.Alein;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AleinRepo extends JpaRepository<Alein,Integer> {

//    List<Alein> findByName(String name);//query dsl
    @Query("from Alein where name=:name")
    List<Alein> find(@Param("name") String name);
}
