# CukeSaladJuggler

## Who can use this?
Anyone writing cucumber steps where you need to share some variables/objects between steps. It also gives you a EL support for your gherkin test steps. You can collect objects/variable created from previous steps in a context map which can be access later in other steps. Also, the same context can be used in gherkin as with el tags.
Internally it uses [juel](http://juel.sourceforge.net/guide/start.html) to parse the EL.
## Pre Requisites
JDK8

## Getting Started


## Sample feature file:
```gherkin
Feature: A feature to demonstrate CukeSaladJuggler util by testing FB graph api /me

  Scenario: testing with FB test users
    Given I create 2 test users in FB app "CukeSalad" using "https://developer.facebook.com/"
    #lets say the above step creates 2 FB users and adds it to the context with name "fbusers".
    Then "${fbusers[0].name}" is not same as "${fbusers[1].name}"
    # The above step compares the names of fbusers created in first step.
    Then the "fbusers" have different details:
    | name               | house              |place               |
    | ${fbusers[0].name} | ${fbusers[0].name} | ${fbusers[0].name} |
    | ${fbusers[1].name} | ${fbusers[1].name} | ${fbusers[1].name} | 
    #the above step is just to demonstrate you can use EL in data table

```

##Latest release:
Release 1.0.0

## How to contribute
These are just a few steps I could think of. If there are any other feature that you wish for, please go ahead and create the same in the [issue tracker](https://github.com/cukesalad/CukeSaladJuggler/issues). I will make best efforts to add them ASAP.
If you wish contribute by coding, please fork the repository and raise a pull request. 

