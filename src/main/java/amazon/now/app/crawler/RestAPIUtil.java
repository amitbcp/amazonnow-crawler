package amazon.now.app.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestAPIUtil {
	
	public static String sendGetRequest(String searchParameter) throws IOException, InterruptedException   {

		String baseUrl = "https://www.amazon.in/s/ref=nb_sb_noss?k=" + searchParameter;
		
		//Constant Get Parameters for a Mobile Device
		String getParameters = "&ie=UTF-8&dataVersion=v0.1&cid=7b8563ea6549b5734786ef235f4833ff5771ad93a00f6817e41ea55fadfc6e29"
				+ "&format=json&cri=oXzEc0YE8laE1KyA&uaAppName=Now&uaAppType=Mobile+Applications&uaAppVersion=2.2.0&ma_dds=C";
		
		//String clientId = "7b8563ea6549b5734786ef235f4833ff5771ad93a00f6817e41ea55fadfc6e29";
		
		//Cookie based on pincode & Mobile Device
		String cookie = "session-id=262-6975540-8579116; session-id-time=2082787201l; "
				+ "session-token=\"ce6K/Ai55Xt8kQx6WmcMTFKblI/mCSGlnnswMhbEoLDh5ONqJn6Roa7qY7eSxwsK2kRd3g1NeWvlhPjW7dYIG6DGhW7xnkWMVCWbIkTg+OLJjBXnpNJDDwc36mGhot9LQNr0LU1EtBws4Rbc06Si77N7AJBAlJcN5y5RoV9GewoH46/p6JEEPy05bL03YiPxOGyTbxHBQ6GSmBTnDHiVHA==\"; "
				+ "pnctxt=marketplaceId%3AA21TJRUUN4KGV; mobile-device-info=dpi:320.0|w:720|h:1280; amzn-app-id=Now/2.2.0/2.2.0; "
				+ "amzn-app-ctxt=1.3%20%7B%22an%22%3A%22Now%22%2C%22av%22%3A%222.2.0%22%2C%22xv%22%3A%221.11.0%22%2C%22os%22%3A%22Android%22%2C%22ov%22%3A%226.0.1%22%2C%22cp%22%3A262152%2C%22uiv%22%3A4%2C%22di%22%3A%7B%22pr%22%3A%22WW_Z00L%22%2C%22md%22%3A%22ASUS_Z00LD%22%2C%22v%22%3A%22ASUS_Z00L_63%22%2C%22mf%22%3A%22asus%22%2C%22dsn%22%3A%22%22%2C%22dti%22%3A%22%22%2C%22ca%22%3A%22Jio%204G%22%2C%22ct%22%3A%22WIFI%22%7D%2C%22dm%22%3A%7B%22w%22%3A720%2C%22h%22%3A1280%2C%22ld%22%3A2%2C%22dx%22%3A268.9410095214844%2C%22dy%22%3A268.6940002441406%7D%2C%22dbg%22%3A%7B%7D%7D; "
				+ "ubid-acbin=260-9515164-1290806";
		
		String url = baseUrl+getParameters;
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
		System.out.println("Response JSON (View in http://jsonviewer.stack.hu/ ) \n"+ response.toString() );
		System.out.println();
		
		return response.toString().toString();

	}

}
