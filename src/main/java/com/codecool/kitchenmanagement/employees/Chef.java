package com.codecool.kitchenmanagement.employees;

import com.codecool.kitchenmanagement.Ingredient;
import com.codecool.kitchenmanagement.IngredientStore;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

public class Chef extends CookerEmployee implements IngredientStore {
    private Random random = new Random();
    private IngredientStore store;

    public Chef(String name, LocalDate birthDate, int salary) {
        super(name, birthDate, salary);
    }

    public void setStore(IngredientStore store) {
        this.store = store;
    }

    @Override
    protected void cookWithKnives() {
        var shouldAskForIngredient = random.nextBoolean();

        if (!shouldAskForIngredient || store == null) {
            return;
        }

        var randomIngredient = Ingredient.randomIngredient();
        System.out.format("I need %s\n", randomIngredient);
        Optional<Ingredient> received = requestIngredient(randomIngredient);
        String ingredientName = received.map(Ingredient::toString).orElse("NOTHING");
        System.out.println("I got " + ingredientName);
    }

    @Override
    public Optional<Ingredient> requestIngredient(Ingredient ingredient) {
        return store.requestIngredient(ingredient);
    }
}
