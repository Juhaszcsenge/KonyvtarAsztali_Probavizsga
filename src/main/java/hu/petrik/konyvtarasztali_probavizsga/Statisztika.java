package hu.petrik.konyvtarasztali_probavizsga;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Statisztika {

    private List<Konyv> konyvek;

    private KonyvDataBase db;

    public Statisztika(){

        listFill();
        System.out.printf("500 oldalnál hosszabb könyvek száma: %d \n", book500());
        System.out.printf("%s 1950-nél régebbi könyv\n", book1950() ? "van" : "nincs");
        Konyv leghosszabb= getLongestBook();
        System.out.printf("A leghosszabb könyv:\n" +
                        "\tSzerző: %s\n" +
                        "\tCím: %s\n" +
                        "\tKiadás éve: %d\n" +
                        "\tOldalszám: %d \n",
                leghosszabb.getAuthor(),
                leghosszabb.getTitle(),
                leghosszabb.getPublish_year(),
                leghosszabb.getPage_count());

        System.out.printf("A legtöbb könyvvel rendelkező szerző: %s\n", getAuthorsMostBooks());
        Scanner sc = new Scanner(System.in);
        System.out.printf("Adjon meg egy könyv címet: ");
        String userInput = sc.nextLine();
        System.out.printf("Az megadott könyvszerzője %s",getKonyvSearch(userInput));


    }

    private void listFill() {

        try {
            db=new KonyvDataBase();
            konyvek= db.getKonyvList();
        }catch (SQLException e){
            System.out.printf("HIba történt: \n Hibakód: %s", e);
            System.exit(0);
        }
    }
    private int book500(){

        int darab=konyvek.stream().filter(k-> k.getPage_count() > 500).collect(Collectors.toList()).size();
        return darab;
    }

    private  boolean book1950(){
        for(Konyv konyv : konyvek ) {
            if (konyv.getPublish_year() < 1950) {return true;}
        }
        return false;


    }
    private Konyv getLongestBook(){

        Konyv longest=konyvek.get(0);
        for (Konyv konyv : konyvek) {
            if (konyv.getPage_count() >= longest.getPage_count()) {
                longest = konyv;
            }
        }


       return longest;


    }

    private String getAuthorsMostBooks() {
        HashMap<String, Integer> authorAndBooks= new HashMap<>();
        for (Konyv konyv : konyvek) {
            if (authorAndBooks.containsKey(konyv.getAuthor())) {
                authorAndBooks.put(konyv.getAuthor(), authorAndBooks.get(konyv.getAuthor()) +1);
            } else {
                authorAndBooks.put(konyv.getAuthor(), 1);
            }
        }
        String szerzo = "";
        int mennyiseg = 0;
        for (Map.Entry<String, Integer > entry : authorAndBooks.entrySet()) {
            if (entry.getValue() > mennyiseg) {
                szerzo = entry.getKey();
                mennyiseg = entry.getValue();
            }
        }


        return szerzo;


    }

    private String getKonyvSearch(String konyvcim) {
        for (Konyv konyv : konyvek) {
            if (konyv.getTitle().equals(konyvcim)) {
                return konyv.getAuthor();
            }
        }

        return "Nem található ilyen könyv";


    }
}
