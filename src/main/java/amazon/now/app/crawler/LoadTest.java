package amazon.now.app.crawler;

import java.io.IOException;

public class LoadTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		int load = 100;
		int counter =0;
		String[] product_names = {"maggi","pepsi","chocolate","milk","tomatoes"};
		for(int i =1;i<=load;i++) {
			for(String product : product_names) {
				System.out.println("----------------------REQUEST COUNT : " + counter + " || Product Name : " + product +"------------------------");
				String jsonResponse = RestAPIUtil.sendGetRequest(product);
				JsonUtils.parseJson(jsonResponse);
				
				Thread.sleep(3 * 1000);
				counter++;
			}
		}
		

	}

}
