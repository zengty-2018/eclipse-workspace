import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.RGBImageFilter;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;  
import java.awt.Graphics2D;
import imagereader.IImageProcessor;

/**
 * This class implement the IImageProcessor interface and give 
 * four methods to get the red, green, blue, gray channel images
 * with Toolkit.
 * @author z18342007
 *
 */
public class ImplementImageProcessor implements IImageProcessor{
	/**
	 * Get the red Channel with ColorFilter red.
	 */
	public Image showChanelR(Image srcImg) {
        ColorFilter redFilter = new ColorFilter("red");
        return Toolkit.getDefaultToolkit().createImage(
                new FilteredImageSource(srcImg.getSource(), redFilter));  
    }
	
	/**
	 * Get the green Channel with ColorFilter green.
	 */
    public Image showChanelG(Image srcImg) {
        ColorFilter greenFilter = new ColorFilter("green");
        return Toolkit.getDefaultToolkit().createImage(
                new FilteredImageSource(srcImg.getSource(), greenFilter));  
    }
	
    /**
	 * Get the blue Channel with ColorFilter blue.
	 */
    public Image showChanelB(Image srcImg) {
        ColorFilter blueFilter = new ColorFilter("blue");
        return Toolkit.getDefaultToolkit().createImage(
                new FilteredImageSource(srcImg.getSource(), blueFilter));  
    }

    /**
	 * Get the gray Channel with ColorFilter gray.
	 */
    public Image showGray(Image srcImg) {
    	ColorFilter grayFilter = new ColorFilter("gray");
        return Toolkit.getDefaultToolkit().createImage(
                new FilteredImageSource(srcImg.getSource(), grayFilter));      
    }
    
    /**
     * Transform the image to Buffer image, used in the test to compare two image.
     * @param img
     * @return
     */
    public static BufferedImage toBufferedImage(Image img){
        if (img instanceof BufferedImage){
            return (BufferedImage) img;
        }

        BufferedImage bufferImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_3BYTE_BGR);

        Graphics2D bGr = bufferImage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
    
        return bufferImage;
    }
}

/**
 * Implement the RGBImageFilter to get a colorFilter to filter the red, 
 * green, blue, gray color as we need.
 * @author z18342007
 *
 */
class ColorFilter extends RGBImageFilter{
    private String color;
    
    /**
     * Constructor of the ColorFilter.
     * @param color: indicate the color that we need.
     */
    public ColorFilter(String color) {
        this.color = color;
        canFilterIndexColorModel = true;
    }
    
    /**
     * Overwrite the method, when we create it with parameter "red", this method will
     * only get the red color of the image, so as "green" and "blue".
     * When the parameter is "gray", this method will use the formula to get gray with
     * red, green and blue.
     */
    public int filterRGB(int x, int y, int rgb) {
    	int ret = 0;
    	
    	switch(color) {
    	case "red":
    		ret = rgb & 0xffff0000;
    		break;
    	case "green":
    		ret = rgb & 0xff00ff00;
    		break;
    	case "blue":
    		ret = rgb & 0xff0000ff;
    		break;
    	case "gray":
    		int gray = (int)( ((rgb & 0x00ff0000) >> 16) * 0.299 + 
        			((rgb & 0x0000ff00) >> 8 ) * 0.587 + 
        			((rgb & 0x000000ff)) * 0.114 );  
    		ret = (rgb & 0xff000000) + (gray << 16) + (gray << 8) + gray;
    	}
    	
    	return ret;
    }
}