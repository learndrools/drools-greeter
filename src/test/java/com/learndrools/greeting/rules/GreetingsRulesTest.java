package com.learndrools.greeting.rules;

import static com.learndrools.greeting.GreetingsAndSalutations.AFTERNOON;
import static com.learndrools.greeting.GreetingsAndSalutations.EVENING;
import static com.learndrools.greeting.GreetingsAndSalutations.MORNING;
import static com.learndrools.greeting.GreetingsAndSalutations.NIGHT;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.kie.api.runtime.rule.QueryResults;

import com.learndrools.greeting.GreetingsAndSalutations;
import com.learndrools.greeting.Person;
import com.learndrools.greeting.Person.Degree;
import com.learndrools.greeting.Person.Education;
import com.learndrools.greeting.Person.Gender;
import com.learndrools.greeting.Person.MaritalStatus;
import com.learndrools.greeting.TimeOfDay;
import com.learndrools.junit.BaseDroolsTestCase;

@RunWith(Parameterized.class)
public class GreetingsRulesTest extends BaseDroolsTestCase {

    public GreetingsRulesTest(int hourOfTheDay, String expectedResult) {
        super("ksession-rules");
        this.hourOfTheDay = hourOfTheDay;
        this.expectedResult = expectedResult;
    }
    
    @Test
    public void testGreetingRule() {
        TimeOfDay tod = new TimeOfDay(hourOfTheDay, 0);
        Person richmond = new Person("Richmond Avenal", Gender.MALE, 28, MaritalStatus.SINGLE, Education.COLLEGE, Degree.NA, false);
        
        knowledgeSession.insert(tod);
        knowledgeSession.insert(richmond);
        
        knowledgeSession.fireAllRules();
        
        QueryResults results = knowledgeSession.getQueryResults("AllGreetingsAndSalutations");
        
        assertEquals(1, results.size());
        
        GreetingsAndSalutations gns = (GreetingsAndSalutations) results.iterator().next().get("gns");
        
        assertEquals(expectedResult, gns.getGreeting());
        
    }
    
    private final int hourOfTheDay;
    private final String expectedResult;
    
    @Parameters(name = "{0} : {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {0, MORNING},
            {11, MORNING},
            {12, AFTERNOON},
            {17, AFTERNOON},
            {18, EVENING},
            {22, EVENING},
            {23, NIGHT},
            {24, NIGHT}
        });
    }

}
