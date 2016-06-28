/**
 * 
 */
package foodsnap_server.test;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.PixelBoundryBackground;
import com.kennycason.kumo.font.scale.LinearFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.palette.ColorPalette;

import foodsnap_server.constants.foodsnap_environmentvar;

import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Saniya
 *
 */
public class TestCode_wordcloud extends JPanel{

    private final WordCloud wordCloud;

    public static void main(String[] args) throws IOException {
        //final JFrame frame = new JFrame("JPanel WordCloud");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.add(
        new TestCode_wordcloud();//);
        /*frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);*/
    }

    public TestCode_wordcloud() throws IOException {
        wordCloud = buildWordCloud();
        /*final JLabel wordCloudLabel = new JLabel(new ImageIcon(wordCloud.getBufferedImage()));
        add(wordCloudLabel);
        repaint();*/
    }

    private static WordCloud buildWordCloud() throws IOException {
        final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
        frequencyAnalyzer.setWordFrequenciesToReturn(300);
        frequencyAnalyzer.setMinWordLength(5);
        
        List<String> reviewList = new ArrayList<String>();
        reviewList.add("Delicious,Delicious,Delicious,Delicious,Delicious,Delicious,Horrible,Awesome,Delicious,Recommended,Horrible,Easy-to-make,Gluten-free,Horrible,Healthy");

        //String reviewList =  "Delicious,Delicious,Delicious,Delicious,Delicious,Delicious,Horrible,Awesome,Delicious,Recommended,Horrible,Easy-to-make,Gluten-free,Horrible,Healthy";
        
        final List<WordFrequency> wordFrequencies = frequencyAnalyzer.load(reviewList);
        System.out.println(wordFrequencies);
        final Dimension dimension = new Dimension(499, 333);
        System.out.println("11");
        final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
        System.out.println("111");
        //wordCloud.setPadding(1);
        System.out.println("1111");
        //wordCloud.setBackground(new PixelBoundryBackground(getInputStream("backgrounds/whale_small.png")));
        System.out.println("11111");
        //wordCloud.setColorPalette(new ColorPalette(new Color(0x4055F1), new Color(0x408DF1), new Color(0x40AAF1), new Color(0x40C5F1), new Color(0x40D3F1), new Color(0xFFFFFF)));
        System.out.println("111111");
        //wordCloud.setFontScalar(new LinearFontScalar(10, 40));
        System.out.println("2");
        wordCloud.build(wordFrequencies);
        
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        wordCloud.writeToStreamAsPNG(byteArrayOutputStream);
        System.out.println(byteArrayOutputStream);
        OutputStream outputStream = new FileOutputStream ("C://Users//Saniya//workspace_test//src//foodsnap_server//wordcloudimages//testwordcloudtest.png"); 
        byteArrayOutputStream.writeTo(outputStream);
        
        AWSCredentials credentials = new BasicAWSCredentials(foodsnap_environmentvar.AWSAccessKey, foodsnap_environmentvar.AWSSecretAccessKey);
        System.out.println("1");
        AmazonS3 s3client = new AmazonS3Client(credentials);
        System.out.println("2"); 
        
        String fileName = "wordcloudimages/testwordcloudtest.png";
        System.out.println("3");
        s3client.putObject(new PutObjectRequest("mas-foodsnap", fileName, 
        		new File("C://Users//Saniya//workspace_test//src//foodsnap_server//wordcloudimages//testwordcloudtest.png"))
        		.withCannedAcl(CannedAccessControlList.PublicRead));        
        System.out.println("4");
        System.out.println("4");
        return wordCloud;
    }

    private static InputStream getInputStream(final String path) {
        System.out.println("getInputStream : " + Thread.currentThread().getContextClassLoader().getResourceAsStream(path));
    	return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    }
	
}