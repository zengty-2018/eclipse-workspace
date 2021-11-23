import imagereader.IImageIO;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;  
import java.awt.image.*;

/**
 * Implement the IImage IO, give the myRead and myWrite method 
 * @author z18342007
 *
 */
public class ImplementImageIO implements IImageIO {
    private static int FILE_HEADER = 14;
    private static int INFO_HEADER = 40;
    
    /**
     * Throught the given file name to open the picture in a byte array,
     * and then according to the regulation get the width, height and
     * bit count of the picture, finally arrange the red, green and 
     * blue color in the order to read the picture.
     */
    public Image myRead(String fileName) throws IOException {
    	File file = new File(fileName);
        FileInputStream fileInputStream = new FileInputStream(file);
        
        byte bitmapFileHeader[] = new byte[14];
        byte bitmapInfoHeader[] = new byte[40];
        
        fileInputStream.read(bitmapFileHeader, 0, FILE_HEADER);
        fileInputStream.read(bitmapInfoHeader, 0, INFO_HEADER);
        
        int width = byteToInt(bitmapInfoHeader, 4, 4);
        int height = byteToInt(bitmapInfoHeader, 8, 4);
        int bitCount = byteToInt(bitmapInfoHeader, 14, 2);
        int widthBytes = ((width * bitCount + 31) / 32) * 4;
        
        byte pixByte[] = new byte[widthBytes*height];
        int pix[] = new int[width*height];
        
        fileInputStream.read(pixByte);
        
        int index = 0;
        for(int i = height - 1; i >= 0; i--){
            for(int j = 0; j < width; j++){
                int begin = i * widthBytes + j * (bitCount / 8);
                pix[index++] = byteToRGB(pixByte, begin, bitCount / 8);
            }
        }
        
        Image img = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(width, height, pix, 0, width));
        return img;
    }
    
    /**
     * Use the method prompt in the https://stackoverflow.com/questions/13605248/java-converting-image-to-bufferedimage
     */
    public Image myWrite(Image image, String fileName) throws IOException {
        File file = new File(fileName + ".bmp");
        BufferedImage bufferedImage = new BufferedImage(
                image.getWidth((ImageObserver)null), image.getHeight((ImageObserver)null), BufferedImage.TYPE_INT_RGB);
        Graphics2D graph = bufferedImage.createGraphics();
        graph.drawImage(image, 0, 0, null);
        graph.dispose();
        ImageIO.write(bufferedImage, "bmp", file);
        
        return image;
    }

    /**
     * Transform the byte array to the int.
     */
    public static int byteToInt(byte[] bytes, int start, int len) {
		int result = 0;
        result = bytes[start + len - 1] & 0xff;
        for(int i = 2; i <= len; i++){
            result = result << 8 | bytes[start + len - i] & 0xff;
        }
		return result;
    }
    
    /**
     * Transform the byte array to the RGB.
     */
    private static int byteToRGB(byte[] bytes, int start, int length) {
        int result;
        int R, G, B;
        R = bytes[start + 2] & 0xff;
        G = bytes[start + 1] & 0xff;
        B = bytes[start] & 0xff;
        result = 0xff << 24 | R << 16 | G << 8 | B;
		return result;
    }
}