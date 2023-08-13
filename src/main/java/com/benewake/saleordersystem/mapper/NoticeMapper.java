package com.benewake.saleordersystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.benewake.saleordersystem.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author Lcs
 */
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {
    /**
     * 根据创建人id查询所有消息 不填userid则为所有消息
     * @param userId
     * @return
     */
    @Select("<script>" +
            "select id,create_user_id as createUserId,FIM_user_name as createUsername,create_time,update_time,message " +
            "from fim_notice_table " +
            "left join fim_users_table on create_user_id = FIM_user_id " +
            "where is_deleted = 0 " +
            "<if test='userId!=null'>" +
            "and create_user_id = #{userId} " +
            "</if>" +
            "<if test='type!=null'>" +
            "and type = #{type} " +
            "</if>" +
            "</script>")
    List<Map<String,Object>> findNoticeByUserId(@Param("userId")Long userId,@Param("type")Integer type);
}
