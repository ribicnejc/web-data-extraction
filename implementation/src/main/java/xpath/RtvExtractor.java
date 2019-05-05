package xpath;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

public class RtvExtractor {
    public String extractData() throws Exception {
        HtmlCleaner cleaner = new HtmlCleaner();
        //CleanerProperties props = cleaner.getProperties();

        //TagNode node = cleaner.clean(new File("input/rtvslo.si/Audi A6 50 TDI quattro_ nemir v premijskem razredu - RTVSLO.si.html"), "UTF8");
        TagNode node = cleaner.clean(new File("input/rtvslo.si/Volvo XC 40 D4 AWD momentum_ suvereno med najboljše v razredu - RTVSLO.si.html"), "UTF8");

        JSONObject json = new JSONObject();

        appendAuthorTimestamp(node, json);
        appendTitle(node, json);
        appendSubTitle(node, json);
        appendLead(node, json);
        appendContent(node, json);

        return json.toString();
    }

    private void appendAuthorTimestamp(TagNode node, JSONObject json) throws Exception {
        String timestamp = ((StringBuilder)node.evaluateXPath("//div[@class='author-timestamp']/text()")[0]).toString().trim();
        String[] parts = timestamp.split("\\| ");
        json.put("Author", parts[0]);
        json.put("PublishedTime", parts[1]);
    }

    private void appendTitle(TagNode node, JSONObject json) throws Exception {
        String title = ((StringBuilder)node.evaluateXPath("//header[@class='article-header']/h1/text()")[0]).toString().trim();
        json.put("Title", title);
    }

    private void appendSubTitle(TagNode node, JSONObject json) throws Exception {
        String subtitle = ((StringBuilder)node.evaluateXPath("//div[@class='subtitle']/text()")[0]).toString().trim();
        json.put("SubTitle", subtitle);
    }

    private void appendLead(TagNode node, JSONObject json) throws Exception {
        String lead = ((StringBuilder)node.evaluateXPath("//p[@class='lead']/text()")[0]).toString().trim();
        json.put("Lead", lead);
    }

    private void appendContent(TagNode node, JSONObject json) throws Exception {
        String content = ((StringBuilder)node.evaluateXPath("//div[@class='article-header-media']/text()")[0]).toString().trim();
        Object[] bodies = node.evaluateXPath("//article[@class='article']/p/text()");
        for(Object body : bodies) {
            content = content + "\n" + body.toString();
        }
        json.put("Content", content);
    }
}
