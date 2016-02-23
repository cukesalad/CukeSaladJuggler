# CukeSaladJuggler

## Who can use this?
Anyone writing cucumber steps where you need to share some variables/objects between steps. It also gives you a EL support for your gherkin test steps. You can collect objects/variable created from previous steps in a context map which can be access later in other steps. Also, the same context can be used in gherkin as with el tags.
Internally it uses [juel](http://juel.sourceforge.net/guide/start.html) to parse the EL.
## Pre Requisites
JDK8

## Why use this module?
Lets say there is a test case where you need to create test FB users to use then in your application registration and then use the id (and other details) of the new FB user in the next step to take some action.
At the time of writing the test case you will not have the detail of this new user. In such cases, would it now be nice to pass the objects created in the previous step to the next step? This module gives you a way to put objects in a map of (String, Object) which is available across test steps.

### What is so unique? we can achieve this by having a static variable.
Ofcourse. This module on itself is not special. It becomes immensely useful when you combine this feature with a EL support. What I mean is you can access all the objects in the test context map in your gherkin code with EL expressions.

### Ok so how to I add objects to context?
```java
CukeSaladContext.addToContext(nameOfTheContext, objectToPutInContext.getClass(), objectToPutInContext);
```

### I still want to see how to use EL in gherkin

Sample feature file:
```gherkin
Feature: A feature to demonstrate CukeSaladJuggler util by testing FB graph api /me

  Scenario: testing with FB test users
    Given I create 2 test users in FB app "CukeSalad" using "https://developer.facebook.com/"
    #lets say the above step creates 2 FB users and adds it to the context with name "fbusers". "fbusers" is a list of maps
    Then "${fbusers[0].name}" is not same as "${fbusers[1].name}"
    # The above step compares the names of fbusers created in first step.
    Then the "fbusers" have different details:
    | name               | house              |place               |
    | ${fbusers[0].name} | ${fbusers[0].name} | ${fbusers[0].name} |
    | ${fbusers[1].name} | ${fbusers[1].name} | ${fbusers[1].name} | 
    #the above step is just to demonstrate you can use EL in data table. Here lets say you can call fb and cross check if the detail you got form the first step is same.

```

##Latest release:
Release 1.0.0

## How to contribute
These are just a few steps I could think of. If there are any other feature that you wish for, please go ahead and create the same in the [issue tracker](https://github.com/cukesalad/CukeSaladJuggler/issues). I will make best efforts to add them ASAP.
If you wish contribute by coding, please fork the repository and raise a pull request. 

