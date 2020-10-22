package mypackage2;

/**
 * @description:
 * @author: shurunlong
 * @create: 2020-10-19 16:48
 */
public class Test {
    public static void main(String[] args) {
        WebServiceDemoService_Service webServiceDemoService_service = new WebServiceDemoService_Service();
        WebServiceDemoService wsd = webServiceDemoService_service.getWebServiceDemoServiceImplPort();
        String result = wsd.hello("你好");

        System.out.println(result);


    }
}
