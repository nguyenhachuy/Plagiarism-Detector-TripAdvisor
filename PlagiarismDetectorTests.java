import jdk.internal.jline.internal.TestAccessible;

public class PlagiarismDetectorTests {
    @BeforeClass 
    public static void runOnceBeforeClass() {
        //Do init
    }

    // Run once, e.g close connection, cleanup
    @AfterClass
    public static void runOnceAfterClass() {
        System.out.println("@AfterClass - runOnceAfterClass");
    }

    @Test
    public static void test1() {
        
    }
    
}