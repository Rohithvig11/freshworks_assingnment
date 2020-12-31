package File_processing_with_JSON;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import java.util.*;
public class Main {
	public static void main(String args[]) throws IOException, ParseException, InterruptedException
	{
		Scanner scan=new Scanner(System.in);
		int n,i;
		String key="";
		String name="";
		String regno="";
		int year;
		String dept="";
		String filepath="";
		int ttl;
		System.out.println("Enter Filepath else specify NA:");
		filepath=scan.nextLine();
		if(filepath.equals("NA"))
		filepath="C:\\Java\\defaultpath\\data";
		else
		filepath=filepath+"\\data";
		CRDprocessing store=new CRDprocessing(filepath);
		JSONObject json_value = new JSONObject();
		json_value.put("name", "rohith");
		json_value.put("regno", 104143);
		json_value.put("dept","CSE");
		json_value.put("year", 4);
		store.createfn("rohith",json_value,-1);
		json_value.clear();
		json_value.put("name", "vignesh");
		json_value.put("regno", 104163);
		json_value.put("dept","CSE");
		json_value.put("year", 3);
		store.createfn("vignesh",json_value,2);
        System.out.println(store.createfn("abcdefghijklmnopqrstuvwxyz1234567890111111",json_value,2)); //failure-key size is more than 32
//		code for getting user input .....

//      System.out.println("Enter the no of users to store");
//		no=scan.nextInt(); // no of users
//		for(i=0;i<no;i++)
//		{
//			scan.nextLine();
//			System.out.println("Enter the Key:");
//			key=scan.nextLine();
//			if(store.exists(key))
//			{
//				System.out.println("Key Already Exists!");
//			}
//			else
//			{
//			System.out.println("Enter the Name:");
//			name=scan.nextLine();
//			System.out.println("Enter the regno:");
//			regno=scan.nextLine();
//			System.out.println("Enter the dept");
//			dept=scan.nextLine();
//			System.out.println("Enter the year");
//			year=scan.nextInt();
//			System.out.println("If you want to specify TTL give the value in seconds else give -1");
//			ttl=scan.nextInt();
//			json.put("name", name);
//			json.put("regno", regno);
//			json.put("dept", dept);
//			json.put("year",year);
//			store.createfn(key, json_value,ttl);
//			json.clear();
//			}
//		}
		System.out.println(store.read("vignesh"));//success-reads the user key values
		Thread.sleep(3000);
		System.out.println(store.read("vignesh"));//failure-does not exist as it gets deleted after specified ttl
		System.out.println(store.read("rohith"));//success-reads the user key values
		System.out.println(store.delete("rohith"));//success-element with key rohith is deleted
		System.out.println(store.delete("vignesh"));//failure-key not found
	}
}
	
