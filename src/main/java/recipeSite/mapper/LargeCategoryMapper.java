package recipeSite.mapper;

import org.apache.ibatis.annotations.Mapper;
import recipeSite.domain.LargeCategory;
import java.util.List;

@Mapper
public interface LargeCategoryMapper {
    List<LargeCategory> findAll();
}
