package it.ivncr.erp.model.accesso;


import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "sec_role")
public class Ruolo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "sec_role_id_seq")
	@SequenceGenerator(name = "sec_role_id_seq", sequenceName = "sec_role_id_seq")
	@Column(name="id")
	private Integer id;
	
	@Column(name="rolename")
	private String rolename;
	
	@Column(name="description")
	private String description;

	@OneToMany
	@JoinTable(
			name="sec_role_permission",
			joinColumns = { @JoinColumn(name="roleid", referencedColumnName="id")},
			inverseJoinColumns = { @JoinColumn(name="permissionid", referencedColumnName="id")}
	)
	private List<Permesso> permissions;

	@OneToMany
	@JoinTable(
			name="sec_user_role",
			joinColumns = { @JoinColumn(name="roleid", referencedColumnName="id")},
			inverseJoinColumns = { @JoinColumn(name="userid", referencedColumnName="id")}
	)
	private Set<Utente> users;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Permesso> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permesso> permissions) {
		this.permissions = permissions;
	}

	public Set<Utente> getUsers() {
		return users;
	}

	public void setUsers(Set<Utente> users) {
		this.users = users;
	}
}
