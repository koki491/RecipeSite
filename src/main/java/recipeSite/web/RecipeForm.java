package recipeSite.web;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
public class RecipeForm {
    private String cooking_name;
    private MultipartFile cooking_image;
    private String cooking_recipe;
    private Integer large_category_id;
    private Integer small_category_id;
}
