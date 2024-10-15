package org.carth.aoc2020

import org.carth.common.Puzzle

class Day21(input: String) : Puzzle<Int, String>() {

    private val dishes = input.lines().associate { line ->
        val ingredients = line.substringBefore(" (").split(" ").toSet()
        val allergens = line.substringAfter("(contains ").substringBefore(")").split(", ").toSet()
        ingredients to allergens
    }
    private val ingredients: Set<String> = dishes.keys.flatten().toSet()
    private val allergens: Set<String> = dishes.values.flatten().toSet()

    override fun solvePartOne(): Int {
        val ingredientsWOAllergens = ingredientsWOAllergens()
        return dishes.keys.map { ingredients ->
            ingredients intersect ingredientsWOAllergens
        }.flatten().size
    }

    override fun solvePartTwo(): String {
        val allergenByIngredient = allergenByIngredient()
        return allergenByIngredient.entries.sortedBy { it.key }.joinToString(",") { it.value }
    }

    private fun ingredientsWOAllergens(): Set<String> {
        val ingredientsWithAllergens = allergens.map { allergen ->
            val dishesWithAllergen = dishes.filter { (_, allergens) -> allergen in allergens }
            val ingredientsWithAllergen = dishesWithAllergen.map { (ingredients, _) -> ingredients }
            ingredientsWithAllergen.reduce { ingredientWithAllergen, ingredients -> ingredients intersect ingredientWithAllergen }
        }.flatten().toSet()
        return ingredients subtract ingredientsWithAllergens
    }

    private fun allergenByIngredient(): Map<String, String> {
        val safeIngredients = ingredientsWOAllergens()
        val allergenByIngredients = allergens.associateWith { allergen ->
            val dishesWithAllergen = dishes.entries.filter { (_, allergens) -> allergen in allergens }
            val onlyIngredientsWithAllergen =
                dishesWithAllergen.map { (ingredients, _) -> ingredients - safeIngredients }
            onlyIngredientsWithAllergen.reduce { ingredientWithAllergen, ingredients -> ingredients intersect ingredientWithAllergen }
                .toMutableSet()
        }.toMutableMap()
        val res: MutableMap<String, String> = mutableMapOf()
        while (allergenByIngredients.isNotEmpty()) {
            val only1Ingredient = allergenByIngredients
                .filter { (_, ingredients) -> ingredients.size == 1 }
                .map { (allergen, ingredients) -> allergen to ingredients.first() }
                .toMap()
            res.putAll(only1Ingredient)
            only1Ingredient.keys.forEach { allergen -> allergenByIngredients.remove(allergen) }
            allergenByIngredients.values.forEach { ingredients -> ingredients.removeAll(only1Ingredient.values.toSet()) }
        }
        return res
    }
}
