package com.cn.hnust.print;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.util.IOUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cn.hnust.enums.OrderStatusEnum;
import com.cn.hnust.enums.QualityStatusEnum;
import com.cn.hnust.enums.QualityTypeEnum;
import com.cn.hnust.pojo.Project;
import com.cn.hnust.pojo.QualityPicExplain;
import com.cn.hnust.pojo.QualityReport;
import com.cn.hnust.util.DateFormat;
import com.cn.hnust.util.DateUtil;
import com.cn.hnust.util.UploadAndDownloadPathUtil;



/**
 * @Description: 导出项目统计数据excel
 * @Author: polo
 * @CreateDate:2018/08/24
 */

public class ProjectStatisticsPrint {

	private static File tempPath;

	/**
	 * pdf打印,使用excel编辑，生成pdf
	 * 
	 * @param path
	 * @throws Exception
	 */
	public static String printExcel(HttpServletRequest request, List<Project> sampleFinishes,List<Project> productFinishs,List<Project> normals)throws Exception {
         //样品完结数量
		 int sample_tl = 0;
		 if(sampleFinishes != null && sampleFinishes.size() >0){
			 sample_tl = sampleFinishes.size();
		 }
		 //大货完结数量
		 int product_tl = 0;
		 if(productFinishs != null && productFinishs.size() >0){
			 product_tl = productFinishs.size();
		 }
		 //进行中项目数量
		 int normal_tl = 0;
		 if(normals != null && normals.size() >0){
			 normal_tl = normals.size();
		 }
		
        //创建workbook  
         HSSFWorkbook wb = new HSSFWorkbook();  
           
         //创建sheet  
         HSSFSheet sheet = wb.createSheet("延期项目统计表格");  
          
         HSSFFont font = wb.createFont();
         font.setFontName("黑体");
         font.setFontHeightInPoints((short) 16);//设置字体大小
         
         
         HSSFFont font2 = wb.createFont();
         font2.setFontName("黑体");
         font2.setFontHeightInPoints((short) 16);//设置字体大小
         font2.setColor(HSSFColor.RED.index);

         
         //创建行row:添加表头0行  
         HSSFRow row = sheet.createRow(0);  
         HSSFCellStyle  style = wb.createCellStyle();      
         style.setFont(font);
         
         HSSFCellStyle cellStyle = wb.createCellStyle();
         HSSFDataFormat format= wb.createDataFormat();
         cellStyle.setDataFormat(format.getFormat("yyyy/m/d"));
         cellStyle.setAlignment(HorizontalAlignment.CENTER);
         cellStyle.setBorderBottom(BorderStyle.THIN);
         cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
         cellStyle.setBorderLeft(BorderStyle.THIN);
         cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);
         cellStyle.setBorderRight(BorderStyle.THIN);
         cellStyle.setRightBorderColor(HSSFColor.BLACK.index);
         cellStyle.setBorderTop(BorderStyle.THIN);
         cellStyle.setTopBorderColor(HSSFColor.BLACK.index);
         
