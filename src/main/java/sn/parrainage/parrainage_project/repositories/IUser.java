package sn.parrainage.parrainage_project.repositories;

import sn.parrainage.parrainage_project.entities.Utilisateur;

public interface IUser {
    public Utilisateur seConnecter(String login, String password);
    public void saveParrainage(int IdElecteur, int idCandidat);
    public String toString();
    public boolean nbParrain(int electeurId);
    public int countParrainsForCandidat(int candidatId);
}
