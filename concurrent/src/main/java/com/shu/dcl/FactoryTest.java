package com.shu.dcl;

/**
 * @description:
 * @author: shurunlong
 * @create: 2020-12-08 11:55
 */
public class FactoryTest {
    private static Factory.Dcl<Haha> client=Factory.of(Haha::new);

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    System.out.println(client.get());
                }
            }).start();
        }


    }
}
