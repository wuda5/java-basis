package optional;

import entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;

/**
 * @Description :
 * @Author: wuwangqiang
 * @Version: 2020/4/23 0023 10:06
 */
@Slf4j
public class returnDefault {

    @Test
    public void whenEmptyValue_thenReturnDefault() {
        User user = null;
        User user2 = new User("anna@gmail.com", "1234");
        User user3 = new User("33.com", "3");
//        Optional<User> s = Optional.ofNullable(user);
        User result = Optional.ofNullable(user3).orElse(user2);
//        User result = Optional.ofNullable(user).orElse(user2);

//        assertEquals(user2.getEmail(), result.getEmail());
        System.out.println("result:"+result);
    }

    @Test
    public void givenEmptyValue_whenCompare_thenOk() {
        User user = null;
        log.debug("Using orElse");
        User result = Optional.ofNullable(user).orElse(createNewUser());
        log.debug("Using orElseGet");
        User result2 = Optional.ofNullable(user).orElseGet(() -> createNewUser());

        System.out.println("re1:"+result);
        System.out.println("re2:"+result2);
    }

    /**这里 Optional  不为空：*/
    @Test
    public void givenPresentValue_whenCompare_thenOk() {
        User user = new User("john@gmail.com", "1234");

        log.info("Using orElse");
        //这里 Optional  不为空：,但用 orElse 还是执行了 createNewUser
        User result = Optional.ofNullable(user).orElse(createNewUser());
        log.info("Using orElseGet");
        User result2 = Optional.ofNullable(user).orElseGet(() -> createNewUser());
        System.out.println("re1:"+result);
        System.out.println("re2:"+result2);
    }

    private User createNewUser() {
        log.debug("Creating New User");
        return new User("extra@gmail.com", "1234");
    }
}
