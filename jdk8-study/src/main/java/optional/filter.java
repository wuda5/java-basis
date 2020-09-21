package optional;

import entity.User;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertTrue;

/**
 * @Description :
 * @Author: wuwangqiang
 * @Version: 2020/4/23 0023 16:13
 */
public class filter {

    @Test
    public void whenFilter_thenOk() {
        User user = new User("anna@gmail.com", "1234");
        /***此时 position 为null, 好好领悟，一般就是药 ！=null 的过滤下来； */
        Optional<User> result = Optional.ofNullable(user)
                .filter(u -> u.getPosition() == null );
//                .filter(u -> u.getEmail() != null && u.getEmail().contains("@"));

        result.get();
        result.ifPresent(x-> System.out.println(x));
        System.out.println("xx:"+result.isPresent());
        assertTrue(result.isPresent());
    }




}
