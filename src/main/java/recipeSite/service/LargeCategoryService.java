package recipeSite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import recipeSite.domain.LargeCategory;
import recipeSite.mapper.LargeCategoryMapper;

import java.util.List;

@Service
@Transactional
public class LargeCategoryService {

    @Autowired
    private LargeCategoryMapper largeCategoryMapper;

    public List<LargeCategory> findAll() {
        return this.largeCategoryMapper.findAll();
    }
}
