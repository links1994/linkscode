package org.example.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.pojo.Customer;

public interface CustomerMapper {
    // 依据id查询customer的错误方式
    Customer queryCustomerById0(Integer id);
    // 依据id查询customer方式1
    Customer queryCustomerById1(Integer id);
    // 依据id查询customer方式2
    Customer queryCustomerById2(Integer id);
    // 依据id查询customer方式3
    Customer queryCustomerById3(Integer id);

    // 查询客户
    @Select("select * from customer where c_id = #{id}")
    Customer queryCustomerById(Integer id);

    // 插入客户
    @Insert("insert into customer(c_name,c_age) values (#{cName},#{cAge})")
    int insertCustomer(Customer customer);

    // 更新客户
    @Update("update customer set c_name=#{cName},c_age=#{cAge} where c_id=#{cId}")
    int updateCustomer(Customer customer);

    // 删除客户
    @Delete("delete from customer where c_id=#{id}")
    int deleteCustomerById(Integer id);
}
