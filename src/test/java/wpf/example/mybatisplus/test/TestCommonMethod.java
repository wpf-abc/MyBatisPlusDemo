package wpf.example.mybatisplus.test;

import com.baomidou.mybatisplus.plugins.Page;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import wpf.example.mybatisplus.entity.Employee;
import wpf.example.mybatisplus.mapper.EmployeeMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class TestCommonMethod {
    private ApplicationContext context =
            new ClassPathXmlApplicationContext("applicationContext.xml");
    
    private EmployeeMapper employeeMapper =
            context.getBean("employeeMapper", EmployeeMapper.class);

    @Test
    public void testDelete(){
        employeeMapper.deleteById(8);
        //employeeMapper.deleteBatchIds(Arrays.asList(9,10));

        // 根据条件
        //Map<String,Object> map =new HashMap<>();
        //map.put("last_name", "zzz");
        //employeeMapper.deleteByMap(map);
    }

    @Test
    public void testSelect03(){
        //分页查询
        List<Employee> employees = employeeMapper.selectPage(new Page<>(2, 2), null);
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
    }

    @Test
    public void testSelect02(){
        //通过多个id，动态<foreach>
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        List<Employee> employees = employeeMapper.selectBatchIds(list);
        System.out.println(employees.size());

        //通过Map封装条件
        Map<String,Object> map = new HashMap<>();
        map.put("gender", 1);
        map.put("last_name", "Red");    //这里不要写成了 lastName
        List<Employee> employees1 = employeeMapper.selectByMap(map);
        System.out.println(employees1.size());
    }
    
    @Test
    public void testSelect01(){
        // 1. ById
        Employee result = employeeMapper.selectById(7);
        System.out.println(result.toString());

        // 2. By columns
        Employee employee1 = new Employee();
        employee1.setId(7);
        employee1.setLastName("Update");
        //非空列作为查询条件；只能查一条记录，有多条记录则会报错
        Employee result1 = employeeMapper.selectOne(employee1);
        System.out.println(result1.toString());
    }
    
    @Test
    public void testUpdate(){
        Employee employee = new Employee();
        employee.setLastName("Update");
        employee.setEmail("update@163.com");
        employee.setAge(15);
        //employee.setGender(1);
        employee.setId(7);
        //Integer rows = employeeMapper.updateById(employee);
        Integer rows = employeeMapper.updateAllColumnById(employee);
        System.out.println("受影响行数: " + rows);
    }
    @Test
    public void testInsert(){
        Employee employee = new Employee();
        employee.setLastName("Green");
        employee.setEmail("aaa@163.com");
        //employee.setAge(15);
        //employee.setGender(1);

        // SQL: insert into tbl_employee(last_name,email) values (?,?);
        //Integer rows = employeeMapper.insert(employee);
        // insert() 和 insertAllColumn() 的区别：
        // SQL: insert into tbl_employee(last_name,email,age,gender) values(?,?,null,null);
        Integer rows = employeeMapper.insertAllColumn(employee);

        System.out.println("受影响行数: " + rows + "，回写的主键值：" + employee.getId());
    }
    
    @Test
    public void testApplicationContext() throws SQLException {
        DataSource dataSources = context.getBean(DataSource.class);
        System.out.println(dataSources);

        Connection connection = dataSources.getConnection();
        System.out.println(connection);
    }
}
