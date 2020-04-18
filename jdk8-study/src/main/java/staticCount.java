

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description :统计文本中所有单词相关的的统计信息
 *
 * 使用stream统计文章单词数
 *
 * @Author: wuwangqiang
 * @Version: 2020/4/18 0018 13:18
 */
public class staticCount {

    public static void main(String[] args) throws IOException {

        String path1 = "classes/production/jdk8study/danci.txt";
//        String path1 = System.getProperty("user.dir") + "\\src\\main\\resources\\danci.txt";
              System.out.println(path1);

              test(path1);
              test2(path1);
        // 使用try-resource 关闭资源
        try (BufferedReader reader = new BufferedReader(
                new FileReader(path1)))
        {
            long wordCount = reader.lines()
                    // trim前后空格(使用方法引用)
                    .map(String::trim)
                    // 过滤掉空串
                    .filter(s -> !s.isEmpty())
                    // 把空格隔开的转为单词数组
                    .map(s -> s.split(" "))
                    // 得到数组长度
                    .mapToInt(array -> array.length)
                    // 并行(都是无状态操作)
                    .parallel()
                    // 求和
                    .sum();

            System.out.println("单词数:" + wordCount);

        }

    }

    public static void test(String path1){
        try
        {
            BufferedReader reader = new BufferedReader(
                    new FileReader(path1));

            List<String> list = reader.lines()
                    // trim前后空格(使用方法引用)
                    .map(String::trim).collect(Collectors.toList());
            list.forEach(x-> System.out.println(x));
        }
        catch (Exception e){

        }
    }

    public static void test2(String path1){
        try
        {
            System.out.println("+++++++++++++++ ");
            BufferedReader reader = new BufferedReader(
                    new FileReader(path1));

            List<String[]> collect = reader.lines()
                    // trim前后空格(使用方法引用)
                    .map(String::trim)
                    // 过滤掉空串
                    .filter(s -> !s.isEmpty())
                    // 把空格隔开的转为单词数组
                    .map(s -> s.split(" "))
                    .collect(Collectors.toList());

            collect.forEach(x->
                    {
                    Arrays.stream(x).forEach(y
                            -> {System.out.println(y);
                             }
                    );
                        System.out.println("------------");
                    }
            );
        }
        catch (Exception e){

        }
    }
}
