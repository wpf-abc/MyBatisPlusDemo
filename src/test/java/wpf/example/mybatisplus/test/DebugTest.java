package wpf.example.mybatisplus.test;

import java.util.HashMap;

public class DebugTest {
    //step over 相当于一行一行的执行
    //step in 进入方法内部
    //step out 从方法内出来
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Tom");
        map.put("age", 12);
        map.put("qq", "123456");
        map.put("email", "qq@QQ.com`");

        Object qq = map.get("qq");
        System.out.println(qq);

        map.remove("age");
        System.out.println(map);
    }
}
