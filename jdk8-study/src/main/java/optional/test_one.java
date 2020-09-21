package optional;

import entity.User;

import java.util.Optional;

/**
 * @Description :
 * @Author: wuwangqiang
 * @Version: 2020/4/23 0023 9:48
 *
 * ifPresent 和 isPresent 不同注意！！
 */
import static org.junit.Assert.*;//必须是static
import org.junit.Test;
public class test_one {

    @Test
    public void whenCheckIfPresent_thenOk() {
        User user = new User("john@gmail.com", "1234");
        Optional<User> opt = Optional.ofNullable(user);
        assertTrue(opt.isPresent());

        assertEquals(user.getEmail(), opt.get().getEmail());

        /*** ifPresent 和 isPresent 不同注意！！*/
        Optional.ofNullable(user).ifPresent(x-> System.out.println(x));
        boolean present = Optional.ofNullable(user).isPresent();
    }

    public static void main(String[] args) {
//        User user =null ;
//        Optional<User> opt = Optional.of(user);

        testCanNull();
    }

    private static void testCanNull() {
        String name = "John";
        String name2 = null;
        Optional<String> opt = Optional.ofNullable(name2);
//        Optional<String> opt = Optional.ofNullable(name);

        //  opt.get() 为null 汇报 空指针
        assertEquals("John", opt.get());
    }

}
