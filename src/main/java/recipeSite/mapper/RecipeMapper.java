package recipeSite.mapper;

import org.apache.ibatis.annotations.Mapper;

import recipeSite.domain.Recipe;

@Mapper
public interface RecipeMapper {
    Recipe findById(Integer id);

    void save(Recipe recipe);

    void update(Recipe recipe);

    void delete(Recipe recipe);
}
