package tn.esprit.spring.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.entities.TimesheetPK;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class TimesheetServiceImpl implements ITimesheetService {
	private static final Logger l = Logger.getLogger(TimesheetServiceImpl.class);


	@Autowired
	MissionRepository missionRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;
	@Autowired
	EmployeRepository employeRepository;
	
	public int ajouterMission(Mission mission) {
		missionRepository.save(mission);
		return mission.getId();
	}
    
	public void affecterMissionADepartement(int missionId, int depId) {
		Optional <Mission> mission = missionRepository.findById(missionId) ;
		Optional <Departement> dep = deptRepoistory.findById(depId);
		
		if(mission.isPresent() && dep.isPresent()){
		mission.get().setDepartement(dep.get());
		missionRepository.save(mission.get());}
		
	}

	public void ajouterTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin) {
		TimesheetPK timesheetPK = new TimesheetPK();
		timesheetPK.setDateDebut(dateDebut);
		timesheetPK.setDateFin(dateFin);
		timesheetPK.setIdEmploye(employeId);
		timesheetPK.setIdMission(missionId);
		
		Timesheet timesheet = new Timesheet();
		timesheet.setTimesheetPK(timesheetPK);
		timesheet.setValide(false); //par defaut non valide
		timesheetRepository.save(timesheet);
		
	}

	
	public void validerTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin, int validateurId) {
		l.debug("In valider Timesheet");
		Optional <Employe> validateur = employeRepository.findById(validateurId);
		Optional <Mission> mission = missionRepository.findById(missionId);
		if(validateur.isPresent() && mission.isPresent() ) {
 		if(!validateur.get().getRole().equals(Role.CHEF_DEPARTEMENT)){
			l.info("l'employe doit etre chef de departement pour valider une feuille de temps !");
			return;
		}
 		boolean chefDeLaMission = false;
		for(Departement dep : validateur.get().getDepartements()){
			if(dep.getId() == mission.get().getDepartement().getId()){
				chefDeLaMission = true;
				break;
			}
		}
		
		if(!chefDeLaMission){
			l.info("l'employe doit etre chef de departement de la mission en question");
			return;
		}
		}
		TimesheetPK timesheetPK = new TimesheetPK(missionId, employeId, dateDebut, dateFin);
		Timesheet timesheet =timesheetRepository.findBytimesheetPK(timesheetPK);
		timesheet.setValide(true);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		l.debug("dateDebut : " + dateFormat.format(timesheet.getTimesheetPK().getDateDebut()));
		
	}

	
	public List<Mission> findAllMissionByEmployeJPQL(int employeId) {
		return timesheetRepository.findAllMissionByEmployeJPQL(employeId);
	}

	
	public List<Employe> getAllEmployeByMission(int missionId) {
		return timesheetRepository.getAllEmployeByMission(missionId);
	}

}