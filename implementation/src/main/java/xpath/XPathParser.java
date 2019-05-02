package xpath;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.json.JSONObject;

import java.io.File;


public class XPathParser {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello");
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));


        HtmlCleaner cleaner = new HtmlCleaner();
        CleanerProperties props = cleaner.getProperties();

        TagNode node = cleaner.clean(new File("input/avto.net/Toyota GT 86.html"));
        //TagNode node = cleaner.clean(new File("input/avto.net/Mazda RX-7.html"));

        // Title
        Object[] myNodes = node.evaluateXPath("//div[@class='OglasDataTitle']");

        // Price
        myNodes = node.evaluateXPath("//p[@class='OglasDataCenaTOP']");

        // Data
        myNodes = node.evaluateXPath("//div[@class='OglasWrapper RoundedBottom MarginBTM']");

        // Seller phone
        myNodes = node.evaluateXPath("//div[@class='OglasMenuBox Bold OglasMenuBoxPhone']");


        JSONObject json = new JSONObject();
        json.append("lala", 3);

        System.out.println(json.toString());

    }
}
