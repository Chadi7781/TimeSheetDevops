package tn.esprit.spring.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.services.EmployeServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;


@RunWith(SpringRunner.class)
@SpringBootTest()
public class TimesheetSpringBootCoreDataJpaMvcRest1ApplicationTests {
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	EmployeRepository employeRepoistory;
	@Autowired
	EmployeServiceImpl employeServiceImpl;
    private static final Logger l = LogManager.getLogger(TimesheetSpringBootCoreDataJpaMvcRest1ApplicationTests.class);

//yasmine
	@Test
	public void TestAjouterContrat() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d = dateFormat.parse("2021-10-07"); //date contrat 07 october 2021
		Contrat contrat = new Contrat(d, "CDD", 500); //type de contrat cdd et salaire 500
		int idcontrat = employeServiceImpl.ajouterContrat(contrat);

		assertEquals(2, idcontrat);
	}

//yasmine
	@Test
	public void testAffecterContratAEmployet() {

		employeServiceImpl.affecterContratAEmploye(1, 5); //contrat id = 1, employe id= 5
		Employe employe = employeRepoistory.findById(1).get(); 
		int idContrat = employe.getContrat().getReference();
		assertEquals(4, idContrat);

	}
	
//yasmine
	@Test
	public void TestDeleteContratById() {
		Contrat contrat = contratRepoistory.findById(1).get();
		
		
		if(contrat.getReference()!=0) {
	    	
			employeServiceImpl.deleteContratById(1);
			assertThat(employeRepoistory.existsById(1)).isFalse();//confirm that employe has beeen deleted
	    	 }
	    	 else {
	    		 assertNull(contrat);
	    	 }
	
	}

	
//yasmine
	@Test
	public void TestdeleteAllContratJPQL() {
		employeServiceImpl.deleteAllContratJPQL();
		List<Contrat> contrat = (List<Contrat>) contratRepoistory.findAll();
		assertEquals(0, contrat.size());
	}

}
