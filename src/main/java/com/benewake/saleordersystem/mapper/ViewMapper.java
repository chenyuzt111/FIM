package com.benewake.saleordersystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.benewake.saleordersystem.entity.View;
import jnr.ffi.annotations.In;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author Lcs
 */
@Mapper
public interface ViewMapper extends BaseMapper<View> {

    /**
     * 根据用户id和表id获取用户新增的视图
     * @param userId
     * @param tableId
     * @return
     */
    @Select({"call fim_aps_system.get_user_view(#{userId,mode=IN,jdbcType=BIGINT},#{tableId,mode=IN,jdbcType=BIGINT})"})
    List<View> getUserView(@Param("userId")Long userId, @Param("tableId")Long tableId);

    /**
     * 根据表id获取所有列信息
     * @param tableId
     * @return
     */
    @Select("<script>" +
            "select * from fim_col_table " +
            "where table_id = #{tableId} " +
            "</script>")
    List<Map<String, Object>> getAllCols(@Param("tableId") Long tableId);

    @Insert("<script> " +
            "Insert into fim_view_table(user_id,view_name,table_id) "+
            "values " +
            "(" +
            "#{view.userId},#{view.viewName},#{view.tableId}" +
            ")" +
            "</script>")
    int insertView(@Param("view") View view);
}
