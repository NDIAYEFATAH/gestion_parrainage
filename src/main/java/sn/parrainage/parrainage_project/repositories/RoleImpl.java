package sn.parrainage.parrainage_project.repositories;

import sn.parrainage.parrainage_project.DBConnection;
import sn.parrainage.parrainage_project.entities.Role;

import java.sql.ResultSet;

public class RoleImpl implements IRole{
    private DBConnection db = new DBConnection();
    private ResultSet rs;
    @Override
    public Role getRoleById(int id) {
        String sql = "SELECT * FROM role WHERE id = ?";
        Role role = null;
        try {
            db.initPrepar(sql);
            db.getPstm().setInt(1, id);
            rs = db.executeSelect();
            if(rs.next()){
                role = new Role(rs.getInt("id"), rs.getString("name"), rs.getInt("etat")
                );
            }
            db.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return role;
    }
}
