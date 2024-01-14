package sn.parrainage.parrainage_project.repositories;

import sn.parrainage.parrainage_project.entities.Utilisateur;

public interface IUser {
    public Utilisateur seConnecter(String login, String password);
}
