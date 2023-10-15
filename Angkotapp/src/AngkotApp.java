import java.util.Scanner;
import java.util.Random;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;

public class AngkotApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selamat datang di aplikasi angkot!");
        
        // Menghasilkan kode pembayaran (barcode)
        String barcodeData = generateBarcodeData();
        String barcodeFilePath = "barcode.png";
        generateBarcode(barcodeData, barcodeFilePath);
        System.out.println("Silakan tunjukkan barcode di ponsel Anda kepada kondektur.");
        
        // Menggambarkan barcode
        System.out.println("Barcode telah disimpan di file: " + barcodeFilePath);
        
        // Pemindaian barcode (dalam keadaan nyata ini akan dilakukan oleh perangkat pemindai barcode)
        System.out.print("Pindai barcode (masukkan kode manual untuk simulasi): ");
        String scannedBarcode = scanner.next();
        
        // Memeriksa apakah barcode yang dipindai cocok dengan barcode pembayaran
        if (scannedBarcode.equals(barcodeData)) {
            System.out.println("Pembayaran berhasil! Selamat menikmati perjalanan Anda.");
        } else {
            System.out.println("Pembayaran gagal. Silakan coba lagi.");
        }
    }

    // Fungsi untuk menghasilkan data barcode (dalam contoh ini, data barcode adalah nomor acak)
    private static String generateBarcodeData() {
        Random random = new Random();
        int barcodeNumber = random.nextInt(1000000);
        return String.format("%06d", barcodeNumber);
    }

    // Fungsi untuk menghasilkan dan menyimpan gambar barcode
    private static void generateBarcode(String data, String filePath) {
        int width = 300;
        int height = 300;
        String format = "png";

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, width, height);
            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
            ImageIO.write(image, format, new File(filePath));
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
