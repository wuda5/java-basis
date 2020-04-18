import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Description :
 * @Author: wuwangqiang
 * @Version: 2020/4/18 0018 14:45
 *
 * 关于JDK8对List的分组汇总--https://blog.csdn.net/u011165335/article/details/76154510

 */
public class groupBy_test {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c", "d", "a", "a", "d", "e");

        String collect = list.stream().collect(Collectors.joining("-"));
        System.out.println(collect);

        Map<String, Long> collect1 = list.stream()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println(collect1);


    }

//    2.统计字符串的单词个数
//
//            这个其实跟上面一样的,下面只写一个简洁的方法
    public static void wordStringCount(String s) {
        //这里开始是字符串,分割后变成字符串流
        Map<String, Long> result = Arrays.stream(s.split("\\s+"))
                .map(word -> word.replaceAll("[^a-zA-Z]", ""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(result);

    }
}
