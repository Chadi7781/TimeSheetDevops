package tn.esprit.spring.test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import static org.junit.Assert.assertNotEquals;

import org.apache.log4j.Logger;
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
import tn.esprit.spring.services.EntrepriseServiceImpl;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;



@RunWith(SpringRunner.class)
@SpringBootTest 
public class EntrepriseImplTest {
	private static final Logger l = Logger.getLogger(EntrepriseServiceImpl.class);

 
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
		
		assertThat(eens1).isNotNull();  
	

		 l.info("Entreprise "+e1.getName()+" successfully added ");
		System.out.println("done 1");
	 }

	@Test
	@Order(2)

	public void ajouterDepartementTest() {
		Departement dep2=new Departement("RH" );

		int dens2=es.ajouterDepartement(dep2);
		assertThat(dens2).isNotNull();  
		 l.info("Departement"+dep2.getName()+" successfully added");
		System.out.println("done 2");
	}

	@Test
	@Order(3)
	public void affecterDepartementAEntrepriseTest() {
		
		Entreprise e1=new Entreprise("infoo","Cite El Ghazela");
		Departement dep2=new Departement("RH2" );

		es.ajouterEntreprise(e1);
		es.ajouterDepartement(dep2);
		
		es.affecterDepartementAEntreprise(dep2.getId(),e1.getId());
		List<String> deps=(List<String>)es.getAllDepartementsNamesByEntreprise(e1.getId());
		
		
		assertThat(deps).size().isPositive();
		
		 l.info(dep2.getName()+" departement successfully affected to  "+dep2.getName()+" entreprise.");

		System.out.println("done 3");

	}


	@Test
	@Order(4)

public void getAllDepartementsNamesByEntrepriseTest() {
		Entreprise e1=new Entreprise("infoo","Cite El Ghazela");
		Departement dep2=new Departement("RH2" );

		es.ajouterEntreprise(e1);
		es.ajouterDepartement(dep2);
		es.affecterDepartementAEntreprise(dep2.getId(),e1.getId());
		
	List<String> deps=(List<String>) es.getAllDepartementsNamesByEntreprise(e1.getId());
	assertThat(deps).size().isPositive();
	 l.info(" departement List  " +deps);

	System.out.println("done 4");

}
	 
	


	@Test
	@Order(5)

	public void getEntrepriseByIdTest() {
		Entreprise e1=new Entreprise("SSII Consulting","Cite El Ghazela");

		es.ajouterEntreprise(e1);
		Assert.assertNotNull(es.getEntrepriseById(e1.getId()));
		 l.error("Entreprise not found");

		System.out.println("done 5");

	}
	
	@Test
	@Order(6)

	public void deleteDepartementById() {
		Departement dep2=new Departement("RH" );
		es.ajouterDepartement(dep2);

		boolean existBeforeDelete =dr.findById(dep2.getId()) .isPresent();

		es.deleteDepartementById(dep2.getId());
		boolean existAfterDelete =dr.findById(dep2.getId()) .isPresent();
		
		assertTrue(existBeforeDelete);
		assertFalse(existAfterDelete);
		 l.info("Departement successfully Deleted");

		System.out.println("done 6");

		
	}
	
	@Test
	@Order(7)

	public void deleteEntrepriseById() {
		Entreprise e1=new Entreprise("SSII Consulting","Cite El Ghazela");
		es.ajouterEntreprise(e1);

		boolean existBeforeDelete =er.findById(e1.getId()) .isPresent();

		es.deleteEntrepriseById(e1.getId());
		boolean existAfterDelete =er.findById(e1.getId()) .isPresent();
		
		assertTrue(existBeforeDelete);
		assertFalse(existAfterDelete);
		 l.info("Entreprise successfully deleted");

		System.out.println("done 7");


		
	}
	
 

}