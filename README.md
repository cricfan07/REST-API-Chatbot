This file contains the necessary documentation that is required to be read before 
executing the application, 
information about the OpenWeather API and StockNews API, and the
expected output from the freenode chat when the user types the required commands. 
The link for freenode is https://webchat.freenode.net

Both APIs utilize a parse method to retrieve the data from the json response of the API call and 
convert that necessary data into a Java object. 

OpenWeather API: 

Example commands:
Weather Dallas
Weather San Francisco

In the application, the file OpenWeather.java contains the necessary functionality for making API calls to the OpenWeather API. 
In this class, you will see a static method named getWeatherData(String location), in which the API URL is created using the 
specified parameter location which is of type String. When the chat is running on freenode, you will be asked to enter a valid
city name and this city will be passed by value to the location parameter in order to make the appropriate API call. 

Note: A valid API call would result in a HTTP response code of 200.

If the city name is invalid, then the HTTP response code would result in a value other than 200 and the API call will not work.

The second method in the class is the parseJson(String jsonString) method that is called by the getWeatherData(String location) method in order to retrieve the current temperature from the String variable location that is used to make the API URL. 

The API url string that is made in the getWeatherData method is passed by value to the parameter jsonString in the parseJson method after the HTTP connection is made to the API. 

StockNews API:

Example commands: 
Company FB
Company AMZN

In the application, the file StockNews.java contains the necessary functionality for making API calls to the StockNews API. 
In this class, you will see a static method named getStockNews(String companyTicker), in which the API URL is created using the 
specified parameter companyTicker which is of type String. When the chat is running on freenode, you will be asked to enter a valid company ticker code and this value will be passed in to the companyTicker parameter in order to make the appropriate API call. 

When a valid API call is made by entering a valid stock ticker, then the getStockNews method will retrieve the two most recent stock news on the company, which is obtained by using the second static method in the class, parseJson(String jsonString). 

The API URL string that is made in the getStockNews method is passed by value to the parameter jsonString in the parseJson method after the HTTP connection is made to the API. 




Executing the Application and Expected Output on Freenode:

In this application, the driver program is the MySimpleBotMain.java file. This is the class that needs to be run to start the chat with the bot on Freenode. This class will create an instance of the class MySimpleBot from MySimpleBot.java which extends the Pircbot abstract class. 

In MySimpleBotMain, the instance of the MySimpleBot class will connect to the endpoint of the freenode chat site and once it connects, it will join the specified channel. 

Note: The channel must begin with a # symbol. 

Once the user and the bot are connected to the same specified channel, the bot will send a message saying that you can enter any message and it will respond. This is done by using the sendMessage(String channel, String message) method. 

When this sendMessage method is invoked once, then an overridden version of the OnMessage method will also be invoked at the same time as well.
This overridden method is implemented in the MySimpleBot class where majority of the functionality is implemented for the bot to respond to specific commands. 
The constructor is also implemented in the class to set the name of the bot when connected to the channel.

For example, each time you reply Hello, the bot will respond Hello back to the user and will see a list of available commands the user can choose from. 

Note: If the user enters an invalid command, that is, the user happens to misspell Weather or Company, then the bot will prompt the user to check their command and enter again. 

If the user enters a valid command, meaning that they correctly spell Weather and Company, then the bot will be able to respond back.

If the user types in Weather followed by a city name or a city name, country code, then the bot will reply back with the current temperature and high and low temperatures, in degrees Fahrenheit, from the OpenWeather API. 

Note: If the user correctly enters the command Weather but happens to type a city name that is either misspelled or does not exist within OpenWeather's list of city names, then the bot will return a message back saying that the city was not found and that the user needs to try again. 

If the user correctly enters the command Company followed by a company stock ticker code, then the bot will reply back with the two most recent stock news for that company. 


However, if the user correctly enters the command Company, but happens to incorrectly type one of the available stock ticker codes from the StockNews API, then the bot will not respond. In this case, this means that the ticker symbol is incorrect.
