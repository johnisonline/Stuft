package com.home;

import com.home.Person.Item;
import com.home.Person.ItemRepository;
import com.home.Person.Person;

import com.home.Person.PersonRepository;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.impl.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.core.GraphDatabase;

import java.io.File;

@Configuration
@EnableNeo4jRepositories(basePackages = "com.home.Person")
public class Application extends Neo4jConfiguration implements CommandLineRunner {

    public Application() {
        setBasePackage("com.home.Person");
    }

    @Bean
    GraphDatabaseService graphDatabaseService() {
        return new GraphDatabaseFactory().newEmbeddedDatabase("accessingdataneo4j.db");
    }

    @Autowired
    PersonRepository personRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    GraphDatabase graphDatabase;

    public void run(String... args) throws Exception {
        Person greg = new Person("Greg");
        Person roy = new Person("Roy");
        Person craig = new Person("Craig");

        Item dress = new Item("pant");
        Item cereal = new Item("cereal");
        System.out.println("Before linking up with Neo4j...");
        for (Person person : new Person[]{greg, roy, craig}) {
            System.out.println(person);
        }
      /*  System.out.println("item list...");
        for(Item item: new Item[]{dress,cereal}){
            System.out.println(item);
        }
*/

        Transaction tx = graphDatabase.beginTx();
        try {
            personRepository.save(greg);
            personRepository.save(roy);
            personRepository.save(craig);

            itemRepository.save(dress);
            itemRepository.save(cereal);

            greg = personRepository.findByName(greg.name);
            greg.itemsBought(dress);
            greg.itemsBought(cereal);
            personRepository.save(greg);

            /*roy = personRepository.findByName(roy.name);
            roy.worksWith(craig);
            // We already know that roy works with greg
            personRepository.save(roy);
*/
            // We already know craig works with roy and greg

            System.out.println("Lookup each person by name...");
            for (String name: new String[]{greg.name, roy.name, craig.name}) {
                System.out.println(personRepository.findByName(name));
            }

            System.out.println("Looking up what Greg brought...");
            /*for(Person item : personRepository.findAllItemsBought("name")) {
                System.out.println(item.name + " works with Greg.");
            }
*/
            tx.success();
        } finally {
            tx.close();
        }

    }

    public static void main(String[] args) throws Exception {
        FileUtils.deleteRecursively(new File("accessingdataneo4j.db"));

        SpringApplication.run(Application.class, args);
    }

}