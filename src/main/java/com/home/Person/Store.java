package com.home.Person;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * Created by Home on 1/22/2015.
 */
@NodeEntity
public class Store {

    @GraphId
    Long StoreID;

    public String name;

    public String city;

    public String state;

    public Store(){}
    public Store(String name, String city, String state){
        this.name = name;
        this.city=city;
        this.state=state;
    };






}
