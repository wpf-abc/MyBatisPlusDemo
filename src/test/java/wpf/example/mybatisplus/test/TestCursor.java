package wpf.example.mybatisplus.test;

import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import wpf.example.mybatisplus.TestCursorHelper;
import wpf.example.mybatisplus.entity.Employee;
import wpf.example.mybatisplus.mapper.EmployeeMapper;

public class TestCursor {


    @Test
    public void testCursor(){
        TestCursorHelper testHelper = new TestCursorHelper();
        testHelper.testCursor1();
    }

    @Test
    public void testCursor2(){
        TestCursorHelper testHelper = new TestCursorHelper();
        testHelper.testCursor2();
    }
    
    @Test
    public void testCurSor3(){
        TestCursorHelper testHelper = new TestCursorHelper();
        testHelper.testCursor3();
    }
}
