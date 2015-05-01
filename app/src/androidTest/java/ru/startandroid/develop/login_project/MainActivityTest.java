package ru.startandroid.develop.login_project;

import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.*;

public class MainActivityTest extends TestCase {

    @Test
    public void testThatSucceeds(){
        String url = MainActivity.makeUrl("http://109.234.38.29/scripts/client.php","user","password");
        assertEquals(url, "http://109.234.38.29/scripts/client.php?func=is_user&log=user&pass=password");  
        byte [] res = "fail".getBytes();  
        byte [] res2 = "success".getBytes();  
        assertFalse(MainActivity.isUser(res));
        assertTrue(MainActivity.isUser(res2)); 
    }    
}
