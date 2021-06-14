package recipeSite.mapper;

import org.apache.ibatis.annotations.Mapper;
import recipeSite.domain.SmallCategory;

import java.util.List;

@Mapper
public interface SmallCategoryMapper {
    SmallCategory findById(SmallCategory smallCategory);

    List<SmallCategory> findByLargeCategoryId(SmallCategory smallCategory);
}
