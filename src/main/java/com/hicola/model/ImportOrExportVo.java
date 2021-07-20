package com.hicola.model;

import lombok.Data;

/**
 * @author baiyang
 * @date 2021/7/19
 */
@Data
public class ImportOrExportVo {
    private String itemName;
    private String startStr;
    private String endStr;
    private String lastMonthEndPlanProgress;
    private String monthActualProgress;
    private String websId;
    private String itemId;
    private String question;
}
