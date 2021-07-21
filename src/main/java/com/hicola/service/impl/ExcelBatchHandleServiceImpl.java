package com.hicola.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hicola.mapper.ExcelMapper;
import com.hicola.model.ImportOrExport;
import com.hicola.model.ImportOrExportVo;
import com.hicola.model.ResponseInfo;
import com.hicola.service.IExcelBatchHandleService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @author bai.yang email:willis.bai@outlook.com
 * @date 2021/7/21
 * @
 */
@Service
public class ExcelBatchHandleServiceImpl extends ServiceImpl<ExcelMapper, ImportOrExport> implements IExcelBatchHandleService {
    private static final Logger logger = LoggerFactory.getLogger(ExcelBatchHandleServiceImpl.class);

    @Value("${filePath.temp}")
    private String temporaryFilePath;

    private static final String POINT = ".";
    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";

    @Override
    public ResponseInfo importBatchData(MultipartFile multipartFile, HttpServletRequest request, HttpServletResponse response) {
        if (null == multipartFile || multipartFile.isEmpty()) {
            logger.warn("上传的文件为空");
            return ResponseInfo.returnErrorMsg();
        }
        if (isExcelFile(multipartFile)) {
            logger.warn("文件格式不正确");
            return ResponseInfo.returnErrorMsg();
        }
        ResponseInfo res;
        String tempFilePath = temporaryFilePath + File.separator + System.currentTimeMillis() + ".xlsx";
        File tempFile = new File(tempFilePath);
        try {
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), tempFile);
            EasyExcel.read(tempFile, ImportOrExportVo.class, new ExcelBatchHandleListener()).sheet().doRead();
            res = ResponseInfo.returnSuccessMsg();
        } catch (IOException e) {
            logger.warn("解析、处理excel文件失败", e);
            res = ResponseInfo.returnErrorMsg();
        } finally {
            logger.info("删除临时文件");
            FileUtils.deleteQuietly(tempFile);
        }
        return res;
    }

    private boolean isExcelFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String suffix = StringUtils.substringAfterLast(fileName, POINT);
        return (!StringUtils.equalsAny(suffix, XLS, XLSX));
    }
}
