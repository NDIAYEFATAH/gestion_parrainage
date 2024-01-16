package sn.parrainage.parrainage_project.entities;

import sn.parrainage.parrainage_project.DBConnection;
import sn.parrainage.parrainage_project.repositories.IRole;
import sn.parrainage.parrainage_project.repositories.IUser;
import sn.parrainage.parrainage_project.repositories.RoleImpl;

import java.sql.ResultSet;

public class UtilisateurImpl implements IUser {
    private DBConnection db = new DBConnection();
    private ResultSet rs;
    @Override
    public Utilisateur seConnecter(String login, String password) {
        String sql = "SELECT * FROM user WHERE login = ? AND password = ?";
        Utilisateur user =null;
        try {
            db.initPrepar(sql);

            db.getPstm().setString(1,login);
            db.getPstm().setString(2,password);

            rs = db.executeSelect();
            if (rs.next()){
                user = new Utilisateur();
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setLogin(rs.getString("login"));
//                user.setPassword(rs.getString("password"));
                user.setActived(rs.getInt("actived"));
                IRole iRole = new RoleImpl();
                int idRole = rs.getInt("profil");
                Role role = iRole.getRoleById(idRole);
                user.setProfile(role);
            }
            db.closeConnection();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return user;
    }
}
