/**
 * 
 */
package sendotpsms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.SplittableRandom;

/**
 * @author pradeep gupta
 *
 */
public class SendOtpSMS {

	public static void main(String[] args) {

		String otp = SendOtpSMS.generateOtp(5);
		System.out.println("Generated Otp = " + otp);
	}

	private static String generateOtp(int otpLength) {
		// Java 8 = SplittableRandom
		SplittableRandom splittableRandom = new SplittableRandom();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < otpLength; i++){
			sb.append(splittableRandom.nextInt(0, 10));
		}
			
		sendSMS(sb.toString());
		return sb.toString();
	}
	public static void sendSMS (String otpStr) {
		  try {
		   
		   String apiKey = "apiKey=" + "dL3eR9xN8B8-LylZqEV8q254E1lfWd4VmWvI70GX1U";
		   
		   String message = "&message=" + URLEncoder.encode("Your OTP is " + otpStr+" :Pradeep testing:",
		     "UTF-8");
		   
		   String numbers = "&numbers=" + "3258698758"; //give the correct number
		  // String numbers = "&numbers=" + "2135462532,1254879542"; //give the correct number
		   String apiURL = "https://api.textlocal.in/send/?" + apiKey + message + numbers;
		   
		   URL url = new URL(apiURL);
		   URLConnection connection = url.openConnection();
		   connection.setDoOutput(true);
		   
		   BufferedReader reader = new BufferedReader(new 
		     InputStreamReader(connection.getInputStream()));
		   
		   String line = "";
		   StringBuilder sb = new StringBuilder();
		   
		   while ( (line = reader.readLine()) != null) {
		    sb.append(line).append("\n");
		   }
		   
		   System.out.println(sb.toString());
		   
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		 }
}
