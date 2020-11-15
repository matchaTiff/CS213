import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Class that controls the data flow into the model object and updates the view when
 * the data changes. Handles the buttons and displays results on the GUI.
 * Controls the functionality of the first stage.
 * 
 * @author Seth Santos, Tiffany Chen
 * 
 */
public class Controller {

    @FXML private ComboBox sandwichChoices;
    @FXML private Label ingredients;
    @FXML private ImageView sandwichImage;
    @FXML private ListView extraIngredients;
    @FXML private ListView selectedIngredients;
    @FXML private TextField priceOfSandwich;
    @FXML private Button showOrderButton;

    private Sandwich sandwich;
    private Image image;
    private int MAX_EXTRAS = 6;

    Extra ingredient1 = new Extra("Onions");
    Extra ingredient2 = new Extra("Relish");
    Extra ingredient3 = new Extra("Ranch");
    Extra ingredient4 = new Extra("Mayonnaise");
    Extra ingredient5 = new Extra("Ingredient 5");
    Extra ingredient6 = new Extra("Ingredient 6");
    Extra ingredient7 = new Extra("Ingredient 7");
    Extra ingredient8 = new Extra("Ingredient 8");
    Extra ingredient9 = new Extra("Ingredient 9");
    Extra ingredient10 = new Extra("Ingredient 10");

    /**
     * Initializes any controls. Set selection choices.
    */
    public void initialize() {
        sandwichChoices.getItems().addAll(
                "Chicken Sandwich",
                "Beef Sandwich",
                "Fish Sandwich"
                );
        sandwichChoices.getSelectionModel().selectFirst();
        if( sandwichChoices.getValue().equals("Chicken Sandwich") ) {
            ingredients.setText("Lettuce, tomato, fried chicken, mayo");
            sandwich = new Chicken();
        }

        extraIngredients.getItems().addAll(
                ingredient1.getExtraName(),
                ingredient2.getExtraName(),
                ingredient3.getExtraName(),
                ingredient4.getExtraName(),
                ingredient5.getExtraName(),
                ingredient6.getExtraName(),
                ingredient7.getExtraName(),
                ingredient8.getExtraName(),
                ingredient9.getExtraName(),
                ingredient10.getExtraName()
        );
        priceOfSandwich.setText( String.format("%.2f", sandwich.price() ) );
    }

    /**
     * Checks what combobox selection was chosen and sets the corresponding image.
     */
    public void comboBox() {
        if( sandwichChoices.getValue().equals("Chicken Sandwich") ) {
            sandwichImage.setImage(new Image("chicken_sandwich.png"));
            ingredients.setText("Lettuce, tomato, fried chicken, mayo");
            sandwich = new Chicken();
        }
        else if( sandwichChoices.getValue().equals("Beef Sandwich") ){
            sandwichImage.setImage(new Image("beef_sandwich.png"));
            ingredients.setText("Beef, cheese, tomato, lettuce");
            sandwich = new Beef();
        }
        else {
            sandwichImage.setImage(new Image("fish_sandwich.png"));
            ingredients.setText("Fried fish fillet, lettuce, tartar sauce");
            sandwich = new Fish();
        }
        priceOfSandwich.setText( String.format("%.2f", sandwich.price() ) );
    }

    /**
     * Opens a new stage when the "Show Order" button is clicked.
     * @throws IOException
     */
    public void showOrderClicked() throws IOException {
        new NewStage(); // create second stage
    }

    /**
     * When the Add button is clicked, Add the selected ingredients from the first ListView
     * to the second ListView to indicate it was selected and removes it from the first ListView.
     */
    public void add() {
        if( selectedIngredients.getItems().size() < MAX_EXTRAS ) { // Be sure that the size does not exceed the max amount of extras
            if( extraIngredients.getSelectionModel().getSelectedIndex() != -1 ) { // Tests whether or not an item was selected
                selectedIngredients.getItems().add( extraIngredients.getItems().remove(extraIngredients.getSelectionModel().getSelectedIndex()) );
                sandwich.extras.clear();
                sandwich.extras.addAll( selectedIngredients.getItems() );
                priceOfSandwich.setText( String.format("%.2f", sandwich.price() ) );
            }
            //extraIngredients.getItems().remove( extraIngredients.getSelectionModel().getSelectedIndex() );
        }
        else {
            // Can't add anymore items
        }
    }

    /**
     * When the remove button is clicked, remove the selected ingredients from the second ListView 
     * and adds it to the first ListView to indicate it was removed.
     */
    public void remove() {
        if( selectedIngredients.getItems().size() > 0 ){ // There are still items in the selected items
            if( selectedIngredients.getSelectionModel().getSelectedIndex() != -1 ) { // Tests whether or not an item was selected
                extraIngredients.getItems().add(selectedIngredients.getItems().remove(selectedIngredients.getSelectionModel().getSelectedIndex()));
                sandwich.extras.clear();
                sandwich.extras.addAll( selectedIngredients.getItems() );
                priceOfSandwich.setText( String.format("%.2f", sandwich.price() ) );
            }
        }
        else {
            // Print that there are no more items
        }
    }

    /**
     * When the Clear Selected button is clicked, remove the selected Ingredients and adds them
     * to the first ListView.
     */
    public void clear() {
        if( selectedIngredients.getItems().size() > 0 ) { // There are items selected
            extraIngredients.getItems().addAll( selectedIngredients.getItems() );
            selectedIngredients.getItems().clear();
            sandwich.extras.clear();
            sandwich.extras.addAll( selectedIngredients.getItems() );
            priceOfSandwich.setText( String.format("%.2f", sandwich.price() ) );
        }
        else {
            // There are no items to clear
        }
    }
}
