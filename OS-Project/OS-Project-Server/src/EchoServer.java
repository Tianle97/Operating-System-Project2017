import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class EchoServer {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		ServerSocket m_ServerSocket = new ServerSocket(2004,10);
		int id = 0;
		while (true) {
			Socket clientSocket = m_ServerSocket.accept();
			ClientServiceThread cliThread = new ClientServiceThread(clientSocket, id++);
			cliThread.start();
		}
	}
}

class ClientServiceThread extends Thread {
	Socket clientSocket;
	String message;
	int clientID = -1;
	boolean running = true;
	ObjectOutputStream out;
    ObjectInputStream in;
	String exMsg = "";
	String Mes = "";
	String vMes = "";
	Person p;
	int o = 0;
	ClientServiceThread(Socket s, int i) {
	    clientSocket = s;
	    clientID = i;
	}
  
public void sendMessage(String msg) {
	try {
		out.writeObject(msg);
		out.flush();
		} catch(IOException ioException){
			ioException.printStackTrace();
		}
}

public void run() {
	System.out.println("Accepted Client : ID - " + clientID + " : Address - " + clientSocket.getInetAddress().getHostName());
	try {
		out = new ObjectOutputStream(clientSocket.getOutputStream());
		out.flush();
		in = new ObjectInputStream(clientSocket.getInputStream());
		System.out.println("Accepted Client : ID - " + clientID + " : Address - " + clientSocket.getInetAddress().getHostName());
		do{
			try {
				//main menu 
				sendMessage(exMsg + " Enter 1 for Register\n Enter 2 for Log-in \n Enter 9 to exit");
				exMsg="";
				message = (String)in.readObject();
				if(message.equals("1"))
				{
					//if user enter '1' they will go to enter their some details
					//call 'register' method
					register();   
				}else if(message.equals("2"))
				{
					//if user already registered,they can enter '2' for login.
					//call 'log_in' method
					log_in();    
				}
			}catch(ClassNotFoundException classnot){
				System.err.println("Data received in unknown format");
				}
			}while(!message.equals("9")); //If user enter '9' will exit.
		System.out.println("Ending Client : ID - " + clientID + " : Address - " + clientSocket.getInetAddress().getHostName());
		System.out.println("user wants to exit.");
    } catch (Exception e) {
    	e.printStackTrace();
    }
}

//"log_in" Method if user enter 2'
//They will in another menu (add fitness or meal records,view their records,view meal records,and enter their records again,also can back to main menu)
public void log_in() {
	System.out.println("User wants to Log-in.");
	File file = new File("data//" +File.separator); //open data document
	String[] s = file.list();     
	boolean flag = false;
	try {
		for(int i=0;i<s.length;i++)
		{
			System.out.println(s[i]);  //output all of .dat name
		}
		sendMessage("Please enter your Name: ");
		String name = (String)in.readObject();
		for(int i=0;i<s.length;i++)
		{
			if(s[i].startsWith(name))  
			{
				flag = true;
				break;
			}
		}
		//flag=true means name is correct.
		if(flag == true) 		
		{
			//open the special dat
			FileInputStream fi = new FileInputStream("data//" + name + ".dat"); 
			@SuppressWarnings("resource")
			ObjectInputStream inn = new ObjectInputStream(fi); 
			p = (Person) inn.readObject();      //read file object
			sendMessage("Please enter your PPS Number: ");   
			String pps = (String)in.readObject();
			if(pps.equals(p.getPPS_Number()))
			{
				// if pps is correct will do next step(view)
				Mes="You successed to log_in!!\n"; 
				view(p);
			}
			else if(!pps.equals(p.getPPS_Number()))
			{
				//if pps is wrong will back to main menu.
				exMsg="Your PPS Number is wrong.\n";
			}
		}
		// flag == false means name is not correct.the document is not exist.
		else if(flag == false)
		{
			//will have a sentence on client and back to main menu.
			exMsg="The account is not exist.\n";
		}
		}catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
}

//3.Add fitness record and meal records
public void add(Person p,int n) {
	Record re = new Record();
	System.out.println("User wants to enter the fitness details");
	Mes="";
	try {
		//if user enter 2 will asked enter their fitness details.
		if(n==2)
		{
			sendMessage(Mes+"Please Model about your fitness:");
			String mode = (String)in.readObject();
			re.setMode(mode);
			
			sendMessage("Please enter your Duration about your fitness:");
			String duration = (String)in.readObject();
			re.setDur(duration);
		}
		//if user enter 1 will asked enter their meal details
		if(n==1)
		{
			sendMessage("Please enter your type of meal:");
			String tom = (String)in.readObject();
			re.setTom(tom);
			
			sendMessage("Can you describe your meal(max 100 characters)?");
			String des = (String)in.readObject();
			re.setDes(des);
		}
		p.r.add(re);
		FileOutputStream output = new FileOutputStream("data//" +p.getName()+ ".dat");
	    ObjectOutputStream objOut=new ObjectOutputStream(output);
	    objOut.writeObject(p);
	    objOut.flush();
	    objOut.close();
	    System.out.println(re.getDes());
	    System.out.println(p.getName());
	    vMes = "Please";
	    view(p);
	}catch (ClassNotFoundException | IOException e) {
		e.printStackTrace();
		System.out.println(e.toString());
	}
}

//view the last 10 records or meals records
public void view(Person p) {
	System.out.println("user want to view their records.");
	try { 
		sendMessage(vMes+" Enter 1 for write meal recordes\n Enter 2 for write fitness recordes\n Enter 4 for last ten recordes\n Or enter 5 for last ten meal recordes\n Or enter 7 for menu.\n");
		vMes = "";
		String a = (String)in.readObject();
		//if user enter 2 will asked enter their meal details.call 'add' method
		if(a.equals("2"))
		{
			System.out.println("User wants to add fitness records.");
			add(p,2);
		}
		//if user enter 1 will asked enter their meal details.call 'add' method
		if(a.equals("1"))
		{
			System.out.println("User wants to add meal records.");
			add(p,1);
		}
		//if user enter 4 will view the meal details and fitness details
		//it will view all details if the number of all records < 10 
		//it will view last 10 details if the number of all records >= 10 
		if(a.equals("4")) 
		{
			ArrayList<Record> rec = p.getR();
			String e="";
			if (rec.size()>=10)
			{
				for(int i=rec.size()-1,j=1;i>=rec.size()-10;i--,j++)
				{
					if(rec.get(i).getMode()==null)
					{
						e = e+j+". => Type of meal: "+rec.get(i).getTom()+"\n Description: "+rec.get(i).getDes()+"\n";
					}
					else 
					{
						e = e+j+". => Mode: "+rec.get(i).getMode()+"\n Duration: "+rec.get(i).getDur()+"\n";
					}
				}
			}
			else
			{
				for(int i=rec.size()-1,j=1;i>=0;i--,j++)
				{
					if(rec.get(i).getMode()==null)
					{
						e = e+j+". => Type of meal: "+rec.get(i).getTom()+"\n Description: "+rec.get(i).getDes()+"\n";
					}
					else 
					{
						e = e+j+". => Mode: "+rec.get(i).getMode()+"\n Duration: "+rec.get(i).getDur()+"\n";
					}
				}
			}
			vMes = e;
			view(p);
		}
		//if user enter 5 will view the meal details 
		//it will view all meal details if the number of meal records < 10 
		//it will view last 10 meal details if the number of meal records >= 10 
		else if (a.equals("5"))
		{
			int n=0;
			String e ="";
			ArrayList<Record> rec = p.getR();
			for(int i=0;i<rec.size();i++)
			{
				if(p.r.get(i).getTom() != null)
				{
					n++;
				}
			}
			System.out.println(n);
			if(n<=10)
			{
				for(int i=rec.size()-1,j=1;j<=n;i--)
				{
					if(rec.get(i).getTom()!=null)
					{
						e = e+j+". => Type of meal: "+rec.get(i).getTom()+"\n Description: "+rec.get(i).getDes()+"\n";
						j++;
					}
				}
			}
			else
			{
				for(int i=rec.size()-1,j=1;j<10;i--)
				{
					if(rec.get(i).getTom()!=null)
					{
						e = e+j+". => Type of meal: "+rec.get(i).getTom()+"\n Description: "+rec.get(i).getDes()+"\n";
						j++;
					}
				}
			}
			vMes = e;
			view(p);
		}
		//if user enter 7 will back to main menu
		else if (a.equals("7"))
		{
			exMsg="";
		}
	}catch (ClassNotFoundException | IOException e) {
		e.printStackTrace();
		System.out.println(e.toString());
	}
}

//"register" Method
public void register() {
	Person p = new Person();
	System.out.println("User wants to enter the details");
	sendMessage("Please enter your Name:");
	try {
		//Name
		String string1 = (String)in.readObject();
		p.setName(string1);
		//Address
		sendMessage("Please enter your Address:");
		String string2 = (String)in.readObject();
		p.setAddress(string2); 
		//PPS Number
		sendMessage("Please enter your PPS Number:");
		String string3 = (String)in.readObject();
		p.setPPS_Number(string3);
		//Age
		sendMessage("Please enter your Age");
		String string4 = (String)in.readObject();
		p.setAge(string4);
		//Weight
		sendMessage("Please enter your Weight:");
		String string5 = (String)in.readObject();
		p.setWeight(string5);
		//Height
		sendMessage("Please enter your Height");
		String string6 = (String)in.readObject();
		p.setHeight(string6);
		System.out.println(p.getName());
		//save in a dat document
		FileOutputStream output = new FileOutputStream("data//" +p.getName()+ ".dat");
	    ObjectOutputStream objOut=new ObjectOutputStream(output);
	    objOut.writeObject(p);
	    objOut.flush();
	    objOut.close();
	    } catch (ClassNotFoundException | IOException e) {
	    	e.printStackTrace();
	    	System.out.println(e.toString());
	    }
}
}
