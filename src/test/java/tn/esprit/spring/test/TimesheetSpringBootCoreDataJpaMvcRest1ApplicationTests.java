package tn.esprit.spring.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.services.IContratService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.ContratServiceImpl;
import tn.esprit.spring.services.IContratService;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;

import org.junit.Test;


@RunWith(SpringRunner.class)
@SpringBootTest()
public class TimesheetSpringBootCoreDataJpaMvcRest1ApplicationTests {
	private final static Logger logger = LogManager.getLogger(ContratServiceImpl.class);
	@Autowired 
	IEmployeService em;
	@Autowired
	IEntrepriseService es;
	@Autowired
	IContratService cs;
	@Autowired
	ContratRepository cr;
	
	@Autowired
	EntrepriseRepository er;
	
	@Autowired
	DepartementRepository dr;
  
	@Test
	@Order(1)
    public void testAjouterContrat() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse("2020-10-30");
        Contrat contrat = new Contrat(1,date,"yasmine", 700);
        int AjoutContrat = cs.ajouterContrat(contrat);
        long start = System.currentTimeMillis();
        long elapsedTime = System.currentTimeMillis() - start;
        logger.info("Method execution time: " + elapsedTime + " milliseconds.");
        logger.info("Ajout Contrat list is here");
    }
	
    @Test
    @Order(2)
    public void testRetrieveAllContrat() {
        List<Contrat> listEmployes = cs.getAllContrats();
        long start = System.currentTimeMillis();
        long elapsedTime = System.currentTimeMillis() - start;
        logger.info("Method execution time: " + elapsedTime + " milliseconds.");
        logger.info("RetrieveAllContrat list is here");
    }
    @Test
    @Order(2)
    public void getContratById() throws ParseException {
        try{
            Contrat Contratretrieved = cs.getContratById(1);
            long start = System.currentTimeMillis();
            long elapsedTime = System.currentTimeMillis() - start;
            logger.info("Method execution time: " + elapsedTime + " milliseconds.");
            logger.info("Contrat By Id list is here");
        }catch(Exception e){
            System.out.println("get contrat with reference 2 not founded");
        }
    }
    
    @Test
    @Order(3)
    public void deleteContratById() throws ParseException {
        try {
            cs.deleteById(1);
            long start = System.currentTimeMillis();
            long elapsedTime = System.currentTimeMillis() - start;
            logger.info("Method execution time: " + elapsedTime + " milliseconds.");
            logger.info("get Contrat By Id list is here");
        } catch (Exception e) {
            System.out.println("contrat with reference 2 not founded");
        }
    }

}
