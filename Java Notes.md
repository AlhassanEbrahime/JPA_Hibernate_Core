 
### `OOP` features are not used in java :

- multiple inheritance: 
	 - It causes diamond problem 
	 
- operator overloading :
	- It does not support changing operators behaviour except `+` operator for strings ( concatenation )
	
- `destructors`:
	- java relies on garbage collector 
	
- pointers :
	- java has references but with no pointers
	
- passing  by reference: 
	- Encourages encapsulation by not allowing direct access to caller's reference
	
- Global variables
	- Buffer overflows(corrupting memory)
	- Dangling pointers(accessing free memory)
	- memory leaks(forgetting to `deallocate`)
	  
- default arguments

---

### Interface and abstract Class :

- the default accessibility for method in a class if the access modifier if not specified is`package-private

- interface method before `java8` was public abstract

- default method is public by default

- in overriding I can change the access modifier to wider access modifier
  - package-private (default) -> can become `protected` or `public`
  - protected -> can be `public`

#### Extending functional interface rules :
- if the child interface does not declare a new abstract method.
```java
@FunctionalInterface
interface Parent {
    void doWork();
}

// Child is still a functional interface

@FunctionalInterface
interface Child extends Parent {} 

```

- if the child adds another abstract method, it loses its functional nature
```java
@FunctionalInterface
interface Parent {
    void doWork();
}

// ❌ Compile-time error: Not a functional interface!
interface Child extends Parent {
    void doMoreWork(); // Adds a second abstract method
}

```


---

### Exception handling

 - `finally` block will be executed either exception happened or not
   - it used when i want to close resource like database connection , file or network connection
   
---

### Exception handling in overriding

#### Checked exception:
   - The overriding method can throw :
     - the same checked exception as the super class method
     - A subclass of the original exception 
     - no exception at all  

   - You have to throw exception in the super method first if you want to throw exception :

   ```java
   abstract class Parent{
	   abstract void go () throws Exception;
   }
   
   class Child exctends Parent{
	   @Override
	   void go() throw (subclass of the super mehtod or the same)
   }
```

--- 

#### Unchecked exception:
- can be thrown freely in overridden method (even if not declared)

- the overriding function throws exception same of the super or subclass of it
  

#### Exceptions hierarchy :

![[Pasted image 20250604005136.png]]


