package wpf.example.mybatisplus.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import wpf.example.mybatisplus.entity.Employee;

/**
 * Mapper接口
 *
 * 基于Mybatis: 在 Mapper接口中编写CRUD相关的方法，提供 Mapper接口所对应的SQL映射文件，
 *      以及方法对应的SQL语句.
 *
 * 基于MP:  让xxxMapper接口继承 BaseMapper<T>接口即可.
 * 		   BaseMapper<T> : 泛型指定的就是当前Mapper接口所操作的实体类类型
 */
public interface EmployeeMapper extends BaseMapper<Employee> {
    //  MyBatis 方式插入数据后想要立即获得主键：
    //   Integer  insertEmployee(Employee employee );
    //   <insert useGeneratedKeys="true" keyProperty="id" > SQL...</insert>
    //   插入完成后会把主键值传递给 id 字段。

    //MybatisPlus 则会自动回写到对象中去
}
