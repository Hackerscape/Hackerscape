# Hackerscape

I started hackerscape because I love Runescape and I am also into programming/Java. I am not a very good programmer and this project will be a lot of fun for me. I have some fun ideas and minigames that I look forward to implementing, which I will outline below. If you are a programmer, Runescape enthusiast, Hacker, Gamer, Etc- I would love to hear from you or see you IN-Game! If you found this on Git-hub or somewhere else and you would like to participate in the development or just have questions, feel free to reach out to me at null.routed@outlook.com.

This rsps was created from a version of Project Insanity and there are a lot of bugs that will need to be worked out. If you are interested in resolving a few of them or have fixes for something- feel free to submit a pull request! To quote a very talented man: "Talent wins games, but teamwork and intelligence win championships." --Michael Jordan

# Known-Bugs/Issues/Additions

This is a list of things that are either broken, have an issue or will be Implemented at some time. A few of these I have some awesome ideas for and will go into detail in description. 

* NPC Animations
     * Found quite a few animations that are definately shoddy implementation. The attack animation seems to be warped sprites. 
* Barrows Mini-game
     * Need to add a "Kill Count" to the screen so that people know how many of the brothers they have killed.
     * There is also an issue when you enter one of the tombs, upon leaving the mini-map no longer works. Will need to fix this. 
* Shops
     * I am going to completely redisign the shops to better represent an economic environment. Prices are currently all over the place and shops seem to be missing some key items. 
* Hunter
     * This skill needs some serious work, as well as the hunter skilling zone- going to be some big changes here. 
* Drop Tables
     * Will need to sompletely rewrite drop tables to better suite the economy in-mind.
* God Wars
     * Will need to rewrite a bit of this to add the "Followers" of the gods and setup a required amount of kills before being able to enter the gods room. 
* Pest Control
     * Pest control needs a bit of work, very lazy version was included in the source. Portals need to be added to their respective locations and NPC Summoning needs to be added. 
* Summoning Skill
	 * Currently summoning does work, but you are unable to dismiss your pet. There is also a bit of a glitch where if you "Toggle" the Summon option on a pet it will no longer be attached to you after a teleport. 
* Passwords
     * Currently passwords are saved in plaintext, will need to implement some sort of salted hashing function.
		 
		  
This list will surely grow as I work on the source, there is a lot to be done.

# New Content

* Bitcoin
     * I look forward to implementing some form of "Bitcoin" to the game, with a dark market for people to buy and sell things for their bitcoin. Perhaps mining will result in the accumulation of bitcoin and maybe monsters may drop some. I am not sure how i will do this yet. I also want to add it to thieving as well.
* Bank Account Hacking [Hacking]
	 * This may turn out to be a new skill, everyone will have an account number at some point and users will be able to sniff around and find these account numbers, they will then have some sort of work to be able to hack the account and perhaps withdraw a random item into their own bank account, I am not entirely sure how i am going to work it yet but it will come!
* Ddos
	 * I want to impliment some sort of ddos to the game, perhaps not in the typical sense but perhaps a spell you could cast on another player during pk. In order to be able to utilize this new spell you must have x level in hacking. I am not quite sure yet what the effect will be so I am open to suggestions. 
* Account Hacking Events
     * A user account is created with valuable items in its bank account and your job is to crack the password. The initial password clue will be visible to all players and the winner is the one who follows the trail, logs into the account and transfers out the items to their own bank account. I am very excited about this event, should be alot of fun!
	 
If you have any ideas on a new skill or something that may compliment my ideas, please let me know! I woud love to hear from you. If you think you are capable of helping/coding some of these, feel free to open a pull request! Thanks. 


# How to run the server.

1.) You will need to download the Java SE Development Kit, This is the one i Use and should also work fine for you: [Java SE Development Kit 8u161](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

2.) Once you have downloaded and installed the development kit, or if you already had it- go ahead and run the "Run server.bat" In the source folder. If it flashes and does nothing or throws and error, you will need to edit the bat file and fix the Java path for your environment. Simply change the current path:

@echo off
title Server
:run
cls
**"C:\Program Files\Java\jdk1.8.0_161\bin\java.exe"** -Xmx1000m -cp bin;deps/poi.jar;deps/mysql.jar;deps/mina.jar;deps/slf4j.jar;deps/slf4j-nop.jar;deps/jython.jar;log4j-1.2.15.jar; server.Server
goto run

Change the bold text to where your bin folder is located. You will then need to do this for the compile bat and also the client compile and run bat files.





		
			


