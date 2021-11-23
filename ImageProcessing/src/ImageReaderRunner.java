import imagereader.IImageIO;
import imagereader.IImageProcessor;
import imagereader.Runner;

/**
 * The runner of the image reader.
 * @author z18342007
 *
 */
public class ImageReaderRunner {
    public static void main(String[] args) {
        IImageIO imageioer = new ImplementImageIO();
        IImageProcessor processor = new ImplementImageProcessor();
        Runner.run(imageioer, processor);
    }
}