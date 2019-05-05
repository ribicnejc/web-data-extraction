package xpath;

public class XPathParser {
    public static void main(String[] args) throws Exception {
        // avto.net extraction
        AvtoExtractor avtoExtractor = new AvtoExtractor();
        System.out.println(avtoExtractor.extractData());

        // overstock.com extraction
        OverExtractor overExtractor = new OverExtractor();
        System.out.println(overExtractor.extractData());

        // rtvslo.si extraction
        RtvExtractor rtvExtractor = new RtvExtractor();
        System.out.println(rtvExtractor.extractData());
    }


}
