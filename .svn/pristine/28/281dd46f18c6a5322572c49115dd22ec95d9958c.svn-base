package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.AnalysisIssue;
import com.cn.hnust.pojo.ComplaintIssue;
import com.cn.hnust.pojo.IssueReply;
import com.cn.hnust.pojo.ProjectComplaint;
import com.cn.hnust.pojo.ProjectComplaintQuery;

public interface ProjectComplaintService {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectComplaint record);

    int insertSelective(ProjectComplaint record);

    ProjectComplaint selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectComplaint record);

    int updateByPrimaryKeyWithBLOBs(ProjectComplaint record);

    int updateByPrimaryKey(ProjectComplaint record);
    
    
    
    int insertSelective(ProjectComplaint record,List<ComplaintIssue> complaintIssues);
    
    /**
     * 保存投诉的同时，更新问题标签上complaint_id
     * @Title insertSelective 
     * @Description 
     * @param record
     * @param complaintIssues
     * @param issueList
     * @return
     * @return int
     */
    int insertSelective(ProjectComplaint record,List<ComplaintIssue> complaintIssues,List<AnalysisIssue> issueList);
    /**
     * 根据项目号查询
     * @Title selectByProjectNo 
     * @Description 
     * @param projectNo
     * @return
     * @return List<ProjectComplaint>
     */
    List<ProjectComplaint> selectByProjectNo(String projectNo);
    
    /**
     * 根据项目号模糊查询
     * @Title selectByProjectNoDim 
     * @Description 
     * @param projectNo
     * @return
     * @return List<ProjectComplaint>
     */
    List<ProjectComplaint> selectByProjectNoDim(String projectNo);
    /**
     * 查询客户投诉列表
     * @Title complaintList 
     * @Description
     * @param projectComplaintQuery
     * @return
     * @return List<ProjectComplaint>
     */
    List<ProjectComplaint> queryComplaintList(ProjectComplaintQuery projectComplaintQuery);
    
    /**
     * 查询客户投诉总数
     * @Title queryCount 
     * @Description
     * @param projectComplaintQuery
     * @return
     * @return int
     */
    int queryCount(ProjectComplaintQuery projectComplaintQuery);
    
    
    
    /**
     * 查询投诉任务未处理的数量
     * @Title countUnFinished 
     * @Description
     * @param projectNo
     * @return
     * @return int
     */
    int countUnFinished(String projectNo);
    
    
    /**
     * 更新投诉表同时插入回复内容
     * @Title updateAndInsertReply 
     * @Description
     * @param record
     * @param replyList
     * @return
     * @return int
     */
    int updateAndInsertReply(ProjectComplaint record,List<IssueReply> replyList);
/**
 * 
 * @Title:ProjectComplaintService
 * @Description:获取质量投诉次数
   @author wangyang
 * @param allProjectNo
 * @return int
 * @throws
 */
	String getComplaintsNumber(String allProjectNo);
}
