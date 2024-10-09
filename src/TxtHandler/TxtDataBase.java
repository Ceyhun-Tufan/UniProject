package TxtHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import TxtHandler.Serializers;


// Txt dosya islemlerinde yazma ve okumayi hizlandirmak icin class 

// checkDb() - argumanlar : Yok - return tipi : Boolean
//
// db nin varligini kontrol eder. Duruma gore TF return eder

// loadData() - argumanlar : Yok - return tipi : ArrayList<String>
//
// txt dosyasindan tum verileri bir ArrayList'ine alir.
// listedeki tum elemanlari daha oncesinde atanmis #bakiniz setSerializerClass()# 
// class yapisina gore serializer'dan gecirerek ayni class yapisindan olusan bir liste dondurur

// writeData() - argumanlar : ArrayList<String> ; return tipi : Void
// 
// parametre olarak belirlenmis class yapisindan olusan bir liste alir.
// bu listedeki elemanlari mapten gecirerek, map islemini daha farkli yapabilirsiniz, 
// degerleri tek satirlik bir stringe cevirir. daha sonrasinda verileri 
// txt dosyaniza yazar 

// setPath() - argumanlar : String ; return tipi : Void
//
// txt dosyanizin yolunu belirler 



public class TxtDataBase {

    private String path;


    public boolean setPath(String newpath) {
        path = newpath;
        return checkDb();
    }

    public ArrayList<String> loadData() {

        String line;
        ArrayList<String> data = new ArrayList<String>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

            System.out.println("Veriler yukleniyor");
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
            reader.close();

        } catch (IOException e) {
            System.err.println(e);
        }

        return data;

    }

    public void writeData(ArrayList<String> data) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {

            for (String line : data) {
                line += "\n";
                writer.write(line);
            }

            writer.close();

        } catch (IOException e) {
            System.err.println(e);
        }

    }

    private boolean checkDb() {

        File f = new File(path);

        if (f.exists()) {

            return true;

        } else {
            try {
                f.createNewFile();
                return true;
            } catch (Exception e) {
                System.err.println(e);
                return false;
            }
        }

    }

}