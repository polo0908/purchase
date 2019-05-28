package com.cn.hnust.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.ProjectTask;

public interface ProjectTaskMapper {
    int deleteByPrimaryKey(Integer id);
    int deleteByQualityId(Integer qualityId);
    
    /**
     * 根据项目号删除任务
     * @Title deleteByProjectId 
     * @Description
     * @param qualityId
     * @return
     * @return int
     */
    int deleteByProjectNo(String projectNo);

    int insert(ProjectTask record);

    int insertSelective(ProjectTask record);

    ProjectTask selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectTask record);

    int updateByPrimaryKey(ProjectTask record);

	List<ProjectTask> selectProjectTask(ProjectTask projectTask);

	List<ProjectTask> selectProjectTaskCount(ProjectTask projectTask);
    /**
     * 统计每个人未完成的数量
     * @return
     */
	List<ProjectTask> statisticsProjectTaskNoFinish(ProjectTask projectTask);
    /**
     * 统计最近30天完成数量
     * @return
     */
	List<ProjectTask> statisticsProjectTaskFinish(ProjectTask projectTask);

	List<ProjectTask> statisticsProjectTaskOnTime(String acceptor);

	List<ProjectTask> statisticsProjectTaskAllFinish(String acceptor);

	List<ProjectTask> selectMeetingRecordTask(String meetingNo);
    /**
     * 统计会议任务未完成的数量
     * @param acceptor
     * @return
     */
	List<ProjectTask> selectMeetingRecordTaskNoFinish(String acceptor);

	List<ProjectTask> selectProjectTaskIfExist(ProjectTask projectTask);
    /**
     *查询拓展最久的任务时间
     * @param userName
     * @return
     */
	ProjectTask selectProjectTaskMaxDate(@Param("userName")String userName);
	
	
	int hasAlreadyTask(@Param("triggerId")int triggerId,@Param("userId")int userId,@Param("projectNo")String projectNo);
	
	List<ProjectTask> selectAll();
	
	List<ProjectTask> selectByQualityId(@Param("qualityId")Integer qualityId);
	
	/**
	 * 批量插入
	 * @Title insertBatch 
	 * @Description
	 * @return
	 * @return int
	 */
	int insertBatch(List<ProjectTask> list);
	
	/**
	 * 根据项目号查询各状态下任务数
	 * @Title selectCountByStatus 
	 * @Description
	 * @return
	 * @return Map<String,Integer>
	 */
	Map<String,Long> selectCountByStatus(@Param("projectNo")String projectNo);
	
	
	/**
	 * 根据项目号查询任务
	 * @Title selectByProjectNo 
	 * @Description
	 * @param projectNo
	 * @return
	 * @return List<ProjectTask>
	 */
	List<ProjectTask> selectByProjectNo(@Param("projectNo")String projectNo);
	
}