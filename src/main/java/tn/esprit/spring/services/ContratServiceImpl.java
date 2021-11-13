package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.repository.ContratRepository;

@Service
public class ContratServiceImpl implements IContratService {


	@Autowired
	ContratRepository contratRepository;
	
	
	public List<Contrat> getAllContrats() {
		return (List<Contrat>) contratRepository.findAll();
	}

	public int ajouterContrat(Contrat contrat) {
		contratRepository.save(contrat);	
		return contrat.getReference();
	}

	public Contrat getContratById(int id){
		return   contratRepository.findByReference(id);
	}

	public void deleteById(int id){
		Contrat contrat = new Contrat();
		contratRepository.delete(getContratById(contrat.getReference()));
	}
}
