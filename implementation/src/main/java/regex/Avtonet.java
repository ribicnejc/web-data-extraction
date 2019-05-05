package regex;

import java.util.ArrayList;
import java.util.HashMap;

public class Avtonet {
    public String title;
    public String price;
    public ArrayList<String> contacts;
    public HashMap<String, String> data;

    public Avtonet(String title, String price, ArrayList<String> contacts, HashMap<String, String> data) {
        this.title = title;
        this.price = price;
        this.contacts = contacts;
        this.data = data;
    }
}
