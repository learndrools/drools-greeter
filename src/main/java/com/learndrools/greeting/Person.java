package com.learndrools.greeting;

public class Person {
    public static enum Gender { FEMALE, MALE }
    public static enum MaritalStatus { SINGLE, MARRIED }
    public static enum Education { NONE, DROPOUT, HIGH_SCHOOL, COLLEGE, MASTERS, PHD }
    public static enum Degree { NA, MA, MFA, MS, MBA, MD }
 
    private String name;
    private Gender gender;
    private MaritalStatus maritalStatus;
    private Education education;
    private Integer age;
    private boolean knighted;
    private Degree degree;

	public Person(String name, Gender gender, Integer age, MaritalStatus maritalStatus, Education education, Degree degree, boolean knighted) {
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.maritalStatus = maritalStatus;
		this.education = education;
		this.degree = degree;
		this.knighted = knighted;
	}

	public String getName() { return name; }
	public Gender getGender() { return gender; }
	public Integer getAge() { return age; }
	public MaritalStatus getMaritalStatus() { return maritalStatus; }
    public Education getEducation() { return education; }
    public boolean isKnighted() { return knighted; }
    public Degree getDegree() { return degree; }
}