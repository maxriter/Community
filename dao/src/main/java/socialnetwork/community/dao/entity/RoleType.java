package socialnetwork.community.dao.entity;


public enum RoleType {

    USER("USER"),
    VIP("VIP"),
    ADMIN("ADMIN");

    String roleType;

    private RoleType(String roleType){
        this.roleType = roleType;
    }

    public String getRoleType(){
        return roleType;
    }

}