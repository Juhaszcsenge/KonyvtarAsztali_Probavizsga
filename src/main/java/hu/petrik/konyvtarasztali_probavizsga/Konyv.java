package hu.petrik.konyvtarasztali_probavizsga;

public class Konyv {

    private  int id;
    private String title;
    private String author;
    private int publish_year;
    private int page_count;

    public Konyv(int id, String title, String author, int publish_year, int page_count) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publish_year = publish_year;
        this.page_count = page_count;

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublish_year() {
        return publish_year;
    }

    public int getPage_count() {
        return page_count;
    }
}
