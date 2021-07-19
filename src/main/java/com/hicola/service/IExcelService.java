package com.hicola.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hicola.model.ImportOrExport;
import com.hicola.model.ResponseInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author baiyang
 * @date 2021/7/19
 */
public interface IExcelService extends IService<ImportOrExport> {
    ResponseInfo importData(MultipartFile multipartFile, HttpServletRequest request, HttpServletResponse response);

}
