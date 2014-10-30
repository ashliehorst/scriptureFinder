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

import scripturefinderapp.Journal;

/**
 *
 * @author Ashlie
 */
public class ScriptureFinderAppTest {
    Journal journal = new Journal();
    
    @Test
    public void scriptureFinderAppTest() {
        Scripture s = new Scripture();
        s.setBook("Moses");
        Assert.assertEquals(s.getBook(), "Moses");    
    }
    
     @Test
    public void scriptureFinderAppTest2() {
        Scripture s = new Scripture();
        s.setChapter("1");
        Assert.assertEquals(s.getChapter(), 1);     
    }
    
     @Test
    public void scriptureFinderAppTest3() {
        Scripture s = new Scripture();
        s.setStartVerse("39");
        Assert.assertEquals(s.getStartVerse(), 39);      
    }
    
    @Test
    public void scriptureFinderAppTest4() {
        Scripture s = new Scripture();
        s.setEndVerse("22");
        Assert.assertEquals(s.getStartVerse(), 22);      
    }

    @Test
    public void basicContentTest() {
        Entry e = new Entry();
        e.setContent("This is a journal entry");
        Assert.assertEquals(e.getContent(), "This is a journal entry");
    }
 
      @Test
    public void getEmptyTest() {
        Entry e = new Entry();       
        List<Scripture> list = e.getScriptureList();        
        Assert.assertEquals(list, null);
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
