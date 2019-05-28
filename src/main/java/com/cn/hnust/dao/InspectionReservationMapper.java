package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.InspectionReservation;

public interface InspectionReservationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InspectionReservation record);

    int insertSelective(InspectionReservation record);

    InspectionReservation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InspectionReservation record);

    int updateByPrimaryKey(InspectionReservation record);
    
	List<InspectionReservation> selectInspectionReservation(InspectionReservation inspectionReservation);

	int selectInspectionReservationCount(InspectionReservation inspectionReservation);

	InspectionReservation selectInspectionReservationById(@Param("projectNoId")String projectNoId);
	
	InspectionReservation selectInspectionReservationByNo(@Param("projectNo")String projectNo);
	
	/**
	 * 根据项目号查询查询
	 * @Title selectInspectionList 
	 * @Description
	 * @param projectNo
	 * @return
	 * @return List<InspectionReservation>
	 */
	List<InspectionReservation> selectInspectionList(@Param("projectNo")String projectNo);
}