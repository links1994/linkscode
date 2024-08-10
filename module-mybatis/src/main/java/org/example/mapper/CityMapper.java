package org.example.mapper;

import org.apache.ibatis.annotations.Select;
import org.example.pojo.City;
import java.util.List;

public interface CityMapper {
    // 依据省份id查询城市
    List<City> findCityByProvinceId(Integer id);


    @Select("select * from city where p_id = #{id}")
    List<City> findCityByProvinceIdOnAnnotation(Integer id);
}
