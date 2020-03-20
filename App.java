import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class App {

  public static void main(String[] args) {
    if (args.length != 2 && args.length != 1) {
      System.out.println("Error Wrong parameters");
      System.out.println("java App [list] in order to see full shopping list");
      System.out.println("java App [add] [productName] to add a product");
      System.out.println("java App [delete] [productName] to delete a product");
      System.exit(-1);
    }

    ArrayList<String> productList = new ArrayList<>();
    try {
      BufferedReader br = new BufferedReader(new FileReader("shoplist.txt"));
      String line;
      while ((line = br.readLine()) != null) {
        productList.add(line);
      }
      br.close();
    } catch (FileNotFoundException ex) {
      System.out.println("There is no shoplist.txt\nCreate a shoplist.txt file to start creating your shopping list");
      System.exit(-1);
    } catch (IOException ex) {
      ex.printStackTrace();
    }

    if (args[0].equals("list")) {
      System.out.println(productList);
    } else if (args[0].equals("add")) {
      try {
        BufferedWriter bw = new BufferedWriter(new FileWriter("shoplist.txt"));
        String result = "";
        for (String s : productList) {
          result += s;
          result += "\n";
        }
        result += args[1];
        bw.write(result);
        bw.close();

      } catch (IOException e) {
        e.printStackTrace();
      }

    } else if (args[0].equals("delete")) {
      productList.remove(args[1]);
      try {
        BufferedWriter bw = new BufferedWriter(new FileWriter("shoplist.txt"));
        String result = "";
        for (String s : productList) {
          result += s;
          result += "\n";
        }
        bw.write(result);
        bw.close();

      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      System.out.println(args[0] + " is not a valid action");
    }


  }
}
