package form;

import static org.junit.Assert.fail;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import model.BasicCv;
import model.Functional;
import model.ImportHelper;
import model.ImportLatexhelper;
import model.Item;

public class ExportImportFunctional {

	@Test
	public void test() throws Exception {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		BasicCv cv = new Functional("testFunctional", "6985454555", "212255", "Ioaninnon 121", "adsa@gmoil.com");
		
		cv.getSections().get(0).getItems().add(new Item("Java specialist with 3 years experience"));
		
		Item java = new Item("Java");
		Item android = new Item("Android");
		
		cv.getSections().get(1).getItems().add(java);
		cv.getSections().get(1).getItems().add(android);
		
		cv.getSections().get(2).getItems().add(new Item("CompanyA",format.parse("2015-01-01")));
		cv.getSections().get(2).getItems().add(new Item("CompanyB",format.parse("2016-01-01")));
		
		cv.getSections().get(3).getItems().add(new Item("Ptyhion-University of Ioannina",format.parse("2015-01-01")));

		cv.getSections().get(4).getItems().add(new Item("Course A",format.parse("2015-01-01")));
		cv.getSections().get(4).getItems().add(new Item("Course B",format.parse("2016-01-01")));
		
		cv.getSections().get(5).getItems().add(new Item("Info 1"));
		cv.getSections().get(5).getItems().add(new Item("Info 2"));
		
		cv.getSections().get(6).getItems().add(new Item("Football"));
		cv.getSections().get(6).getItems().add(new Item("Basketball"));
		
		cv.latex();
		BasicCv cv2 = ImportLatexhelper.readLatex("testFunctional.tex");
		
		
		if(!cv.equals(cv2)){
			fail("Should be true");
		}
		
		
	}
	
	
	@Test
	public void test2() throws Exception {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		BasicCv cv = new Functional("testFunctional", "6985454555", "212255", "Ioaninnon 121", "adsa@gmoil.com");
		
		cv.getSections().get(0).getItems().add(new Item("Java specialist with 3 years experience"));
		
		Item java = new Item("Java");
		Item android = new Item("Android");
		
		cv.getSections().get(1).getItems().add(java);
		cv.getSections().get(1).getItems().add(android);
		
		cv.getSections().get(2).getItems().add(new Item("CompanyA",format.parse("2015-01-01")));
		cv.getSections().get(2).getItems().add(new Item("CompanyB",format.parse("2016-01-01")));
		
		cv.getSections().get(3).getItems().add(new Item("Ptyhion-University of Ioannina",format.parse("2015-01-01")));

		cv.getSections().get(4).getItems().add(new Item("Course A",format.parse("2015-01-01")));
		cv.getSections().get(4).getItems().add(new Item("Course B",format.parse("2016-01-01")));
		
		cv.getSections().get(5).getItems().add(new Item("Info 1"));
		cv.getSections().get(5).getItems().add(new Item("Info 2"));
		
		cv.getSections().get(6).getItems().add(new Item("Football"));
		cv.getSections().get(6).getItems().add(new Item("Basketball"));
		
		cv.export();
		BasicCv cv2 = ImportHelper.readText("testFunctional.txt");
		
		
		if(!cv.equals(cv2)){
			fail("Should be true");
		}
		
		
	}

}
