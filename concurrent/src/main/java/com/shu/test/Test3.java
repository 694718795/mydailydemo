package com.shu.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description:
 * @author: shurunlong
 * @create: 2020-08-10 17:21
 */
public class Test3 {
    public static void main(String[] args) {
        Student student1 = new Student(1,5, "yangtao", "1");
        Student student2 = new Student(2,15, "yangtao", "1");
        Student student3 = new Student(3,25, "yangtao", "2");
        Student student4 = new Student(4,24, "yangtao", "2");
        List<Student> studentList =new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);

        //单一分组条件,根据code
        Map<String, List<Student>> singleMap = studentList.stream().collect(Collectors.groupingBy(Student::getCode));
        //{1=[Student{age=200, username='yangtao', code='1'}, Student{age=220, username='yangtao', code='1'}],
        // 2=[Student{age=500, username='yangtao', code='2'}, Student{age=504, username='yangtao', code='2'}]}
        System.out.println(singleMap);
        System.out.println("===================");
        //组合分组条件

        Map<String, List<Student>> complexMap = studentList.stream().collect(Collectors.groupingBy(e -> fetchGroupKey(e)));
        List<Student> studentList1 = complexMap.get("yangtao+1");
        List<Student> studentList2 = complexMap.get("yangtao+2");
        System.out.println(complexMap);
//       {yangtao+2=[Student{age=500, username='yangtao', code='2'}, Student{age=504, username='yangtao', code='2'}], yangtao+1=[Student{age=200, username='yangtao', code='1'}, Student{age=220, username='yangtao', code='1'}]}

        System.out.println(studentList1);
        System.out.println(studentList2);
        //[Student{age=200, username='yangtao', code='1'}, Student{age=220, username='yangtao', code='1'}]
        //[Student{age=500, username='yangtao', code='2'}, Student{age=504, username='yangtao', code='2'}]


        Map<String, List<Student>> listMap = studentList.stream().collect(Collectors.groupingBy(e -> gruopmethod(e)));
        Map<String, List<Student>> listMap1 = studentList.stream().collect(Collectors.groupingBy(student -> {
            if (student.getAge()<=10){
                return "low";
            } else if (10<student.getAge()&&student.getAge()<20){
                return "mid";
            } else {
                return "high";
            }
        }));
        System.out.println(listMap);
        //{high=[Student{id=3, age=25, username='yangtao', code='2'}, Student{id=4, age=24, username='yangtao', code='2'}], low=[Student{id=1, age=5, username='yangtao', code='1'}], mid=[Student{id=2, age=15, username='yangtao', code='1'}]}



    }
    private static String fetchGroupKey(Student student){
        return student.getUsername() +"+"+ student.getCode();

    }


    private static String gruopmethod(Student student){
        if (student.getAge()<=10){
            return "low";
        } else if (10<student.getAge()&&student.getAge()<20){
            return "mid";
        } else {
            return "high";
        }
    }
}
