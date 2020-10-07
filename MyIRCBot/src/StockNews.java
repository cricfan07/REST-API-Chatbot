

//import the libraries necessary for creating the API call
import java.net.*;
import java.io.*;


//imports the gson library to convert between Json responses and Java objects
import com.google.gson.*; 

//This class stores the functionality for getting web requests from the stock news API
public class StockNews 
{
	/* 
	 * This method accepts a jsonString of type String and parses it into 
	 * a Java object. The method returns a string array which contains the 
	 * two most recent stock news for a specific company ticker code. 
	*/
	public static String[] parseJson(String jsonString)
	{
		JsonElement jElement = new JsonParser().parse(jsonString);
		JsonObject jObject1 = jElement.getAsJsonObject(); //starts the parsing of the JSON response
		
		JsonArray jArray = jObject1.getAsJsonArray("data"); //obtains the JsonArray "data"
		
		//retrieves the first most recent stock news
		jObject1 = jArray.get(0).getAsJsonObject(); 
		
		//retrieves the second most recent stock news
		JsonObject jObject2 = jArray.get(1).getAsJsonObject(); 
		
		String[] companyNews = new String[2];
		
		//convert the stock news from a Json Response to Java String types
		companyNews[0] = jObject1.get("text").getAsString();
		companyNews[1] = jObject2.get("text").getAsString();
		
	
		return companyNews; //return the two stock news
	}
	
	/*
	 * This method accepts a companyTicker of type String from the user in the Freenode chat
	 * in order to make a API URL string and conduct the API call. After the JSON response
	 * is parsed, the stock news are obtained as strings are returned through a String 
	 * array. 
	 */
	public static String[] getStockNews(String companyTicker) throws Exception
	{
		//my API key obtained from the StockNews API
		final String API_KEY = "naoyevx3tli0wztkimpsyafmpsgf8h8xobutwoyr";
		
		//creates the API URL needed to make the API call
		String urlString = "https://stocknewsapi.com/api/v1?tickers=" + companyTicker + "&items=50&token=" + API_KEY;
		
		//Creating a Request
		URL url = new URL(urlString);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET"); //we want to be able to read data from the web service

		//Reading the Request
		connection.getResponseCode();
			
				
		String inputLine;
		StringBuffer responseContent = new StringBuffer();
				
			
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		while((inputLine = inputReader.readLine()) != null) {
			responseContent.append(inputLine);
		}
		inputReader.close();
			
			
		//parse the JSON response and obtain the two latest stock news
		String[] companyNews = parseJson(responseContent.toString());
			
		connection.disconnect(); //disconnect the connection
			
		return companyNews; //return the two most latest stock news
			
			
	}
	
}

