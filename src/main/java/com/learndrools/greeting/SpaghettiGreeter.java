package com.learndrools.greeting;

import static com.learndrools.greeting.Person.Degree.*;
import static com.learndrools.greeting.Person.Education.*;
import static com.learndrools.greeting.Person.Gender.*;
import static com.learndrools.greeting.Person.MaritalStatus.*;

public class SpaghettiGreeter {
	
	public String greet(Person person, TimeOfDay timeOfDay) {
		String greeting = "";
		if (timeOfDay.getHour() < 12) {
			greeting = "Good Morning";
		} else if ((timeOfDay.getHour() >= 12) && (timeOfDay.getHour() <= 17)) {
			greeting = "Good Afternoon";
	    } else if ((timeOfDay.getHour() >= 18) && (timeOfDay.getHour() <= 22)) {
	        greeting = "Good Evening";
	    } else {
	        greeting = "Good Night";
	    }
		
		String prenominal = "";
		String postnominal = "";
		
		if (person.isKnighted()) {
			if (person.getGender() == FEMALE) {
				prenominal = "Dame";
			} else {
				prenominal = "Sir";
			}
			if (person.getEducation().compareTo(MASTERS) >= 0) {
                switch (person.getDegree()) {
				case MA:
					postnominal = ", M.A.";
					break;
				case MFA:
					postnominal = ", M.F.A.";
					break;
				case MS:
					postnominal = ", M.S.";
					break;
				case MBA:
					postnominal = ", M.B.A.";
					break;
				case MD:
					postnominal = ", M.D.";
					break;
			    default:
				    break;
			    }
			}
		} else if (person.getEducation() == PHD) {
		    prenominal = "Dr.";
		} else if (person.getAge() > 10) {
			if (person.getGender() == FEMALE) {
				if (person.getMaritalStatus() == MARRIED) {
					prenominal = "Mrs.";
				} else if (person.getMaritalStatus() == SINGLE) {
					prenominal = "Ms.";
				}
			} else if (person.getGender() == MALE) {
				prenominal = "Mr.";
			}
		} else {
			prenominal = "Little";
		}
		
		return greeting + " " + prenominal + " " + person.getName() + postnominal;
	}

	public static void main(String[] args) {
		Person mauriceMoss = new Person("Maurice Moss", MALE, 32, SINGLE, PHD, MD, true);
		TimeOfDay nineAm = new TimeOfDay(9, 0);
		SpaghettiGreeter greeter = new SpaghettiGreeter();
		
		System.out.println(greeter.greet(mauriceMoss, nineAm));
	}

}
