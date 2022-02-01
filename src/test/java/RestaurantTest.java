import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantTest {
    Restaurant restaurant;
    
    RestaurantService service = new RestaurantService();
    public void createTestRestaurant(){
        LocalTime openingTime = LocalTime.parse("09:00:00");
        LocalTime closingTime = LocalTime.parse("23:00:00");
        restaurant = service.addRestaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        createTestRestaurant();
        
        LocalTime openingTime = LocalTime.parse("09:30:00");
        LocalTime closingTime = LocalTime.parse("23:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        boolean result = restaurant.isRestaurantOpen();
        assertTrue(result);
    
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        createTestRestaurant();
        LocalTime openingTime = LocalTime.parse("09:00:00");
        LocalTime closingTime = LocalTime.parse("23:30:00");
    
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
    
        boolean result = restaurant.isRestaurantOpen();
    
        assertFalse(result);
        //Failing Test Case
    
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        createTestRestaurant();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
       createTestRestaurant();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        createTestRestaurant();
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    
    @Test
    public void item_not_added_to_menu_should_return_zero() {
        createTestRestaurant();
        int price = restaurant.getSelectedItemPrice();
        assertEquals(0, price);
    }
    
    
    @Test
    public void select_item_from_list_should_return_order_cost(){
        int totalCost;
        createTestRestaurant();
        List<String> selectedItemNames = Arrays.asList("Sweet corn soup", "Vegetable lasagne" ) ;
        totalCost = restaurant.getTotalCost(selectedItemNames) ;
        assertEquals(388, totalCost);
    }
    
 
}