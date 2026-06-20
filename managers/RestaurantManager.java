package managers;

import models.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import exceptions.*;

// Singleton
public class RestaurantManager {
    private List<Restaurant> restaurants = new ArrayList<>();
    private static RestaurantManager instance = null;
    private static final Logger logger = Logger.getLogger(RestaurantManager.class.getName());

    private RestaurantManager() {
        // private constructor
    }

    public static RestaurantManager getInstance() {
        if (instance == null) {
            instance = new RestaurantManager();
        }
        return instance;
    }

    public void addRestaurant(Restaurant r) throws exceptions.InvalidOrderException {
        if (r == null) {
            throw new exceptions.InvalidOrderException("Cannot add null restaurant");
        }
        restaurants.add(r);
        logger.info("Added restaurant id=" + r.getId() + " name=" + r.getName() + " location=" + r.getLocation());
    }

    public List<Restaurant> searchByLocation(String loc) throws exceptions.RestaurantNotFoundException {
        if (loc == null || loc.trim().isEmpty()) {
            throw new exceptions.RestaurantNotFoundException("Search location cannot be null or empty");
        }
        logger.info("Searching restaurants by location='" + loc + "'");
        List<Restaurant> result = new ArrayList<>();
        loc = loc.toLowerCase();
        for (Restaurant r : restaurants) {
            String rl = r.getLocation().toLowerCase();
            if (rl.equals(loc)) {
                result.add(r);
                logger.fine("Found restaurant id=" + r.getId() + " name=" + r.getName());
            }
        }
        if (result.isEmpty()) {
            logger.info("No restaurants found for location='" + loc + "'");
            throw new exceptions.RestaurantNotFoundException("No restaurants found for location: " + loc);
        }
        logger.info("Search completed. found=" + result.size());
        return result;
    }
}
