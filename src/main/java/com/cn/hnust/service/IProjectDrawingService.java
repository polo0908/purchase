package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.ProjectDrawing;

public interface IProjectDrawingService {
  /**
   * 批量添加图纸信息
   * @param projectDrawingList
   */
  public void batchAddProjectDrawing(List<ProjectDrawing> projectDrawingList);
  
  public List<ProjectDrawing> selectAllProjectDrawing();

  public List<ProjectDrawing> selectProjectDrawingByProjectNo(java.lang.String projectNo);

  public void addProjectDrawing(ProjectDrawing projectDrawing);
 
  public List<ProjectDrawing> selectProjectDemandReportByNo(java.lang.String projectNo);
}
