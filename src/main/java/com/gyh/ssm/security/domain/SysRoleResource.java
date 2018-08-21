package com.gyh.ssm.security.domain;

public class SysRoleResource {
    private Integer roleId;

    private Integer resourceId;
    
    private String roleName;
    
    private String resourceUrl;
    
    public SysRoleResource() {
		super();
	}

    public SysRoleResource(String roleName, String resourceUrl) {
		super();
		this.roleName = roleName;
		this.resourceUrl = resourceUrl;
	}
    
    public SysRoleResource(Integer roleId, Integer resourceId, String roleName, String resourceUrl) {
		super();
		this.roleId = roleId;
		this.resourceId = resourceId;
		this.roleName = roleName;
		this.resourceUrl = resourceUrl;
	}

	public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
    
}