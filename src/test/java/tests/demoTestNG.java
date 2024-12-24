package tests;

import org.testng.annotations.*;

public class demoTestNG {

    @BeforeClass
    public void beforeClass(){
        System.out.println("Jednom pre svih testova!");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("Jednom pre svakog testa!");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("Jednom nakon svakog testa!");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("Jednom nakon svih testova!");
    }

    @Test
    public void test1(){
        System.out.println("Ovo je test1!");
    }

    @Test
    public void test2(){
        System.out.println("Ovo je test2!");
    }

}
