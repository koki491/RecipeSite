package recipeSite.web;

import lombok.Data;

@Data
public class RecipeForm {
    private String cooking_name;
    private byte[] cooking_image;
    private String large_category_name;
    private String small_category_name;
}
