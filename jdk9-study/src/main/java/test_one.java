import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * @Description :
 * @Author: wuwangqiang
 * @Version: 2020/4/23 0023 16:27
 */
public class test_one {

    @Test
    public void whenGetStream_thenOk() {
        User user = new User("john@gmail.com", "1234");
        List<String> emails = Optional.ofNullable(user)
                .stream()
                .filter(u -> u.getEmail() != null && u.getEmail().contains("@"))
                .map( u -> u.getEmail())
                .collect(Collectors.toList());

        assertTrue(emails.size() == 1);
        assertEquals(emails.get(0), user.getEmail());
    }
}
