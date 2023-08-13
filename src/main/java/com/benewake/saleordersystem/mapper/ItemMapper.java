package com.benewake.saleordersystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.benewake.saleordersystem.entity.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author Lcs
 */
@Mapper
public interface ItemMapper extends BaseMapper<Item> {
    /**
     * 获取订单中po pr yg状态对应的物料库存情况
     * @return
     */
    @Select("<script>" +
            "select c.item_id,item_code,item_name,poCount,prCount,ygCount,ifnull(priority,0) as priority " +
            "from (select b.item_id,b.item_code,b.item_name,sum(case when inquiry_type=1 then sale_num else 0 end) as poCount," +
            "sum(case when inquiry_type = 2 then sale_num else 0 end) as prCount, " +
            "sum(case when inquiry_type = 3 then sale_num else 0 end) as ygCount " +
            "from fim_item_table as b left join fim_inquiry_table as a on a.item_id = b.item_id " +
            "and a.state>=0 " +
            "<if test='startTime!=null'> " +
            "and created_time &gt; #{startTime} " +
            "</if> " +
            "<if test='endTime!=null'> " +
            "and created_time &lt; #{endTime} " +
            "</if> " +
            "group by b.item_id ) as c " +
            "left join ( " +
            "select item_id,priority from inventory_top_table " +
            "<if test='userId!=null'>" +
            "where user_id = #{userId} " +
            "</if> " +
            "<if test='userId==null'>" +
            "where user_id = -1 " +
            "</if> " +
            ") as d on  c.item_id = d.item_id " +
            "order by priority desc " +
            "</script>")
    List<Map<String,Object>> getInventories(@Param("userId")Long userId,@Param("startTime")String startTime,@Param("endTime")String endTime);

    /**
     * 订单编码模糊查询
     * @param itemCode
     * @return
     */
    @Select("<script>" +
            "select item_id as id,item_code as itemCode,item_name as itemName," +
            "b.item_type_name as itemType from fim_item_table as a " +
            "left join item_type_dic as b on a.item_type = b.item_type " +
            "where item_code like #{itemCode}" +
            "</script>")
    List<Map<String,Object>> selectCodeLikeList(String itemCode);

    @Select("<script>" +
            "select item_type_name from item_type_dic " +
            "where item_type_name like #{type}" +
            "</script>")
    List<String> getItemTypeList(@Param("type") String s);
}
