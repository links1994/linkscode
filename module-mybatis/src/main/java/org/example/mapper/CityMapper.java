package org.example.mapper;

import org.example.pojo.City;
import java.util.List;

public interface CityMapper {
    // 依据省份id查询城市
    List<City> findCityByProvinceId(Integer id);
}
