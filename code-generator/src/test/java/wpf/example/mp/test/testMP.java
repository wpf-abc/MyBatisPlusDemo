package wpf.example.mp.test;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

public class testMP {
    @Test
    public void testGenerator(){
        //1.全局配置
        GlobalConfig globalConfig =new GlobalConfig();
        globalConfig.setActiveRecord(true)    //开启AR模式
                .setAuthor("wpf")
                .setOutputDir("F:\\IDEAWorskspace\\MyBatisPlusDemo\\code-generator\\src\\main\\java")   //路径
                .setFileOverride(true)  //文件覆盖
                .setIdType(IdType.AUTO) //主键策略
                .setServiceName("%sService")    //设置生成的接口的首字母是否为 I
                .setBaseResultMap(true)
                .setBaseColumnList(true);   //sql片段

        //2.数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)    //数据库类型
                .setDriverName("com.mysql.jdbc.Driver")
                .setUrl("jdbc:mysql://127.0.0.1:3306/mybatisplus")
                .setUsername("root")
                .setPassword("admin123");

        //3.策略配置
        StrategyConfig strategyConfig =new StrategyConfig();
        strategyConfig.setCapitalMode(true) //全局大小写
                .setDbColumnUnderline(true) //指定是否使用了下划线
                .setNaming(NamingStrategy.underline_to_camel)   //数据库表映射到实体的策略
                .setTablePrefix("tbl_")
                .setInclude("tbl_employee");    //生成的表

        //4.包名策略配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("wpf.example.mp")
                .setMapper("mapper")
                .setService("service")
                .setController("controller")
                .setEntity("entity")
                .setXml("mapper");

        //5.整合配置
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .execute();
    }
}
