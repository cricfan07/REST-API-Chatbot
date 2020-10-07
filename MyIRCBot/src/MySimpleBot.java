
import org.jibble.pircbot.*; //imports the pircbot abstract class


//This class extends the main pircbot class 
//This class is the main logic of your pircbot, this is where you will implement any functionality
public class MySimpleBot extends PircBot 
{

	//Constructor
	public MySimpleBot() 
	{
		this.setName("Theta"); //this is the name the bot will use to join the IRC server
	}
	
	//every time a message is sent, this method will be called and this information will be passed on
	//this is how you read a message from the channel
	public void onMessage(String channel, String sender, String login, String hostname, String message) 
	{
		//Use this function to read a message from the channel
		
		if(message.contains("Hello")) 
		{
			//this is how you send a message back to the channel
			
			//Provide the user with their available commands
			sendMessage(channel, "Hey " + sender + "! ");
			sendMessage(channel, "These are your available commands\u003A ");
			sendMessage(channel, "Weather city or city/country code");
			sendMessage(channel, "Company stock ticker");
			
		}
		
		
		//the user wants weather, call the weather API 
		else if(message.contains("Weather")) 
		{
		
			//get the city name from the second word in the command
			try {
				
				//obtains the city name after the command Weather is entered
				int k = message.indexOf(' ');
				String city = message.substring(k + 1);
				
				//obtains the three temperatures as strings
				String weatherData[] = OpenWeather.getWeatherData(city);
				String currentTemp = weatherData[0];
				String minTemp = weatherData[1];
				String maxTemp = weatherData[2];
				
				//check if the currentTemp is an ERROR string making the API call not work
				if(currentTemp.equals("ERROR")) {
					sendMessage(channel, "City not found! Please check the City name and try again!");
				}
				
				else {
					//displays the temperatures in degrees Fahrenheit
					sendMessage(channel, "The weather in " + city + " is going to be " + currentTemp + " \u00B0F"+ " with a high of " + maxTemp + " \u00B0F"  + " and a low of " + minTemp +  " \u00B0F");
				}
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
		}
		
		//the user wants stock news on a company, call the stock news API 
		else if(message.contains("Company")) 
		{
		
			try {
				
				//obtains the city name after the command Weather is entered
				int k = message.indexOf(' ');
				String companyTicker = message.substring(k + 1);
				
				//obtains the two company's stock news as strings
				String companyNews[] = StockNews.getStockNews(companyTicker);
				sendMessage(channel, companyNews[0]);
				sendMessage(channel, companyNews[1]);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		else 
		{
			// message the user to enter a valid command in case the command was misspelled
			sendMessage(channel, "Command was invalid, here are your available commands\u003A");
			sendMessage(channel, "Weather city or city/country code");
			sendMessage(channel, "Company stock ticker");
			sendMessage(channel, "Make sure the command is not misspelled");
		}
		
				
	}
	
	

}
