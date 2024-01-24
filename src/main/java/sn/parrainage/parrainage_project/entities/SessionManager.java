package sn.parrainage.parrainage_project.entities;

public class SessionManager {
    private static Utilisateur currentUserId; // L'ID de l'utilisateur actuellement connecté

    public static void setCurrentUserId(Utilisateur userId) {
        currentUserId = userId;
    }

    public static Utilisateur getCurrentUserId() {
        return currentUserId;
    }
    public static boolean isUserLog()
    {
        return currentUserId !=null;
    }

}

