package com.hicola.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hicola.model.ImportOrExport;
import org.springframework.stereotype.Repository;

/**
 * @author baiyang
 * @date 2021/7/19
 */
@Repository
public interface ExcelMapper extends BaseMapper<ImportOrExport> {
}
