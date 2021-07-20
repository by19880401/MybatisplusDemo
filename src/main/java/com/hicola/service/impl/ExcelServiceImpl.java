package com.hicola.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.hicola.mapper.ExcelMapper;
import com.hicola.model.ImportOrExport;
import com.hicola.model.ImportOrExportVo;
import com.hicola.model.ResponseInfo;
import com.hicola.service.IExcelService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author baiyang
 * @date 2021/7/19
 */
@Service
public class ExcelServiceImpl extends ServiceImpl<ExcelMapper, ImportOrExport> implements IExcelService {
    private static final Logger logger = LoggerFactory.getLogger(ExcelServiceImpl.class);

    private static final String POINT = ".";
    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";

    private static final String TITLE_NAME = "任务项名称";
    private static final String TITLE_START = "（实际）开始时间";
    private static final String TITLE_END = "（实际）结束时间";
    private static final String TITLE_NO_PROGRESS = "截止上月末累计完成比例（%）";
    private static final String TITLE_COM_PROGRESS = "本月实际完成比例（%）";
    private static final String TITLE_ENTITY_ID = "实体单元编码";
    private static final String TITLE_ITEM_ID = "任务项ID";

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private final ExcelMapper excelMapper;

    @Autowired
    public ExcelServiceImpl(ExcelMapper excelMapper) {
        this.excelMapper = excelMapper;
    }

    @Autowired


    private static String parseObject(Object obj) {
        if (Objects.isNull(obj)) {
            return "";
        }
        if (obj instanceof Date) {
            Date dateVal = (Date) obj;
            return DateUtil.format(dateVal, DATE_FORMAT);
        } else if (obj instanceof Long) {
            Long longVal = (Long) obj;
            return Long.toString(longVal);
        } else if (obj instanceof Double) {
            Double doubleVal = (Double) obj;
            return Double.toString(doubleVal);
        } else if (obj instanceof Integer) {
            Integer intVal = (Integer) obj;
            return Integer.toString(intVal);
        } else if (obj instanceof String) {
            String strVal = (String) obj;
            return strVal;
        } else {
            return String.valueOf(obj);
        }
    }

    @Override
    public ResponseInfo importData(MultipartFile multipartFile, HttpServletRequest request, HttpServletResponse response) {
        if (null == multipartFile || multipartFile.isEmpty()) {
            logger.warn("上传的文件为空");
            return ResponseInfo.returnErrorMsg();
        }
        if (isExcelFile(multipartFile)) {
            logger.warn("文件格式不正确");
            return ResponseInfo.returnErrorMsg();
        }
        List<ImportOrExportVo> excelDataList = readExcelData(multipartFile);
        if (CollectionUtils.isEmpty(excelDataList)) {
            logger.warn("读取的数据为空");
            return ResponseInfo.returnErrorMsg();
        }

        /**List<ImportOrExportVo>转换为List<ImportOrExport>*/
        List<ImportOrExport> ieList = excelDataList.stream().map(excelObj -> {
            ImportOrExport ie = new ImportOrExport();
            ie.setItemId(excelObj.getItemId());
            ie.setItemName(excelObj.getItemName());
            ie.setActualStart(DateUtil.parseDate(excelObj.getStartStr()));
            ie.setActualEnd(DateUtil.parse(excelObj.getEndStr()));
            ie.setMonthEndProgress(Double.parseDouble(excelObj.getMonthActualProgress()));
            ie.setWbsId(excelObj.getWebsId());
            ie.setQuestion(excelObj.getQuestion());
            return ie;
        }).collect(Collectors.toList());
        /**批量保存，默认每次batch的数目为：1000*/
        this.saveBatch(ieList);
        return ResponseInfo.returnSuccessMsg();
    }

    private boolean isExcelFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String suffix = StringUtils.substringAfterLast(fileName, POINT);
        return (!StringUtils.equalsAny(suffix, XLS, XLSX));
    }

    private List<ImportOrExportVo> readExcelData(MultipartFile file) {
        List<ImportOrExportVo> resultList = Lists.newArrayList();
        InputStream inputStream;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            logger.warn("获取文件流失败");
            return resultList;
        }
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<Map<String, Object>> excelDataList = reader.readAll();
        if (CollectionUtils.isEmpty(excelDataList)) {
            return resultList;
        }
        excelDataList.stream().forEach(map -> {
            ImportOrExportVo vo = new ImportOrExportVo();
            String titleName = parseObject(map.get(TITLE_NAME));
            String titleStart = parseObject(map.get(TITLE_START));
            String titleEnd = parseObject(map.get(TITLE_END));
            String titleNoProgress = parseObject(map.get(TITLE_NO_PROGRESS));
            String titleComProgress = parseObject(map.get(TITLE_COM_PROGRESS));
            String titleEntityId = parseObject(map.get(TITLE_ENTITY_ID));
            String titleItemId = parseObject(map.get(TITLE_ITEM_ID));
            vo.setItemName(StringUtils.isBlank(titleName) ? "" : titleName);
            vo.setStartStr(StringUtils.isBlank(titleStart) ? "" : titleStart);
            vo.setEndStr(StringUtils.isBlank(titleEnd) ? "" : titleEnd);
            vo.setLastMonthEndPlanProgress(StringUtils.isBlank(titleNoProgress) ? "" : titleNoProgress);
            vo.setMonthActualProgress(StringUtils.isBlank(titleComProgress) ? "" : titleComProgress);
            vo.setWebsId(StringUtils.isBlank(titleEntityId) ? "" : titleEntityId);
            vo.setItemId(StringUtils.isBlank(titleItemId) ? "" : titleItemId);
            resultList.add(vo);
        });
        logger.info("读取excel文件数据成功，共{}条数据", resultList.size());
        return resultList;
    }

}
