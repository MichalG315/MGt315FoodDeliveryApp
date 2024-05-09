package pl.zajavka.api.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MenuItemCategories {
    BREAKFAST("Breakfast"),
    MAIN_COURSE("Main course"),
    STARTER("Starter"),
    BURGER("Burger"),
    WRAP("Wrap"),
    SANDWICH("Sandwich"),
    SOUP("Soup"),
    SALAD("Salad"),
    BEVERAGE("Beverage"),
    PIZZA("Pizza");

    private final String toPrint;

}
