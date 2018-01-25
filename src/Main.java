import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String[] message = {
                "problem z klasą",
                "problem wymaganą metodą",
                "problem z nawiasami",
                "ok"
        };
        String path = "Projekty/";
        try {
            File directory = new File(path);
            PrintWriter writer = new PrintWriter(path+"__raport.txt", "UTF-8");
            for (File file: directory.listFiles()) {
                Pattern pattern = Pattern.compile("s(\\d+)_projekt_([IVX]+).java");
                Matcher matcher = pattern.matcher(file.getName());
                if (matcher.matches()) {
                    LinkedList list = new LinkedList();
                    int okragle = 0, kwadratowe = 0, klamrowe = 0;
                    boolean[] raport = {false, false, false};
                    String[] warunek = {
                            "class s" + matcher.group(1) + "_projekt_" + matcher.group(2),
                            "public static void main(String[] args)",
                            "public static int[][][] mySort(int[] dane)"
                    };
                    BufferedReader reader = Files.newBufferedReader(Paths.get(file.getAbsolutePath()));
                    int r;
                    while ((r = reader.read()) != -1) {
                        switch (r) {
                            case '(':
                                okragle++;
                                break;
                            case ')':
                                okragle--;
                                break;
                            case '[':
                                kwadratowe++;
                                break;
                            case ']':
                                kwadratowe--;
                                break;
                            case '{':
                                klamrowe++;
                                break;
                            case '}':
                                klamrowe--;
                                break;
                        }
                        list.insert((char) r);
                    }

                    if (!raport[0]) {
                        if (list.hasText(warunek[0]) && list.hasText(warunek[1]))
                            raport[0] = true;
                    }
                    if (!raport[1]) {
                        if (list.hasText(warunek[2]))
                            raport[1] = true;
                    }

                    if (okragle == 0 && kwadratowe == 0 && klamrowe == 0) {
                        raport[2] = true;
                    }

                    if (!raport[0])
                        writer.write(matcher.group(1) + " - " + message[0] + "\n");
                    if (!raport[1])
                        writer.write(matcher.group(1) + " - " + message[1] + "\n");
                    if (!raport[2])
                        writer.write(matcher.group(1) + " - " + message[2] + "\n");
                    if (raport[0] && raport[1] && raport[2])
                        writer.write(matcher.group(1) + " - " + message[3] + "\n");
                }
            }
            writer.close();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}
