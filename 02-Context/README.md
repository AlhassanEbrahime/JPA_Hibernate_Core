## Hibernate Persistent Context & Entity manager

### 1-Persistent Context:
![Transaction Persistent Context](https://www.baeldung.com/wp-content/uploads/2019/11/transition-persistence-context.png)

- Every change that happens to the Entity object in the transaction block remains in the context till the end of the transaction then a query is sent to the Database

``` java
    try{
            em.getTransaction().begin();
                   
                   //every thing happens here it happens in the context
        
            em.getTransaction().commit(); // after the commit the changes sent to the data base as a query
        } finally {
            em.close();
        }
```



### 2- Entity manager methods

```java
      try{
            em.getTransaction().begin();


//            em.persist();     -> Adding an entity in the context
//            em.find();        -> Finds by PK. Get from DB and add it to the context if it doesn't already exist
//            em.remove();      -> Marking entity from removal
//            em.merge();       -> Merges an entity from outside the context in the context
//            em.refresh();     -> Mirror the context from the database
//            em.detach();      -> Taking the entity out of the context
//            em.getReference() -> only issues a query if something used the instance (Lazy)


            em.getTransaction().commit();
        } finally {
            em.close();
        }
```


### 3- Hibernate properties

- To view sql queries sent to the database

``` java 
      Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql","true");
```

- To make the updates to an Entity ( Table ) automatically

``` java
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.hbm2ddl.auto","update");
        props.put("hibernate.hbm2ddl.auto","create");
```
- Note : Never use create in production; it drops the table and recreat it