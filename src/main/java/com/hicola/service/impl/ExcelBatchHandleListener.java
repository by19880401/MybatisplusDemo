package com.hicola.service.impl;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.hicola.model.ImportOrExportVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bai.yang email:willis.bai@outlook.com
 * @date 2021/7/21
 * @
 */
public class ExcelBatchHandleListener extends AnalysisEventListener<ImportOrExportVo> {
    private static final Logger logger = LoggerFactory.getLogger(ExcelBatchHandleListener.class);

    @Override
    public void invoke(ImportOrExportVo excelObj, AnalysisContext analysisContext) {
        logger.info("==>{}", excelObj);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        int num = analysisContext.readRowHolder().getRowIndex();
        logger.info("num is: {}", num);
    }
}
