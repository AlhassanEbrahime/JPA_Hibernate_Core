## Configuration for adding JPA/Hibernate
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

```

and the client `Main` will be like this :

```java

public class Main {
    public static void main(String[] args) {


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");

        EntityManager em = emf.createEntityManager(); // represents the context

        try {
            em.getTransaction().begin();

            Demo d = new Demo();

            d.setId(2L);
            d.setName("kabab");

            em.persist(d); // add this to context  -> Not an insert query

            em.getTransaction().commit();
        }finally {
            em.close();
        }

    }
}
```

### 2. Uisng Class:
Create a backage `persistance` then create your Custom persistance class implements `PersistenceUnitInfo` interface

```java

public class CustomPersistenceUnitInfo implements PersistenceUnitInfo {
    @Override
    public String getPersistenceUnitName() {
        return "my-persistence-unit";
    }

    @Override
    public String getPersistenceProviderClassName() {
        return "org.hibernate.jpa.HibernatePersistenceProvider";
    }

    @Override
    public PersistenceUnitTransactionType getTransactionType() {
        return PersistenceUnitTransactionType.RESOURCE_LOCAL;
    }

    @Override
    public DataSource getJtaDataSource() {
        return null;
    }

    @Override
    public DataSource getNonJtaDataSource() {

        //replace with your database configuraion
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/Your database");
        dataSource.setUsername("username");
        dataSource.setPassword("password");

        return dataSource;
    }

    @Override
    public List<String> getManagedClassNames() {
        //replace with your entity
        return List.of("org.project.entities.Demo");
    }


    @Override
    public List<String> getMappingFileNames() {
        return List.of();
    }

    @Override
    public List<URL> getJarFileUrls() {
        return List.of();
    }

    @Override
    public URL getPersistenceUnitRootUrl() {
        return null;
    }

    @Override
    public boolean excludeUnlistedClasses() {
        return false;
    }

    @Override
    public SharedCacheMode getSharedCacheMode() {
        return null;
    }

    @Override
    public ValidationMode getValidationMode() {
        return null;
    }

    @Override
    public Properties getProperties() {
        return null;
    }

    @Override
    public String getPersistenceXMLSchemaVersion() {
        return "";
    }

    @Override
    public ClassLoader getClassLoader() {
        return null;
    }

    @Override
    public void addTransformer(ClassTransformer classTransformer) {

    }

    @Override
    public ClassLoader getNewTempClassLoader() {
        return null;
    }
}

```

You need to add a dependency in `pom.xml` to make hibernate connect to your `DBMS`

```xml
        <dependency>
            <groupId>com.zaxxer</groupId>
            <artifactId>HikariCP</artifactId>
            <version>6.2.1</version>
        </dependency>
```

Then the `Main` will be like this:

```java
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), new HashMap<>());
        EntityManager em = emf.createEntityManager(); // represents the context

        try {
            em.getTransaction().begin();
            Demo d = new Demo();
            d.setId(2L);
            d.setName("kabab");
            em.persist(d); // add this to context  -> Not an insert query
            em.getTransaction().commit();
        }finally {
            em.close();
        }
    }
}

```
