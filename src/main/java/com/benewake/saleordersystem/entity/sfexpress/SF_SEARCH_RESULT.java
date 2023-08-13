package com.benewake.saleordersystem.entity.sfexpress;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: xsong
 * date:2022/11/8 14:40
 * 描述：顺丰速运查询返回结果
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SF_SEARCH_RESULT {

    private String apiResponseID;

    private String apiErrorMsg;

    private String apiResultCode;

    private String apiResultData;

}