         //创建边框样式
         HSSFCellStyle boderStyle = wb.createCellStyle();
         boderStyle.setAlignment(HorizontalAlignment.CENTER);
         boderStyle.setBorderBottom(BorderStyle.THIN);
         boderStyle.setBottomBorderColor(HSSFColor.BLACK.index);
         boderStyle.setBorderLeft(BorderStyle.THIN);
         boderStyle.setLeftBorderColor(HSSFColor.BLACK.index);
         boderStyle.setBorderRight(BorderStyle.THIN);
         boderStyle.setRightBorderColor(HSSFColor.BLACK.index);
         boderStyle.setBorderTop(BorderStyle.THIN);
         boderStyle.setTopBorderColor(HSSFColor.BLACK.index);
        
         
         //退税金额使用样式
         HSSFCellStyle lastStyle = wb.createCellStyle();
         lastStyle.setAlignment(HorizontalAlignment.CENTER);
         lastStyle.setBorderBottom(BorderStyle.THIN);
         lastStyle.setBottomBorderColor(HSSFColor.BLACK.index);
         lastStyle.setBorderLeft(BorderStyle.THIN);
         lastStyle.setLeftBorderColor(HSSFColor.BLACK.index);
         lastStyle.setBorderRight(BorderStyle.THIN);
         lastStyle.setRightBorderColor(HSSFColor.BLACK.index);
         lastStyle.setBorderTop(BorderStyle.THIN);
         lastStyle.setTopBorderColor(HSSFColor.BLACK.index);
         lastStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);// 设置背景色
         lastStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    
         //创建单元格  
         HSSFCell cell = row.createCell(0); //第一个单元格  
         cell.setCellValue("打样完成");  
         cell.setCellStyle(style);           
         style.setFont(font2);
         
         
         //相同的工厂数
         int factoryNum = 0;
         int startCol = 0;
         String factoryName = "";
         Date endDate = null; 
         //获取当月截止日期，如果是15号之前，截止日期时上月15号，15号或者15号以后截止日期是本月15号
         Calendar cal= Calendar.getInstance();  
    	 int year = cal.get(Calendar.YEAR);
    	 int month = cal.get(Calendar.MONTH)+1;
         if(cal.get(Calendar.DAY_OF_MONTH) >= 15){
        	 String d = year + "-" + month + "-15";
        	 endDate = DateUtil.StrToDate(d);
         }else{
             //获取15号日期
        	 String d = year + "-" + month + "-15";
        	 //获取上个月15号日期
        	 endDate = DateUtil.getPrevMonthDate(DateUtil.StrToDate(d));
        }
         DecimalFormat df=new DecimalFormat("¥###,##0.00");
         
         for (int i=0;i<sample_tl;i++){
        	 if(i == 0){
        		 row = sheet.createRow(1); 
            	 cell = row.createCell(0); //获取单元格 
            	 cell.setCellValue("采购名"); 
            	 cell.setCellStyle(boderStyle);
            	 cell = row.createCell(1); //获取单元格 
            	 cell.setCellValue("工厂名");  
            	 cell.setCellStyle(boderStyle);
            	 cell = row.createCell(2); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("延期项目名");  
            	 cell = row.createCell(3); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("最近去该厂日期");  
            	 cell = row.createCell(4); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("项目号");  
            	 cell = row.createCell(5); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("跟单名");  
            	 cell = row.createCell(6); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("原定交期");  
            	 cell = row.createCell(7); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("第一次修改交期");  
            	 cell = row.createCell(8); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("第二次修改交期");  
            	 cell = row.createCell(9); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("第三次修改交期");  
            	 cell = row.createCell(10); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("实际完成日期");  
            	 cell = row.createCell(11); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("紧急交货期");  
            	 cell = row.createCell(12); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("总耗时(天)");  
            	 cell.setCellStyle(style);  
            	 cell = row.createCell(13); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("截止日期");  
            	 cell = row.createCell(14); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("本期扣款天数");             	
            	 cell = row.createCell(15); //获取单元格 
            	 cell.setCellStyle(lastStyle);
            	 cell.setCellValue("本期预计扣款金额");             	
            	 
        	 }
    	 
        	 int spendDays = 0; //总耗时
        	 int delayDays = 0; //延期时间
        	 
        	 //总耗时
        	 if(sampleFinishes.get(i).getSampleFinishTime() != null && sampleFinishes.get(i).getDateSampleUploading() != null){
        		 spendDays = DateFormat.calDays(sampleFinishes.get(i).getSampleFinishTime(),sampleFinishes.get(i).getDateSampleUploading());
        	 }else if(sampleFinishes.get(i).getDateSampleUploading() == null && sampleFinishes.get(i).getActualStartDate() != null){
        		 spendDays = DateFormat.calDays(sampleFinishes.get(i).getSampleFinishTime(),sampleFinishes.get(i).getActualStartDate())-7;
        	 }
        	 
        	 //延期时间
        	 if(sampleFinishes.get(i).getSampleFinishTime() != null && sampleFinishes.get(i).getSampleScheduledDate() != null){
        		 delayDays = DateFormat.calDays(sampleFinishes.get(i).getSampleFinishTime(),sampleFinishes.get(i).getSampleScheduledDate()) - 7;
        	 }
//        	 if(sampleFinishes.get(i).getUrgentDeliveryDate() != null){
//        		 delayDays =  DateFormat.calDays(sampleFinishes.get(i).getUrgentDeliveryDate(), sampleFinishes.get(i).getSampleFinishTime());
//        	 }
        	 
        	 row = sheet.createRow(i+2); 
        	 cell = row.createCell(0); //获取单元格 
        	 cell.setCellValue(sampleFinishes.get(i).getPurchaseName());  
        	 cell.setCellStyle(boderStyle);
        	 cell = row.createCell(1); //获取单元格 
        	 cell.setCellValue(sampleFinishes.get(i).getCompanyName());  
        	 if(factoryName != null && factoryName.equals(sampleFinishes.get(i).getCompanyName())){
        		 factoryNum++;

        	 }else{
        		 if(factoryNum != 0){
            		 CellRangeAddress region = new CellRangeAddress(startCol, startCol +factoryNum, (short) 1, (short) 1);
            		 sheet.addMergedRegion(region);
        		 }
        		 factoryNum = 0;
        		 factoryName = sampleFinishes.get(i).getCompanyName();
        		 startCol= 2+i;
        	 }
        	 cell.setCellStyle(boderStyle);
        	 cell = row.createCell(2); //获取单元格 
        	 cell.setCellValue(sampleFinishes.get(i).getProjectName()==null?"":sampleFinishes.get(i).getProjectName() + "  延期"+delayDays+"天");  
        	 cell.setCellStyle(boderStyle);
        	 cell = row.createCell(3); //获取单元格 
        	 cell.setCellValue("");  
        	 cell.setCellStyle(boderStyle);
        	 cell = row.createCell(4); //获取单元格 
        	 cell.setCellValue(sampleFinishes.get(i).getProjectNo());  
        	 cell.setCellStyle(boderStyle);
        	 cell = row.createCell(5); //获取单元格 
        	 cell.setCellValue(sampleFinishes.get(i).getSellName());  
        	 cell.setCellStyle(boderStyle);
        	 cell = row.createCell(6); //获取单元格 
        	 cell.setCellStyle(cellStyle);
        	 if(sampleFinishes.get(i).getOriginalSampleScheduledDate()!=null){
        		 cell.setCellValue(sampleFinishes.get(i).getOriginalSampleScheduledDate()); 
        	 }

        	 
        	 //第一次修改交期
        	 cell = row.createCell(7); //获取单元格        
        	 cell.setCellStyle(cellStyle);
        	 if(sampleFinishes.get(i).getDeliveryList()!=null&&sampleFinishes.get(i).getDeliveryList().size()>0){
        		 cell.setCellValue(sampleFinishes.get(i).getDeliveryList().get(0).getNewDeliveryDate()); 
        	 }        	 
        	 //第二次修改交期
        	 cell = row.createCell(8); //获取单元格        
        	 cell.setCellStyle(cellStyle);
        	 if(sampleFinishes.get(i).getDeliveryList()!=null&&sampleFinishes.get(i).getDeliveryList().size()>1){
        		 cell.setCellValue(sampleFinishes.get(i).getDeliveryList().get(1).getNewDeliveryDate()); 
        	 }        	 
        	 //第三次修改交期
        	 cell = row.createCell(9); //获取单元格        
        	 cell.setCellStyle(cellStyle);
        	 if(sampleFinishes.get(i).getDeliveryList()!=null&&sampleFinishes.get(i).getDeliveryList().size()>2){
        		 cell.setCellValue(sampleFinishes.get(i).getDeliveryList().get(2).getNewDeliveryDate()); 
        	 }        	
        	 
        	 cell = row.createCell(10); //获取单元格 
        	 cell.setCellStyle(cellStyle);
        	 cell.setCellValue(sampleFinishes.get(i).getSampleFinishTime()); 
        	 
        	 cell = row.createCell(11); //获取单元格 
        	 if(sampleFinishes.get(i).getUrgentDeliveryDate()!= null){       		
        		 cell.setCellValue(sampleFinishes.get(i).getUrgentDeliveryDate());         		
        	 }
        	 cell.setCellStyle(cellStyle);
        	 
        	 //总耗时
        	 cell = row.createCell(12); //获取单元格         	
        	 cell.setCellValue(spendDays);  
        	 cell.setCellStyle(boderStyle);
        	 
        	 //截止日期
        	 cell = row.createCell(13); //获取单元格   
        	 cell.setCellStyle(cellStyle);
        	 cell.setCellValue(endDate);
        	 
        	 //本期扣款天数，如果结束日期大于等于15号，算到15号
        	 //如果结束日期小于15，按照结束日期算
        	 int chargeDays = 0;
        	 if(sampleFinishes.get(i).getSampleFinishTime().getTime() >=  endDate.getTime() && delayDays > 30){        		 
        		 chargeDays = DateFormat.calDays(endDate,DateUtil.getPrevMonthDate(endDate));
        	 }else if(sampleFinishes.get(i).getSampleFinishTime().getTime() > DateUtil.getPrevMonthDate(endDate).getTime()){
        		 chargeDays = DateFormat.calDays(sampleFinishes.get(i).getSampleFinishTime(),DateUtil.getPrevMonthDate(endDate)) - 1;     		 
        	 }
        	 //如果扣款时间大于延期时间，按照延期扣款
        	 if(chargeDays > delayDays){
        		 chargeDays = delayDays;
        	 }
        	 //当交期大于上个月15号时，扣款日期即为延期时间
        	 if(sampleFinishes.get(i).getSampleScheduledDate().getTime() >=  DateUtil.getPrevMonthDate(endDate).getTime()){
        		 chargeDays = delayDays;
        	 }

        	 
        	 //本期扣款天数
        	 cell = row.createCell(14); //获取单元格   
        	 cell.setCellValue(chargeDays); 
        	 cell.setCellStyle(boderStyle);
        	 //本期预计扣款金额
        	 cell = row.createCell(15); //获取单元格 
        	 cell.setCellStyle(lastStyle);
        	 cell.setCellValue(chargeDays == 0? "0" :df.format(chargeDays*50));      
         }
         
         
         int extend_tl = 0; //向下扩展行数
         extend_tl = sample_tl+3;
         row = sheet.createRow(extend_tl); 
         cell = row.createCell(0);
         cell.setCellValue("大货完成");  
         cell.setCellStyle(style);          
         style.setFont(font2);
         for (int i=0;i<product_tl;i++){  
        	 if(i == 0){
        		 row = sheet.createRow(extend_tl+1); 
            	 cell = row.createCell(0); //获取单元格 
            	 cell.setCellValue("采购名");  
            	 cell.setCellStyle(boderStyle);
            	 cell = row.createCell(1); //获取单元格 
            	 cell.setCellValue("工厂名");  
            	 cell.setCellStyle(boderStyle);
            	 cell = row.createCell(2); //获取单元格 
            	 cell.setCellValue("延期项目名");  
            	 cell.setCellStyle(boderStyle);
            	 cell = row.createCell(3); //获取单元格 
            	 cell.setCellValue("最近去该厂日期");  
            	 cell.setCellStyle(boderStyle);
            	 cell = row.createCell(4); //获取单元格 
            	 cell.setCellValue("项目号"); 
            	 cell.setCellStyle(boderStyle);
            	 cell = row.createCell(5); //获取单元格 
            	 cell.setCellValue("跟单名");
            	 cell.setCellStyle(boderStyle);
            	 cell = row.createCell(6); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("原定交期");  
            	 cell = row.createCell(7); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("第一次修改交期");  
            	 cell = row.createCell(8); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("第二次修改交期");  
            	 cell = row.createCell(9); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("第三次修改交期");  
            	 cell = row.createCell(10); //获取单元格 
            	 cell.setCellValue("实际完成日期");  
            	 cell.setCellStyle(boderStyle);
            	 cell = row.createCell(11); //获取单元格 
            	 cell.setCellValue("紧急交货期");  
            	 cell.setCellStyle(boderStyle);
            	 cell = row.createCell(12); //获取单元格 
            	 cell.setCellValue("总耗时(天)");    
            	 cell.setCellStyle(style);  
            	 cell.setCellStyle(boderStyle);
             	 cell = row.createCell(13); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("截止日期");  
            	 cell = row.createCell(14); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("本期扣款天数");  
               	 cell = row.createCell(15); //获取单元格 
            	 cell.setCellStyle(lastStyle);
            	 cell.setCellValue("本期预计扣款金额"); 
        	 }

        	 
        	 
        	 
        	 int spendDays = 0;   //总耗时
        	 int delayDays = 0;   //延期时间
        	 //总耗时
        	 if(productFinishs.get(i).getFinishTime() != null && productFinishs.get(i).getDateSampleUploading() != null){
        		 spendDays = DateFormat.calDays(productFinishs.get(i).getFinishTime(),productFinishs.get(i).getDateSampleUploading());
        	 }else if(productFinishs.get(i).getDateSampleUploading() == null && productFinishs.get(i).getActualStartDate() != null){
        		 spendDays = DateFormat.calDays(productFinishs.get(i).getFinishTime(),productFinishs.get(i).getActualStartDate())-7;
        	 }
        	 
         	 //延期时间
        	 if(productFinishs.get(i).getFinishTime() != null && productFinishs.get(i).getDeliveryDate() != null){
        		 delayDays = DateFormat.calDays(productFinishs.get(i).getFinishTime(),productFinishs.get(i).getDeliveryDate()) - 7;
        	 }    	 
//        	 if(productFinishs.get(i).getUrgentDeliveryDate() != null){
//        		 delayDays =  DateFormat.calDays(productFinishs.get(i).getFinishTime(), productFinishs.get(i).getUrgentDeliveryDate());
//        	 }
        	 row = sheet.createRow(extend_tl+i+2); 
        	 cell = row.createCell(0); //获取单元格 
        	 cell.setCellValue(productFinishs.get(i).getPurchaseName());  
        	 cell.setCellStyle(boderStyle);
        	 cell = row.createCell(1); //获取单元格 
        	 cell.setCellValue(productFinishs.get(i).getCompanyName());  
        	 cell.setCellStyle(boderStyle);
        	 if(factoryName != null && factoryName.equals(productFinishs.get(i).getCompanyName())){
        		 factoryNum++;
        	 }else{
        		 if(factoryNum != 0){
            		 CellRangeAddress region = new CellRangeAddress(startCol, startCol+factoryNum, (short) 1, (short) 1);
            		 sheet.addMergedRegion(region);
        		 }
        		 factoryNum = 0;
        		 factoryName = productFinishs.get(i).getCompanyName();
        		 startCol= extend_tl+2+i;
        	 }
        	 
        	 cell = row.createCell(2); //获取单元格 
        	 cell.setCellValue(productFinishs.get(i).getProjectName() ==null? "": productFinishs.get(i).getProjectName() + "  延期"+delayDays+"天");   
        	 cell.setCellStyle(boderStyle);
        	 cell = row.createCell(3); //获取单元格 
        	 cell.setCellValue("");
        	 cell.setCellStyle(boderStyle);
        	 cell = row.createCell(4); //获取单元格 
        	 cell.setCellValue(productFinishs.get(i).getProjectNo());  
        	 cell.setCellStyle(boderStyle);
           	 cell = row.createCell(5); //获取单元格 
        	 cell.setCellValue(productFinishs.get(i).getSellName());  
        	 cell.setCellStyle(boderStyle);
        	 cell = row.createCell(6); //获取单元格        
        	 cell.setCellValue(productFinishs.get(i).getOriginalDeliveryDate());  
        	 cell.setCellStyle(cellStyle);
        	 
        	 //第一次修改交期
        	 cell = row.createCell(7); //获取单元格        
        	 cell.setCellStyle(cellStyle);
        	 if(productFinishs.get(i).getDeliveryList()!=null&&productFinishs.get(i).getDeliveryList().size()>0){
        		 cell.setCellValue(productFinishs.get(i).getDeliveryList().get(0).getNewDeliveryDate()); 
        	 }        	 
        	 //第二次修改交期
        	 cell = row.createCell(8); //获取单元格        
        	 cell.setCellStyle(cellStyle);
        	 if(productFinishs.get(i).getDeliveryList()!=null&&productFinishs.get(i).getDeliveryList().size()>1){
        		 cell.setCellValue(productFinishs.get(i).getDeliveryList().get(1).getNewDeliveryDate()); 
        	 }        	 
        	 //第三次修改交期
        	 cell = row.createCell(9); //获取单元格        
        	 cell.setCellStyle(cellStyle);
        	 if(productFinishs.get(i).getDeliveryList()!=null&&productFinishs.get(i).getDeliveryList().size()>2){
        		 cell.setCellValue(productFinishs.get(i).getDeliveryList().get(2).getNewDeliveryDate()); 
        	 }        	
        	 
        	 
        	 cell = row.createCell(10); //获取单元格        	
        	 cell.setCellValue(productFinishs.get(i).getFinishTime());  
        	 cell.setCellStyle(cellStyle);
        	 
        	 cell = row.createCell(11); //获取单元格 
        	 if(productFinishs.get(i).getUrgentDeliveryDate()!= null){        		 
        		 cell.setCellValue(productFinishs.get(i).getUrgentDeliveryDate());  
        	 }     
        	 cell.setCellStyle(cellStyle);
        	 cell = row.createCell(12); //获取单元格 
        	 cell.setCellValue(spendDays);  
        	 cell.setCellStyle(boderStyle);
        	 
        	 
        	 //截止日期
        	 cell = row.createCell(13); //获取单元格   
        	 cell.setCellStyle(cellStyle);
        	 cell.setCellValue(endDate);
        	 
        	 
        	 
        	 //本期扣款天数，如果结束日期大于等于15号，算到154号
        	 //如果结束日期小于15，按照结束日期算
        	 int chargeDays = 0;
        	 if(productFinishs.get(i).getFinishTime().getTime() >=  endDate.getTime() && delayDays > 30){        		 
        		 chargeDays = DateFormat.calDays(endDate,DateUtil.getPrevMonthDate(endDate));
        	 }else if(productFinishs.get(i).getFinishTime().getTime() > DateUtil.getPrevMonthDate(endDate).getTime()){
        		 chargeDays = DateFormat.calDays(productFinishs.get(i).getFinishTime(),DateUtil.getPrevMonthDate(endDate))-1;
        	 }
        	 //如果扣款时间大于延期时间，按照延期扣款
        	 if(chargeDays > delayDays){
        		 chargeDays = delayDays;
        	 }
        	 //当交期大于上个月15号时，扣款日期即为延期时间
        	 if(productFinishs.get(i).getDeliveryDate().getTime() >=  DateUtil.getPrevMonthDate(endDate).getTime()){
        		 chargeDays = delayDays;
        	 }
        	 
        	 //本期扣款天数
        	 cell = row.createCell(14); //获取单元格   
        	 cell.setCellValue(chargeDays); 
        	 cell.setCellStyle(boderStyle);
        	 //本期预计扣款金额
        	 cell = row.createCell(15); //获取单元格 
        	 cell.setCellStyle(lastStyle);
        	 cell.setCellValue(chargeDays == 0? "0" :df.format(chargeDays*50));      
         }
         
         

         extend_tl = extend_tl+product_tl+3;
         row = sheet.createRow(extend_tl); 
         cell = row.createCell(0);
         cell.setCellValue("进行中项目");  
         cell.setCellStyle(style);          
         style.setFont(font2);
         for (int i=0;i<normal_tl;i++){  
        	 if(i == 0){
            	 row = sheet.createRow(extend_tl+1); 
            	 cell = row.createCell(0); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("采购名");  
            	 cell = row.createCell(1); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("工厂名");  
            	 cell = row.createCell(2); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("延期项目名");  
            	 cell = row.createCell(3); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("最近去该厂日期");  
            	 cell = row.createCell(4); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("项目号");  
            	 cell = row.createCell(5); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("跟单名");  
            	 cell = row.createCell(6); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("原定交期");  
            	 cell = row.createCell(7); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("第一次修改交期");  
            	 cell = row.createCell(8); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("第二次修改交期");  
            	 cell = row.createCell(9); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("第三次修改交期");  
            	 cell = row.createCell(10); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("实际完成日期");  
            	 cell = row.createCell(11); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("紧急交货期");  
            	 cell = row.createCell(12); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("总耗时(天)");  
            	 cell.setCellStyle(style);  
            	 cell.setCellStyle(boderStyle);
             	 cell = row.createCell(13); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("截止日期");  
            	 cell = row.createCell(14); //获取单元格 
            	 cell.setCellStyle(boderStyle);
            	 cell.setCellValue("本期扣款天数");  
               	 cell = row.createCell(15); //获取单元格 
            	 cell.setCellStyle(lastStyle);
            	 cell.setCellValue("本期预计扣款金额"); 
        	 }
       
        	 
        	 int spendDays = 0;   //总耗时
        	 int delayDays = 0;   //延期时间
        	 //总耗时
        	 if(normals.get(i).getDateSampleUploading() != null){
        		 spendDays = DateFormat.calDays(new java.util.Date(),normals.get(i).getDateSampleUploading());
        	 }else if(normals.get(i).getDateSampleUploading() == null && normals.get(i).getActualStartDate() != null){
        		 spendDays = DateFormat.calDays(new java.util.Date(),normals.get(i).getActualStartDate())-7;
        	 }       	        	 
        	 //样品延期时间
        	 if(normals.get(i).getDeliveryDate() == null && normals.get(i).getSampleScheduledDate() != null){
        		 delayDays = DateFormat.calDays(new java.util.Date(),normals.get(i).getSampleScheduledDate()) - 7;
        	 }        	 
         	 //延期时间
        	 if(normals.get(i).getDeliveryDate() != null){
        		 delayDays = DateFormat.calDays(new java.util.Date(),normals.get(i).getDeliveryDate()) - 7;
        	 }    	 
//        	 if(normals.get(i).getUrgentDeliveryDate() != null){
//        		 delayDays =  DateFormat.calDays(new java.util.Date(), normals.get(i).getUrgentDeliveryDate());
//        	 }
        	 
        	 row = sheet.createRow(extend_tl+i+2); 
        	 cell = row.createCell(0); //获取单元格 
        	 cell.setCellValue(normals.get(i).getPurchaseName());  
        	 cell.setCellStyle(boderStyle);
        	 cell = row.createCell(1); //获取单元格 
        	 cell.setCellValue(normals.get(i).getCompanyName());  
        	 cell.setCellStyle(boderStyle);
        	 if(factoryName != null && factoryName.equals(normals.get(i).getCompanyName())){
        		 factoryNum++;    
        	 }else{
        		 if(factoryNum != 0){
            		 CellRangeAddress region = new CellRangeAddress(startCol, startCol+factoryNum, (short) 1, (short) 1);
            		 sheet.addMergedRegion(region);
        		 }
        		 factoryNum = 0;
        		 factoryName = normals.get(i).getCompanyName();
        		 startCol= extend_tl+2+i;
        	 }
        	 
        	 cell = row.createCell(2); //获取单元格 
        	 cell.setCellValue(normals.get(i).getProjectName()==null?"":normals.get(i).getProjectName() + "  延期"+delayDays+"天");  
        	 cell.setCellStyle(boderStyle);
        	 cell = row.createCell(3); //获取单元格 
        	 cell.setCellValue("");
        	 cell.setCellStyle(boderStyle);
        	 cell = row.createCell(4); //获取单元格 
        	 cell.setCellValue(normals.get(i).getProjectNo());  
        	 cell.setCellStyle(boderStyle);
        	 cell = row.createCell(5); //获取单元格 
        	 cell.setCellValue(normals.get(i).getSellName());  
        	 cell.setCellStyle(boderStyle);
        	 cell = row.createCell(6); //获取单元格        	
        	 cell.setCellValue(normals.get(i).getOriginalDeliveryDate() == null ? normals.get(i).getOriginalSampleScheduledDate() : normals.get(i).getOriginalDeliveryDate());  
        	 cell.setCellStyle(cellStyle);
        	 //第一次修改交期
        	 cell = row.createCell(7); //获取单元格        
        	 cell.setCellStyle(cellStyle);
        	 if(normals.get(i).getDeliveryList()!=null&&normals.get(i).getDeliveryList().size()>0){
        		 cell.setCellValue(normals.get(i).getDeliveryList().get(0).getNewDeliveryDate()); 
        	 }        	 
        	 //第二次修改交期
        	 cell = row.createCell(8); //获取单元格        
        	 cell.setCellStyle(cellStyle);
        	 if(normals.get(i).getDeliveryList()!=null&&normals.get(i).getDeliveryList().size()>1){
        		 cell.setCellValue(normals.get(i).getDeliveryList().get(1).getNewDeliveryDate()); 
        	 }        	 
        	 //第三次修改交期
        	 cell = row.createCell(9); //获取单元格        
        	 cell.setCellStyle(cellStyle);
        	 if(normals.get(i).getDeliveryList()!=null&&normals.get(i).getDeliveryList().size()>2){
        		 cell.setCellValue(normals.get(i).getDeliveryList().get(2).getNewDeliveryDate()); 
        	 }        	 
        	 
        	 cell = row.createCell(10); //获取单元格        
        	 cell.setCellStyle(cellStyle);
        	 
        	 cell = row.createCell(11); //获取单元格 
        	 if(normals.get(i).getUrgentDeliveryDate()!= null){        		
        		 cell.setCellValue(normals.get(i).getUrgentDeliveryDate());  
        	 }
        	 cell.setCellStyle(cellStyle);
        	 cell = row.createCell(12); //获取单元格       	
        	 cell.setCellValue(spendDays); 
        	 cell.setCellStyle(boderStyle);
        	 
        	 
        	 //截止日期
        	 cell = row.createCell(13); //获取单元格   
        	 cell.setCellStyle(cellStyle);
        	 cell.setCellValue(endDate);
        	 
        	 //本期扣款天数，如果结束日期大于等于15号，算到15号
        	 //如果结束日期小于15，按照结束日期算
        	 int chargeDays = 0;      		 
        	 chargeDays = DateFormat.calDays(endDate.getTime(),DateUtil.getPrevMonthDate(endDate));
        	 //当交期大于上个月15号时，扣款日期即为延期时间
        	 if(normals.get(i).getSampleScheduledDate() != null){
        		 //加上7天来算是否扣款
        		 Date sampleScheduledDate = DateFormat.addDays(normals.get(i).getSampleScheduledDate(), 7);
        		 if(sampleScheduledDate.getTime() >=  DateUtil.getPrevMonthDate(endDate).getTime()){
        			 //如果小于延期时间，按照扣款日期来算扣款天数
        			 int calDays = DateFormat.calDays(endDate,sampleScheduledDate);
        			 if(calDays > delayDays){
        				 chargeDays = delayDays; 
        			 }else{
        				 chargeDays = calDays;
        			 }       			 
            	 }
        	 }
        	 //当有大货交期，以大货交期计算
        	 if(normals.get(i).getDeliveryDate() != null){
    			 //加上7天来算是否扣款
        		 Date deliveryDate = DateFormat.addDays(normals.get(i).getDeliveryDate(), 7);
        		 if(deliveryDate.getTime() >=  DateUtil.getPrevMonthDate(endDate).getTime()){
        			//如果小于延期时间，按照扣款日期-7天来算扣款天数
        			 int calDays = DateFormat.calDays(endDate,deliveryDate);
        			 if(calDays > delayDays){
        				 chargeDays = delayDays; 
        			 }else{
        				 chargeDays = calDays;
        			 }
            	 }
        	 }
        	 
        	 
        	 //本期扣款天数
        	 cell = row.createCell(14); //获取单元格   
        	 cell.setCellValue(chargeDays <= 0 ? 0:chargeDays); 
        	 cell.setCellStyle(boderStyle);
        	 //本期预计扣款金额
        	 cell = row.createCell(15); //获取单元格 
        	 cell.setCellStyle(lastStyle);
        	 cell.setCellValue(chargeDays <= 0? "0" :df.format(chargeDays*50));      
         }
         
         //自动调整列宽
         int i=0;
         while (i<16) {
        	 sheet.autoSizeColumn((short)i);
        	 i++;
		 }
		
		String paths = UploadAndDownloadPathUtil.getFilePath();

		tempPath = new File(paths);
		// deleteFile(tempPath);
		if (!tempPath.exists() || !tempPath.isDirectory()) {
			tempPath.mkdir(); // 如果不存在，则创建该文件夹
		}
		FileOutputStream fs = new FileOutputStream(paths + File.separator + DateFormat.currentDate().replace("-", ".") + ".xls", false);
		wb.write(fs);
		fs.close();		

		return paths + File.separator + DateFormat.currentDate().replace("-", ".") + ".xls";
	}



	/**
	 * 导出最近一个月工厂项目
	 * 
	 * @param path
	 * @throws Exception
	 */
	public static String exportMonthProject(HttpServletRequest request,List<Project> productFinishs)throws Exception {
		 //大货完结数量
		 int product_tl = 0;
		 if(productFinishs != null && productFinishs.size() >0){
			 product_tl = productFinishs.size();
		 }
		
        //创建workbook  
         HSSFWorkbook wb = new HSSFWorkbook();  
           
         //创建sheet  
         HSSFSheet sheet = wb.createSheet("最近一个月完成项目");  
          
         HSSFFont font = wb.createFont();
         font.setFontName("宋体");
         font.setFontHeightInPoints((short) 13);//设置字体大小
         
         
         HSSFFont font2 = wb.createFont();
         font2.setFontName("宋体");
         font2.setFontHeightInPoints((short) 16);//设置字体大小
         font2.setColor(HSSFColor.RED.index);

         
         //创建行row:添加表头0行  
         HSSFRow row = sheet.createRow(0);  
         HSSFCellStyle  style = wb.createCellStyle();      
         style.setFont(font2);
         style.setAlignment(HorizontalAlignment.CENTER);
         style.setBorderBottom(BorderStyle.THIN);
         style.setBottomBorderColor(HSSFColor.BLACK.index);
         style.setBorderLeft(BorderStyle.THIN);
         style.setLeftBorderColor(HSSFColor.BLACK.index);
         style.setBorderRight(BorderStyle.THIN);
         style.setRightBorderColor(HSSFColor.BLACK.index);
         style.setBorderTop(BorderStyle.THIN);
         style.setTopBorderColor(HSSFColor.BLACK.index);
         
         
         
         HSSFCellStyle cellStyle = wb.createCellStyle();
         cellStyle.setFont(font);
         HSSFDataFormat format= wb.createDataFormat();
         cellStyle.setDataFormat(format.getFormat("yyyy/m/d"));
         cellStyle.setAlignment(HorizontalAlignment.CENTER);
         cellStyle.setBorderBottom(BorderStyle.THIN);
         cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
         cellStyle.setBorderLeft(BorderStyle.THIN);
         cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);
         cellStyle.setBorderRight(BorderStyle.THIN);
         cellStyle.setRightBorderColor(HSSFColor.BLACK.index);
         cellStyle.setBorderTop(BorderStyle.THIN);
         cellStyle.setTopBorderColor(HSSFColor.BLACK.index);
         
         //创建边框样式
         HSSFCellStyle boderStyle = wb.createCellStyle();
         boderStyle.setFont(font);
         boderStyle.setAlignment(HorizontalAlignment.CENTER);
         boderStyle.setBorderBottom(BorderStyle.THIN);
         boderStyle.setBottomBorderColor(HSSFColor.BLACK.index);
         boderStyle.setBorderLeft(BorderStyle.THIN);
         boderStyle.setLeftBorderColor(HSSFColor.BLACK.index);
         boderStyle.setBorderRight(BorderStyle.THIN);
         boderStyle.setRightBorderColor(HSSFColor.BLACK.index);
         boderStyle.setBorderTop(BorderStyle.THIN);
         boderStyle.setTopBorderColor(HSSFColor.BLACK.index);
        
         
         //退税金额使用样式
         HSSFCellStyle lastStyle = wb.createCellStyle();
         lastStyle.setAlignment(HorizontalAlignment.CENTER);
         lastStyle.setBorderBottom(BorderStyle.THIN);
         lastStyle.setBottomBorderColor(HSSFColor.BLACK.index);
         lastStyle.setBorderLeft(BorderStyle.THIN);
         lastStyle.setLeftBorderColor(HSSFColor.BLACK.index);
         lastStyle.setBorderRight(BorderStyle.THIN);
         lastStyle.setRightBorderColor(HSSFColor.BLACK.index);
         lastStyle.setBorderTop(BorderStyle.THIN);
         lastStyle.setTopBorderColor(HSSFColor.BLACK.index);
         lastStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);// 设置背景色
         lastStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
         //相同的工厂数
         row = sheet.createRow(0); 
         HSSFCell cell = null;
    
         for (int i=0;i<product_tl;i++){  
        	 if(i == 0){
        		 row = sheet.createRow(0); 
            	 cell = row.createCell(0); //获取单元格 
            	 cell.setCellValue("采购名");  
            	 cell.setCellStyle(style);
            	 cell = row.createCell(1); //获取单元格 
            	 cell.setCellValue("评分");  
            	 cell.setCellStyle(style);
            	 cell = row.createCell(2); //获取单元格 
            	 cell.setCellValue("跟单名");  
            	 cell.setCellStyle(style);
            	 cell = row.createCell(3); //获取单元格 
            	 cell.setCellValue("评分");  
            	 cell.setCellStyle(style);
            	 cell = row.createCell(4); //获取单元格 
            	 cell.setCellValue("质检1"); 
            	 cell.setCellStyle(style);
            	 cell = row.createCell(5); //获取单元格 
            	 cell.setCellValue("评分");
            	 cell.setCellStyle(style);
            	 cell = row.createCell(6); //获取单元格 
            	 cell.setCellStyle(style);
            	 cell.setCellValue("质检2");  
            	 cell = row.createCell(7); //获取单元格 
            	 cell.setCellStyle(style);
            	 cell.setCellValue("评分");  
            	 cell = row.createCell(8); //获取单元格 
            	 cell.setCellStyle(style);
            	 cell.setCellValue("质检3");  
            	 cell = row.createCell(9); //获取单元格 
            	 cell.setCellStyle(style);
            	 cell.setCellValue("评分");  
            	 cell = row.createCell(10); //获取单元格 
            	 cell.setCellValue("项目号");  
            	 cell.setCellStyle(style);
            	 cell = row.createCell(11); //获取单元格 
            	 cell.setCellValue("工厂名");  
            	 cell.setCellStyle(style);
            	 cell = row.createCell(12); //获取单元格 
            	 cell.setCellValue("完成日期");  
            	 cell.setCellStyle(style);
            	 cell = row.createCell(13); //获取单元格 
            	 cell.setCellValue("项目阶段");  
            	 cell.setCellStyle(style);
            	 cell = row.createCell(14); //获取单元格 
            	 cell.setCellValue("是否延期");  
            	 cell.setCellStyle(style);
            	 cell = row.createCell(15); //获取单元格 
            	 cell.setCellValue("延期天数");  
            	 cell.setCellStyle(style);
      
        	 }
    	
        	 row = sheet.createRow(i+1); 
        	 cell = row.createCell(0); //获取单元格 
        	 cell.setCellValue(productFinishs.get(i).getPurchaseName() ==null?"":productFinishs.get(i).getPurchaseName());   
        	 cell.setCellStyle(boderStyle);
        	 cell = row.createCell(1); //获取单元格 
        	 cell.setCellValue("");
        	 cell.setCellStyle(lastStyle);
        	 cell = row.createCell(2); //获取单元格 
        	 cell.setCellValue(productFinishs.get(i).getSellName());  
        	 cell.setCellStyle(boderStyle);
           	 cell = row.createCell(3); //获取单元格 
        	 cell.setCellValue("");  
        	 cell.setCellStyle(lastStyle);
        	 cell = row.createCell(4); //获取单元格        
        	 cell.setCellValue(productFinishs.get(i).getZhijian1() == null ? "":productFinishs.get(i).getZhijian1());  
        	 cell.setCellStyle(cellStyle);
        	 cell = row.createCell(5); //获取单元格 
        	 cell.setCellValue("");  
        	 cell.setCellStyle(lastStyle);
        	 cell = row.createCell(6); //获取单元格        
        	 cell.setCellValue(productFinishs.get(i).getZhijian2() == null ? "":productFinishs.get(i).getZhijian2());  
        	 cell.setCellStyle(cellStyle);
        	 cell = row.createCell(7); //获取单元格 
        	 cell.setCellValue("");  
        	 cell.setCellStyle(lastStyle);
        	 cell = row.createCell(8); //获取单元格        
        	 cell.setCellValue(productFinishs.get(i).getZhijian3() == null ? "":productFinishs.get(i).getZhijian3());  
        	 cell.setCellStyle(cellStyle);
        	 cell = row.createCell(9); //获取单元格 
        	 cell.setCellValue("");  
        	 cell.setCellStyle(lastStyle);
        	 cell = row.createCell(10); //获取单元格 
        	 cell.setCellValue(productFinishs.get(i).getProjectNo());  
        	 cell.setCellStyle(boderStyle);
        	 cell = row.createCell(11); //获取单元格 
        	 cell.setCellValue(productFinishs.get(i).getCompanyName());  
        	 cell.setCellStyle(boderStyle);
        	 cell = row.createCell(12); //获取单元格     	
        	 if(productFinishs.get(i).getSampleFinishTime() != null){
        		 cell.setCellValue(productFinishs.get(i).getSampleFinishTime());  
        	 }       	
        	 if(productFinishs.get(i).getFinishTime() != null){
        		 cell.setCellValue(productFinishs.get(i).getFinishTime());  
        	 }       	
        	 cell.setCellStyle(cellStyle);
           	 cell = row.createCell(13); //获取单元格 
        	 cell.setCellValue(OrderStatusEnum.getEnum(productFinishs.get(i).getProjectStatus()).getValue());  
        	 cell.setCellStyle(boderStyle);
        	 
        	 cell.setCellStyle(cellStyle);
           	 cell = row.createCell(14); //获取单元格 
           	 //判断是否延期
           	 String delay = "";
           	 int delayDays = 0;
           	 if(productFinishs.get(i).getSampleFinishTime() != null && productFinishs.get(i).getProjectStatus() == OrderStatusEnum.SAMPLE_ORDER.getCode()){
           		  if(productFinishs.get(i).getSampleFinishTime().getTime()  > productFinishs.get(i).getSampleScheduledDate().getTime() + 7*24*60*60*1000){
           			  delay = "有延期";
           			  delayDays = DateFormat.calDays(productFinishs.get(i).getSampleFinishTime(), productFinishs.get(i).getSampleScheduledDate())-7;
           		  }else{
           			  delay = "无延期";
           		  }
           	 }
        	 if(productFinishs.get(i).getFinishTime() != null && productFinishs.get(i).getDeliveryDate() != null && productFinishs.get(i).getProjectStatus() == OrderStatusEnum.COMPLETE_ORDER.getCode()){
          		  if(productFinishs.get(i).getFinishTime().getTime()  > productFinishs.get(i).getDeliveryDate().getTime() + 7*24*60*60*1000){
          			  delay = "有延期";
          			  delayDays = DateFormat.calDays(productFinishs.get(i).getFinishTime(), productFinishs.get(i).getDeliveryDate())-7;
          		  }else{
          			  delay = "无延期";
          		  }
          	 } 
        	
        	 if("有延期".equals(delay)){
        		 cell.setCellValue(delay);       
            	 cell.setCellStyle(style);
            	 cell = row.createCell(15); //获取单元格 
            	 cell.setCellValue(delayDays);  
            	 cell.setCellStyle(style);
        	 }else{
        		 cell.setCellValue(delay);       
            	 cell.setCellStyle(boderStyle);
            	 cell = row.createCell(15); //获取单元格 
            	 cell.setCellValue(delayDays);  
            	 cell.setCellStyle(boderStyle);
        	 }     	
        	 
        	 
         }
         
         //自动调整列宽
         int i=0;
         while (i<16) {
        	 sheet.autoSizeColumn((short)i);
        	 i++;
		 }
		
		String paths = UploadAndDownloadPathUtil.getFilePath();

		tempPath = new File(paths);
		// deleteFile(tempPath);
		if (!tempPath.exists() || !tempPath.isDirectory()) {
			tempPath.mkdir(); // 如果不存在，则创建该文件夹
		}
		FileOutputStream fs = new FileOutputStream("最近一个月完成的项目导出.xls", false);
		wb.write(fs);
		fs.close();		

		return "最近一个月完成的项目导出.xls";
	}

}
