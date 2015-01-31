package com.home.Person;

import com.home.Person.Item;
import com.home.Person.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Home on 1/24/2015.
 */
public interface PersonRepository  extends CrudRepository<Person, String>{

    Person findByName (String name);



   // List<Person> findAllItemsBought(String name);
}
