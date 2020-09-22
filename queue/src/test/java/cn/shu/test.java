package cn.shu;

import java.util.Arrays;
import java.util.stream.Collectors;

public class test {

    public static void main(String[] args) {
        String[] str={"1","2","3"};
        String s = Arrays.toString(str);

        String s1 = Arrays.stream(str).collect(Collectors.joining()).toString();

        System.out.println(s);
        System.out.println(s1);
    }
}
