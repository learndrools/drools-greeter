package com.learndrools.greeting.rules;

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
public class SalutationsRulesTest extends BaseDroolsTestCase {
    
    public SalutationsRulesTest(String testInfo, int age, boolean knighted, Gender gender, Education education, Degree degree, MaritalStatus maritalStatus, String expectedPrenomial, String expectedPostnomial) {
        super("ksession-rules");
        this.testInfo = testInfo;
        this.age = age;
        this.knighted = knighted;
        this.gender = gender;
        this.education = education;
        this.degree = degree;
        this.maritalStatus = maritalStatus;
        this.expectedPrenomial = expectedPrenomial;
        this.expectedPostnomial = expectedPostnomial;
    }

    @Test
    public void testSalutationRule() {
        TimeOfDay tod = new TimeOfDay(9, 0);
        Person person = new Person("Some Name", gender, age, maritalStatus, education, degree, knighted);
        
        knowledgeSession.insert(tod);
        knowledgeSession.insert(person);
        
        knowledgeSession.fireAllRules();
        
        QueryResults results = knowledgeSession.getQueryResults("AllGreetingsAndSalutations");
        
        assertEquals(1, results.size());
        
        GreetingsAndSalutations gns = (GreetingsAndSalutations) results.iterator().next().get("gns");
        
        if (expectedPrenomial != null) {
            assertEquals(expectedPrenomial, gns.getPrenomial());
        }
        
        if (expectedPostnomial != null) {
            assertEquals(expectedPostnomial, gns.getPostnomial());
        }
    }
    
    @SuppressWarnings("unused")
    private final String testInfo;
    private final int age;
    private final boolean knighted;
    private final Gender gender;
    private final Education education;
    private final Degree degree;
    private final MaritalStatus maritalStatus;
    private final String expectedPrenomial;
    private final String expectedPostnomial;
    
    @Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {"Male Knighted", 30, true, Gender.MALE, Education.NONE, Degree.NA, MaritalStatus.SINGLE, "Sir", null},
            {"Female Knighted", 30, true, Gender.FEMALE, Education.NONE, Degree.NA, MaritalStatus.SINGLE, "Dame", null},
            {"Male with PHD", 30, false, Gender.MALE, Education.PHD, Degree.NA, MaritalStatus.SINGLE, "Dr.", null},
            {"Female with PHD", 30, false, Gender.FEMALE, Education.PHD, Degree.NA, MaritalStatus.SINGLE, "Dr.", null},
            {"Person with an MD", 30, false, Gender.FEMALE, Education.PHD, Degree.MD, MaritalStatus.SINGLE, null, "M.D."},
            {"Person with an MA", 30, false, Gender.FEMALE, Education.PHD, Degree.MA, MaritalStatus.SINGLE, null, "M.A."},
            {"Person with an MFA", 30, false, Gender.FEMALE, Education.PHD, Degree.MFA, MaritalStatus.SINGLE, null, "M.F.A."},
            {"Person with an MBA", 30, false, Gender.FEMALE, Education.PHD, Degree.MBA, MaritalStatus.SINGLE, null, "M.B.A."},
            {"Person under 10 years of age", 9, false, Gender.FEMALE, Education.HIGH_SCHOOL, Degree.NA, MaritalStatus.SINGLE, "Little", null},
            {"Default Male", 30, false, Gender.MALE, Education.NONE, Degree.NA, MaritalStatus.SINGLE, "Mr.", null},
            {"Default Single Female", 30, false, Gender.FEMALE, Education.NONE, Degree.NA, MaritalStatus.SINGLE, "Ms.", null},
            {"Default Married Female", 30, false, Gender.FEMALE, Education.NONE, Degree.NA, MaritalStatus.MARRIED, "Mrs.", null},
        });
    }

}
