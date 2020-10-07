

//This is the class that sets up my chatbot.
public class MySimpleBotMain {
	


	public static void main(String[] args)  
	{
		
		//Now start our bot up.
		MySimpleBot bot = new MySimpleBot();
		
		//Enable debugging output.
		bot.setVerbose(true);
		
		try {
			//Connect to the IRC Server.
			bot.connect("irc.freenode.net");
		}
		 catch (Exception e) {
			 System.out.println("Can't connect: " + e);
			 return;
		 }
		
		
		//Join the #CS2336 channel.
		bot.joinChannel("#CS2336"); //Name of the channel that you want to connect to
		
		//this is the default message it will send when your pircbot first goes live
		bot.sendMessage("#CS2336", "Hey! Enter any message and I'll respond!");
		
		
	}

}
