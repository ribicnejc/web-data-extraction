package regex;

import java.io.Serializable;

public class Overstock implements Serializable {
    public String ListPrice;
    public String Price;
    public String Saving;
    public String SavingPercent;
    public String Title;
    public String Content;

    public Overstock(String ListPrice, String Price, String Saving, String SavingPercent, String Title, String Content) {
        this.Content = Content;
        this.ListPrice = ListPrice;
        this.Title = Title;
        this.SavingPercent = SavingPercent;
        this.Saving = Saving;
        this.Price = Price;
    }
}
