// Projeyi tamamen anlamak icin paketlere ozel yazilan dokumantasyonlara bakiniz
// eger dokumantasyon yoksa YAZ



import TxtHandler.TxtDataBase;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        TxtDataBase db = new TxtDataBase();
        if(db.setPath("database.txt")){
            System.out.println("Baglandi");
        }
        else{return;} // anlasilmiyorsa TxtDataBase dokumantasyonunu oku
    
        ArrayList<String> datalar = db.loadData();
        db.writeData(datalar);
    }


}
