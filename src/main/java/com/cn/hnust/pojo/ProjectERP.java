package com.cn.hnust.pojo;

import java.io.Serializable;
import java.util.Date;

public class ProjectERP implements Serializable {
    private String id;

    private String projectNo;

    private String projectNameC;

    private String projectNameE;

    private String zhijian1;

    private String zhijian2;
    
    private String zhijian3;

    private String customerManager;

    private String merchandManager1;

    private String merchandManager2;

    private String engineer1;

    private String engineer2;

    private String engineer3;
    
    private String customerManager1;
    
    private String customerManager2;
    
    private String companyName;
    
    private String merchandising;   //成熟跟单
    private String maturePurchase;  //成熟采购
    
    private Integer supplementaryContract;  //0,代表正常合同  1.代表补合同
    private String contractNo;   //合同号
    
    
    private String taskTitle;
    private int  roleType;
    private int taskId;
    
    /** 项目等级*/
    private int plantAnalysis;
    /** 项目材料属性*/
    private int projectMaterialProperties;
    
    /** 样品交期*/
    private Date dateSample; //样品交期
    /** 合同交期*/
    private Date completionTime; //大货交期
    /** 上传日期*/
    private Date dateSampleUploading; //合同签订日期
    
    private Date createDate;//创建时间
    
    private Date moneyDate;//项目到账时间
    
    private String factoryId; //快制造工厂id
    
    private String technician;    //技术员
    
    private String customerName;  //客户名
    
    private Date poDate;          //po日期
    
    private String city;          //工厂所在城市
    
    private Integer customerGrade;  //客户等级
    
    
    @Override
	public String toString() {
		return "ProjectERP [id=" + id + ", projectNo=" + projectNo
				+ ", projectNameC=" + projectNameC + ", projectNameE="
				+ projectNameE + ", zhijian1=" + zhijian1 + ", zhijian2="
				+ zhijian2 + ", zhijian3=" + zhijian3 + ", customerManager="
				+ customerManager + ", merchandManager1=" + merchandManager1
				+ ", merchandManager2=" + merchandManager2 + ", engineer1="
				+ engineer1 + ", engineer2=" + engineer2 + ", engineer3="
				+ engineer3 + ", customerManager1=" + customerManager1
				+ ", customerManager2=" + customerManager2 + ", companyName="
				+ companyName + ", merchandising=" + merchandising
				+ ", maturePurchase=" + maturePurchase
				+ ", supplementaryContract=" + supplementaryContract
				+ ", contractNo=" + contractNo + ", taskTitle=" + taskTitle
				+ ", roleType=" + roleType + ", taskId=" + taskId
				+ ", plantAnalysis=" + plantAnalysis
				+ ", projectMaterialProperties=" + projectMaterialProperties
				+ ", dateSample=" + dateSample + ", completionTime="
				+ completionTime + ", dateSampleUploading="
				+ dateSampleUploading + ", createDate=" + createDate
				+ ", moneyDate=" + moneyDate + ", factoryId=" + factoryId
				+ ", technician=" + technician + ", customerName="
				+ customerName + ", poDate=" + poDate + ", city=" + city
				+ ", customerGrade=" + customerGrade + "]";
	}

	public Integer getCustomerGrade() {
		return customerGrade;
	}

	public void setCustomerGrade(Integer customerGrade) {
		this.customerGrade = customerGrade;
	}

	public Integer getSupplementaryContract() {
		return supplementaryContract;
	}




	public void setSupplementaryContract(Integer supplementaryContract) {
		this.supplementaryContract = supplementaryContract;
	}




