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

    private Image image;

    Extra ingredient1 = new Extra("Ingredient 1");
    Extra ingredient2 = new Extra("Ingredient 2");
    Extra ingredient3 = new Extra("Ingredient 3");
    Extra ingredient4 = new Extra("Ingredient 4");
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
    }

    public void remove(){

    }

    public void clear(){

    }


}
