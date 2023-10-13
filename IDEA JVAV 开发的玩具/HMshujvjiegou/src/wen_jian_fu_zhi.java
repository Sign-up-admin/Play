import java.io.*;
public class wen_jian_fu_zhi {

    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\22届计应工程1班覃朝军\\Desktop\\PT精选.txt");
            BufferedInputStream BufferedInputStream = new BufferedInputStream(fis);
            FileOutputStream fos = new FileOutputStream("C:\\Users\\22届计应工程1班覃朝军\\Music\\PT精选.txt");
            BufferedOutputStream BufferedOutputStream = new BufferedOutputStream(fos);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                BufferedOutputStream.write(buffer, 0, length);
            }
            BufferedOutputStream.close();


            fos.close();
            BufferedInputStream.close();
            fis.close();

            System.out.println("File copied successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }


