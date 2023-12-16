package info.hccis.performance;

import info.hccis.SoccorJersey.bo.SoccorJerseValidationBO;
import info.hccis.SoccorJersey.jpa.entity.SoccorJerseys;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


class SoccorJerseyApplicationTests {
    public SoccorJerseyApplicationTests() {
    }

    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }
	@Test
	void SoccorJersey_Test_JerseyType() {
        SoccorJerseys sj = new SoccorJerseys(1, 3, "Argentina", "Paris Saint-Germain", "Home", "Lionel Messi");
        assertEquals("Home", sj.getNameType());
	}
    @Test
    void SoccorJersey_Validation_Testing_ID() {
        SoccorJerseValidationBO sjvBO = new SoccorJerseValidationBO();
        SoccorJerseys sj = new SoccorJerseys(0, 3, "Argentina", "Paris Saint-Germain", "Home", "Lionel Messi");
        boolean ouput = sjvBO.isValid(sj);
        assertEquals(false, ouput);
    }


}
