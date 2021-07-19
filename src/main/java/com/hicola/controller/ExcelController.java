package com.hicola.controller;

import com.hicola.model.ResponseInfo;
import com.hicola.service.IExcelService;
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

/**
 * @author baiyang
 * @date 2021/7/19
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {
    private static final Logger logger = LoggerFactory.getLogger(ExcelController.class);

    private final IExcelService excelService;

    @Autowired
    public ExcelController(IExcelService excelService) {
        this.excelService = excelService;
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public Object importExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        ResponseInfo resObj = new ResponseInfo();
        try {
            resObj = excelService.importData(file, request, response);
            logger.info("导入成功");
        } catch (Exception e) {
            logger.warn("导入失败");
        }
        return resObj;
    }
}
