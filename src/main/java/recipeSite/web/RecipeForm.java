package recipeSite.web;

import lombok.Data;

@Data
public class RecipeForm {
    private String cooking_name;
    private byte[] cooking_image;
    private String cooking_recipe;
    private Integer large_category_id;
    private Integer small_category_id;
}
