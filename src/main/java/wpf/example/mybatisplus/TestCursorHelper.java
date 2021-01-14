package wpf.example.mybatisplus;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wpf.example.mybatisplus.entity.Employee;
import wpf.example.mybatisplus.mapper.EmployeeMapper;


public class TestCursorHelper {
    private ApplicationContext context =
            new ClassPathXmlApplicationContext("applicationContext.xml");

    private EmployeeMapper employeeMapper =
            context.getBean("employeeMapper", EmployeeMapper.class);

    private DefaultSqlSessionFactory sqlSessionFactoryBean =
            context.getBean("sqlSessionFactoryBean", DefaultSqlSessionFactory.class);

    public void testCursor1(){
        SqlSession sqlSession = sqlSessionFactoryBean.openSession();

        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

        Cursor<Employee> scan = mapper.scan(5);
        scan.forEach(System.out::println);
    }

    @Transactional
    public void testCursor2(){
        Cursor<Employee> scan = employeeMapper.scan(5);
        scan.forEach(System.out::println);
    }
}
