import regex.RegEx;

public class Main {
    public static void main(String[] args) throws Exception{

        System.out.println("################## REGEX ################");
        System.out.println("############ Parsing overstock ###########");

        System.out.println(RegEx.parsePageOverstock(0));
        System.out.println(RegEx.parsePageOverstock(1));

        System.out.println("############ ################# ###########");
        System.out.println("############ Parsing rtvslo ###########");

        System.out.println(RegEx.parsePageRtvSlo(0));
        System.out.println(RegEx.parsePageRtvSlo(1));

        System.out.println("############ ################# ###########");
        System.out.println("############ Parsing avtonet ###########");

        System.out.println(RegEx.parsePageAvtoNet(0));
        System.out.println(RegEx.parsePageAvtoNet(1));
//        https://regex101.com/

        System.out.println("##########################################");
    }

}
