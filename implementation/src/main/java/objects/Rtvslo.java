package objects;

public class Rtvslo {
    public String Author;
    public String PublishedTime;
    public String Title;
    public String SubTitle;
    public String Lead;
    public String Content;

    public Rtvslo(String Author, String PublishedTime, String title, String subTitle, String lead, String content) {
        this.Author = Author;
        this.PublishedTime = PublishedTime;
        this.Title = title;
        this.SubTitle = subTitle;
        this.Lead = lead;
        this.Content = content;
    }
}
