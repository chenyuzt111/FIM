package com.benewake.saleordersystem.excel.model;

import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.util.Date;


/**
 * @author Lcs
 */
@Data
public class BillMaterialModel {

    /**
     * BOM版本
     */
    private String BOMVersion;

    /**
     * BOM简称
     */
    private String BOMAbbreviation;

    /**
     * BOM分类
     */
    private String BOMClassification;

    /**
     * BOM用途
     */
    private String BOMPurpose;

    /**
     * 父项物料编码
     */
    private String parentMaterialCode;

    /**
     * 父类规格型号
     */
    private String parentSpecification;

    /**
     * 数据状态
     */
    private String dataStatus;

    /**
     * 禁用状态
     */
    private String disableStatus;

    /**
     * 项次
     */
    private String item;

    /**
     * 子项物料编码
     */
    private String sonMaterialCode;

    /**
     * 子类规格型号
     */
    private String sonSpecification;

    /**
     * 子项单位
     */
    private String sonUnit;

    /**
     * 用量分子
     */
    private Long molecule;

    /**
     * 用量分母
     */
    private Long denominator;

    /**
     * 固定损耗
     */
    private Long fixedLoss;

    /**
     * 变动损耗率
     */
    private Long variableLossRate;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 默认发料仓库
     */
    private String defaultIssuingWarehouse;

    /**
     * 发料方式
     */
    private String materialIssuanceMethod;

    /**
     * 默认发料仓位
     */
    private String defaultIssuingBin;

    /**
     * 供应类型
     */
    private String supplyType;

    /**
     * 生效日期
     */
    @DateTimeFormat(value = "yyyy/MM/dd")
    private Date effectiveDate;

    /**
     * 失效日期
     */
    @DateTimeFormat(value = "yyyy/MM/dd")
    private Date expiryDate;

    /**
     * 北醒图纸号
     */
    private String benewakeDrawingNo;

    /**
     * 北醒图纸号版本
     */
    private String benewakeDrawingNoVersion;

}
