package com.itheima.bean;
//package com.itheima.domain.User;


//import com.itheima.domain.Uactory.FactoryBean;
import com.itheima.domain.People;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component("g")

public class factoryTest implements FactoryBean<People> {

    @Override
    public People getObject() throws Exception {
        People a=  new People();
        a.setName("=====help create people---- ---");
        return a;
    }

    @Override
    public Class<?> getObjectType() {
        return People.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
//        return true;
    }
}
