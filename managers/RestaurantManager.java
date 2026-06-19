package managers;

import models.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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

    public void addRestaurant(Restaurant r) {
        restaurants.add(r);
        if (r != null) {
            logger.info("Added restaurant id=" + r.getId() + " name=" + r.getName() + " location=" + r.getLocation());
        } else {
            logger.info("Added null restaurant");
        }
    }

    public List<Restaurant> searchByLocation(String loc) {
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
        logger.info("Search completed. found=" + result.size());
        return result;
    }
}
