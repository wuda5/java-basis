import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Description : 扁平流的演化
 * @Author: wuwangqiang
 * @Version: 2020/4/18 0018 15:18
 *
 * 1.合并list或者数组 集合
 */
public class flapMap_group {


    public static void main(String[] args) {
        List<String> list = Arrays.asList("ab-cc-dd", "bb-cc", "cc-ab-dd", "dd-cc-ab");
        /**1.会得到多个流*/
        List<String[]> xx = list.stream().map(x -> x.split("-")).collect(Collectors.toList());
        xx.forEach(a-> System.out.println(Arrays.toString(a)));
        /**2.处理合并流*/
        List<String> b = xx.stream().flatMap(x -> Arrays.stream(x)).collect(Collectors.toList());
        System.out.println("--> 转换多个流为1个后："+b);
//        Map<String[], Long> list = list.stream().map(x -> x.split("-")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        /**3. 分组统计 */
        Map<String, Long> collect1 = b.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println(collect1);




        /** 4.--> 统一简化上面的流程-- **/
        Map<String, Long> result = list.stream().map(x -> x.split("-"))
                .flatMap(x -> Arrays.stream(x))
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        System.out.println("简化的结果："+result);

    }

}
