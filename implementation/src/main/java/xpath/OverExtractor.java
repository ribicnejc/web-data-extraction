package xpath;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

public class OverExtractor {
    public String extractData() throws Exception {
        HtmlCleaner cleaner = new HtmlCleaner();
        //CleanerProperties props = cleaner.getProperties();

        //TagNode node = cleaner.clean(new File("input/overstock.com/jewelry01.html"), "Cp1252");
        TagNode node = cleaner.clean(new File("input/overstock.com/jewelry02.html"), "Cp1252");

        // Find entries in table
        Object[] table = node.evaluateXPath("//table[@border='0'][@cellpadding='2']/tbody/tr[@bgcolor]");
        JSONArray json = new JSONArray();

        for(Object entry : table) {
            handleEntry((TagNode)entry, json);
        }

        return json.toString();
    }

    private void handleEntry(TagNode entry, JSONArray json) throws Exception {
        Object[] tds = entry.evaluateXPath("/td");
        TagNode leftSide, rightSide;
        if(tds.length == 2) { // is valid entry
            TagNode description = (TagNode) tds[1];
            Object[] leftAndRightDesc = description.evaluateXPath("table/tbody/tr/td");
            leftSide = (TagNode) ((TagNode)leftAndRightDesc[0]).evaluateXPath("table/tbody")[0];
            rightSide = (TagNode) leftAndRightDesc[1];

            JSONObject jsonObject = new JSONObject();
            addTitle((TagNode) tds[1], jsonObject);
            handleLeftSide(leftSide, jsonObject);
            handleRightSide(rightSide, jsonObject);
            json.put(jsonObject);
        }
    }

    private void addTitle(TagNode node, JSONObject json) throws Exception {
        String title = ((StringBuilder)node.evaluateXPath("a/text()")[0]).toString();
        json.put("Title", title);
    }

    private void handleLeftSide(TagNode node, JSONObject json) throws Exception {
        Object[] trs = node.evaluateXPath("tr");
        for(int i = 0; i < 3; i++) {
            TagNode tr = (TagNode) trs[i];
            Object[] tds = tr.evaluateXPath("td");
            String right = ((StringBuilder)((TagNode)tds[1]).evaluateXPath("text()")[0]).toString();
            switch(i) {
                case 0:
                    json.put("ListPrice", right);
                    break;
                case 1:
                    json.put("Price", right);
                    break;
                case 2:
                    String[] parts = right.split(" \\(");
                    json.put("Saving", parts[0]);
                    json.put("SavingPercent", "(" + parts[1]);
            }
        }
    }

    private void handleRightSide(TagNode node, JSONObject json) throws Exception {
        String desc = ((StringBuilder)node.evaluateXPath("text()")[0]).toString();
        json.put("Content", desc);
    }
}
