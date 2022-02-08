package com.arpangroup;

import com.arpangroup.entity.master.CityEntity;
import com.arpangroup.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.net.InetAddress;
import java.util.Arrays;

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
public class OnboardApp implements CommandLineRunner {
    @Autowired
    private ServerProperties serverProperties;

    @Autowired
    CityRepository cityRepository;

    public static void main(String[] args) {
        SpringApplication.run(OnboardApp.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n##############################################");
        System.out.println("OnboardApp Application Started on " + InetAddress.getLocalHost().getHostAddress() + ":" +serverProperties.getPort());
        System.out.println("##############################################");

//        initCities();
    }


    private void initCities() {
        CityEntity[] cities = {
                new CityEntity("Kolkata", 22.5726f, 88.3639f),
                new CityEntity("Kolhapur", 16.7050f, 74.2433f),
                new CityEntity("Howrah", 22.5958f, 88.2636f),
                new CityEntity("Delhi", 28.7041f, 77.1025f)
        };

        /*
        CuisineEntity[] cuisines = {
                new CuisineEntity("Biryani"),
                new CuisineEntity("Bengali")
        };

        CollectionEntity[] collections = {
                new CollectionEntity("Newly Opened", "The best new places in town"),
                new CollectionEntity("Trending This Week", "Most popular restaurants in town this week"),
                new CollectionEntity("Great Food, No Bull", "Your search for the city's top rated restaurants ends here"),
                new CollectionEntity("Great Cafes", "Caffeinate away with that perfect cup of cappuccino, mocha or latte from some of the best coffee spots"),
                new CollectionEntity("Brilliant Biryanis", "Hyderabadi, Kolkata, Lucknowi...the choice is yours"),
                new CollectionEntity("Mishti", "Hyderabadi, Kolkata, Lucknowi...the choice is yours"),
                new CollectionEntity("Street Food", "Delectable bites for when you're on-the-go"),
                new CollectionEntity("Rooftops", "If you fancy a meal with a view"),
                new CollectionEntity("Live Sports Screenings", "Catch all the live sporting action with great food & drinks!"),
                new CollectionEntity("Great Chinese", "Some of the best Chinese food that your city has to offer"),
                new CollectionEntity("Great Breakfasts", "Some of the best Chinese food that your city has to offer"),
                new CollectionEntity("Taste of Bengal", "The best the City of Joy has to offer")
        };

        String[] establishments  = {
                "Casual Dining", "Quick Bites", "Bar",
                "Dhaba", "Fine Dining", "Bakery",
                "Café", "Dessert Parlour", "Sweet Shop",
                "Lounge", "Pub", "Food Court",
                "Kiosk", "Beverage Shop", "Club",
                "Butcher Shop", "Confectionery", "Microbrewery",
                "Paan Shop", "Bhojanalya", "Food Truck",
                "Mess", "Tea Room", "Noodle Shop",
                "Fast Food", "Shack", "Cocktail Bar",
                "Dairy", "Fruits & Vegetable", "Super Market",
                "Liquor Store", "Pop up"
        };


        cities[0].assignCuisine(cuisines);
        cities[0].addCollections(collections);
        cities[0].addEstablishments(Arrays.stream(establishments).map(EstablishmentEntity::new).toArray(EstablishmentEntity[]::new));
        */
        cityRepository.deleteAll();
        cityRepository.saveAll(Arrays.asList(cities));
    }
}
