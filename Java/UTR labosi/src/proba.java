import java.io.*;

public class proba {
    public static void main(String args[]) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        while (s != null) {
            int x = Integer.parseInt(s);
            System.out.println(x*x);
            s = reader.readLine();
        }
    }
}
