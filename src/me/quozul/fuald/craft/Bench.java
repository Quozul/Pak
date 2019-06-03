package me.quozul.fuald.craft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bench {
    List<Recipe> RECIPES = new ArrayList<>();
    String NAME;

    public Bench(String name, Recipe... recipes) {
        this.setName(name);
        this.addRecipes(recipes);
    }

    public List<Recipe> getRecipes() {
        return this.RECIPES;
    }

    public void addRecipes(Recipe... recipes) {
        RECIPES.addAll(Arrays.asList(recipes));
    }

    public void setName(String name) {
        this.NAME = name;
    }

    public String getName() {
        return this.NAME;
    }
}
