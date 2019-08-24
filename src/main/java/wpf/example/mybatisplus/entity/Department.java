package wpf.example.mybatisplus.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * 开启 AR，只需继承 Model<T>
 *     并指明主键属性
 */
@Data
public class Department extends Model<Department> {

    private Integer id;
    private Integer deptNum;
    private String deptName;
    private Integer floor;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
