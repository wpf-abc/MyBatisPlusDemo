package wpf.example.mybatisplus.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * 默认会使用实体类名首字母小写去找对应的表
 *
 * 注解 @TableName 和 @TableId 都可以在全局配置文件中指定
 */
@Data
//@TableName(value = "tbl_employee")  //指定表名
public class Employee {

    /**
     * value 指定主键列，实体类和表对应都是id则可不指定
     * type 指定主键生成策略
     */
    //@TableId(value = "id",type = IdType.AUTO)
    private Integer id ;

    /**
     * value 指定列名(该属性意义不大，2.3版本后驼峰命名默认开启)
     * exist 该列是否是数据库的表字段，默认是true
     */
    @TableField(value = "last_name",exist = true)
    private String  lastName;
    private String  email ;
    private Integer gender;
    private Integer age ;

    @TableField(exist = false)
    private Double salary ;
}
