package com.purushoth.hotel.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
@Entity
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users = new HashSet<>();
    
     public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role(String name) {
        this.name = name;
    }

    public void assignRoleToUser(User user){
        user.getRoles().add(this);
        this.getUsers().add(user);
    }

    public void removeUserFromRole(User user){
        user.getRoles().remove(this);
        this.getUsers().remove(user);

    }

    public void removeAllUsersFromRole(){
        if (this.getUsers() != null){
            List<User> roleUsers = this.getUsers().stream().toList();
            roleUsers.forEach(this :: removeUserFromRole);
        }
    }
    public  String getName(){
        return name != null? name : "";
    }
}