	public String getContractNo() {
		return contractNo;
	}




	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}




	public String getCity() {
		return city;
	}




	public void setCity(String city) {
		this.city = city;
	}




	public Date getPoDate() {
		return poDate;
	}
	public void setPoDate(Date poDate) {
		this.poDate = poDate;
	}
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getTechnician() {
		return technician;
	}

	public void setTechnician(String technician) {
		this.technician = technician;
	}

	public String getFactoryId() {
		return factoryId;
	}

	public void setFactoryId(String factoryId) {
		this.factoryId = factoryId;
	}

	public String getZhijian3() {
		return zhijian3;
	}

	public void setZhijian3(String zhijian3) {
		this.zhijian3 = zhijian3;
	}

	public String getMerchandising() {
		return merchandising;
	}

	public void setMerchandising(String merchandising) {
		this.merchandising = merchandising;
	}

	public String getMaturePurchase() {
		return maturePurchase;
	}

	public void setMaturePurchase(String maturePurchase) {
		this.maturePurchase = maturePurchase;
	}

	public Date getMoneyDate() {
		return moneyDate;
	}

	public void setMoneyDate(Date moneyDate) {
		this.moneyDate = moneyDate;
	}

	private static final long serialVersionUID = 1L;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getProjectNameC() {
		return projectNameC;
	}

	public void setProjectNameC(String projectNameC) {
		this.projectNameC = projectNameC;
	}

	public String getProjectNameE() {
		return projectNameE;
	}

	public void setProjectNameE(String projectNameE) {
		this.projectNameE = projectNameE;
	}

	public String getZhijian1() {
		return zhijian1;
	}

	public void setZhijian1(String zhijian1) {
		this.zhijian1 = zhijian1;
	}

	public String getZhijian2() {
		return zhijian2;
	}

	public void setZhijian2(String zhijian2) {
		this.zhijian2 = zhijian2;
	}

	public String getCustomerManager() {
		return customerManager;
	}

	public void setCustomerManager(String customerManager) {
		this.customerManager = customerManager;
	}

	public String getMerchandManager1() {
		return merchandManager1;
	}

	public void setMerchandManager1(String merchandManager1) {
		this.merchandManager1 = merchandManager1;
	}

	public String getMerchandManager2() {
		return merchandManager2;
	}

	public void setMerchandManager2(String merchandManager2) {
		this.merchandManager2 = merchandManager2;
	}

	public String getEngineer1() {
		return engineer1;
	}

	public void setEngineer1(String engineer1) {
		this.engineer1 = engineer1;
	}

	public String getEngineer2() {
		return engineer2;
	}

	public void setEngineer2(String engineer2) {
		this.engineer2 = engineer2;
	}

	public String getEngineer3() {
		return engineer3;
	}

	public void setEngineer3(String engineer3) {
		this.engineer3 = engineer3;
	}

	public String getCustomerManager1() {
		return customerManager1;
	}

	public void setCustomerManager1(String customerManager1) {
		this.customerManager1 = customerManager1;
	}

	public String getCustomerManager2() {
		return customerManager2;
	}

	public void setCustomerManager2(String customerManager2) {
		this.customerManager2 = customerManager2;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public int getRoleType() {
		return roleType;
	}

	public void setRoleType(int roleType) {
		this.roleType = roleType;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public int getPlantAnalysis() {
		return plantAnalysis;
	}

	public void setPlantAnalysis(int plantAnalysis) {
		this.plantAnalysis = plantAnalysis;
	}

	public int getProjectMaterialProperties() {
		return projectMaterialProperties;
	}

	public void setProjectMaterialProperties(int projectMaterialProperties) {
		this.projectMaterialProperties = projectMaterialProperties;
	}

	public Date getDateSample() {
		return dateSample;
	}

	public void setDateSample(Date dateSample) {
		this.dateSample = dateSample;
	}

	public Date getCompletionTime() {
		return completionTime;
	}

	public void setCompletionTime(Date completionTime) {
		this.completionTime = completionTime;
	}

	public Date getDateSampleUploading() {
		return dateSampleUploading;
	}

	public void setDateSampleUploading(Date dateSampleUploading) {
		this.dateSampleUploading = dateSampleUploading;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}