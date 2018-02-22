package amazon.now.app.crawler;

import org.json.*;

public class JsonUtils {
	
	public static void parseJson(String jsonResponse) {
		
		JSONObject obj = new JSONObject(jsonResponse);
		JSONArray results = obj.getJSONObject("results").getJSONArray("sections");
		
		for(Object  section : results) {
			//Inside the section elements - 0,1,...
			JSONObject sub_section = (JSONObject) section;
			JSONArray items = sub_section.getJSONArray("items");
			
			for(Object item : items) {
				JSONObject product = (JSONObject)item;
				String price = product.getJSONObject("prices").getJSONObject("buy").getString("price");
				String merchant = product.getJSONObject("merchant").getString("id");
				print("****Title : %s \n    <ASIN : %s> | <Price : %s> | Merchant ID : %s\n",product.get("title"),product.get("asin"),price,merchant);				
			}			
		}		
	}	
	
	private static void print(String msg,Object... args) {	
		
		System.out.println(String.format(msg, args));
		
	}

}
