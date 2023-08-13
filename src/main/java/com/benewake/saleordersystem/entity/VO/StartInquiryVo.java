package com.benewake.saleordersystem.entity.VO;

import com.benewake.saleordersystem.entity.Inquiry;
import lombok.Data;

import java.util.List;

/**
 * @author Lcs
 * @since 2023年07月16 13:56
 * 描 述： TODO
 */
@Data
public class StartInquiryVo {
    private List<Inquiry> inquiryList;
    private Integer startInquiry;
}
