package tn.esprit.spring.test;

import org.junit.After;
import org.junit.Before;

public abstract class AbstractBaseTest {

	  @Before
	  public final void baseSetUp() { // or any other meaningful name
	    System.out.println("AbstractBaseTest.setUp");
	  }

	  @After
	  public final void baseTearDown() { // or any other meaningful name
	    System.out.println("AbstractBaseTest.tearDown");
	  }
	}