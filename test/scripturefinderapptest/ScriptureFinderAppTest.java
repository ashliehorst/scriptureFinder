/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scripturefinderapptest;

import java.util.List;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import scripturefinderapp.Scripture;
import scripturefinderapp.Entry;
import scripturefinderapp.Date;
import scripturefinderapp.Entry2;
import scripturefinderapp.File;
import class3.ScriptureFinderApp;

/**
 *
 * @author Ashlie
 */
public class ScriptureFinderAppTest {
    
    public ScriptureFinderAppTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void scriptureFinderAppTest() {
        Scripture s = new Scripture("Moses", 1, 39);
        Assert.assertEquals(s.getBook(), "Moses");
        Assert.assertEquals(s.getChapter(), 1);
        Assert.assertEquals(s.getVerse(), 39);      
    }
    
    @Test
    public void scriptureFinderAppTest2() {
        Entry finder = new Entry();
        Scripture s = finder.findScripture("I like Proverbs 3:5-6");
        
        Assert.assertEquals(s.getBook(), "Proverbs");
        Assert.assertEquals(s.complete(), "Proverbs 3:5-6");
    }
    
    @Test
    public void scriptureFinderAppTest3() {
        Entry finder2 = new Entry();
        Scripture s = finder2.findScripture("2 Kings 5:11");
        
        Assert.assertEquals(s.getBook(), "2 Kings");
        Assert.assertEquals(s.complete(), "2 Kings 5:11");
    }
    
    @Test
    public void scriptureFinderAppTest4() {
        Entry finder2 = new Entry();
        Scripture s = finder2.findScripture("Doctrine and Covenants 1:1");

        Assert.assertEquals(s.getBook(), "Doctrine and Covenants");
        Assert.assertEquals(s.complete(), "Doctrine and Covenants 1:1");
    }
   
    @Test
    public void scriptureFinderAppTest5() {
        Entry finder2 = new Entry();
        Scripture s = finder2.findScripture("D&C 1:1");

        Assert.assertEquals(s.getBook(), "D&C");
        Assert.assertEquals(s.complete(), "D&C 1:1");
    }
    
    @Test
    public void scriptureFinderAppTest6() {
        Scripture s = new Scripture("Alma", 0, 39);
        Assert.assertEquals(s.getBook(), "Alma");
        Assert.assertEquals(s.getChapter(), "Error: Not valid chapter number");
        Assert.assertEquals(s.getVerse(), 39);      
    }
    
    @Test
    public void scriptureFinderAppTest7() {
        Scripture s = new Scripture("Alma", 1, 0);
        Assert.assertEquals(s.getBook(), "Alma");
        Assert.assertEquals(s.getChapter(), 1);
        Assert.assertEquals(s.getVerse(), "Error: Not valid chapter number");      
    }
  
    @Test
    public void scriptureFinderAppTest8() {
        Scripture s = new Scripture("Ashlie", 1, 39);
        Assert.assertEquals(s.getBook(), "Error: Not valid book");
        Assert.assertEquals(s.getChapter(), 1);
        Assert.assertEquals(s.getVerse(), 39);      
    }
    
    @Test
    public void scriptureFinderAppTest9() {
        Date d = new Date("February", 1, 1993);
        Assert.assertEquals(d.getMonth(), "February");
        Assert.assertEquals(d.getDay(), 1);
        Assert.assertEquals(d.getYear(), 1993);      
    } 
    
    @Test
    public void scriptureFinderAppTest10() {
        Date d = new Date("Ashlie", 1, 1993);
        Assert.assertEquals(d.getMonth(), "Error: Not a valid year");
        Assert.assertEquals(d.getDay(), 1);
        Assert.assertEquals(d.getYear(), 1993);      
    } 
    
    @Test
    public void scriptureFinderAppTest11() {
        Date d = new Date("February", 0, 1993);
        Assert.assertEquals(d.getMonth(), "February");
        Assert.assertEquals(d.getDay(), "Error: Not a valid day");
        Assert.assertEquals(d.getYear(), 1993);      
    } 
    
    @Test
    public void scriptureFinderAppTest12() {
        Date d = new Date("February", 1, 0);
        Assert.assertEquals(d.getMonth(), "February");
        Assert.assertEquals(d.getDay(), 1);
        Assert.assertEquals(d.getYear(), "Error: Not a valid year");      
    } 
    
    @Test
    public void scriptureFinderAppTest13() {
        Date d = new Date(9, 1, 1994);
        Assert.assertEquals(d.getMonth(), 9);
        Assert.assertEquals(d.getDay(), 1);
        Assert.assertEquals(d.getYear(), 1994);      
    } 
    
    @Test
    public void scriptureFinderAppTest14() {
        Date d = new Date(98, 1, 1994);
        Assert.assertEquals(d.getMonth(), "Error: Not a valid month");
        Assert.assertEquals(d.getDay(), 1);
        Assert.assertEquals(d.getYear(), 1994);      
    } 
       
    @Test
    public void scriptureFinderAppTest15() {
        Entry finder = new Entry();
        Scripture s = finder.findScripture("You like Malacahi chapter 4?");
        
        Assert.assertEquals(s.getBook(), "Malacahi");
        Assert.assertEquals(s.complete(), "Malachai 4");
    }
    
    @Test
    public void scriptureFinderAppTest16() {
        Date d = new Date(1, 1, 1991);
        d.setMonth(2);
        d.setDay(2);
        d.setYear(1994);
        Assert.assertEquals(d.getMonth(), 2);
        Assert.assertEquals(d.getDay(), 2);
        Assert.assertEquals(d.getYear(), 1994);      
    } 
    
    @Test
    public void scriptureFinderAppTest17() {
        File f = new File("file.docx"); 
        Assert.assertEquals(f.importFile("file.docx"), "file.docx");      
    }  
  
    @Test
    public void basicContentTest() {
        Entry2 e = new Entry2();
        e.setContent("This is a journal entry");
        Assert.assertEquals(e.getContent(), "This is a journal entry");
    }
    
    @Test
    public void constructorTest() {
        Entry2 e = new Entry2("this is the content", "2014-09-29");
        Assert.assertEquals(e.getContent(), "this is the content");
        Assert.assertEquals(e.getDate(), "2014-09-29");
    }
    
    @Test
    public void getScripturesTest() {
        Entry2 e = new Entry2("I like Moses 1:39", "2014-09-29");
        
        List<Scripture> list = e.getScripture();
        
        Assert.assertEquals(list.size(), 1);
        Assert.assertEquals(list.get(0).toString(), "Moses 1:39");
        Assert.assertEquals(list.get(0).getBook(), "Moses");
        Assert.assertEquals(list.get(0).getChapter(), 1);
        Assert.assertEquals(list.get(0).getVerse(), 39);
    }
    
      @Test
    public void getEmptyScripturesTest() {
        Entry2 e = new Entry2("I like food", "2014-09-29");
        
        List<Scripture> list = e.getScripture();
        
        Assert.assertEquals(list, null);
      //  Assert.assertEquals(list.size(), 0);
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
}
