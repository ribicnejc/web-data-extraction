import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import regex.RegEx;
import sun.plugin.dom.html.HTMLObjectElement;
import utils.HTMLHelper;

public class Main {
    public static void main(String[] args) throws Exception{
        System.out.println("############ Parsing overstock ###########");
        System.out.println(RegEx.parsePageOverstock(0));
//        https://stackoverflow.com/questions/6560672/java-regex-to-extract-text-between-tags
    }

}
