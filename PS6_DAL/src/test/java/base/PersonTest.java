package base;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;



public class PersonTest {
	
	private static PersonDomainModel per1;
	private static PersonDomainModel perGot;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		
		per1 = new PersonDomainModel();
		per1.setFirstName("Bertram");
		per1.setLastName("Wooster");
		per1.setCity("London");
		per1.setPostalCode(55555);
		per1.setStreet("Street");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		PersonDomainModel per;	
		PersonDAL.deletePerson(per1.getPersonID());
		per = PersonDAL.getPerson(per1.getPersonID());
		assertNull("The Person shouldn't have been in the database",per);	
	}

	@Test
	public void testAddAndGetPerson() {
		PersonDAL.addPerson(per1);
		perGot = PersonDAL.getPerson(per1.getPersonID());
		assertTrue(perGot.getFirstName() == per1.getFirstName());
		assertTrue(perGot.getLastName() == per1.getFirstName());
	
	}
	
	@Test
	public void testDeletePerson(){
		PersonDAL.addPerson(per1);
		perGot = PersonDAL.getPerson(per1.getPersonID());
		assertTrue(perGot.getFirstName() == per1.getFirstName());
		assertTrue(perGot.getLastName() == per1.getFirstName());
		PersonDAL.deletePerson(perGot.getPersonID());
		assertNull(PersonDAL.getPerson(perGot.getPersonID()));
	}
	
	@Test
	public void UpdatePersonTest()
	{		
		PersonDomainModel per;
		final String C_LASTNAME = "Bott";
		
		per = PersonDAL.getPerson(per1.getPersonID());		
		assertNull("The Person shouldn't have been in the database",per);		
		PersonDAL.addPerson(per1);	
		
		per1.setLastName(C_LASTNAME);
		PersonDAL.updatePerson(per1);
		
		per = PersonDAL.getPerson(per1.getPersonID());

		assertTrue("Name Didn't Change",per1.getLastName() == C_LASTNAME);
	}

}
