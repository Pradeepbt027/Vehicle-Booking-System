package com.eikona.tech.entity;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="et_role")
public class Role extends Auditable<String> implements Serializable{
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long id;

    @Column(name = "name")
    private String name;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Privilege> privileges;
    
    @SuppressWarnings("unused")
	private String privilegeStr;

    @Column
    private boolean isDeleted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrivilegeStr() {
		List<Privilege> privilegeList=getPrivileges();
		String permission="";
		for(Privilege privilege:privilegeList) {
			if(permission.isEmpty()) {
				permission = privilege.getName();
			}else {
				permission+=","+privilege.getName();
			}
			
		}
		return permission;
	}

	public void setPrivilegeStr(String privilegeStr) {
		this.privilegeStr = privilegeStr;
	}

	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Role(String name, List<Privilege> privileges, boolean isDeleted) {
		this.name = name;
		this.privileges = privileges;
		this.isDeleted = isDeleted;
	}

	public Role() {
		
	}
}