package AllTests;


import com.example.CMS_01.Service.UserServiceImpl;
import com.example.CMS_01.Web.HomePageController;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HomePageControllerTest {

    private HomePageController homePageController;

    @Before
    public void setUp() {
        // Initialize the homePageController before each test
        homePageController = new HomePageController(new UserServiceImpl(null,null));
    }


    @Test
    public void testGetSWE() {
        String viewName = homePageController.getSWE();
        assertEquals("Classes/SWE/Subject1.html", viewName);
    }

    @Test
    public void testGetCpp() {
        String viewName = homePageController.getCpp();
        assertEquals("Classes/C++/Subject2.html", viewName);
    }

    @Test
    public void testGetAlgorithms() {
        String viewName = homePageController.getAlgorithms();
        assertEquals("Classes/Algorithms/Subject3.html", viewName);
    }
}