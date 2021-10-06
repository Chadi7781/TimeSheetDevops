package tn.esprit.spring.test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import static org.junit.Assert.assertNotEquals;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.TimesheetSpringBootCoreDataJpaMvcRest1Application;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;



@RunWith(SpringRunner.class)
@SpringBootTest (classes={TimesheetSpringBootCoreDataJpaMvcRest1Application.class})
public class TimesheetSpringBootCoreDataJpaMvcRest1ApplicationTests {
 
	@Autowired 
	IEmployeService em;
	@Autowired
	IEntrepriseService es;
	
	@Autowired
	EntrepriseRepository er;
	
	@Autowired
	DepartementRepository dr;
	
	@Test
	@Order(1)
	 public void ajouterEntrepriseTest() {
		Entreprise e1=new Entreprise("SSII Consulting","Cite El Ghazela");

		int eens1=es.ajouterEntreprise(e1);
		Assert.assertNotNull(eens1);
		System.out.println("done 1");
	 }

	@Test
	@Order(2)

	public void ajouterDepartementTest() {
		Departement dep2=new Departement("RH" );

		int dens2=es.ajouterDepartement(dep2);
		Assert.assertNotNull(dens2);
		System.out.println("done 2");
	}
	
	@Test
	@Order(3)

	public void affecterDepartementAEntrepriseTest() {
		
		
		Integer depid  =1;
		Integer entrid  =1;
		es.affecterDepartementAEntreprise(depid,entrid);
		List<String> deps=(List<String>)es.getAllDepartementsNamesByEntreprise(entrid);
		
		
		assertThat(deps).size().isGreaterThan(0);
		System.out.println("done 3");

	}

	
	@Test
	@Order(4)

public void getAllDepartementsNamesByEntrepriseTest() {
	List<String> deps=(List<String>) es.getAllDepartementsNamesByEntreprise(1);
	assertThat(deps).size().isGreaterThan(0);
	System.out.println("done 4");

}
	 
	
	

	@Test
	@Order(5)

	public void getEntrepriseByIdTest() {
		Integer identreprise=1;
		
		
		Assert.assertNotNull(es.getEntrepriseById(identreprise));

		System.out.println("done 5");

	}
	
	@Test
	@Order(6)

	public void deleteDepartementById() {
		Integer iddep=1;
		boolean existBeforeDelete =dr.findById(iddep) .isPresent();

		es.deleteDepartementById(iddep);
		boolean existAfterDelete =dr.findById(iddep) .isPresent();
		
		assertTrue(existBeforeDelete);
		assertFalse(existAfterDelete);
		System.out.println("done 6");

		
	}
	
	@Test
	@Order(7)

	public void deleteEntrepriseById() {
		Integer identreprise=1;
		boolean existBeforeDelete =er.findById(identreprise) .isPresent();

		es.deleteEntrepriseById(identreprise);
		boolean existAfterDelete =er.findById(identreprise) .isPresent();
		
		assertTrue(existBeforeDelete);
		assertFalse(existAfterDelete);
		System.out.println("done 7");


		
	}
	
	

}