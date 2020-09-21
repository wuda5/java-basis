import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description :
 * @Author: wuwangqiang
 * @Version: 2020/4/18 0018 14:13
 *
 * summaryStatistics
 */
public class LongSummaryStatisticsTest {
    public static void main(String[] args) {
        String path = "classes/production/jdk8study/danci.txt";

        // 使用try-resource 关闭资源
        // 使用try-resource 关闭资源
        try (BufferedReader reader = new BufferedReader(
                new FileReader(path)))
        {

            Map<String, Long> counts = reader.lines()
                    // trim前后空格(使用方法引用)
                    .map(String::trim)
                    // 过滤掉空串
                    .filter(s -> !s.isEmpty())
                    // 把空格隔开的转为数组
                    .map(s -> s.split(" "))
                    // 数组转成流
                    .map(array -> Stream.of(array))
                    // 拉平
                    .flatMap(stream -> stream)
                    // 分组
                    .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

            System.out.println("--》总共有多少个【不同】单词:" + counts.size());
            System.out.println("--》每个单词出现次数:" + counts);

            // 统计信息
            LongSummaryStatistics summaryStatistics = counts.entrySet().stream()
                    // 得到次数
                    .mapToLong(entry -> entry.getValue())
                    // 统计
                    .summaryStatistics();

            System.out.println("统计信息:" + summaryStatistics);
            System.out.println("共有单词个数--sum:"+summaryStatistics.getSum());
            System.out.println("共有【不同的】单词个数--count:"+summaryStatistics.getCount());
            System.out.println("出现次数最【多】的单词个数--count:"+summaryStatistics.getMax());
            System.out.println("出现次数【最少】的单词个数--count:"+summaryStatistics.getMin());
        }
        catch (Exception e){}
    }
}
