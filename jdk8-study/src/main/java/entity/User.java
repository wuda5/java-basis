package entity;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Description :
 * @Author: wuwangqiang
 * @Version: 2020/4/23 0023 9:50
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String name;
    private String email;
    private String position;

    public User(String s, String s1) {
        this.name =s;
        this.email = s1;
    }


//    private int age;
//
//    public User(String s, String s1) {
//    }
}
