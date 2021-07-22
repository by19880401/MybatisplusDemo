package com.hicola.service.impl;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.hicola.exception.ExcelBatchException;
import com.hicola.model.ImportOrExportVo;
import com.hicola.model.enums.ErrorCodeEnum;
import com.hicola.utils.ObjectUtils;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 注：监听器ExcelBatchHandleListener继承AnalysisEventListener，
 * 这里特别需要注意的是，因为ExcelBatchHandleListener并没有交给Spring容器来管理，
 * 所以**Service和*Mapper等无法注入监听器代码
 * 这里通过构造方法的方式传过来，从而完成对数据库的操作
 *
 * @author bai.yang email:willis.bai@outlook.com
 * @date 2021/7/21
 * @
 */
public class ExcelBatchHandleListener extends AnalysisEventListener<ImportOrExportVo> {
    private static final Logger logger = LoggerFactory.getLogger(ExcelBatchHandleListener.class);

    public ExcelBatchHandleListener() {
        /**在进行invoke之前，肯定会有提前初始化部分参数或者查询db的操作，这些操作可以在构造器中处理*/
        doBeforeAllAnalysed();
    }

    private void doBeforeAllAnalysed() {
        logger.info("ExcelBatchHandleListener--doBeforeAllAnalysed：初始化");
    }

    @SneakyThrows
    @Override
    public void invoke(ImportOrExportVo excelObj, AnalysisContext analysisContext) {
//        logger.info("ExcelBatchHandleListener--invoke：一行一行处理数据");
        if (ObjectUtils.INSTANCE.isObjAllFieldsNull(excelObj)) {
            throw new ExcelBatchException(ErrorCodeEnum.RECORD_NULL_ERROR);
        }
        logger.info("==>{}", excelObj);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        logger.info("ExcelBatchHandleListener--doAfterAllAnalysed：处理完成后汇总收尾");
        int num = analysisContext.readRowHolder().getRowIndex();
        logger.info("处理数据总量: {}", num);
    }

    @Override
    public void onException(Exception exception, AnalysisContext context) {
//        logger.info("ExcelBatchHandleListener--onException：一行一行异常处理");
        Object exceptionLineObj = context.readRowHolder().getCurrentRowAnalysisResult();
        if (exception instanceof ExcelBatchException) {
            ExcelBatchException ebe = (ExcelBatchException) exception;
            logger.warn("异常信息: {}, 异常数据对象：{}", ebe.getMessage(), exceptionLineObj);
        } else {
            logger.warn("其它未知异常");
        }
    }
}
