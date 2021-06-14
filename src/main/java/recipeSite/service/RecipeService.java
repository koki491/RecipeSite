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
        return recipeMapper.findById(id);
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
