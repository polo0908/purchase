package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.ProjectFactory;
import com.cn.hnust.pojo.ProjectFactoryQuery;

public interface ProjectFactoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectFactory record);

    int insertSelective(ProjectFactory record);

    ProjectFactory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectFactory record);

    int updateByPrimaryKey(ProjectFactory record);
    
    
    /**
     * 批量更新工厂状态
     * @Title updateBatch 
     * @Description
     * @param factoryList
     * @return
     * @return int
     */
    int updateBatch(List<ProjectFactory> factoryList);
    
    
    /**
     * 根据项目号和工厂id查询
     * @Title selectByProjectNoAndFactoryId 
     * @Description
     * @param projectNo
     * @param factoryId
     * @return
     * @return ProjectFactory  
     */
    ProjectFactory selectByProjectNoAndFactoryId(@Param("projectNo")String projectNo,@Param("factoryId")String factoryId);
    
    
    /**
     * 根据项目号查询
     * @Title selectByProjectNoAndFactoryId 
     * @Description
     * @param projectNo
     * @param factoryId
     * @return
     * @return ProjectFactory
     */
    List<ProjectFactory> selectByProjectNo(@Param("projectNo")String projectNo);   
    
    
    /**
     * 根据项目号查询
     * @Title selectByProjectNoAndFactoryId 
     * @Description
     * @param projectNo
     * @param factoryId
     * @return
     * @return ProjectFactory
     */
    List<ProjectFactory> selectByProjectNoGroupByFactoryId(@Param("projectNo")String projectNo);   
    
    /**
     * 
     * @Title selectByProjectNoAndFactoryName 
     * @Description
     * @param projectNo
     * @param factoryId
     * @return
     * @return ProjectFactory
     */
    ProjectFactory selectByProjectNoAndFactoryName(@Param("projectNo")String projectNo,@Param("factoryName")String factoryName);

    /**
     * 根据项目号查询
     * @Title selectByFactoryId 
     * @Description
     * @param projectNo
     * @param factoryId
     * @return
     * @return ProjectFactory
     */
    List<ProjectFactory> selectByFactoryId(@Param("factoryId")String factoryId,@Param("queryDate")Integer queryDate); 
    
    
    
    /**
     * 查询工厂项目统计列表
     * @Title selectFactoryList 
     * @Description 
     * @return
     * @return int
     */
    List<ProjectFactory> selectFactoryList(ProjectFactoryQuery projectFactoryQuery);
    /**
     * 查询工厂项目统计数量
     * @Title selectFactoryList 
     * @Description 
     * @return
     * @return int
     */
    int selectFactoryListCount(ProjectFactoryQuery projectFactoryQuery);
    
    
    /**
     * 
     * @Title selectByProjectNo 
     * @Description
     * @return
     * @return int
     */
    int selectCountByFactoryName(@Param("factoryName")String factoryName);
    
    
    /**
     * 根据工厂查询单一工厂项目个数
     * @Title selectCountByFactoryNameOnly 
     * @Description
     * @param factoryName
     * @return
     * @return int
     */
    int selectCountByFactoryNameOnly(@Param("factoryName")String factoryName);
    
    
    
    
    
    /**
     * 查询工厂项目列表
     * @Title selectFactoryList 
     * @Description 
     * @return
     * @return int
     */
    List<ProjectFactory> selectProjectList(ProjectFactoryQuery projectFactoryQuery);
    /**
     * 查询工厂项目数量
     * @Title selectFactoryList 
     * @Description 
     * @return
     * @return int
     */
    int selectProjectListCount(ProjectFactoryQuery projectFactoryQuery);
    
    
    /**
     * 查询工厂
     * @Title selectAllFactory 
     * @Description
     * @return
     * @return List<ProjectFactory>
     */
    List<String> selectAllFactory(String inputKey);
}