/**
 * 
 */
package foodsnap_server.model;

import java.awt.Dimension;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;
import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;

import foodsnap_server.constants.foodsnap_environmentvar;

/**
 * @author Saniya
 *
 */
public class ProductReviews {
	
	public static String buildWordCloud(Long foodid, String review)  {
        try {
			WordCloud wordCloud;
			FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
	        frequencyAnalyzer.setWordFrequenciesToReturn(300);
	        frequencyAnalyzer.setMinWordLength(5);
	        
	        List<String> reviewList = new ArrayList<String>();
	        reviewList.add(review);
	
	        final List<WordFrequency> wordFrequencies = frequencyAnalyzer.load(reviewList);
	        //System.out.println(wordFrequencies);
	        final Dimension dimension = new Dimension(499, 333);
	        //System.out.println("11");
	        wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
	        //wordCloud.setPadding(1);
	        //wordCloud.setBackground(new PixelBoundryBackground(getInputStream("backgrounds/whale_small.png")));
	        //wordCloud.setColorPalette(new ColorPalette(new Color(0x4055F1), new Color(0x408DF1), new Color(0x40AAF1), new Color(0x40C5F1), new Color(0x40D3F1), new Color(0xFFFFFF)));
	        //wordCloud.setFontScalar(new LinearFontScalar(10, 40));
	        wordCloud.build(wordFrequencies);
	        
	        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	        wordCloud.writeToStreamAsPNG(byteArrayOutputStream);
	        //System.out.println(byteArrayOutputStream);
	        byte[] buffer = byteArrayOutputStream.toByteArray();
	        InputStream is = new ByteArrayInputStream(buffer);
	        /*
	        OutputStream outputStream = new FileOutputStream ("C://Users//Saniya//workspace_test//src//foodsnap_server//wordcloudimages//" + foodid + "wordcloud.png"); 
	        byteArrayOutputStream.writeTo(outputStream);
	        */
	        AWSCredentials credentials = new BasicAWSCredentials(foodsnap_environmentvar.AWSAccessKey, foodsnap_environmentvar.AWSSecretAccessKey);
	        AmazonS3 s3client = new AmazonS3Client(credentials);
	        String fileName = "wordcloudimages/" + foodid + "wordcloud.png";
	        
	        ObjectMetadata om = new ObjectMetadata();
	        om.setContentType("image/png");
	        s3client.putObject(new PutObjectRequest("mas-foodsnap", fileName, is, om)
	        		.withCannedAcl(CannedAccessControlList.PublicRead));        
	        //System.out.println("4");
	        //System.out.println("s3://mas-foodsnap/" + fileName);
	        return "http://s3-us-west-2.amazonaws.com/mas-foodsnap/" + fileName;
        }
        catch (Exception e) {
        	System.out.println("Something amiss!");
        	return "NoReviewsAvailable";
        }       
    }
	
	private static InputStream getInputStream(final String path) {
        //System.out.println("getInputStream : " + Thread.currentThread().getContextClassLoader().getResourceAsStream(path));
    	return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    }


}
