package recipeSite.mapper;

import org.apache.ibatis.annotations.Mapper;

import recipeSite.domain.Recipe;

import java.util.List;

@Mapper
public interface RecipeMapper {
    //レシピを表示
    Recipe findById(Recipe recipe);

    //大Categoryのidかつ小Categoryのidのレシピを表示
    List<Recipe> findByLargeSmallId(Recipe recipe);

    List<Recipe> findByName(Recipe recipe);

    List<Recipe> findByUserId(Recipe recipe);

    void save(Recipe recipe);

    void update(Recipe recipe);

    void delete(Recipe recipe);
}
