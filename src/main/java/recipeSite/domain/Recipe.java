package recipeSite.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    private Integer id;
    private User user_id;
    private String cooking_name;
    private byte[] cooking_image;
    private Integer large_category_id;
}
