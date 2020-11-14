import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;

public class Controller {

    @FXML private ComboBox sandwichChoices;
    @FXML private Label ingredients;
    @FXML private ImageView sandwichImage;
    @FXML private ListView extraIngredients;
    @FXML private ListView selectedIngredients;

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

    public void initialize() {
        sandwichChoices.getItems().addAll(
                "Chicken Sandwich",
                "Beef Sandwich",
                "Fish Sandwich"
                );
        sandwichChoices.getSelectionModel().selectFirst();
        if( sandwichChoices.getValue().equals("Chicken Sandwich") ) {
            ingredients.setText("Lettuce, tomato, fried chicken, mayo");
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

    }

    public void comboBox() {
        if( sandwichChoices.getValue().equals("Chicken Sandwich") ) {
            sandwichImage.setImage(new Image("chicken_sandwich.png"));
            ingredients.setText("Lettuce, tomato, fried chicken, mayo");
        }else if( sandwichChoices.getValue().equals("Beef Sandwich") ){
            sandwichImage.setImage(new Image("beef_sandwich.png"));
            ingredients.setText("Beef, cheese, tomato, lettuce");
        }else{
            sandwichImage.setImage(new Image("fish_sandwich.png"));
            ingredients.setText("Fried fish fillet, lettuce, tartar sauce");
        }

    }

    public void add(){
        if( selectedIngredients.getItems().size() < MAX_EXTRAS ) { // Be sure that the size does not exceed the max amount of extras
            if( extraIngredients.getSelectionModel().getSelectedIndex() != -1 ) { // Tests whether or not an item was selected
                selectedIngredients.getItems().add(extraIngredients.getItems().remove(extraIngredients.getSelectionModel().getSelectedIndex()));
            }
            //extraIngredients.getItems().remove( extraIngredients.getSelectionModel().getSelectedIndex() );
        }else{ // Can't add anymore items

        }
    }

    public void remove(){
        if( selectedIngredients.getItems().size() > 0 ){ // There are still items in the selected items
            if( selectedIngredients.getSelectionModel().getSelectedIndex() != -1 ) { // Tests whether or not an item was selected
                extraIngredients.getItems().add(selectedIngredients.getItems().remove(selectedIngredients.getSelectionModel().getSelectedIndex()));
            }
        }else{
            // Print that there are no more items
        }
    }

    public void clear(){
        if( selectedIngredients.getItems().size() > 0 ) { // There are items selected
            extraIngredients.getItems().addAll( selectedIngredients.getItems() );
            selectedIngredients.getItems().clear();
        }else{ // There are no items to clear

        }
    }


}
