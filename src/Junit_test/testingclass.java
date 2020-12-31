package Junit_test;

import static org.junit.jupiter.api.Assertions.*;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import File_processing_with_JSON.CRDprocessing;

class testingclass {	
	@Test
	void test_create() {
		String s1="";
		String s2="";
		String s="created successfully";
		String snew="key already exists!";
		CRDprocessing crdobj=new CRDprocessing("C:\\Java\\defaultpath\\data");
		JSONObject jsontest=new JSONObject();
		jsontest.put("name", "test");
		jsontest.put("regno", "111111");
		jsontest.put("dept", "CSE");
		jsontest.put("year", 0);
		s1=crdobj.createfn("test",jsontest,-1);
		s2=crdobj.createfn("test",jsontest,-1);
		assertEquals(s1,s);
		assertEquals(s2,snew);
		crdobj.delete("test");	
	}
	
	@Test
	void test_read() {
		String s1="";
		String s="test {\"regno\":\"111111\",\"year\":0,\"name\":\"test\",\"dept\":\"CSE\"}";
		CRDprocessing crdobj=new CRDprocessing("C:\\Java\\defaultpath\\data");
		JSONObject jsontest=new JSONObject();
		jsontest.put("name", "test");
		jsontest.put("regno", "111111");
		jsontest.put("dept", "CSE");
		jsontest.put("year", 0);
		s1=crdobj.createfn("test",jsontest,-1);
		s1=crdobj.read("test");
		assertEquals(s1,s);
		crdobj.delete("test");
		s1=crdobj.read("test");
		s="Key not found!";
		assertEquals(s1,s);
	}
	
	@Test
	void test_delete() {
		CRDprocessing crdobj=new CRDprocessing("C:\\Java\\defaultpath\\data");
		JSONObject jsontest=new JSONObject();
		jsontest.put("name", "test");
		jsontest.put("regno", "111111");
		jsontest.put("dept", "CSE");
		jsontest.put("year", 0);
		crdobj.createfn("test",jsontest,-1);
		String s1="";
		String s="deleted sucessfully";
		s1=crdobj.delete("test");
		assertEquals(s1,s);
		s="Key Doesn't Exist!";
		s1=crdobj.delete("test");
		assertEquals(s1,s);
	}
	
	@Test
	void test_ttl() throws InterruptedException {
		String s1="";
		String s="test {\"regno\":\"111111\",\"year\":0,\"name\":\"test\",\"dept\":\"CSE\"}";
		CRDprocessing crdobj=new CRDprocessing("C:\\Java\\defaultpath\\data");
		JSONObject jsontest=new JSONObject();
		jsontest.put("name", "test");
		jsontest.put("regno", "111111");
		jsontest.put("dept", "CSE");
		jsontest.put("year", 0);
		s1=crdobj.createfn("test",jsontest,1);
		s1=crdobj.read("test");
		assertEquals(s1,s);
		Thread.sleep(2000);
		s1=crdobj.read("test");
		s="Key not found!";
		assertEquals(s1,s);
	}

}

