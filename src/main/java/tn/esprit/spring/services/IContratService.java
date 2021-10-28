package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Contrat;

public interface IContratService {
	public List<Contrat> getAllContrats();
	public int ajouterContrat(Contrat contrat);
	public Contrat getContratById(int id);
	public void deleteById(int id);

}
