package com.hicola.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author baiyang
 * @date 2021/7/19
 */
@Data
@TableName("p_schedule_import_export_record")
public class ImportOrExport {
    @TableId
    private String fid;
    private String itemName;
    private Date actualStart;
    private Date actualEnd;
    private Double monthEndProgress;
    private String wbsId;
    private String itemId;
    private String question;
}
