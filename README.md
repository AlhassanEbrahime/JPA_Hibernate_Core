# JPA/Hibernate Core

## Table of Contents
- [Entities](#entities)

## Entities
JPA/Hibernate can be configured in two ways:

### 1. Using XML file:
Create a `persistence.xml` file in the `META-INF` directory:

```xml
<persistence version="3.0" xmlns="https://jakarta.ee/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="https://jakarta.ee/xml/ns/persistence
             https://jakarta.ee/xml/ns/persistence/persistence_3_0.xsd">

    <persistence-unit name="my-persistence-unit" transaction-type="RESOURCE_LOCAL">
        <description>description</description>
        <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
        <exclude-unlisted-classes>false</exclude-unlisted-classes>

        <properties>
            <property name="jakarta.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>
            <property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/Your database"/>
            <property name="jakarta.persistence.jdbc.user" value="Username"/>
            <property name="jakarta.persistence.jdbc.password" value="Password"/>
        </properties>
    </persistence-unit>
</persistence>
