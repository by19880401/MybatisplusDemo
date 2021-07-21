package com.hicola.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author baiyang
 * @date 2021/7/19
 */
@Data
public class ImportOrExportVo {
    @ExcelProperty(value = "任务项名称", index = 0)
    private String itemName;
    @ExcelProperty(value = "实际开始时间", index = 1)
    private String startStr;
    @ExcelProperty(value = "实际完成时间", index = 2)
    private String endStr;
    @ExcelProperty(value = "截止上月末累计完成比例（%）", index = 3)
    private String lastMonthEndPlanProgress;
    @ExcelProperty(value = "本月实际完成比例（%）", index = 4)
    private String monthActualProgress;
    @ExcelProperty(value = "实体单元编码", index = 5)
    private String wbsId;
    @ExcelProperty(value = "任务项ID", index = 6)
    private String itemId;
    @ExcelProperty(value = "异常提示", index = 7)
    private String question;
}
