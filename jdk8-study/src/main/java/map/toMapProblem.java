package map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * toMap 一次Collectors.toMap的问题
 * 参考: https://www.jianshu.com/p/aeb21dea87e0
 *
 * 原来Collectors.toMap底层是基于Map.merge方法来实现的，而merge中value是不能为null的，如果为null，就会抛出空指针异常
 * 注意: key 是可以为null 的哦!!!!
 *
 * **/
public class toMapProblem {

    public static void main(String[] args) {
        // initMemberList为获取数据的方法
        List<Member> list = toMapProblem.initMemberList();
        // --Exception in thread "main" java.lang.NullPointerException
        Map<String, String> memberMap = list.stream().collect(Collectors.toMap(Member::getId, Member::getImgPath));
        System.out.println(memberMap);
    }

    /**1. 解决方式1
     原来for循环的方式，亦或是forEach的方式*/
    @Test
    public void resovleOne(){
        List<Member> list = toMapProblem.initMemberList();

        Map<String, String> memberMap = new HashMap<>();
        list.forEach((answer) -> memberMap.put(answer.getId(), answer.getImgPath()));
        System.out.println(memberMap);

    }
    /** 2. 解决方式 2
     使用stream的collect的重载方法：*/
    @Test
    public void resovleSecond(){
        List<Member> list = toMapProblem.initMemberList();

        // m代表了 new的map, v 就是list的元素
        Map<String, String> memberMap = list.stream()
                .collect(HashMap::new, (m,v)-> m.put(v.getId(), v.getImgPath()), HashMap::putAll);
        System.out.println(memberMap);

        list.stream() .collect(HashMap::new, (map,v)-> map.put(v.getId(),v.getImgPath() ), HashMap::putAll);

    }

    // setImgPath 为 null--
    public static List<Member> initMemberList() {
        Member member1 = new Member();
//        member1.setId(null);// key可以null
        member1.setId("id_1");
        member1.setImgPath("http://www.baidu.com");
        // 这里有一个null导致的
        Member member2 = new Member();
        member2.setId("id_1");
//        member2.setImgPath("hh");
        member2.setImgPath(null);

        List<Member> list = new ArrayList<>();
        list.add(member1);
        list.add(member2);
        return list;
    }

}








@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
class Member {
    private String id;
    private String imgPath;

    // get set省略
}