package com.matiasborra.jokes.model.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "jokes", schema = "public")
public class Joke implements java.io.Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category categories;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "language_id")
	private Language language;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id")
	private Type types;

	@Column(name = "text1")
	private String text1;

	@Column(name = "text2")
	private String text2;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "joke_flag", schema = "public", joinColumns = {
			@JoinColumn(name = "joke_id", nullable = false, updatable = false) }, inverseJoinColumns = {
			@JoinColumn(name = "flag_id", nullable = false, updatable = false) })
	private Set<Flag> flagses = new HashSet<Flag>(0);

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "jokes_flags", schema = "public", joinColumns = {
			@JoinColumn(name = "joke_id", nullable = false, updatable = false) }, inverseJoinColumns = {
			@JoinColumn(name = "flag_id", nullable = false, updatable = false) })
	private Set<Flag> flagses_1 = new HashSet<Flag>(0);

	@OneToMany(mappedBy = "joke", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<JokeFlag> jokeFlags = new HashSet<>();

	public Joke() {
	}

	public Joke(long id) {
		this.id = id;
	}

	public Joke(long id, Category categories, Language language, Type types, String text1, String text2,
				Set<Flag> flagses, Set<Flag> flagses_1) {
		this.id = id;
		this.categories = categories;
		this.language = language;
		this.types = types;
		this.text1 = text1;
		this.text2 = text2;
		this.flagses = flagses;
		this.flagses_1 = flagses_1;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Category getCategory() {
		return categories;
	}

	public void setCategory(Category categories) {
		this.categories = categories;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Type getType() {
		return types;
	}

	public void setType(Type types) {
		this.types = types;
	}

	public String getText1() {
		return text1;
	}

	public void setText1(String text1) {
		this.text1 = text1;
	}

	public String getText2() {
		return text2;
	}

	public void setText2(String text2) {
		this.text2 = text2;
	}

	public Set<Flag> getFlags() {
		return flagses;
	}

	public void setFlags(Set<Flag> flagses) {
		this.flagses = flagses;
	}

	public Set<Flag> getFlagses_1() {
		return flagses_1;
	}

	public void setFlagses_1(Set<Flag> flagses_1) {
		this.flagses_1 = flagses_1;
	}

	public Set<JokeFlag> getJokeFlags() {
		return jokeFlags;
	}

	public void setJokeFlags(Set<JokeFlag> jokeFlags) {
		this.jokeFlags = jokeFlags;
	}
}
