package wpf.example.mybatisplus.test;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import wpf.example.mybatisplus.entity.Department;

import java.util.List;

public class TestAR {
    private ApplicationContext context =
            new ClassPathXmlApplicationContext("applicationContext.xml");

    /***
     * 删除不存在的属于删除成功
     */
    @Test
    public void testDelete(){
        Department department = new Department();
        boolean b = department.deleteById(3);
        System.out.println("b = " + b);

        department.setId(4);
        boolean b1 = department.deleteById();
        System.out.println("b1 = " + b1);

        boolean b2 = department.delete(new EntityWrapper().like("dept_name", "xx"));
        System.out.println("b2 = " + b2);
    }

    /**
     * 分页
     */
    @Test
    public void testSelect05(){
        Department department = new Department();
        Page<Department> departmentPage = department.selectPage(new Page<Department>(2, 1),
                new EntityWrapper<Department>()
                        .eq("floor", 1));
        List<Department> records = departmentPage.getRecords();
        System.out.println("records = " + records);
    }
    
    @Test
    public void testSelect04(){
        Department department = new Department();
        int sum = department.selectCount(new EntityWrapper()
                .eq("floor", 1));
        System.out.println(sum);
    }

    @Test
    public void testSelect03(){
        Department department = new Department();
        List<Department> departments = department.selectList(new EntityWrapper()
                .eq("dept_name", "AA"));
        System.out.println(departments);
    }

    @Test
    public void testSelect02(){
        Department department = new Department();
        List<Department> departments = department.selectAll();
        System.out.println(departments);
    }

    @Test
    public void testSelect01(){
        Department department = new Department();
        Department result = department.selectById(2);
        System.out.println(result.toString());

        department.setId(1);
        Department dept = department.selectById();
        System.out.println(dept.toString());
    }

    /**
     * 修改
     */
    @Test
    public void testUpdate(){
        Department department = new Department();
        department.setId(2);
        department.setDeptNum(1001);
        department.setDeptName("BB");
        department.setFloor(1);

        boolean result = department.updateById();
        System.out.println(result);
    }

    /**
     * 插入
     */
    @Test
    public void testInsert(){
        Department department = new Department();
        department.setDeptNum(1000);
        department.setDeptName("AA");
        department.setFloor(1);

        boolean result = department.insert();
        System.out.println(result);
    }

}
