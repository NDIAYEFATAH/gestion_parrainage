package sn.parrainage.parrainage_project.entities;



public class Utilisateur {
    private int id;
    private String nom,prenom,login,password;
    private int actived;
    private Role role;

    public Utilisateur() {
    }

    public Utilisateur(int id, String nom, String prenom, String login, String password, int actived, Role role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.actived = actived;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getActived() {
        return actived;
    }

    public void setActived(int actived) {
        this.actived = actived;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
