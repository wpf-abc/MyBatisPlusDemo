package wpf.example.mybatisplus.test;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import wpf.example.mybatisplus.entity.Employee;
import wpf.example.mybatisplus.mapper.EmployeeMapper;

import java.util.Arrays;
import java.util.List;

public class TestEntityWrapper {
    private ApplicationContext context =
            new ClassPathXmlApplicationContext("applicationContext.xml");

    private EmployeeMapper employeeMapper =
            context.getBean("employeeMapper", EmployeeMapper.class);

    // EntityWrapper 都是要求写数据库列名，而不是Java的对象的字段名

    @Test
    public void testEWDelete(){
        employeeMapper.delete(new EntityWrapper<Employee>()
                .eq("last_name", "ccc")
                .eq("email", "ccc")
        );
    }

    @Test
    public void testEWUpdate(){
        Employee employee =new Employee();
        employee.setLastName("UpdatePlus");

        employeeMapper.update(employee, new EntityWrapper<Employee>()
                .eq("gender", 0)
        );
    }
    
    @Test
    public void testEWSelect03(){
        List<Employee> employees = employeeMapper.selectList(new EntityWrapper<Employee>()
                .eq("gender", 1)
                //.orderBy("age")   //asc
                //.orderAsc(Arrays.asList("age"))
                .orderDesc(Arrays.asList("age"))
                //.last("limit 1,3")    // 拼接到SQL语句最后面，有sql注入风险
        );
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
    }

    @Test
    public void testEWSelect02(){
        List<Employee> employees = employeeMapper.selectList(new EntityWrapper<Employee>()
                .eq("gender", 1)
                .like("email", "@at")
                //.or()   // WHERE (gender = ? AND email LIKE ? OR last_name LIKE ?)
                .orNew()  // WHERE (gender = ? AND email LIKE ?) OR (last_name LIKE ?)
                .like("last_name", "z")
        );
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
    }

    /**
     * 条件查询
     */
    @Test
    public void testEWSelect01(){
        // 18 < age <50 , gender = 1 , the first page
        List<Employee> employees = employeeMapper.selectPage(new Page<Employee>(1, 2),
                new EntityWrapper<Employee>()
                        .between("age", 18, 50)
                        .eq("gender", 1)
                        .eq("last_name", "zzz")
        );
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
    }
}
