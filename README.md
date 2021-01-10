# Project Energy System - Phase 2

## About

Object-Oriented Programming, Series CA, CD
2020-2021

<https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa2>

Student: Abagiu Ioan-Razvan 321CD

## Tests

Class Test#main
  * runs the solution on the tests from checker/, comparing out results with
  the ref results
  * runs checkstyle for code cleanliness

Tests details: checker/README

Must have libraries for the implementation:
* Jackson Core 
* Jackson Databind 
* Jackson Annotations

## Implementation

I have used the Jackson library in order to parse the JSONs and write the
final classes as JSONs files to out files.
Every entity whether it is a consumer, distributor or producer has their own
methods that are accessible through each class with only private fields that
can be accessed over getters and setters, many of them having just the getter
because the setter wasn't necessary for the user.
Each consumer has pointers to their previous distributor and current distributor
in order to compute their expenses and in debt prices. The distributor
has an ArrayList of DataCons, which is the data a Consumer can hold in a 
minimalist way with only the important information. Also, a distributor holds
an ArrayList of the producer ids he is subscribed to. The producer has
an ArrayList for the distributors he is providing energy, also those being
its observers and an ArrayList of monthly stats including his subscribers
and the month they are subscribed.
The order of the instructions is described in the ### Flow section.

### Entities

Packages are displayed in this form: [Package] -> class - details

[checker] - all of the checker's components including google's checkstyle

[entities] - enum with all the types of energy a producer can have

[factory] - implemented here the Factory design pattern

-> ConsumerOutput - contains the consumer's output data to be displayed in JSON
format
-> DistributorOutput - contains the distributor's output data to be displayed in
JSON format
-> EntityFactory - factory for creating consumers, distributors and producers
-> Output (interface) - common interface for the three types
-> OutputData - class that contains all the final data to be displayed in JSON
format
-> ProducerOutput - contains the producer's output data to be displayed in JSON
format

[fileio] - classes for handling the input/output of the program

-> Consumer            ]
-> Distributor         ] -> classes for each primar entity
-> Producer            ]
-> DataCons -> contains information about the consumer's signed contract 
-> NewConsumer -> data about the new consumers coming in every month change
-> DistributorChange -> data about the new distributors changes coming in every
month - infrastructure cost changes
-> ProducerChange -> data about the new energy a producer is changing in every
month
-> MonthState -> contains information about the distributor's that claim energy
from the subscribed producer
-> MonthlyUpdate -> info about all the data that is changing in the current 
month
-> InitialData -> info about the initial data
-> Input -> initial data and monthly updates, meaning all the total data 
fetched from the input JSONs

[input] - the initial input modified so it can sustain a singleton pattern
and can be changeable so it won't disturb the initial data
-> MInput - stays for ModifiedInput

[strategies] - implemented here the Strategy design pattern used in choosing
the producers as a distributor
-> EnergyChoiceStrategyType - an enum with all strategies a distributor can have
-> IStrategy (interface) - common interface the other strategies implement with
a choose() method and a compare() method for sorting the producers
-> GreenStrategy - first chooses the renewable-energy producers, then the the
lower prices and higher quantities
-> PriceStrategy - first chooses the lower prices and then the higher energy
a producer can deliver to its distributors
-> QuantityStrategy - chooses the higher energy a producer can deliver to
its distributors

Test -> runs the #main class and compares out and ref results for giving score
Main -> entry point of the implementation


### Flow

First of all, the distributors set their initial budget and their choosing
strategy. They compute their initial production cost and make the initial 
contract prices. Then it is the consumer's turn to set their initial budget
and choose a contract. The consumers receive their income and pay their
contracts so that the distributors will finaly receive their contracts money
and pay their expenses.

Apart from this, the general flow includes recalculating the contract price
every month, checking for monthly changes like incoming new consumers,
distributors changing their infrastructure cost or producers changing their 
energy and taking into consideration the entities who bankrupt.

The simulation main system (Main class) queries the entities - consumers,
distributors and producers and also queries the monthly updates. Consumers
communicate with distributors but they do not question the producers, this 
relation being true also in reverse. The distributors is the middle class which
communicates with both of the entities. 

### OOP design elements

I have used abstraction for both of the Design Patterns needed in this phase,
with the IStrategy and Observable, Observer interfaces. Also, encapsulation
is obviously used a lot of times in the code with getters, setters and methods
that can be accessed only from the current class.

### Design patterns

Following design patterns where used in this phase apart from the phase 1 -
Singleton and Factory.

-> Strategy Design Pattern
Each distributor has a strategy of choosing their producers, so I implemented
those strategies in 3 classes and used composition over inheritance for using 
them.

-> Observer Design Pattern
By knowing that the distributors subscribed to a producer have to reapply their
choosing strategy every time the producer made a change, I made the producer
class as the Observable and their subscribed distributors the observers. 
Everytime there is a change in the energy, a flag to reapply the strategy is 
set on so the distributors will know they have to reconsider their strategy.

### Problems encountered, limitations, difficulties

To solve the 'javadoc' checkstyle problems that appeared even on getters and
setters which I assume they have an obvious role, I didn't explain them but
put in the description their parameters and return values.

A limitation I have encountered with the checker is that it doesn't test
the testcase in which a consumer recovers from his debt. In all the tests, 
the debtful consumer never recovers and is set to be bankrupt the next month.

