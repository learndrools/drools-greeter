package com.learndrools.greeting;

public class GreetingsAndSalutations {
    private Person recipient;
    private TimeOfDay tod;
    private String greeting;
    private String prenomial;
    private String postnomial;

    public GreetingsAndSalutations(Person recipient, TimeOfDay tod, String greeting) {
        this.recipient = recipient;
        this.tod = tod;
        this.greeting = greeting;
    }

    public Person getRecipient() {
        return recipient;
    }

    public void setRecipient(Person recipient) {
        this.recipient = recipient;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getPrenomial() {
        return prenomial;
    }

    public void setPrenomial(String prenomial) {
        this.prenomial = prenomial;
    }

    public String getPostnomial() {
        return postnomial;
    }

    public void setPostnomial(String postnomial) {
        this.postnomial = postnomial;
    }
    
    public TimeOfDay getTod() {
        return tod;
    }

    public void setTod(TimeOfDay tod) {
        this.tod = tod;
    }

    @Override
    public String toString() {
        String result = getGreeting() + getPrenomial() + " " + getRecipient().getName();
        
        if (getPostnomial() != null) {
            result = result + ", " + getPostnomial();
        }
        
        return result;
    }
}
