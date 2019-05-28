package com.cn.hnust.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.InspectionReservation;

public interface IInspectionReservationService {

	public void addInspectionReservation(InspectionReservation inspectionReservation);
	
	public List<InspectionReservation> selectInspectionReservation(InspectionReservation inspectionReservation);
	
	public int selectInspectionReservationCount(InspectionReservation inspectionReservation);

	public InspectionReservation selectInspectionReservationById(String projectNoId);
	
	public InspectionReservation selectInspectionReservationByNo(String projectNo);

	public void updateInspectionReservation(InspectionReservation inspectionReservation);
	
	
	/**
	 * 根据项目号查询查询
	 * @Title selectInspectionList 
	 * @Description
	 * @param projectNo
	 * @return
	 * @return List<InspectionReservation>
	 */
	List<InspectionReservation> selectInspectionList(String projectNo);
}
