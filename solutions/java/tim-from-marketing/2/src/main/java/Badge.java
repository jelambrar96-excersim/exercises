class Badge {
    public String print(Integer id, String name, String department) {
        String idString = id == null ? "" : ("[" + Integer.toString(id) + "] - ");
        String departmentString = department == null ? " - OWNER" : (" - " + department.toUpperCase());
        return idString + name + departmentString;
    }
}
