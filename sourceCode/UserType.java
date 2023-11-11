package sourceCode;
public enum UserType {
    SCRUM_MASTER("everything"),
    COLLABORATOR("collaborator"),
    VIEWER("nothing");

    private final String PERM;

    UserType(String permission) {
        this.PERM = permission;
    }

    public boolean getPermission(String edit) {
        // Scrum Master permissions: everything
        if (PERM.equals(SCRUM_MASTER.PERM)) {
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