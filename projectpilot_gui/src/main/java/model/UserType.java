package model;
public enum UserType {
    COLLABORATOR("collaborator"),
    VIEWER("nothing"),
    SCRUM_MASTER("everything");

    private final String PERM;

    UserType(String permission) {
        this.PERM = permission;
    }

    public boolean getPermission(String edit) {
        // Scrum Master permissions: everything
        if (PERM.equals("everything")) {
            return true;
        }
        // Viewer permissions: nothing
        if (PERM.equals(VIEWER.PERM)) {
            return false;
        }
        // Colaborator permissions: varied. Has to check the passed in setter to change.
        if (edit.equals("moveTask") || edit.equals("addTask") || edit.equals("editTask") || edit.equals("deleteTask")) {
            return true;
        }

        return false;
    }

}