package com.home.Person;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import java.util.Date;
import java.util.HashSet;

/**
 * Created by Home on 1/22/2015.
 */
@NodeEntity
public class Item {

    @GraphId
    Long itemID;
    public String name;

    public Item(){}

    public Item(String name)
    {
        this.name = name;
    }

    @RelatedTo(type="SELLS", direction = Direction.INCOMING)
    public @Fetch HashSet<Store> store;

    public void availableAt(Store store)
    {
        if(store == null)
            this.store = new HashSet<Store>();
        else
            this.store.add(store);
    }

    public String toString() {
        String itemResults = name + "...sells at";
        for (Store stor : store)
            itemResults += stor.name + "in city " + stor.city + "in state " + stor.state + "\n";
        return itemResults;
    }

}
