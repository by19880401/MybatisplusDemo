package com.hicola.controller;

import com.hicola.model.ResponseInfo;
import com.hicola.service.IExcelBatchHandleService;
import com.hicola.service.IExcelService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author baiyang
 * @date 2021/7/19
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {
    private static final Logger logger = LoggerFactory.getLogger(ExcelController.class);

    private final IExcelService excelService;

    private final IExcelBatchHandleService excelBatchHandleService;

    /**
     * 当注入多个service时，构造器注入应该如下方式写
     * @param excelService
     * @param excelBatchHandleService
     */
    @Autowired
    public ExcelController(IExcelService excelService, IExcelBatchHandleService excelBatchHandleService) {
        this.excelService = excelService;
        this.excelBatchHandleService = excelBatchHandleService;
    }


    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public Object importExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        ResponseInfo resObj = new ResponseInfo();
        try {
            resObj = excelService.importData(file, request, response);
            if (Objects.isNull(resObj)) {
                return ResponseInfo.returnErrorMsg();
            }
            if (StringUtils.equals(resObj.getErrorCode(), ResponseInfo.SUCCESS_CODE)) {
                logger.info("导入成功");
                return ResponseInfo.returnSuccessMsg();
            }
            return resObj;
        } catch (Exception e) {
            logger.warn("导入失败", e);
            return ResponseInfo.returnErrorMsg();
        }
    }

    @RequestMapping(value = "/importBatch", method = RequestMethod.POST)
    public Object importBatchExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        ResponseInfo resObj;
        try {
            resObj = excelBatchHandleService.importBatchData(file, request, response);
        } catch (Exception e) {
            logger.warn("操作失败", e);
            return ResponseInfo.returnErrorMsg();
        }
        return ResponseInfo.returnDefaultMsg(resObj);
    }
}
