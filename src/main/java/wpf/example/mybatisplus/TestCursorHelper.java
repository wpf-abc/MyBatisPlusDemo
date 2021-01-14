package wpf.example.mybatisplus;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import wpf.example.mybatisplus.entity.Employee;
import wpf.example.mybatisplus.mapper.EmployeeMapper;

/**
 * mybatis 流式查询：
 *  mapper层：
 *      返回Cursor<T>类型的值即可。如： Cursor<Employee> scan(@Param("limit") int  limit);
 *  调用：
 *      流式查询的时候连接必须保持打开，且需要自己关闭。
 *      1. wrongWay() 是错误的调用方式，连接立马关闭了。
 *      2. testCursor1() 使用工厂获取 SqlSession
 *      3. testCursor2() 使用事务，注解版
 *      4. testCursor3() 使用事务，编程版
 */
public class TestCursorHelper {
    private ApplicationContext context =
            new ClassPathXmlApplicationContext("applicationContext.xml");

    private EmployeeMapper employeeMapper =
            context.getBean("employeeMapper", EmployeeMapper.class);

    private DefaultSqlSessionFactory sqlSessionFactoryBean =
            context.getBean("sqlSessionFactoryBean", DefaultSqlSessionFactory.class);

    TransactionTemplate transactionTemplate = new TransactionTemplate();

    private void wrongWay(){
        Cursor<Employee> scan = employeeMapper.scan(5);
        scan.forEach(System.out::println);
    }

    public void testCursor1(){
        SqlSession sqlSession = sqlSessionFactoryBean.openSession();

        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

        Cursor<Employee> scan = mapper.scan(5);
        scan.forEach(System.out::println);
    }

    /**
     * 注意要在外部调用
     */
    @Transactional
    public void testCursor2(){
        Cursor<Employee> scan = employeeMapper.scan(5);
        scan.forEach(System.out::println);
    }

    public void testCursor3(){
        transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
                Cursor<Employee> scan = employeeMapper.scan(5);
                scan.forEach(System.out::println);
                return null;
            }
        });

    }
}
