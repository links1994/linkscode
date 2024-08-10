package org.example.mapper;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.Province;

public interface ProvinceMapper {
    // 依据id查询省份方式1
    Province findProvinceById1(Integer id);
    // 依据id查询省份方式2
    Province findProvinceById2(Integer id);

    @Select("select * from province where p_id = #{id}")
    @Results({
            @Result(id = true,column = "p_id",property = "pId"),
            @Result(column = "p_name",property = "pName"),
            @Result(column = "p_id",property = "cityList",
                    many = @Many(select = "com.cn.mapper.CityMapper.findCityByProvinceIdOnAnnotation"))
    })
    Province findProvinceByIdOnAnnotation(Integer id);
}
