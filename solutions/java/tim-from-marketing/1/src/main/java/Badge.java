class Badge {
    public String print(Integer id, String name, String department) {
        String idString = id == null ? "" : String.format("[%d] - ", id);
        String departmentString = department == null ? " - OWNER" : String.format(" - %s", department.toUpperCase());
        return idString + name + departmentString;
    }
}
