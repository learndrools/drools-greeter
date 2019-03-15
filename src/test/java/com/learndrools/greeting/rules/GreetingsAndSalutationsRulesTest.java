package com.learndrools.greeting.rules;

import static org.junit.Assert.*;

import org.junit.Test;
import org.kie.api.runtime.rule.QueryResults;

import com.learndrools.greeting.GreetingsAndSalutations;
import com.learndrools.greeting.Person;
import com.learndrools.greeting.Person.Degree;
import com.learndrools.greeting.Person.Education;
import com.learndrools.greeting.Person.Gender;
import com.learndrools.greeting.Person.MaritalStatus;
import com.learndrools.greeting.TimeOfDay;
import com.learndrools.junit.BaseDroolsTestCase;

public class GreetingsAndSalutationsRulesTest extends BaseDroolsTestCase {

    public GreetingsAndSalutationsRulesTest() {
        super("ksession-rules");
    }
    
    @Test
    public void testGreetingGoodMorning() {
        TimeOfDay tod = new TimeOfDay(10, 0);
        Person richmond = new Person("Richmond Avenal", Gender.MALE, 28, MaritalStatus.SINGLE, Education.COLLEGE, Degree.NA, false);
        
        knowledgeSession.insert(tod);
        knowledgeSession.insert(richmond);
        
        knowledgeSession.fireAllRules();
        
        QueryResults results = knowledgeSession.getQueryResults("AllGreetingsAndSalutations");
        
        assertEquals(1, results.size());
        
        GreetingsAndSalutations gns = (GreetingsAndSalutations) results.iterator().next().get("gns");
        
        assertEquals("Good Morning, ", gns.getGreeting());
        
    }

}
