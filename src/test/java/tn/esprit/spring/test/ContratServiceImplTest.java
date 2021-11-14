/*package tn.esprit.spring.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.services.ContratServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ContratServiceImplTest {

	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	@InjectMocks
	private ContratServiceImpl contratService;
	  
	 final  Logger log = Logger.getLogger(ContratServiceImplTest.class);
	Contrat contrat1;
	
	/*
	@Before
	@Test
	public void setUp() {
		
		   log.info("debut test ajout");	  
		   Contrat contrat1=new Contrat();
		   contrat1.setDateDebut(new Date());
		   contrat1.setSalaire(300);
		   contrat1.setReference(1);
		   contrat1.setTypeContrat("CDI");
		   assertThat(contrat1).isNotNull()	; 
		   contratRepoistory.save(contrat1);
		   log.info(" test ajout"+contrat1.getReference()+" Contrat terminé");
	}

	  @After public void tearDown() { contratRepoistory.deleteAll();
	  
	  }
	 
	
	@Test
	@Order(1)
	public void RetrieveAllContratTest() {
		List<Contrat> listContrats = contratService.getAllContrats();
	        assertThat(listContrats).isNotNull(); 
	        log.info("RetrieveAllContrat list is here");
	        
	        System.out.println("done 2");
	}
	@Test
	@Order(2)
	public void getContratById() {
		  try{
		Contrat contratretrieved = contratService.getContratById(contrat1.getReference());
		 assertThat(contratretrieved).isNotNull(); 
		   long start = System.currentTimeMillis();
           long elapsedTime = System.currentTimeMillis() - start;
           log.info("Method execution time: " + elapsedTime + " milliseconds.");
           log.info("Contrat By Id list is here");
	
	         
	        }catch(Exception e){
	            System.out.println("contrat2 not founded");
	        }
	        
	        System.out.println("done 3");
		}
	
  @Test
	  @Order(3)
	    public void deleteContratById()  {
		  try {
				contratService.deleteById(contrat1.getReference());
				long start = System.currentTimeMillis();
	            long elapsedTime = System.currentTimeMillis() - start;
	            log.info("Method execution time: " + elapsedTime + " milliseconds.");
	            log.info("get Contrat By Id list is here");
				
	            assertTrue(true);
	   		  log.info("contrat1 deleted with success");
		  }
		  catch (Exception e) {
	            System.out.println("contrat2 not founded");
	        }
		  System.out.println("done 4");
		}
	 
	
}

*/