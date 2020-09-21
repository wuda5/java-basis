package optional;

import entity.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;

/**
 * @Description :
 * @Author: wuwangqiang
 * @Version: 2020/4/23 0023 16:32
 */
public class find_frist {

    @Test
    public void whenEmptyStream_thenReturnDefaultOptional() {
//        List<User> users = null;
        List<User> users = new ArrayList<>();
        users.add(new User("hah", "s"));

//         User result =null;
//        Optional.ofNullable(users).ifPresent(x->
//        {
//            User aDefault = x.stream().findFirst().orElse(new User("default", "1234"));
//            users.add(aDefault);
//        });

        User user = users.stream().findFirst().orElse(new User("default", "1234"));

        System.out.println("xx:"+user);
        assertEquals(user.getEmail(), "default");
    }
}
