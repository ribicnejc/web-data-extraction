package xpath;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AvtoExtractor {
    public String extractData() throws Exception {
        HtmlCleaner cleaner = new HtmlCleaner();
        //CleanerProperties props = cleaner.getProperties();

        TagNode node = cleaner.clean(new File("input/avto.net/Toyota GT 86.html"), "Cp1252");
        //TagNode node = cleaner.clean(new File("input/avto.net/Mazda RX-7.html"), "Cp1252");
        JSONObject json = new JSONObject();

        appendTitle(node, json);
        appendPrice(node, json);
        appendPhones(node, json);
        appendData(node, json);

        return json.toString();
    }

    private void appendTitle(TagNode page, JSONObject json) {
        String title;
        try {
            TagNode titleNode = (TagNode) page.evaluateXPath("//div[@class='OglasDataTitle']/h1")[0];
            title = ((StringBuilder) titleNode.evaluateXPath("text()")[0]).toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        title = title.replace("&nbsp;"," ").trim();
        json.put("title", title);
    }

    private void appendPrice(TagNode page, JSONObject json) {
        String price;
        try {
            TagNode titleNode = (TagNode) page.evaluateXPath("//p[@class='OglasDataCenaTOP']")[0];
            price = ((StringBuilder) titleNode.evaluateXPath("text()")[0]).toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        price = price.replace("&nbsp;"," ").trim();
        json.put("price", price);
    }

    private void appendPhones(TagNode page, JSONObject json) {
        ArrayList<String> phones = new ArrayList<>();
        try {
            Object[] phoneObjects = page.evaluateXPath("//div[@class='OglasMenuBox Bold OglasMenuBoxPhone']");
            for(Object phone : phoneObjects) {
                String number = ((StringBuilder) ((TagNode)phone).evaluateXPath("text()")[0]).toString();
                phones.add(number);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        JSONArray contacts = new JSONArray();

        for(String contact : phones) {
            String trimmed = contact.replace("&nbsp;"," ").trim();
            contacts.put(trimmed);
        }

        json.put("contacts", contacts);
    }

    private void appendData(TagNode page, JSONObject json) {
        HashMap<String, String> data = new HashMap<>();
        HashMap<String, ArrayList<String>> data2 = new HashMap<>();
        String data3;
        try {
            Object[] dataObjects = page.evaluateXPath("//div[@class='OglasData']");
            for(Object dataObject : dataObjects) {
                TagNode tn = (TagNode) dataObject;
                Object[] leftArray = tn.evaluateXPath("div[@class='OglasDataLeft']");
                if(leftArray.length > 0) {
                    String normalLeftString = ((StringBuilder) tn.evaluateXPath("div[@class='OglasDataLeft']/text()")[0]).toString();
                    String normalRightString = ((StringBuilder) tn.evaluateXPath("div[@class='OglasDataRight']/text()")[0]).toString();
                    normalLeftString = normalLeftString.replace("&nbsp;"," ").trim();
                    normalRightString = normalRightString.replace("&nbsp;"," ").replace("\n", "").trim();
                    data.put(normalLeftString, normalRightString);
                } else {
                    String leftString = ((StringBuilder) tn.evaluateXPath("div[@class='OglasEQLeft']/text()")[0]).toString();
                    Object[] rights = tn.evaluateXPath("div[@class='OglasEQRightWrapper']/div[@class='OglasEQRight']");
                    ArrayList<String> rightStrings = new ArrayList<>();
                    for(Object rightObject : rights) {
                        String rightString = ((StringBuilder) ((TagNode)rightObject).evaluateXPath("text()")[0]).toString();
                        rightStrings.add(rightString);
                    }
                    data2.put(leftString, rightStrings);
                }
            }

            data3 = page.evaluateXPath("//div[@class='OglasEQtext']/text()")[0].toString().trim();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }


        JSONObject jsonData = new JSONObject();
        for(Map.Entry<String, String> entry : data.entrySet()) {
            jsonData.put(entry.getKey(), entry.getValue());
        }

        for(Map.Entry<String, ArrayList<String>> entry : data2.entrySet()) {
            ArrayList<String> values = entry.getValue();
            JSONArray arr = new JSONArray();
            for(String val : values) arr.put(val);
            jsonData.put(entry.getKey(), values);
        }

        jsonData.put("description", data3);

        json.put("data", jsonData);
    }
}
