package optional;

import entity.User;
import org.junit.Test;

import java.util.Optional;

/**
 * @Description :
 * @Author: wuwangqiang
 * @Version: 2020/4/23 0023 10:19
 */
public class return_excption {

    @Test(expected = IllegalArgumentException.class)
    public void whenThrowException_thenOk() {

        User user=null;

        User result = Optional.ofNullable(user)
                .orElseThrow( () -> new IllegalArgumentException());
    }
}
