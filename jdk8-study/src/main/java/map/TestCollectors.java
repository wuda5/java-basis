package map;


import entity.Student;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * xuhaixing
 * 2018/7/20 21:44
 *
 * groupingBy用于分组-->默认得到的map 的value 一般都是集合list，且map的entry个数可能很少
 * toMap用于list转map, list有几个元素,对应的map 的entry 一般就有多少对!!(只是说key 相同时候)
 *
 * 需要注意list转set，set中的值不允许有重复，所以只有一个，
 * ，list转map，map的key不能重复，所以自己设置选用老的还是新的，还一点需要注意，list转map时key可以为null,value不可以为null,否则会报空指针异常
 **/
public class TestCollectors {

    /**
     * Collectors.groupingBy 分组1
     */
    @Test
    public void testGrouping() {
        List<String> items = Arrays.asList("apple", "banana", "apple", "orange", "banana");
        Collector<Object, ?, Map<Object, Long>> objectMapCollector = Collectors.groupingBy(Function.identity(), Collectors.counting());
        Map<Object, Long> collect = items.stream().collect(objectMapCollector);
        System.out.println(collect);
        //{orange=1, banana=2, apple=2}
    }

    /**
     * Collectors.groupingBy 分组2
     *
     * 我们分组后要统计map中各项value的某一项的总和，这里我们统计每个city中的城市人数
     */
    @Test
    public void testGrouping2() {
        List<Student> students = Arrays.asList(new Student("apple", "男", 10.0),
                new Student("banana", "男", 10.0),
                new Student("orange", "男", 20.0),
                new Student("pipe", "女", 40.0),
                new Student("pinck", "女", 80.0)
        );

        //根据sex分组
        Map<String, List<Student>> collect = students.stream().collect(Collectors.groupingBy(Student::getSex));
        System.out.println(collect);
        //{
        // 女=[Student{name='pipe', sex='女', money=40.0}, Student{name='pinck', sex='女', money=80.0}],
        // 男=[Student{name='apple', sex='男', money=10.0}, Student{name='banana', sex='男', money=10.0}, Student{name='orange', sex='男', money=20.0}]
        // }

        //根据sex分组，然后对money求和
        Map<String, Double> collect1 = students.stream().collect(Collectors.groupingBy(Student::getSex, Collectors.summingDouble(Student::getMoney)));
        System.out.println(collect1);
        //{女=120.0, 男=40.0} -- 汇总和
        Map<String, Long> collect2 = students.stream().collect(Collectors.groupingBy(Student::getSex, Collectors.counting()));
//        {女=2, 男=3}  -- 计数和
        System.out.println(collect2);

    }

    /**partitioningBy--> 分为boolean 两组
     * list、set
     */
    @Test
    public void testGrouping3() {
        List<Student> students = Arrays.asList(
                new Student("apple", "男", 10.0),
                new Student("banana", "男", 10.0),
                new Student("gg", "男", 710.0),
                new Student("orange", "男", 20.0),
                new Student("pipe", "女", 40.0),
                new Student("pinck", "女", 80.0)
        );
        Map<Boolean, Long> re = students.stream().collect(Collectors.partitioningBy(input -> input.getSex().equals("男"), Collectors.counting()));
        System.out.println(re);
        Map<Boolean, Optional<Student>> mm = students.stream().collect(Collectors.partitioningBy(input -> input.getSex().equals("男"), Collectors.maxBy(Comparator.comparing(Student::getMoney))));
        System.out.println("max:"+mm);

             


        Map<String, List<Student>> collect1 = students.stream().collect(Collectors.groupingBy(Student::getSex));
        System.out.println(collect1);
        //{
        // 女=[Student{name='pipe', sex='女', money=40.0}, Student{name='pinck', sex='女', money=80.0}],
        // 男=[Student{name='apple', sex='男', money=10.0}, Student{name='banana', sex='男', money=10.0}, Student{name='orange', sex='男', money=20.0}]
        // }

        Map<String, List<Double>> collect2 = students.stream().collect(Collectors.groupingBy(Student::getSex, Collectors.mapping(Student::getMoney, Collectors.toList())));
        System.out.println(collect2);
        //{女=[40.0, 80.0], 男=[10.0, 10.0, 20.0]}

        Map<String, Set<Double>> collect3 = students.stream().collect(Collectors.groupingBy(Student::getSex, Collectors.mapping(Student::getMoney, Collectors.toSet())));
        System.out.println(collect3);
        //{女=[40.0, 80.0], 男=[10.0, 20.0]}
    }
/**-----------------------------groupingby-------------------- tomap 分割线  ------------------------------------------------------------------*/
/**------------------------------------------------- tomap 分割线  ------------------------------------------------------------------*/
/**------------------------------------------------- tomap 分割线  ------------------------------------------------------------------*/
    /**
     * toMap 转成map
     */
    @Test
    public void tesToMap(){
        List<Student> students = Arrays.asList(new Student("apple", "男", 10.0),
                new Student("banana", "男", 10.0),
                new Student("orange", "男", 20.0),
                new Student("pipe", "女", 40.0),
                new Student("pinck", "女", 80.0)
        );
//        students.stream().

        Map<String, Double> collect = students.stream().collect(Collectors.toMap(Student::getName, Student::getMoney));
        System.out.println(collect);
        //{orange=20.0, banana=10.0, apple=10.0, pinck=80.0, pipe=40.0}

//        students.stream().collect(Collectors.toMap( Student::getName,Student::getMoney));
    }

    /**
     * toMap 转成map  key重复
     * 如果 不加 ,(oldValue, newValue)->newValue) 会 异常 java.lang.IllegalStateException: Duplicate key apple
     */
    @Test
    public void tesToMap2(){
        List<Student> students = Arrays.asList(new Student("apple", "男", 10.0),
                new Student("banana", "男", 10.0),
                new Student("orange", "男", 20.0),
                new Student("pipe", "女", 40.0),
                new Student("pinck", "女", 80.0),
                new Student("pinck222", "女", 80.0)
        );
        /*
        java.lang.IllegalStateException: Duplicate key apple
        at java.util.stream.Collectors.lambda$throwingMerger$0(Collectors.java:133)
        at java.util.HashMap.merge(HashMap.java:1254)
        at java.util.stream.Collectors.lambda$toMap$58(Collectors.java:1320)
         */
//        Map<Double,String> collect2 = students.stream().collect(Collectors.toMap(Student::getMoney, Student::getName));
//        System.out.println(collect2);

        Map<Double,String> collect2 = students.stream().collect(Collectors.toMap(Student::getMoney, Student::getName,(oldValue, newValue)->newValue));
        System.out.println(collect2);
        //{80.0=pinck, 40.0=pipe, 20.0=orange, 10.0=banana}  key重复用新值


    }

    /**
     * toMap 转成map 排序
     */
    @Test
    public void tesToMap3() {
        List<Student> students = Arrays.asList(new Student("apple", "男", 10.0),
                new Student("banana", "男", 10.0),
                new Student("orange", "男", 20.0),
                new Student("pipe", "女", 40.0),
                new Student("pinck", "女", 80.0)
        );

        LinkedHashMap<String, Double> collect = students.stream().sorted(Comparator.comparing(Student::getMoney).reversed())
                .collect(Collectors.toMap(Student::getName, Student::getMoney, (oldValue, newValue) -> newValue, LinkedHashMap::new));
        System.out.println(collect);
        //{pinck=80.0, pipe=40.0, orange=20.0, apple=10.0, banana=10.0}

        students.stream().collect(Collectors.toMap( Function.identity(),Function.identity()));
    }
}