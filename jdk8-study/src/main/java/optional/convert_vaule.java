package optional;

import entity.User;
import org.junit.Test;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;

/**
 * @Description :
 * @Author: wuwangqiang
 * @Version: 2020/4/23 0023 10:23
 */
public class convert_vaule {

    @Test
    public void whenMap_thenOk() {
//        User user = new User("anna@gmail.com", "1234");
        User user = null;

        String email = Optional.ofNullable(user)
                .map(u -> u.getEmail()).orElse("default@gmail.com");

        User user1 = Optional.ofNullable(user)
                .orElseGet(User::new);

        /**
         *  .map 里面先判断 optional 是否有值，无值 会直接返回 empty 包装的 optional,在继续执行后面的orElse的xxx
         *  有值，会执行map(xx)里面的信息 来消费掉，
         * */
        Optional<String> xx = Optional.ofNullable(user)
                .map(u -> u.getEmail());

        System.out.println("re:"+email);

        assertEquals(email, user.getEmail());
    }


    @Test
    public void whenFlatMap_thenOk() {
//        User user = new User("anna@gmail.com", "1234");
//        user.setPosition("Developer");
//        String position = Optional.ofNullable(user)
//                .flatMap(u -> u.getPosition()).orElse("default");
//
//        assertEquals(position, user.getPosition().get());
    }
}
