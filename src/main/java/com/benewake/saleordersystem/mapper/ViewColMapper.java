package com.benewake.saleordersystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.benewake.saleordersystem.entity.ViewCol;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ViewColMapper extends BaseMapper<ViewCol> {

    /**
     * 根据viewId获取相应的列信息
     * @param viewId
     * @return
     */
    @Select({"call fim_aps_system.get_view_col(#{viewId,mode=IN,jdbcType=BIGINT})"})
    List<Map<String,Object>> getColMaps(@Param("viewId")Long viewId);

    /**
     * 新增方案列
     * @param cols
     * @return
     */
    @Insert("<script> " +
            "Insert into fim_view_col_table "+
            "values " +
            "<foreach collection='cols' item='col' separator=','> " +
            "(" +
            "#{col.viewId},#{col.colId},#{col.valueOperator},#{col.colValue},#{col.colSeq} " +
            ")" +
            "</foreach>" +
            "</script>")
    int saveViewColList(@Param("cols") List<ViewCol> cols);

    /**
     * 删除对应ViewId下的所有列
     * @param viewId
     * @return
     */
    @Delete("<script>" +
            "delete from fim_view_col_table " +
            "where view_id = #{viewId} " +
            "</script>")
    int deleteByViewId(@Param("viewId") Long viewId);
}
