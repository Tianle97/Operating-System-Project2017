
# _Operating-System Project 2017_


## Lecturer: *Martin Hynes*

## Student Name: _Tianle Shu_

## Student ID: G00353418
# *Introduction*
This Project is a Multi-threaded TCP Server Application which allows multiple users to record their daily training and eating habits.

# *Requests*
1. Register with the	system
• Name </br>
• Address </br>
• PPS	Number </br>
• Age </br>
• Weight </br>
• Height </br>
2. Log-in	to	the	fitness system	from the	client	application	to the	server	application.
3. Add	a	fitness	record	with	the	following	details: </br>
• Mode	(e.g.	Walking,	Running,	Cycling) </br>
• Duration </br>
4. Add	a	meal	record	with	the	following	details: </br>
• Type	of	meal (e.g.	Walking,	Running,	Cycling) </br>
• Meal	Description	(Max	100	characters) </br>
5. View	the	last	ten	records	on	their	record.
6. View	the	last	ten	fitness	records	on	their	record.
7. Delete	a	record	using	the	record	number	shown	in	menu	item	6

# *Explain*
A.</br>
The OS-Project-Server Document hava two main file: data file,src file. </br>

I. data file: There are .dat documents. Save every users information.Every user registed,they hava a .dat document,there are 6 register informations and fitness and meal records that they fill each time. </br>

II. scr file: There are 3 class.(EchoServer.java,Person.java,Record.java) </br>

i. The EchoServer.java : There are 4 methods.('register','log_in',add','view'),main menu:
a. enter '1' for register.</br>
b. enter '2' for log_in.</br>
c. enter '9' to exit.</br>

1.  The 'register' method: When user enter '1'.It will send 6 request sentence to user and receive the user's message from Client and send the some request sentence to Client for user enter.And save these user's information to .dat document.

2.  The 'log_in' method: When user enter '2'.If user already registed,They can enter '2' for 'log_in'. And They will be asked to enter their name(entered when registering ).
                                 a. If the name is wrong,will send "The account is not exist." and back to main menu. </br>
                                 b. If the name is correct. You will be asked to enter thier pps number. </br>
                                 b(1). if the pps number is wrong.will send "Your PPS Number is wrong." and back to main menu.</br>
                                 b(2). if the pps number is correct.will send "You successed to log_in!!" and go to next menu.</br>

3.  The 'add' mehod:when the user log_in successed.User will receive another nemu.</br>
                            a. If user enter '1' user will be asked enter fitness records. save in .dat document.</br>
                            b. If user enter '2' user will be asked enter meal records.  save in .dat document.</br>

4.  The 'view' method:second menu(when log_in seccessed)</br>
                               a. If user enter '1' (go to the 'add' method)user will be asked enter fitness records.</br>
                               b. If user enter '2' (go to the 'add' method)user will be asked enter meal records.  </br>
                               c. If user enter '4' will view the meal details and fitness details.</br>
                                   It will view all details if the number of all records < 10 .</br>
                                   It will view last 10 details if the number of all records >= 10 .</br>
                               d. If user enter '5' will view the meal details.</br>
                                   It will view all meal details if the number of meal records < 10 .</br>
                                   It will view last 10 meal details if the number of meal records >= 10 .</br>
                               e. If user enter '7' will back to main menu.</br>
B.</br>
ii. The Person.java: set and get register information</br>
iii. The Record.java: set and get add meal and fitenss records.</br>
