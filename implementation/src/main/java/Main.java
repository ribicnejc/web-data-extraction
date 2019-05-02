import regex.RegEx;

public class Main {
    public static void main(String[] args) throws Exception{

        System.out.println("################## REGEX ################");
        System.out.println("############ Parsing overstock ###########");
        System.out.println(RegEx.parsePageOverstock(1));

        System.out.println("############ ################# ###########");

        System.out.println("##########################################");
    }

}
