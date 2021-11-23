import org.junit.*;
import static org.junit.Assert.*;
import java.awt.Image;
import java.io.IOException;
import java.awt.image.BufferedImage;

/**
 * The JUnit Test class to identify the width, height and 
 * every pixel are the same.
 * @author z18342007
 *
 */
public class ImageProcessorTest {
    private static String filePath = "./bmptest/";
    private static String goalPath = "./bmptest/goal/";
    private Image img1, img2;
    private Image img1Red, img1Blue, img1Green, img1Gray;
    private Image img2Red, img2Blue, img2Green, img2Gray;
    private Image goalImg1Red, goldImg1Blue, goalImg1Green, goalImg1Gray;
    private Image goalImg2Red, goalImg2Blue, goalImg2Green, goalImg2Gray;

    /**
     * Use the function implement before to get the RGB, Gray pictures.
     * And get the goal to prepare for the compare.
     */
    @Before
    public void init() throws IOException{
        ImplementImageIO imgIO = new ImplementImageIO();
        ImplementImageProcessor imgPro = new ImplementImageProcessor();
        
        img1 = imgIO.myRead(filePath + "1.bmp");
        img2 = imgIO.myRead(filePath + "2.bmp");

        img1Red = imgPro.showChanelR(img1);
        img1Green = imgPro.showChanelG(img1);
        img1Blue = imgPro.showChanelB(img1);
        img1Gray = imgPro.showGray(img1);

        goalImg1Red = imgIO.myRead(goalPath + "1_red_goal.bmp");
        goalImg1Green = imgIO.myRead(goalPath + "1_green_goal.bmp");
        goldImg1Blue = imgIO.myRead(goalPath + "1_blue_goal.bmp");
        goalImg1Gray = imgIO.myRead(goalPath + "1_gray_goal.bmp");

        img2Red = imgPro.showChanelR(img2);
        img2Green = imgPro.showChanelG(img2);
        img2Blue = imgPro.showChanelB(img2);
        img2Gray = imgPro.showGray(img2);

        goalImg2Red = imgIO.myRead(goalPath + "2_red_goal.bmp");
        goalImg2Green = imgIO.myRead(goalPath + "2_green_goal.bmp");
        goalImg2Blue = imgIO.myRead(goalPath + "2_blue_goal.bmp");
        goalImg2Gray = imgIO.myRead(goalPath + "2_gray_goal.bmp");
    }

    /**
     * Compare whether 2 images is equal in width, height and pixels.
     * @param img1
     * @param img2
     * @param name
     */
    private void compare(Image img1, Image img2, String name){
        BufferedImage bufferImg1 = ImplementImageProcessor.toBufferedImage(img1);
        BufferedImage bufferImg2 = ImplementImageProcessor.toBufferedImage(img2);

        int widthA = bufferImg1.getWidth();
        int heightA = bufferImg1.getHeight();
        int widthB = bufferImg2.getWidth();
        int heightB = bufferImg2.getHeight();
        
        assertEquals(widthA, widthB);
        System.out.println(name + ": width is equal.");
        assertEquals(heightA, heightB);
        System.out.println(name + ": height is equal.");

        int flag = 0;
        for(int i = 0; i < heightA; i++){
            for(int j = 0; j < widthA; j++){
                if(bufferImg1.getRGB(j, i) != bufferImg2.getRGB(j, i)){
                    flag = 1;
                    break;
                }
            }
            if(flag == 1){
                break;
            }
        }
        assertEquals(flag, 0);
        System.out.println(name + ": pix is equal.\n");
    }

    /**
     * Compare the first image's red.
     * @throws IOException
     */
    @Test
    public void testImg1Red() throws IOException{
        compare(img1Red, goalImg1Red, "1_red");
    }
    
    /**
     * Compare the first image's green.
     * @throws IOException
     */
    @Test
    public void testImg1Green() throws IOException{
    	compare(img1Green, goalImg1Green, "1_green");
    }
    
    /**
     * Compare the first image's blue.
     * @throws IOException
     */
    @Test
    public void testImg1Blue() throws IOException{
    	compare(img1Blue, goldImg1Blue, "1_blue");
    }
    
    /**
     * Compare the first image's gray.
     * @throws IOException
     */
    @Test
    public void testImg1Gray() throws IOException{
    	compare(img1Gray, goalImg1Gray, "1_gray");
    }
    
    /**
     * Compare the second image's red.
     * @throws IOException
     */
    @Test
    public void testImg2Red() throws IOException{
    	compare(img2Red, goalImg2Red, "2_red");
    }
    
    /**
     * Compare the second image's green.
     * @throws IOException
     */
    @Test
    public void testImg2Green() throws IOException{
    	compare(img2Green, goalImg2Green, "2_green");
    }
    
    /**
     * Compare the second image's blue.
     * @throws IOException
     */
    @Test
    public void testImg2Blue() throws IOException{
    	compare(img2Blue, goalImg2Blue, "2_blue");
    }
    
    /**
     * Compare the second image's gray.
     * @throws IOException
     */
    @Test
    public void testImg2Gray() throws IOException{
    	compare(img2Gray, goalImg2Gray, "2_gray");
    }
}