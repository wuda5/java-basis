package spring.base;

import com.itheima.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component("base")

@Data
@AllArgsConstructor
public class base {

    private Long id;
    private String username;
    private String password;
    private String name;

    public base(String username, String password) {
        this.username = username;
        this.password = password;
        System.out.println("a");
    }

    public base() {
        System.out.println("no+++++++++++++++++++++++++++++++");
    }
}
