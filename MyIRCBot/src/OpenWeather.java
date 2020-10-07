

//import the libraries necessary for creating the API call
import java.net.*;
import java.io.*;


//imports the gson library to convert between Json responses and Java objects
import com.google.gson.*; 


//This class stores the functionality for getting web requests from the open weather map API
public class OpenWeather {
	
	
	/* 
	 * This method accepts a jsonString of type String and parses it into 
	 * a Java object. The method returns the JsonObject "main" from the Json
	 * response which contains the weather in the city. 
	*/
	public static JsonObject parseJson(String jsonString)
	{
		JsonElement jElement = new JsonParser().parse(jsonString);
		JsonObject jObject1 = jElement.getAsJsonObject(); //starts the parsing of the JSON response
		
		
		JsonObject jObject2 = jObject1.getAsJsonObject("main"); //obtains the contents of "main"
		
	
		return jObject2; //returns the contents of object "main"
		
	}
	
	/*
	 * This method accepts a location of type String from the user in the Freenode chat
	 * in order to make a API URL string and conduct the API call. After the JSON response
	 * is parsed, the temperatures are obtained as strings are returned through a String 
	 * array. 
	 */
	public static String[] getWeatherData(String location) throws Exception
	{
		//my API key obtained from the OpenWeather API
		final String API_KEY = "94726e1a08cf6c0456c937560fde6edd";
		
		//creates the API URL needed to make the API call
		String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + API_KEY + "&units=imperial";
		
		//Creating a Request
		URL url = new URL(urlString);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET"); //we want to be able to read data from the web service

		//Reading the Request
		int status = connection.getResponseCode();
		
		//checks if the response code is invalid and checks if the API call does not work
		if (status != 200)
		{
			//initialize the temperatures to ERROR
			String temp = "ERROR";
		    String temp_min = "ERROR";
		    String temp_max = "ERROR";
		    
		    //initialize the ERROR strings into the three temperatures
		    //add the strings to the string array
		    String[] weatherData = new String[3];
			weatherData[0] = temp;
			weatherData[1] = temp_min;
			weatherData[2] = temp_max;
			
			connection.disconnect(); //disconnect the connection
			
			return weatherData; //returns the temperatures as error strings
			
		}
		
		
		String inputLine;
		StringBuffer responseContent = new StringBuffer();
		
	
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		while((inputLine = inputReader.readLine()) != null) {
			responseContent.append(inputLine);
		}
		inputReader.close();
		
			
		String temp = null;
		String temp_min = null;
		String temp_max = null;
		
		//parse the JSON response to retrieve the three temperatures
		JsonObject jObject2 = parseJson(responseContent.toString());
		
		//Obtains the temperatures as strings from the JSON Response
		temp =  (jObject2.get("temp")).getAsString();
		temp_min = (jObject2.get("temp_min")).getAsString();
		temp_max = (jObject2.get("temp_max")).getAsString();
		
		//add the temperature values to the string array
		String[] weatherData = new String[3];
		weatherData[0] = temp;
		weatherData[1] = temp_min;
		weatherData[2] = temp_max;
		
		connection.disconnect(); //disconnect the connection
		
		return weatherData; //returns the valid temperature values 
		
		
	}
	
	
}
