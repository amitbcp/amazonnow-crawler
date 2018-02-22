package amazon.now.app.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Assignment {

	public static void main(String[] args) throws IOException, InterruptedException   {
		System.out.println("Kindly Enter Grocery Product Name to search in Mumbai 400061");		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		boolean askUser = true;
		while(askUser){
			System.out.print("Search Amazon Now for : ");
			String productName = br.readLine();
			String jsonResponse = RestAPIUtil.sendGetRequest(productName);
			JsonUtils.parseJson(jsonResponse);
			askUser = askUser(br);				
		}			
	}
	
	static boolean askUser(BufferedReader br) throws IOException {
		boolean askUser = false;
		int attempt =0;
		
		while(attempt <3) {
			System.out.print("Do you want to search Again (y/n) : ");
			String searchAgain = br.readLine();
			if("n".equalsIgnoreCase(searchAgain)) {
				System.out.println("Thanks");
				askUser=false;
				break;
			}else if ("y".equalsIgnoreCase(searchAgain)) {				
				askUser=true;
				break;
			}else {
				System.out.println("Wrong Input ! Try Again");
				attempt++;
			}
		}		
		return askUser;
	}
	
	

}
