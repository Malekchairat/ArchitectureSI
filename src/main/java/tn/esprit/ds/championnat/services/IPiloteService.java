package tn.esprit.ds.championnat.services;

import tn.esprit.ds.championnat.entities.Pilote;

import java.util.List;

public interface IPiloteService {

    String addPilote(Pilote pilote);

    String assignPiloteToEquipe(Long piloteId, Long equipeId);

}
