package com.home.Person;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Person{

    public Person(){}

    @GraphId
    Long Id;
    public String name;

    public Person(String name){
        this.name = name;
    }

    @RelatedTo(type="STUFF", direction= Direction.OUTGOING)
    public @Fetch HashSet<Item> stuff;

    public void itemsBought(Item item) {
        if (stuff == null){
            stuff = new HashSet<Item>();
        }
        if(item !=null) {
            stuff.add(item);
        }
        else
            System.out.println("Item object is empty");
    }

    public String toString()
    {
        String results = name + "...bought these items ...";
        if(stuff !=null)
            for(Item stuf:stuff)
             results += "\t - " + stuf.name + "\n";

        return results;
    }
}