package form;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import model.BasicCv;
import model.Chronological;
import model.Combined;
import model.Functional;
import model.Item;
import model.Section;

public class DefferencesTest {

	@Test
	public void test() {
		BasicCv cv1 = new Functional("func", "6985454", "232394556", "Ioanninwn 322", "dims@sdsd.com");
		BasicCv cv2 = new Chronological("func", "6985454", "232394556", "Ioanninwn 322", "dims@sdsd.com");
		BasicCv cv3 = new Combined("func", "6985454", "232394556", "Ioanninwn 322", "dims@sdsd.com");
		cv1.getSections().get(0).getItems().add(new Item("Java specialist with 3 years experience"));
		cv2.getSections().get(0).getItems().add(new Item("Java specialist with 3 years experience"));
		cv3.getSections().get(0).getItems().add(new Item("Java specialist with 3 years experience"));
		
		Item java = new Item("Java");
		Item android = new Item("Android");
		
		Item design = new Item("Design");
		Item test = new Item("Test");
		Item classes = new Item("Classes");
		Item person = new Item ("Person");
		Item cat = new Item ("Cat");
		
		classes.getItems().add(person);
		classes.getItems().add(cat);
		test.getItems().add(classes);
		design.getItems().add(classes);
		
		java.getItems().add(design);
		java.getItems().add(test);
		
		android.getItems().add(design);
		android.getItems().add(test);
		
		//cv1.getSections().get(1).getItems().add(java);
		cv1.getSections().get(1).getItems().add(android);
		
		cv2.getSections().get(1).getItems().add(java);
		cv2.getSections().get(1).getItems().add(android);
		
		cv3.getSections().get(1).getItems().add(java);
		cv3.getSections().get(1).getItems().add(android);
		

		Item company1 = new Item("ComanyA");
		Item company2 = new Item("ComapnyB");
		
		
		classes.getItems().add(person);
		classes.getItems().add(cat);
		test.getItems().add(classes);
		design.getItems().add(classes);
		
		company1.getItems().add(design);
		company1.getItems().add(test);
		
		company2.getItems().add(design);
		company2.getItems().add(test);
		
		cv1.getSections().get(2).getItems().add(company1);
		//cv1.getSections().get(2).getItems().add(company2);
		
		cv2.getSections().get(2).getItems().add(company1);
		cv2.getSections().get(2).getItems().add(company2);
		cv3.getSections().get(2).getItems().add(company1);
		cv3.getSections().get(2).getItems().add(company2);
		
		BasicCv difs = cv1.differencesCV(cv2);
		
		cv1.merge(difs);

		for(int i=0; i<cv1.getSections().size();i++){
			Section s1 = cv1.getSections().get(i);
			Section s2 = cv2.getSections().get(i);
			
			for(Item item : s2.getItems()){
				if(!s1.getItems().contains(item)){
					fail("Fail on merge");
				}
			}
		}
		
	}

}
