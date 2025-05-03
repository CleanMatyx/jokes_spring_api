// src/main/java/com/matiasborra/jokes/model/entity/Flag.java
package com.matiasborra.jokes.model.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "flags", schema = "public")
public class Flag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "flag", nullable = false)
	private String flag;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "flag", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<JokeFlag> jokeFlags = new HashSet<>();

	public Flag() {}
	// getters + setters

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public String getFlag() { return flag; }
	public void setFlag(String flag) { this.flag = flag; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public Set<JokeFlag> getJokeFlags() { return jokeFlags; }
	public void setJokeFlags(Set<JokeFlag> jokeFlags) { this.jokeFlags = jokeFlags; }
}
