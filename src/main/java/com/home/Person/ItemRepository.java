package com.home.Person;

import com.home.Person.Item;
import com.home.Person.Person;
import com.home.Person.Store;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Home on 1/24/2015.
 */
public interface ItemRepository extends CrudRepository<Item,String>{
    Item findByName(String name);
    //<Iterable> Store findSoldAt(String name);

}
