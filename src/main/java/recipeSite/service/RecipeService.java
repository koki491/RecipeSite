package recipeSite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import recipeSite.domain.Recipe;
import recipeSite.mapper.RecipeMapper;

import java.util.List;

@Service
@Transactional
public class RecipeService {
    @Autowired
    RecipeMapper recipeMapper;

    public Recipe findById(Integer id) {
        Recipe recipe = new Recipe();
        recipe.setId(id);
        return recipeMapper.findById(recipe);
    }

    public List<Recipe> findByLargeSmallId(Integer large_category_id, Integer small_category_id) {
        Recipe recipe = new Recipe();
        recipe.setLarge_category_id(large_category_id);
        recipe.setSmall_category_id(small_category_id);
        return recipeMapper.findByLargeSmallId(recipe);
    }

    public List<Recipe> findByName(String name) {
        Recipe recipe = new Recipe();
        recipe.setCooking_name(name);
        return recipeMapper.findByName(recipe);
    }

    public List<Recipe> findByUserId(Integer user_id) {
        Recipe recipe = new Recipe();
        recipe.setUser_id(user_id);
        return recipeMapper.findByUserId(recipe);
    }

    public void create(Recipe recipe) {
        recipeMapper.save(recipe);
    }

    public void update(Recipe recipe) {
        recipeMapper.update(recipe);
    }

    public void delete(Recipe recipe) {
        recipeMapper.delete(recipe);
    }
}
