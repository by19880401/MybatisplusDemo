package com.hicola.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hicola.model.ImportOrExport;
import com.hicola.model.ResponseInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author bai.yang email:willis.bai@outlook.com
 * @date 2021/7/21
 * @
 */
public interface IExcelBatchHandleService extends IService<ImportOrExport> {

    ResponseInfo importBatchData(MultipartFile multipartFile, HttpServletRequest request, HttpServletResponse response);
}
