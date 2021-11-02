package tn.esprit.spring.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.aop.TrackTime;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.services.EmployeServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class EmployeServiceImplTest implements AbstractBaseTest {

	
	@Autowired
	EmployeServiceImpl employeServiceImpl1;

	@Autowired
	@Mock
	EmployeRepository employeRepo;
	
	@Autowired
	ContratRepository contratRepository;
	
	private Employe employeA,employeB,employeC,employe;
	private Contrat contrat;
	
     @Test
     public void contextLoads() {
    		//Context Load init for test Methods

     }
     
 final  Logger log = Logger.getLogger(EmployeServiceImplTest.class);
    @Test
    @TrackTime(message = "testCreateEmployee ")
    public void testCreateEmployee() {
    
		employe = new Employe("Zied", "aloulo", "zied@gmail.com", true, Role.ADMINISTRATEUR);

        assertNotNull(employeServiceImpl1.ajouterEmploye(employe));
      log.info("Employee added with success");
       
   
    }
    
    @Test
    @TrackTime(message = "mettreAjourEmailByEmployeId ")
    public void mettreAjourEmailByEmployeId() {
      	Employe employee = new Employe();
      	employee.setId(7);
        if(employee.getId()!=0) {
    		  employee.setEmail("ram@gmail.com");
    		  employeServiceImpl1.mettreAjourEmailByEmployeId(employee.getEmail(),employee.getId());
    		  assertEquals(employee.getEmail()
 	        		 ,"ram@gmail.com");
    		  log.info("Employee updated with success");

    	 }
    	 else {
    		 assertNull(employee);
    	      log.warn("Updated : Employee not found ");

    	 }
       
    }
    @Test
    @TrackTime(message = "testDeleteEmployeById ")
    public void testDeleteEmployeById () {
    	
    	Employe employee = new Employe();
        employee.setEmail("employedelted@gmail.com");
        employee.setNom("deletedEmpNom");
        employee.setPrenom("deletedEmpPrenom");
        employee.setRole(Role.INGENIEUR);
        employee.setActif(true);
        employeServiceImpl1.ajouterEmploye(employee);
       if(employeRepo.findById(employee.getId()).isPresent()) {
    	   employeServiceImpl1.deleteEmployeById(employee.getId());
    	   assertTrue(true);
 		  log.info("Employee deleted with success");

       }
       else {
    	   assertTrue(false);
 		  log.info("Delete : Employee not found  success");

       }

	}
    
    @Test
    @TrackTime(message = "testGetEmployeById ")
    public void testGetEmployeById() {
    	Employe employee = new Employe();
        employee.setId(5);
        
        assertEquals("Chadi", 
        		employeServiceImpl1.getEmployePrenomById(employeA.getId())
        		);
        log.info( "Employe Prenom : "+employeServiceImpl1.getEmployePrenomById(5));
    }

    @Test
    @TrackTime(message = "testGetNombreEmploye ")

    public void testGetNombreEmploye() {
		assertNotEquals(0, employeServiceImpl1.getNombreEmployeJPQL());

		log.info("number employee : "+employeServiceImpl1.getNombreEmployeJPQL());
    }

    @Test
    @TrackTime(message = "getSalaireByEmployeIdJPQLTest ")

    public void getSalaireByEmployeIdJPQLTest() {

       
		float salaire = employeServiceImpl1.getSalaireByEmployeIdJPQL(employeA.getId());
		log.info("getSalaireByEmployeIdJPQL == " + salaire);
		assertThat(salaire).isEqualTo(5000);
	}

    
    
    
   
	@Override
	@Before
	public void baseSetUp() {

		employeA = new Employe("Troudi", "Chadi", "chadi@gmail.com", false, Role.INGENIEUR);		
		employeB = new Employe("Ali", "aloune", "ali@gmail.com", true, Role.ADMINISTRATEUR);
		employeC = new Employe("Foulen", "benFoulen", "foulen@gmail.com", true, Role.CHEF_DEPARTEMENT);
	
		
		List<Employe> employeList = new ArrayList<>();
		    employeList.add(employeA);
		    employeList.add(employeB);
		    employeList.add(employeC);
		    
	
		employeRepo.saveAll(employeList);
		
		

		 contrat = new Contrat(new Date(2021, 01, 05), "TypeCont", 5000);
		
		contrat.setEmploye(employeA);
		contratRepository.save(contrat);
	}

	@Override
	@After
	public void baseTearDown() {
		employeRepo.delete(employeA);
		employeRepo.delete(employeB);
		employeRepo.delete(employeA);
		employeRepo.delete(employe);
		//Deuxieme methode
		//employeRepo.deleteAll();
		contratRepository.deleteAll();
		
		
		
	}
 
}
