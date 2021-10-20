package tn.esprit.spring.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;



import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.services.EmployeServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest()
public class TimesheetSpringBootCoreDataJpaMvcRest1ApplicationTests {

	@Autowired
	EmployeServiceImpl employeServiceImpl1;

	@Autowired
	EmployeRepository employeRepo;
	
     @Test
     public void contextLoads() {
    		//Context Load init for test Methods

     }
     
 final  Logger log = Logger.getLogger(TimesheetSpringBootCoreDataJpaMvcRest1ApplicationTests.class);


     
    
	// http://localhost:8081/SpringMVC/servlet/ajouterEmployer

    @Test
    public void testCreateEmployee() {
    	Employe employee = new Employe();
        employee.setEmail("employe1@gmail.com");
        employee.setNom("devEmp");
        employee.setPrenom("devEmp1");
        employee.setRole(Role.INGENIEUR);
        employee.setActif(true);
        
        assertNotNull(employeServiceImpl1.ajouterEmploye(employee));
      log.info("Employee added with success");
       
   
    }
    
    @Test
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
    public void testGetEmployeById() {
    	Employe employee = new Employe();
        employee.setId(5);
        
        assertEquals("devEmp1", 
        		employeServiceImpl1.getEmployePrenomById(5)
        		);
        log.info( "Employe Prenom : "+employeServiceImpl1.getEmployePrenomById(5));
    }

    @Test
    public void testGetNombreEmploye() {
		assertNotEquals(0, employeServiceImpl1.getNombreEmployeJPQL());

		log.info("number employee : "+employeServiceImpl1.getNombreEmployeJPQL());
    }

  
}