package com.learndrools.greeting;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.learndrools.greeting.Person.Degree;
import com.learndrools.greeting.Person.Education;
import com.learndrools.greeting.Person.Gender;
import com.learndrools.greeting.Person.MaritalStatus;

public class GreetingsExample {

    public static void main(String[] args) {
        KieSession session = null;
        
        try {
            // 1 - Get the KieServices singleton factory
            KieServices factory = KieServices.Factory.get();
            
            // 2 - Get KieContainer from the factory
            KieContainer container = factory.getKieClasspathContainer();
            
            // 3 - Build a new KieSession
            session = container.newKieSession("ksession-rules");
            
            // 4 - Assert some facts
            TimeOfDay nineAm = new TimeOfDay(9, 0);
            TimeOfDay onePm = new TimeOfDay(13, 0);
            TimeOfDay sevenPm = new TimeOfDay(19, 0);
            TimeOfDay elevenPm = new TimeOfDay(23, 0);
            Person maurice = new Person("Maurice Moss", Gender.MALE, 32, MaritalStatus.SINGLE, Education.MASTERS, Degree.MBA, true);
            Person jen = new Person("Jen Barber", Gender.FEMALE, 30, MaritalStatus.SINGLE, Education.PHD, Degree.MD, true);
            
            session.insert(maurice);
            session.insert(jen);
            
            session.insert(onePm);
            session.insert(nineAm);
            session.insert(sevenPm);
            session.insert(elevenPm);
               
            // 5 - Fire all rules
            session.fireAllRules();
        } finally {
            session.dispose();
        }
        

    }

}
