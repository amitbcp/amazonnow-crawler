package amazon.now.app.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class VerifyPrice {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//Default test values
		String asin ="B074QQK24Q";
		String merchant = "A11ULKMMYMPC7N";
		
		System.out.println("Verify Price ---");
		
		boolean askUser = true;
		while(askUser){
			System.out.print("Kindly Enter Product ASIN Code : ");			
			asin = br.readLine();
			
			System.out.print("Kindly Enter Merchant Code : ");			
			merchant = br.readLine();
			
			String response = getProductDetails(asin,merchant);
			
			try {
				parseHTMLPage(response);
			}catch(Exception e) {
			System.err.println("Out of Stock || Merchant does not has it");	
			}
			askUser = Assignment.askUser(br);				
		}			
		

	}
	public static String  getProductDetails(String asin,String merchant) throws IOException, InterruptedException {
		
		String baseUrl = "https://www.amazon.in/gp/aw/d/#ASIN#/ref=sr_1_1?"
				+ "m=#MERCHANT#&ie=UTF8&"
				//+ "m=A1YS8QMP6PKGLN&ie=UTF8&"
				//+ "m=A11ULKMMYMPC7N&ie=UTF8&"
				//Changing QID			
				+"qid=1518978176&sr=8-1";
		
		String url = baseUrl.replace("#ASIN#", asin);
		url = url.replace("#MERCHANT", merchant);
		
		String cookie = "session-id=262-6975540-8579116; session-id-time=2082787201l; "
				+ "session-token=\"ce6K/Ai55Xt8kQx6WmcMTFKblI/mCSGlnnswMhbEoLDh5ONqJn6Roa7qY7eSxwsK2kRd3g1NeWvlhPjW7dYIG6DGhW7xnkWMVCWbIkTg+OLJjBXnpNJDDwc36mGhot9LQNr0LU1EtBws4Rbc06Si77N7AJBAlJcN5y5RoV9GewoH46/p6JEEPy05bL03YiPxOGyTbxHBQ6GSmBTnDHiVHA==\"; "
				
				+ "csm-hit=s-6M8JX3276QJAK7G1BXN5|1518975277921; pnctxt=marketplaceId%3AA21TJRUUN4KGV; "
				+ "ubid-acbin=260-9515164-1290806; mobile-device-info=dpi:320.0|w:720|h:1280; "
				+ "amzn-app-id=Now/2.2.0/2.2.0; amzn-app-ctxt=1.3%20%7B%22an%22%3A%22Now%22%2C%22av%22%3A%222.2.0%22%2C%22xv%22%3A%221.11.0%22%2C%22os%22%3A%22Android%22%2C%22ov%22%3A%226.0.1%22%2C%22cp%22%3A262152%2C%22uiv%22%3A4%2C%22di%22%3A%7B%22pr%22%3A%22WW_Z00L%22%2C%22md%22%3A%22ASUS_Z00LD%22%2C%22v%22%3A%22ASUS_Z00L_63%22%2C%22mf%22%3A%22asus%22%2C%22dsn%22%3A%22%22%2C%22dti%22%3A%22%22%2C%22ca%22%3A%22Jio%204G%22%2C%22ct%22%3A%22WIFI%22%7D%2C%22dm%22%3A%7B%22w%22%3A720%2C%22h%22%3A1280%2C%22ld%22%3A2%2C%22dx%22%3A268.9410095214844%2C%22dy%22%3A268.6940002441406%7D%2C%22dbg%22%3A%7B%7D%7D";
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		//con.setRequestProperty("x-search-client-id", clientId);
		con.setRequestProperty("User-Agent", "Now/2.2.0 (Android/6.0.1/ASUS_Z00LD)");
		con.setRequestProperty("Cookie", cookie);
		Thread.sleep(1 * 1000);
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		return response.toString();
				
	}

	public static void parseHTMLPage(String response) {
		
		Document doc = Jsoup.parse(response);
		String price = doc.getElementById("priceblock_ourprice").text();
		System.out.println("Price :: " + price);
		
		
		
	}
}
