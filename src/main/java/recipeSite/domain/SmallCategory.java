package recipeSite.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmallCategory {
    private Integer id;
    private String category_name;
    private LargeCategory large_category_id;
}
