package com.example.CMS_01.Entity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
/*
 * 'user' is a reserved keyword in SQL, so we name our table users. If you name it user, you will get a org.h2.jdbc.JdbcSQLSyntaxErrorException. 
 *  See https://docs.microsoft.com/en-us/sql/t-sql/language-elements/reserved-keywords-transact-sql?view=sql-server-ver16 for a list of reserved keywords.
 */
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class User {

    @Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;

	@NonNull
	@Valid
	@NotEmpty(message = "you must enter username")
	@Column(nullable = false, unique = true)
	private String username;

	@Valid
    @NonNull
	@Column(nullable = false)
	private String password;

	@GeneratedValue(strategy = GenerationType.TABLE)
	String SWEArticle;
	@GeneratedValue(strategy = GenerationType.TABLE)
	String SWEVideo;
	@GeneratedValue(strategy = GenerationType.TABLE)
	String SWEQuiz;

	@GeneratedValue(strategy = GenerationType.TABLE)
	String CppArticle;
	@GeneratedValue(strategy = GenerationType.TABLE)
	String CppVideo;
	@GeneratedValue(strategy = GenerationType.TABLE)
	String CppQuiz;

	@GeneratedValue(strategy = GenerationType.TABLE)
	String AlgorithmsArticle;
	@GeneratedValue(strategy = GenerationType.TABLE)
	String AlgorithmsVideo;
	@GeneratedValue(strategy = GenerationType.TABLE)
	String AlgorithmsQuiz;
}