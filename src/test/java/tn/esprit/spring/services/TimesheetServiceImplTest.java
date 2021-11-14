
package tn.esprit.spring.services;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.config.LoggingAspect;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.TimesheetPK;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.services.ITimesheetService;
import tn.esprit.spring.services.TimesheetServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest

public class TimesheetServiceImplTest {

	
	private static final Logger l = LogManager.getLogger(TimesheetServiceImplTest.class);

	@Autowired
	ITimesheetService timesheetService;

	@Autowired
	IEntrepriseService entrepriseService;

	@Autowired
	IEmployeService employeService;

	@Autowired
	MissionRepository missionRepository;

	@Autowired
	TimesheetRepository timesheetRepository;

	@Autowired
	DepartementRepository depRepository;

	private static Mission mission;
	private static Departement departement;
	private static Employe employe;
	

	@Before
	public void AjouterDepartementEmployeMission() {

		{
			departement = new Departement("Departement GL");
			entrepriseService.ajouterDepartement(departement);

			l.info("depatement" + departement.getName() + "added successfully");

		}

		{
			employe =new Employe("Jridi", "Asma", "asma.jridi@esprit.tn", true, Role.ADMINISTRATEUR);
			employeService.ajouterEmploye(employe);

			l.info("Employe" + employe.getNom() + "added successfully");

		}

		{

			mission = new Mission("Mission de developpement", "Developpement module RH");
			timesheetService
					.ajouterMission(mission);
			l.info("Mission" + mission.getName() + "added successfully");

		}
	}

	@Test
	public void ajouterMissionTest() {

		int mId = timesheetService.ajouterMission(mission);

		assertThat(mId).isNotNull();

		l.info("Mission, " + mission.getName() + " Mission is successfully added ");

	}

	@Test
	public void AjouterTimeSheetTest() {
		try {

			String dateDebutStr = "04-11-2021";
			String dateFinStr = "31-11-2021";

			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

			Date dateDebut = formatter.parse(dateDebutStr);
			Date dateFin = formatter.parse(dateFinStr);

			timesheetService.ajouterTimesheet(mission.getId(), employe.getId(), dateDebut, dateFin);

			assertThat(timesheetRepository
					.findBytimesheetPK(new TimesheetPK(mission.getId(), employe.getId(), dateDebut, dateFin)))
							.isNotNull();

			l.info("TimeSheet de mission : " + mission.getId() + " de l'employe " + employe.getId()
					+ "added successfully");

		} catch (ParseException e) {
			l.error("Cannot be parsed : " + e);
		}
	}

	@Test
	public void affecterMissionADepartementTest() {
		try {
			timesheetService.affecterMissionADepartement(mission.getId(), departement.getId());

			//assertTrue(missionRepository.findById(mission.getId()).get().getDepartement().equals(departement));
			assertEquals(missionRepository.findById(mission.getId()).get().getDepartement().getId(), departement.getId());

			l.info("mission" + mission.getId() + "is successfully affected to departement " + departement.getId());

		} catch (NullPointerException e) { // if idmission or iddepartement does not exist we catch the exception :
											// exception ala ay haja null ky nheb naamdl get
			l.error("Mission or department does not exist  : " + e);
		}
	}

	@Test
	public void findAllMissionByEmployeJPQLTest() {
		try {

			int size = timesheetService.findAllMissionByEmployeJPQL(employe.getId()).size();
			assertEquals(0, size);
			l.info("Missions of the employe" + size);

		} catch (NullPointerException e) {
			l.error("Employe does not exist  : " + e);
		}
	}
	
	
@Test
	public void getAllEmployeByMissionTest() {
	
		List<Employe> empls = (List<Employe>) timesheetService.getAllEmployeByMission(mission.getId());
		assertThat(empls).size().isEqualTo(0);
		l.info(" Employe List  " +empls);

	}
	
	
	

}
