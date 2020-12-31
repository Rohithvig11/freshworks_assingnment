package File_processing_with_JSON;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CRDprocessing {
	String filePath="";
	public CRDprocessing(String path)
	{
		this.filePath=path;
	}
	HashMap<String,Datagetset> hm;
	FileInputStream fis;
	ObjectInputStream objis;
	FileOutputStream fos;
	ObjectOutputStream objos;
	public synchronized String createfn(String key,JSONObject value,int ttl)
	{
		if(exists(key))
		{
			return "key already exists!";
		}
		else if(key.length()>32) {
			return "key size is more than 32 characters";
		}
		else
		{
		Datagetset ds=new Datagetset();
		ds.setKey(key);
		ds.setValue(value);
		ds.setTtl(ttl);
		ds.setStarttime((new Date().getTime()));
		try {
			File file = new File(filePath);
			if (file.exists()) {
				if(file.length()>1024*1024*1024)
					return "file size exceeded 1GB";
				fis = new FileInputStream(file);
				objis = new ObjectInputStream(fis);
				hm = (HashMap<String, Datagetset>) objis.readObject();
				hm.put(ds.getKey(), ds);
				fos = new FileOutputStream(file);
				objos = new ObjectOutputStream(fos);
				objos.writeObject(hm);
				fis.close();
				objis.close();
				fos.close();
				objos.close();
				fis = new FileInputStream(file);
				objis = new ObjectInputStream(fis);
				hm = (HashMap<String, Datagetset>) objis.readObject();
				return "created successfully";
			} else {
				hm = new HashMap<String, Datagetset>();
				hm.put(ds.getKey(), ds);
				fos = new FileOutputStream(file);
				objos = new ObjectOutputStream(fos);
				objos.writeObject(hm);
				fos.close();
				objos.close();
				return "created successfully";
			}
		}
		catch(Exception e) {
			System.out.println(e);
			return "failed in creation";
		}
		
		}
	}
	public synchronized String read(String key)
	{
		try {
			File file = new File(filePath);
			if (file.exists() && exists(key)) {
				fis = new FileInputStream(file);
				objis = new ObjectInputStream(fis);
				hm = (HashMap<String, Datagetset>) objis.readObject();				
				fis.close();
				objis.close();
				return (key+" "+hm.get(key).getValue());
			} 
			else
			{
				return "Key not found!";
			}
		} catch (Exception exception) {
			return "Key not found!";
		}
	}
	public synchronized String delete(String key)
	{
		if(exists(key))
		{
		try {
			File file = new File(filePath);
			if (file.exists()) {
				fis = new FileInputStream(file);
				objis = new ObjectInputStream(fis);
				hm = (HashMap<String, Datagetset>) objis.readObject();
				fis.close();
				objis.close();
				hm.remove(key);
				fos = new FileOutputStream(file);
				objos = new ObjectOutputStream(fos);
				objos.writeObject(hm);
				fos.close();
				objos.close();
				return "deleted sucessfully";
			}
		} catch (Exception exception) {return "deletion failed";}
		}
		else
		{
				return "Key Doesn't Exist!";
		}
		return "";
	}
	public Boolean exists(String key)
	{
		Boolean existvar=false;
		try {
			File file = new File(filePath);
			if (file.exists()) {
				fis = new FileInputStream(file);
				objis = new ObjectInputStream(fis);
				hm = (HashMap<String, Datagetset>) objis.readObject();
				fis.close();
				objis.close();
				if(hm.containsKey(key))
					existvar=true;
				fos = new FileOutputStream(file);
				objos = new ObjectOutputStream(fos);
				objos.writeObject(hm);
				fos.close();
				objos.close();
			}
			if (existvar) {
				Datagetset ds = hm.get(key);
				long now = new Date().getTime();
				int ttlvalue=ds.getTtl();
				long diff=(now - ds.getStarttime());
				if (ttlvalue> 0 && diff >= (ttlvalue*1000)) {
					hm.remove(key);
					fos = new FileOutputStream(file);
					objos = new ObjectOutputStream(fos);
					objos.writeObject(hm);
					fos.close();
					objos.close();
					existvar = false;
				}
			}
		} catch (Exception exception) {}
		return existvar;
	}
}