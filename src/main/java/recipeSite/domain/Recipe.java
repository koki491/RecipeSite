package recipeSite.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    private Integer id;
    private Integer user_id;
    private String cooking_name;
    private byte[] cooking_image;
    private String coking_recipe;
    private Integer small_category_id;
    private Integer large_category_id;
}
