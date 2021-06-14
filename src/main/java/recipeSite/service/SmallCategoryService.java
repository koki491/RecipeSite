package recipeSite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import recipeSite.domain.SmallCategory;
import recipeSite.mapper.SmallCategoryMapper;

import java.util.List;

@Service
@Transactional
public class SmallCategoryService {

    @Autowired
    private SmallCategoryMapper smallCategoryMapper;

    public SmallCategory findById(Integer id) {
        SmallCategory smallCategory = new SmallCategory();
        smallCategory.setId(id);
        return this.smallCategoryMapper.findById(smallCategory);
    }

    public List<SmallCategory> findByLargeCategoryId(Integer id) {
        SmallCategory smallCategory = new SmallCategory();
        smallCategory.setLarge_category_id(id);
        return this.smallCategoryMapper.findByLargeCategoryId(smallCategory);
    }
}
