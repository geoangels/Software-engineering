package form;

import static org.junit.Assert.fail;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import model.Item;

public class DateOrderTest {

	@Test
	public void test() throws ParseException {
		
		ListableForm form = new ListableForm();
		
		ArrayList<Item> items = new ArrayList<>();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Item it1 = new Item("aaa", format.parse("2017-10-10"));
		items.add(it1);
		
		Date date = format.parse("2018-10-10");
		
		if(!form.checkDate(date, items)){
			fail("should be true");
		}
		
		Item it2 = new Item("bbb", format.parse("2017-11-11"));
		items.add(it1);
		
		if(!form.checkDate(date, items)){
			fail("should be true");
		}
	}
	
	
	@Test
	public void test2() throws ParseException {
		
		ListableForm form = new ListableForm();
		
		ArrayList<Item> items = new ArrayList<>();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Item it1 = new Item("aaa", format.parse("2017-10-10"));
		items.add(it1);
		
		Date date = format.parse("2015-10-10");
		
		if(form.checkDate(date, items)){
			fail("should be false");
		}
		
		Item it2 = new Item("bbb", format.parse("2017-11-11"));
		items.add(it1);
		
		if(form.checkDate(date, items)){
			fail("should be false");
		}
	}
	
	@Test
	public void test3() throws ParseException {
		
		ListableForm form = new ListableForm();
		
		ArrayList<Item> items = new ArrayList<>();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Date date = format.parse("2015-10-10");
		
		if(!form.checkDate(date, items)){
			fail("should be true");
		}

		
		if(!form.checkDate(date, items)){
			fail("should be true");
		}
	}

}
