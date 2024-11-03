# Summary of the Veterinary Hotel Management Project

## Objective
Develop an application for managing a veterinary hotel, overseeing entities such as caretakers, veterinarians, and habitats with trees. The framework po-ulib1.jar intended to interact with the user was designed by the Instituto Superior Técnico Computer Science department.

## Document Structure

### 1. Domain Entities
- **Species:** Each animal belongs to a species identified by a unique key.
- **Animals:** Identified by a unique key, each animal has a name, species, and health status. Satisfaction is calculated based on factors like the presence of other animals of the same species and habitat conditions.
- **Habitats:** Identified by a unique key, each habitat has an area and may contain trees. The suitability of a habitat for specific species affects animal satisfaction.
- **Trees:** Identified by a unique key, trees have a name, age, type (deciduous or evergreen), and contribute to cleaning difficulty in habitats. Age and season influence the effort needed for maintenance.
- **Employees:** Divided into caretakers and veterinarians, each has a unique key, name, and satisfaction level. Satisfaction is calculated differently for each type, considering assigned work and the number of animals they are responsible for.

### 2. Vaccines
Identified by a unique key, each vaccine is associated with specific species and maintains a record of administration. An animal's health status is affected by correct or incorrect vaccinations, with a damage classification system.

### 3. Serialization
The system should allow saving and retrieving the current state, preserving all relevant information.

## Design Requirements
- **Open-Closed Principle:** The code must allow for extension without modification, facilitating the addition of new employee types.
- **Design Patterns:** Implement patterns that enhance code readability, particularly concerning the tree concept and employee satisfaction calculation.
- **Multiple Instances:** The code should be flexible to support managing multiple hotels, even if the current specification considers only one.

This project aims to create a robust and extensible application that enables efficient management of a veterinary hotel.

