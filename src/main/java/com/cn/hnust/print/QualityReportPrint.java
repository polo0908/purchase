package com.cn.hnust.print;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.omg.CORBA.INTERNAL;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cn.hnust.controller.QualityController;
import com.cn.hnust.enums.QualityStatusEnum;
import com.cn.hnust.enums.QualityTypeEnum;
import com.cn.hnust.pojo.Project;
import com.cn.hnust.pojo.QualityPicExplain;
import com.cn.hnust.pojo.QualityReport;
import com.cn.hnust.util.DateFormat;
import com.cn.hnust.util.DateUtil;
import com.cn.hnust.util.UploadAndDownloadPathUtil;



/**
 * @Description: 根据模板文件打印，输出报价模板
 * @Author: polo
 * @CreateDate:2018/08/08
 */

public class QualityReportPrint {

	private static File tempPath;

	private static final Log LOG = LogFactory.getLog(QualityReportPrint.class);
	/**
	 * pdf打印,使用excel编辑，生成pdf
	 * 
	 * @param path
	 * @throws Exception
	 */
	public static String printExcel(HttpServletRequest request, String path,Project project,QualityReport qualityReport,
			List<QualityPicExplain> details,List<QualityPicExplain> bads,List<QualityPicExplain> materials,
			List<QualityPicExplain> packages,List<QualityPicExplain> checks)throws Exception {

        Boolean sampleFlag = (qualityReport.getType() == QualityTypeEnum.SAMPLE.getCode() || qualityReport.getType() == QualityTypeEnum.METAPHASE.getCode());
		Boolean packageFlag = (qualityReport.getType() == QualityTypeEnum.FINAL_TIME.getCode() || qualityReport.getType() == QualityTypeEnum.LARGE_CARGO.getCode()) && (packages == null || packages.size() == 0);
		/*
		 * 打开模板，复制sheet，另存
		 */
       
        File file = null;
        //样品或者中期检验，当无包装图时，使用quality_excel_sample.xls
        //样品或者中期检验，当有包装图时，使用quality_excel_package.xls
        //大货或者终期检验，当无包装图时，使用quality_excel_non_package.xls
        //大货或者终期检验，有包装图时，使用quality_excel20180807.xls
        //大货或者终期检验，有包装图无材质图时，使用quality_excel_non_material.xls
        
		if((qualityReport.getType() == QualityTypeEnum.SAMPLE.getCode() || qualityReport.getType() == QualityTypeEnum.METAPHASE.getCode()) && (packages == null || packages.size() == 0)){
			file = new File(path+File.separator+"demo"+File.separator+"quality_excel_sample.xls");//1
		}else if((qualityReport.getType() == QualityTypeEnum.SAMPLE.getCode() || qualityReport.getType() == QualityTypeEnum.METAPHASE.getCode()) && (packages != null && packages.size()>0)){
			file = new File(path+File.separator+"demo"+File.separator+"quality_excel_package.xls");//3
		}else if((qualityReport.getType() == QualityTypeEnum.FINAL_TIME.getCode() || qualityReport.getType() == QualityTypeEnum.LARGE_CARGO.getCode()) && (packages == null || packages.size() == 0)){
			file = new File(path+File.separator+"demo"+File.separator+"quality_excel_non_package.xls");//2
		}else if((qualityReport.getType() == QualityTypeEnum.FINAL_TIME.getCode() || qualityReport.getType() == QualityTypeEnum.LARGE_CARGO.getCode()) && (packages != null && packages.size()>0) && (materials == null || materials.size() == 0) && (bads == null || bads.size() == 0)){
			file = new File(path+File.separator+"demo"+File.separator+"quality_excel_non_material.xls");
		}else{
			file = new File(path+File.separator+"demo"+File.separator+"quality_excel20180807.xls");//4
		}

		FileInputStream fi = new FileInputStream(file);
		HSSFWorkbook wb = new HSSFWorkbook(fi);
		wb.cloneSheet(0); // 复制工作簿
		wb.setSheetName(1, "quality report"); // 设置工作簿名称

		// 设置相同内容
		int rowNo = 3;
		int colNo = 0;
		Row nRow = null;
		Cell nCell = null;

		HSSFSheet sheet = wb.getSheetAt(1); // 定位到当前工作表
		sheet.setForceFormulaRecalculation(true); // 强制公式自动计算，利用模板时，模板中的公式不会因值发生变化而自动计算。

		nRow = sheet.getRow(rowNo);
		nCell = nRow.getCell(2);
		nCell.setCellValue(project.getProjectName() == null ? "" : project.getProjectName());

		nRow = sheet.getRow(++rowNo);
		nCell = nRow.getCell(2);
		nCell.setCellValue(project.getProjectNo());
		nCell = nRow.getCell(6);
		nCell.setCellValue(qualityReport.getPicNum() == null ? "" : qualityReport.getPicNum());

		nRow = sheet.getRow(++rowNo);
		nCell = nRow.getCell(3);
		nCell.setCellValue(qualityReport.getPlace() == null ? "" : qualityReport.getPlace());
		nCell = nRow.getCell(7);
		nCell.setCellValue(qualityReport.getSpendTime() == null ? "" : qualityReport.getSpendTime());

		nRow = sheet.getRow(++rowNo);
		nCell = nRow.getCell(3);
		nCell.setCellValue(DateFormat.date2String(qualityReport.getCheckDate()));
		nCell = nRow.getCell(7);
		nCell.setCellValue(qualityReport.getOrders());
		
		nRow = sheet.getRow(++rowNo);
		nCell = nRow.getCell(2);
		nCell.setCellValue(QualityStatusEnum.getEnum(qualityReport.getStatus()).getValue() + (qualityReport.getCheckExplain() == null ? "" : qualityReport.getCheckExplain().replaceAll("\\s*", "")) + (qualityReport.getExplainCause() == null ? "" : qualityReport.getExplainCause().replaceAll("\\s*", "")));
		

		rowNo = 11;	
		String serverPath = UploadAndDownloadPathUtil.getProjectImg() + File.separator + qualityReport.getProjectNo()+ File.separator + "1" + File.separator;
		LOG.info("文件路径"+serverPath);
		//添加细节图片
		int d_tl = 0;
		int c_tl = 0; //检验表格图片数量
		int b_tl = 0; //有问题图片数量
		int m_tl = 0; //材质图片数量
		int p_tl = 0; //包装图片数量
		int height2=0;//details表数据处理
		int badsHeight=0;//bads表数据处理
		int materialsHeight=0;//materials表数据处理
		int packagesHeight=0;//packages表数据处理
		int rowNo1=rowNo;
		int rowNo4=0;
		int height1=0;
		int m=0;
		if(details != null && details.size() > 0){
			d_tl = details.size();
			for (int j=0;j<d_tl;j++){
//				File picFile1 = new File(details.get(j).getPicName());
//				String name1 = picFile1.getName();				
//				File f = new File(serverPath+name1);
				String picName = details.get(j).getPicName();
				String[] spilt = picName.split("\\/");
				String name = spilt[spilt.length-1];
				BufferedImage bufferedImage1 = ImageIO.read(new File(serverPath+name));
				double height = bufferedImage1.getHeight();
				double width = bufferedImage1.getWidth();
				double num=width/height;
				rowNo1=rowNo+height2-rowNo4+(getInt(32/num)+2);
				int d = (int) (rowNo1)/56;
				if(d>=1){
					m=1;
				int x = (int) Math.ceil(((double)(rowNo+height2+getInt(32/num)+2))/56);
				if((getInt(32/num)+2)>=56){
				x = (int) Math.ceil(((double)(rowNo+height2+54))/56);
					height2=56*(x-1)+56;	
				}else{
				height2=56*(x-1)+(getInt(32/num)+2);
				rowNo4=56*(x-1);
				}
				}else{
				height2+=getInt(32/num)+2;	
				}
			}
			
			
			if(height2>28){
				insertRow(wb, sheet, rowNo, height2-28);
			}else if(28>=height2){
				height2=28;
			}
			
			/*if(d_tl > 1){
				insertRow(wb, sheet, rowNo, (d_tl-1)*height2);
			}*/
			//如果是样品或者中期检验,质检表的图片数量进行插入行
			if(sampleFlag){
				if(bads != null && bads.size() > 0){
					b_tl =bads.size();
					rowNo1=rowNo;
					int rowNo2=height2+rowNo;
					int a = (int) Math.ceil((double)(rowNo2)/56);
					rowNo4=56*(a-1);
					
					for (int j=0;j<b_tl;j++){
//						File picFile1 = new File(bads.get(j).getPicName());
//						String name1 = picFile1.getName();
						String picName = bads.get(j).getPicName();
						String[] spilt = picName.split("\\/");
						String name = spilt[spilt.length-1];
						BufferedImage bufferedImage1 = ImageIO.read(new File(serverPath+name));
						double height = bufferedImage1.getHeight();
						double width = bufferedImage1.getWidth();
						double num=width/height;
						
						rowNo1=rowNo2+badsHeight-rowNo4+(getInt(32/num)+2);
						int d = (int) (rowNo1)/56;
						if(d>=1){
						int x = (int) Math.ceil(((double)(rowNo2+badsHeight+getInt(32/num)+2))/56);
						if((getInt(32/num)+2)>=56){
							x = (int) Math.ceil(((double)(rowNo2+badsHeight+54))/56);
						badsHeight=56*(x-1)-rowNo2+56;
						rowNo4=56*(x-1);	
						}else{
						badsHeight=56*(x-1)-rowNo2+getInt(32/num)+2;
						rowNo4=56*(x-1);
						}
						}else{
						badsHeight+=getInt(32/num)+2;	
						}
					}
					
					insertRow(wb, sheet, rowNo, badsHeight);
				}
				
				if(materials != null && materials.size() > 0){
					m_tl = materials.size();
					rowNo1=rowNo;
					int rowNo2=rowNo+height2+badsHeight;
					int a = (int) Math.ceil((double)(rowNo2)/56);
					rowNo4=56*(a-1);
					for (int j=0;j<m_tl;j++){
//						File picFile1 = new File(materials.get(j).getPicName());
//						String name1 = picFile1.getName();
						
						String picName = materials.get(j).getPicName();
						String[] spilt = picName.split("\\/");
						String name = spilt[spilt.length-1];
						BufferedImage bufferedImage1 = ImageIO.read(new File(serverPath+name));
						double height = bufferedImage1.getHeight();
						double width = bufferedImage1.getWidth();
						double num=width/height;
						rowNo1=rowNo2+materialsHeight-rowNo4+(getInt(32/num)+2);
						int d = (int) (rowNo1)/56;
						if(d>=1){
						int x = (int) Math.ceil(((double)(rowNo2+materialsHeight+getInt(32/num)+2))/56);
						if((getInt(32/num)+2)>=56){
							x = (int) Math.ceil(((double)(rowNo2+materialsHeight+54))/56);
						materialsHeight=56*(x-1)-rowNo2+56;
						rowNo4=56*(x-1);
						}else{
						materialsHeight=56*(x-1)-rowNo2+getInt(32/num)+2;
						rowNo4=56*(x-1);
						}
						}else{
						materialsHeight+=getInt(32/num)+2;	
						}
					}
					insertRow(wb, sheet, rowNo, materialsHeight);
				}
				
				//当包装图为空时，插入检验图数量
				if(packages == null || packages.size() == 0){
					if(checks != null && checks.size() > 0){
						c_tl = checks.size();
						rowNo1=rowNo;
						int rowNo2=rowNo+height2+badsHeight+materialsHeight;
						int a = (int) Math.ceil((double)(rowNo2)/56);
						rowNo4=56*(a-1);
						for (int j=0;j<c_tl;j++){
//							File picFile1 = new File(checks.get(j).getPicName());
//							String name1 = picFile1.getName();
							String picName = checks.get(j).getPicName();
							String[] spilt = picName.split("\\/");
							String name = spilt[spilt.length-1];
							BufferedImage bufferedImage1 = ImageIO.read(new File(serverPath+name));
							double height = bufferedImage1.getHeight();
							double width = bufferedImage1.getWidth();
							double num=width/height;
							rowNo1=rowNo2+packagesHeight-rowNo4+(getInt(32/num)+2);
							int d = (int) (rowNo1)/56;
							if(d>=1){
							int x = (int) Math.ceil(((double)(rowNo2+packagesHeight+getInt(32/num)+2))/56);
							if((getInt(32/num)+2)>=56){
								x = (int) Math.ceil(((double)(rowNo2+packagesHeight+54))/56);
							packagesHeight=56*(x-1)-rowNo2+56;
							rowNo4=56*(x-1);
							}else{
							packagesHeight=56*(x-1)-rowNo2+getInt(32/num)+2;
							rowNo4=56*(x-1);
							}
							}else{
							packagesHeight+=getInt(32/num)+2;	
							}
						}
						insertRow(wb, sheet, rowNo, packagesHeight);
					}
				}
			   
			}
			
			int dnum=1;
			int rowNo2=rowNo;
			int rowNo3=0;
			height1=0;
			for (int i=0;i<d_tl;i++){
//				File picFile = new File(details.get(i).getPicName());
//				String name = picFile.getName();
				
				String picName = details.get(i).getPicName();
				String[] spilt = picName.split("\\/");
				String name = spilt[spilt.length-1];
				BufferedImage bufferedImage = ImageIO.read(new File(serverPath+name));
				double height = bufferedImage.getHeight();
				double width = bufferedImage.getWidth();
				int startRow=0;
				int startCol = 0;
				int stopCol=0;
				int stopRow=0;
				double num=(width)/(height);
				rowNo2=rowNo+height1+getInt(32/num)+2-rowNo3;
				int d1 = (int)(rowNo2)/56;
				if(d1>=1){
					if(i==0){
						int x = (int) Math.ceil(((double)(rowNo+height1+(getInt(32/num)+2)))/56);
						if((getInt(32/num)+2)>=56){
							x = (int) Math.ceil(((double)(rowNo+height1+54))/56);
						startRow = 56*(x-1);
						startCol = 0;
						stopRow = getInt(56*(x-1) + 54);
					    stopCol =8;
						height1=56*(x-1)+56;
						rowNo3=	56*(x-1);	
						}else{
						startRow = 56*(x-1)+rowNo;
						startCol = 0;
						stopRow = getInt(56*(x-1) + 32/num)+rowNo;
						stopCol =8;
						height1=56*(x-1)+(getInt(32/num)+2)+rowNo;
						rowNo3=	56*(x-1)+rowNo;
						}	
					}else{
				int x = (int) Math.ceil(((double)(rowNo+height1+(getInt(32/num)+2)))/56);
				if((getInt(32/num)+2)>=56){
					x = (int) Math.ceil(((double)(rowNo+height1+54))/56);
				startRow = 56*(x-1);
				startCol = 0;
				stopRow = getInt(56*(x-1) + 54);
			    stopCol =8;
				height1=56*(x-1)+56;
				rowNo3=	56*(x-1);	
				}else{
				startRow = 56*(x-1);
				startCol = 0;
				stopRow = getInt(56*(x-1) + 32/num);
				stopCol =8;
				height1=56*(x-1)+(getInt(32/num)+2);
				rowNo3=	56*(x-1);
				}
					}
				}else{
					if(i==0){
						startRow =  height1+rowNo;
						startCol = 0;
						stopRow = getInt( height1 + 32/num)+rowNo;
						stopCol =8;
						height1+=getInt(32/num)+2;	
					}else{
				startRow =  height1;
				startCol = 0;
				stopRow = getInt( height1 + 32/num);
				stopCol =8;
				height1+=getInt(32/num)+2;
					}
				//rowNo3+=getInt(32/num)+2;
				}
				setPicture(serverPath+name, sheet, startRow, startCol, stopRow, stopCol);
				nRow = sheet.getRow(stopRow++);
				nCell = nRow.getCell(0);
				nCell.setCellValue(details.get(i).getPicExplain() == null ? "" : details.get(i).getPicExplain());			
			}
		}
		
		//样品和中期检验不显示除细节的图
		if(!sampleFlag){
					rowNo = rowNo + height2 + 18;
					int materialsHeight1=0;
			    	//添加不良品图片
					if(bads != null && bads.size() > 0){
						b_tl =bads.size();
						rowNo1=rowNo;
						int rowNo2=rowNo;
						int a =(int) Math.ceil((double)(rowNo2)/56);
						rowNo4=56*(a-1);
						int numa=rowNo2-rowNo4;
						for (int j=0;j<b_tl;j++){
//							File picFile1 = new File(bads.get(j).getPicName());
//							String name1 = picFile1.getName();
							
							String picName = bads.get(j).getPicName();
							String[] spilt = picName.split("\\/");
							String name = spilt[spilt.length-1];
							BufferedImage bufferedImage1 = ImageIO.read(new File(serverPath+name));
							double height = bufferedImage1.getHeight();
							double width = bufferedImage1.getWidth();
							double num=width/height;
							rowNo1=rowNo2+badsHeight-rowNo4+(getInt(32/num)+2);
							int d = (int) (rowNo1)/56;
							if(d>=1){
							int x = (int) Math.ceil(((double)(rowNo2+badsHeight+getInt(32/num)+2))/56);
							if((getInt(32/num)+2)>=56){
								x = (int) Math.ceil(((double)(rowNo2+badsHeight+54))/56);
							badsHeight=56*(x-1)-rowNo2+56;
							rowNo4=56*(x-1);	
							}else{
							if(56*(x-1)<rowNo2){
								badsHeight=56*(x)-rowNo2+getInt(32/num)+2;
								rowNo4=56*(x);	
							}else{
							badsHeight=56*(x-1)-rowNo2+getInt(32/num)+2;
							rowNo4=56*(x-1);
							}
							}
							}else{
							badsHeight+=getInt(32/num)+2;	
							}
						}
						
						
						
						if(badsHeight>28){
						insertRow(wb, sheet, rowNo, badsHeight-28);
						}
						
					if(materials != null && materials.size() > 0){
						m_tl = materials.size();
						rowNo1=rowNo;
						rowNo2=rowNo+badsHeight;
						a = (int) Math.ceil((double)(rowNo2)/56);
						rowNo4=56*(a-1);
						for (int j=0;j<m_tl;j++){
//							File picFile1 = new File(materials.get(j).getPicName());
//							String name1 = picFile1.getName();
							
							
							String picName = materials.get(j).getPicName();
							String[] spilt = picName.split("\\/");
							String name = spilt[spilt.length-1];
							BufferedImage bufferedImage1 = ImageIO.read(new File(serverPath+name));
							double height = bufferedImage1.getHeight();
							double width = bufferedImage1.getWidth();
							double num=width/height;
							rowNo1=rowNo2+materialsHeight-rowNo4+(getInt(32/num)+2);
							int d = (int) (rowNo1)/56;
							if(d>=1){
							int x = (int) Math.ceil(((double)(rowNo2+materialsHeight+getInt(32/num)+2))/56);
							if((getInt(32/num)+2)>=56){
								x = (int) Math.ceil(((double)(rowNo2+materialsHeight+54))/56);
							materialsHeight=56*(x-1)-rowNo2+56;
							rowNo4=56*(x-1);
							}else{
							materialsHeight=56*(x-1)-rowNo2+getInt(32/num)+2;
							rowNo4=56*(x-1);
							}
							}else{
							materialsHeight+=getInt(32/num)+2;	
							}
						}
						//materialsHeight=getHeight(m_tl,materials,serverPath);
						insertRow(wb, sheet, rowNo, materialsHeight);
					}
					
					//当包装图为空时，插入检验图数量
					 
					if(packages == null || packages.size() == 0){
						if(checks != null && checks.size() > 0){
							c_tl = checks.size();
							rowNo2=rowNo+materialsHeight;
							 a = (int) Math.ceil((double)(rowNo2)/56);
							rowNo4=56*(a-1);
							for (int j=0;j<c_tl;j++){
//								File picFile1 = new File(checks.get(j).getPicName());
//								String name1 = picFile1.getName();
								
								String picName = checks.get(j).getPicName();
								String[] spilt = picName.split("\\/");
								String name = spilt[spilt.length-1];
								BufferedImage bufferedImage1 = ImageIO.read(new File(serverPath+name));
								double height = bufferedImage1.getHeight();
								double width = bufferedImage1.getWidth();
								double num=width/height;
								rowNo1=rowNo2+packagesHeight-rowNo4+(getInt(32/num)+2);
								int d = (int) (rowNo1)/56;
								if(d>=1){
								int x = (int) Math.ceil(((double)(rowNo2+packagesHeight+getInt(32/num)+2))/56);
								if((getInt(32/num)+2)>=56){
									x = (int) Math.ceil(((double)(rowNo2+packagesHeight+54))/56);
								packagesHeight=56*(x-1)-rowNo2+56;
								rowNo4=56*(x-1);
								}else{
								packagesHeight=56*(x-1)-rowNo2+getInt(32/num)+2;
								rowNo4=56*(x-1);
								}
								}else{
								packagesHeight+=getInt(32/num)+2;	
								}
							}
						
							insertRow(wb, sheet, rowNo, packagesHeight);
						}
					}
						
					rowNo2=rowNo;
					a = (int) Math.ceil((double)(rowNo2)/56);
					int rowNo3=56*(a-1);
					
					int bHeight1=0;
						for (int i=0;i<b_tl;i++){
//							File picFile = new File(bads.get(i).getPicName());
//							String name = picFile.getName();
							
							String picName = bads.get(i).getPicName();
							String[] spilt = picName.split("\\/");
							String name = spilt[spilt.length-1];
							BufferedImage bufferedImage = ImageIO.read(new File(serverPath+name));
							double height = bufferedImage.getHeight();
							double width = bufferedImage.getWidth();
							int startRow=0;
							int startCol = 0;
							int stopCol=0;
							int stopRow=0;
							double num=(width)/(height);
							rowNo2=rowNo+bHeight1+getInt(32/num)+2-rowNo3;
							int d1 = (int)(rowNo2)/56;
							if(d1>=1){
							if(i==0){
								int x = (int) Math.ceil(((double)(rowNo+bHeight1+(getInt(32/num)+2)))/56);
								if((getInt(32/num)+2)>=56){
								x = (int) Math.ceil(((double)(rowNo+bHeight1+54))/56);
								startRow = 56*(x-1);
								startCol = 0;
								stopRow = getInt(56*(x-1) + 54);
								stopCol =8;
								bHeight1=getInt(56*(x-1))-rowNo+56;
								rowNo3=	56*(x-1);
								}else{
								startRow = 56*(x-1);
								startCol = 0;
								stopRow = getInt(56*(x-1) + 32/num);
								stopCol =8;
								bHeight1=getInt(56*(x-1))-rowNo+(getInt(32/num)+2);
								rowNo3=	56*(x-1);
								}
								}else{
							int x = (int) Math.ceil(((double)(rowNo+bHeight1+(getInt(32/num)+2)))/56);
							if((getInt(32/num)+2)>=56){
								x = (int) Math.ceil(((double)(rowNo+bHeight1+54))/56);
								startRow = 56*(x-1);
								startCol = 0;
								stopRow = getInt(56*(x-1) + 54);
								stopCol =8;
								bHeight1=getInt(56*(x-1))-rowNo+56;
								rowNo3=	56*(x-1);
							}else{
								if(56*(x-1)<rowNo){
									startRow = 56*(x);
									startCol = 0;
									stopRow = getInt(56*(x) + 32/num);
									stopCol =8;
									bHeight1=getInt(56*(x))-rowNo+(getInt(32/num)+2);
									rowNo3=	56*(x);	
								}else{
							startRow = 56*(x-1);
							startCol = 0;
							stopRow = getInt(56*(x-1) + 32/num);
							stopCol =8;
							bHeight1=getInt(56*(x-1))-rowNo+(getInt(32/num)+2);
							rowNo3=	56*(x-1);
								}
							}
								}
							}else{
							startRow = rowNo+ bHeight1;
							startCol = 0;
							stopRow = getInt( rowNo+bHeight1 + 32/num);
							stopCol =8;
							bHeight1+=getInt(32/num)+2;
							//rowNo3+=getInt(32/num)+2;
							}
							
							
							setPicture(serverPath+name, sheet, startRow, startCol, stopRow, stopCol);
							nRow = sheet.getRow(stopRow++);
							nCell = nRow.getCell(0);
							nCell.setCellValue(bads.get(i).getPicExplain() == null ? "" : bads.get(i).getPicExplain());			
						}
						
						rowNo = rowNo + bHeight1;
						//添加材质图片
						 
						for (int i=0;i<m_tl;i++){
//							File picFile = new File(materials.get(i).getPicName());
//							String name = picFile.getName();
							
							String picName = materials.get(i).getPicName();
							String[] spilt = picName.split("\\/");
							String name = spilt[spilt.length-1];
							BufferedImage bufferedImage = ImageIO.read(new File(serverPath+name));
							double height = bufferedImage.getHeight();
							double width = bufferedImage.getWidth();
							int startRow=0;
							int startCol = 0;
							int stopCol=0;
							int stopRow=0;
							double num=(width)/(height);
							rowNo2=rowNo+materialsHeight1+getInt(32/num)+2-rowNo3;
							int d1 = (int)(rowNo2)/56;
							if(d1>=1){
							int x = (int) Math.ceil(((double)(rowNo+materialsHeight1+(getInt(32/num)+2)))/56);
							if((getInt(32/num)+2)>=56){
								x = (int) Math.ceil(((double)(rowNo+materialsHeight1+54))/56);
							startRow = 56*(x-1);
							startCol = 0;
							stopRow = getInt(56*(x-1) + 54);
							stopCol =8;
							materialsHeight1=getInt(56*(x-1))-rowNo+56;
							rowNo3=	56*(x-1);
							}else{
							startRow = 56*(x-1);
							startCol = 0;
							stopRow = getInt(56*(x-1) + 32/num);
							stopCol =8;
							materialsHeight1=getInt(56*(x-1))-rowNo+(getInt(32/num)+2);
							rowNo3=	56*(x-1);
							}
							}else{
							startRow = rowNo+ materialsHeight1;
							startCol = 0;
							stopRow = getInt( rowNo+materialsHeight1 + 32/num);
							stopCol =8;
							materialsHeight1+=getInt(32/num)+2;
							//rowNo3+=getInt(32/num)+2;
							}
							
							
							
							setPicture(serverPath+name, sheet, startRow, startCol, stopRow, stopCol);
							nRow = sheet.getRow(stopRow++);
							nCell = nRow.getCell(0);
							nCell.setCellValue(materials.get(i).getPicExplain() == null ? "" : materials.get(i).getPicExplain());			
						}
					}else{
						
						//添加材质图片
						if(materials != null && materials.size() > 0){
							m_tl = materials.size();
							rowNo1=rowNo;
							int rowNo2=rowNo+height2+badsHeight;
							int a = (int) Math.ceil((double)(rowNo2)/56);
							rowNo4=56*(a-1);
							for (int j=0;j<m_tl;j++){
//								File picFile1 = new File(materials.get(j).getPicName());
//								String name1 = picFile1.getName();
								
								String picName = materials.get(j).getPicName();
								String[] spilt = picName.split("\\/");
								String name = spilt[spilt.length-1];
								
								BufferedImage bufferedImage1 = ImageIO.read(new File(serverPath+name));
								double height = bufferedImage1.getHeight();
								double width = bufferedImage1.getWidth();
								double num=width/height;
								rowNo1=rowNo2+materialsHeight-rowNo4+(getInt(32/num)+2);
								int d = (int) (rowNo1)/56;
								if(d>=1){
								int x = (int) Math.ceil(((double)(rowNo2+materialsHeight+getInt(32/num)+2))/56);
								if((getInt(32/num)+2)>=56){
									x = (int) Math.ceil(((double)(rowNo2+materialsHeight+54))/56);
								materialsHeight=56*(x-1)-rowNo2+56;
								rowNo4=56*(x-1);
								}else{
								materialsHeight=56*(x-1)-rowNo2+getInt(32/num)+2;
								rowNo4=56*(x-1);
								}
								}else{
								materialsHeight+=getInt(32/num)+2;	
								}
							}
							//materialsHeight=getHeight(m_tl,materials,serverPath);
							if(materialsHeight>28){
							insertRow(wb, sheet, rowNo, materialsHeight-28);
							}
						}
						
						//当包装图为空时，插入检验图数量
						if(packages == null || packages.size() == 0){
							if(checks != null && checks.size() > 0){
								c_tl = checks.size();
								int rowNo2=rowNo+materialsHeight;
								int a = (int) Math.ceil((double)(rowNo2)/56);
								rowNo4=56*(a-1);
								for (int j=0;j<c_tl;j++){
//									File picFile1 = new File(checks.get(j).getPicName());
//									String name1 = picFile1.getName();
									
									String picName = checks.get(j).getPicName();
									String[] spilt = picName.split("\\/");
									String name = spilt[spilt.length-1];
									BufferedImage bufferedImage1 = ImageIO.read(new File(serverPath+name));
									double height = bufferedImage1.getHeight();
									double width = bufferedImage1.getWidth();
									double num=width/height;
									rowNo1=rowNo2+packagesHeight-rowNo4+(getInt(32/num)+2);
									int d = (int) (rowNo1)/56;
									if(d>=1){
									int x = (int) Math.ceil(((double)(rowNo2+packagesHeight+getInt(32/num)+2))/56);
									if((getInt(32/num)+2)>=56){
										x = (int) Math.ceil(((double)(rowNo2+packagesHeight+54))/56);
									packagesHeight=56*(x-1)-rowNo2+56;
									rowNo4=56*(x-1);
									}else{
									packagesHeight=56*(x-1)-rowNo2+getInt(32/num)+2;
									rowNo4=56*(x-1);
									}
									}else{
									packagesHeight+=getInt(32/num)+2;	
									}
								}
								//packagesHeight=getHeight(c_tl,checks,serverPath);
								insertRow(wb, sheet, rowNo, packagesHeight);
							}
						}
						
						 
						for (int i=0;i<m_tl;i++){
//							File picFile = new File(materials.get(i).getPicName());
//							String name = picFile.getName();
							
							String picName = materials.get(i).getPicName();
							String[] spilt = picName.split("\\/");
							String name = spilt[spilt.length-1];
							BufferedImage bufferedImage = ImageIO.read(new File(serverPath+name));
							double height = bufferedImage.getHeight();
							double width = bufferedImage.getWidth();
							int startRow=0;
							int startCol = 0;
							int stopCol=0;
							int stopRow=0;
							if(height>width){
								
								double num2=(width)/(height);
								 startRow = rowNo + materialsHeight1;
								 startCol = 0;
								 stopRow = getInt(rowNo + materialsHeight1 + 32/num2);
								 stopCol =8;
								 materialsHeight1+=getInt((32/num2)+2);
								 //stopRow = rowNo + i*34 + 32;
								 //stopCol =(int)(8*num2);
								 //materialsHeight1+=34;
							}else {
								double num=((height)/(width));
								startRow = rowNo +materialsHeight1;
								 startCol = 0;
								 stopRow = getInt(rowNo + materialsHeight1 + 32*num);
								 materialsHeight1+=getInt(32*num)+2;
								stopCol = 8;
							}
							setPicture(serverPath+name, sheet, startRow, startCol, stopRow, stopCol);
							nRow = sheet.getRow(stopRow++);
							nCell = nRow.getCell(0);
							nCell.setCellValue(materials.get(i).getPicExplain() == null ? "" : materials.get(i).getPicExplain());			
						}
						
					}
					


					//添加包装图片
					if(packages != null && packages.size() > 0){
						rowNo = rowNo +materialsHeight1 + 1;
						if(m_tl == 0 && (bads == null || bads.size() == 0)){
							rowNo = rowNo - 4;
						}						
						nRow = sheet.getRow(rowNo);
						nCell = nRow.getCell(2);
						nCell.setCellValue(qualityReport.getBoxNumber());
						nCell = nRow.getCell(6);
						nCell.setCellValue(qualityReport.getPerQty());
						rowNo++;
						nRow = sheet.getRow(++rowNo);
						nCell = nRow.getCell(2);
						nCell.setCellValue(qualityReport.getInventoryQty());
						nCell = nRow.getCell(6);
						nCell.setCellValue(qualityReport.getOpenQty());						
						rowNo = rowNo + 3;
						int pHeight=0;
						int cHeight=0;
						if(packages != null && packages.size() > 0){
					    p_tl = packages.size();
					    
					    	rowNo1=rowNo;
						   int rowNo2=rowNo;
							int a = (int) Math.ceil((double)(rowNo2)/56);
							rowNo4=56*(a-1);
							for (int j=0;j<p_tl;j++){
//								File picFile1 = new File(packages.get(j).getPicName());
//								String name1 = picFile1.getName();
								
								String picName = packages.get(j).getPicName();
								String[] spilt = picName.split("\\/");
								String name = spilt[spilt.length-1];
								
								BufferedImage bufferedImage1 = ImageIO.read(new File(serverPath+name));
								double height = bufferedImage1.getHeight();
								double width = bufferedImage1.getWidth();
								double num=width/height;
								rowNo1=rowNo2+pHeight-rowNo4+(getInt(32/num)+2);
								int d = (int) (rowNo1)/56;
								if(d>=1){
								int x = (int) Math.ceil(((double)(rowNo2+pHeight+getInt(32/num)+2))/56);
								if((getInt(32/num)+2)>=56){
									x = (int) Math.ceil(((double)(rowNo2+pHeight+54))/56);
									pHeight=56*(x-1)-rowNo2+56;
								rowNo4=56*(x-1);
								}else{
									pHeight=56*(x-1)-rowNo2+getInt(32/num)+2;
								rowNo4=56*(x-1);
								}
								}else{
									pHeight+=getInt(32/num)+2;	
								}
							}
							//pHeight=getHeight(p_tl,packages,serverPath);
							if(pHeight>28){
							insertRow(wb, sheet, rowNo, pHeight-28);
							}
							//insertRow(wb, sheet, rowNo, 34*(p_tl-1));
						}
						
						if(checks != null && checks.size() > 0){
							c_tl = checks.size();
							
							   int rowNo2=rowNo+pHeight;
								int a = (int) Math.ceil((double)(rowNo2)/56);
								rowNo4=56*(a-1);
								for (int j=0;j<c_tl;j++){
//									File picFile1 = new File(checks.get(j).getPicName());
//									String name1 = picFile1.getName();
									
									String picName = checks.get(j).getPicName();
									LOG.info("图片名"+ picName);
									String[] spilt = picName.split("\\/");
									String name = spilt[spilt.length-1];
									LOG.info("错误行"+ serverPath+name);
									
									BufferedImage bufferedImage1 = ImageIO.read(new File(serverPath+name));
									double height = bufferedImage1.getHeight();
									double width = bufferedImage1.getWidth();
									double num=width/height;
									rowNo1=rowNo2+cHeight-rowNo4+(getInt(32/num)+2);
									int d = (int) (rowNo1)/56;
									if(d>=1){
									int x = (int) Math.ceil(((double)(rowNo2+cHeight+getInt(32/num)+2))/56);
									if((getInt(32/num)+2)>=56){
										x = (int) Math.ceil(((double)(rowNo2+cHeight+54))/56);
										cHeight=56*(x-1)-rowNo2+56;
									rowNo4=56*(x-1);
									}else{
										cHeight=56*(x-1)-rowNo2+getInt(32/num)+2;
									rowNo4=56*(x-1);
									}
									}else{
										cHeight+=getInt(32/num)+2;	
									}
								}
							//cHeight=getHeight(c_tl,checks,serverPath);
							insertRow(wb, sheet, rowNo, cHeight);
							//insertRow(wb, sheet, rowNo, 34*c_tl);
						}
						/*if(p_tl > 1){
							insertRow(wb, sheet, rowNo, 34*(p_tl-1));
						}
						
						//添加检验表格
						if(checks != null && checks.size()>0){
							c_tl = checks.size();
							insertRow(wb, sheet, rowNo, 34*c_tl);
						}*/
						int pHeight1=0;
						int rowNo2=rowNo;
					    int a = (int) Math.ceil((double)(rowNo2)/56);
						int rowNo3=56*(a-1);
						for (int i=0;i<p_tl;i++){
//						    File picFile = new File(packages.get(i).getPicName());
//							String name = picFile.getName();
							
							String picName = packages.get(i).getPicName();
							String[] spilt = picName.split("\\/");
							String name = spilt[spilt.length-1];
							BufferedImage bufferedImage = ImageIO.read(new File(serverPath+name));
							double height = bufferedImage.getHeight();
							double width = bufferedImage.getWidth();
							int startRow=0;
							int startCol = 0;
							int stopCol=0;
							int stopRow=0;
							double num=(width)/(height);
							rowNo2=rowNo+pHeight1+getInt(32/num)+2-rowNo3;
							int d1 = (int)(rowNo2)/56;
							if(d1>=1){
							int x = (int) Math.ceil(((double)(rowNo+pHeight1+(getInt(32/num)+2)))/56);
							if((getInt(32/num)+2)>=56){
								x = (int) Math.ceil(((double)(rowNo+pHeight1+54))/56);
								startRow = 56*(x-1);
								startCol = 0;
								stopRow = getInt(56*(x-1) + 54);
								stopCol =8;
								pHeight1=getInt(56*(x-1))-rowNo+56;
								rowNo3=	56*(x-1);	
							}else{
							startRow = 56*(x-1);
							startCol = 0;
							stopRow = getInt(56*(x-1) + 32/num);
							stopCol =8;
							pHeight1=getInt(56*(x-1))-rowNo+(getInt(32/num)+2);
							rowNo3=	56*(x-1);
							}
							}else{
							startRow = rowNo+ pHeight1;
							startCol = 0;
							stopRow = getInt( rowNo+pHeight1 + 32/num);
							stopCol =8;
							pHeight1+=getInt(32/num)+2;
							//rowNo3+=getInt(32/num)+2;
							}
						
							setPicture(serverPath+name, sheet, startRow, startCol, stopRow, stopCol);
							nRow = sheet.getRow(stopRow++);
							nCell = nRow.getCell(0);
							nCell.setCellValue(packages.get(i).getPicExplain() == null ? "" : packages.get(i).getPicExplain());			
						}
						
						rowNo = rowNo + pHeight1;
						rowNo2=rowNo;
					    a = (int) Math.ceil((double)(rowNo2)/56);
						rowNo3=56*(a-1);
						//添加检验表格图片
						int cHeight1=0;
						for (int i=0;i<c_tl;i++){
//							File picFile = new File(checks.get(i).getPicName());
//							String name = picFile.getName();
							
							String picName = checks.get(i).getPicName();
							String[] spilt = picName.split("\\/");
							String name = spilt[spilt.length-1];
							
						    BufferedImage bufferedImage = ImageIO.read(new File(serverPath+name));
							double height = bufferedImage.getHeight();
							double width = bufferedImage.getWidth();
							int startRow=0;
							int startCol = 0;
							int stopCol=0;
							int stopRow=0;
							double num=(width)/(height);
							rowNo2=rowNo+cHeight1+getInt(32/num)+2-rowNo3;
							int d1 = (int)(rowNo2)/56;
							if(d1>=1){
							int x = (int) Math.ceil(((double)(rowNo+cHeight1+(getInt(32/num)+2)))/56);
							if((getInt(32/num)+2)>=56){
								x = (int) Math.ceil(((double)(rowNo+cHeight1+54))/56);
								startRow = 56*(x-1);
								startCol = 0;
								stopRow = getInt(56*(x-1) + 54);
								stopCol =8;
								cHeight1=getInt(56*(x-1))-rowNo+56;
								rowNo3=	56*(x-1);	
							}else{
							startRow = 56*(x-1);
							startCol = 0;
							stopRow = getInt(56*(x-1) + 32/num);
							stopCol =8;
							cHeight1=getInt(56*(x-1))-rowNo+(getInt(32/num)+2);
							rowNo3=	56*(x-1);
							}
							}else{
							startRow = rowNo+ cHeight1;
							startCol = 0;
							stopRow = getInt( rowNo+cHeight1 + 32/num);
							stopCol =8;
							cHeight1+=getInt(32/num)+2;
							//rowNo3+=getInt(32/num)+2;
							}
							setPicture(serverPath+name, sheet, startRow, startCol, stopRow, stopCol);
							nRow = sheet.getRow(stopRow++);
							nCell = nRow.getCell(0);
							nCell.setCellValue(checks.get(i).getPicExplain() == null ? "" : checks.get(i).getPicExplain());			
						}
						
						
					}else{
						
						rowNo = rowNo + materialsHeight1;
						//添加检测表格图片
						
						int rowNo2=rowNo;
					    int a = (int) Math.ceil((double)(rowNo2)/56);
						int rowNo3=56*(a-1);
						int cHeight1=0;
						for (int i=0;i<c_tl;i++){
							
//							File picFile = new File(checks.get(i).getPicName());
//							String name = picFile.getName();
							
							String picName = checks.get(i).getPicName();
							String[] spilt = picName.split("\\/");
							String name = spilt[spilt.length-1];
							
							BufferedImage bufferedImage = ImageIO.read(new File(serverPath+name));
							double height = bufferedImage.getHeight();
							double width = bufferedImage.getWidth();
							int startRow=0;
							int startCol = 0;
							int stopCol=0;
							int stopRow=0;
							double num=(width)/(height);
							rowNo2=rowNo+cHeight1+getInt(32/num)+2-rowNo3;
							int d1 = (int)(rowNo2)/56;
							if(d1>=1){
							int x = (int) Math.ceil(((double)(rowNo+cHeight1+(getInt(32/num)+2)))/56);
							if((getInt(32/num)+2)>=56){
								x = (int) Math.ceil(((double)(rowNo+cHeight1+54))/56);
								startRow = 56*(x-1);
								startCol = 0;
								stopRow = getInt(56*(x-1) + 54);
								stopCol =8;
								cHeight1=getInt(56*(x-1))-rowNo+56;
								rowNo3=	56*(x-1);	
							}else{
							startRow = 56*(x-1);
							startCol = 0;
							stopRow = getInt(56*(x-1) + 32/num);
							stopCol =8;
							cHeight1=getInt(56*(x-1))-rowNo+(getInt(32/num)+2);
							rowNo3=	56*(x-1);
							}
							}else{
							startRow = rowNo+ cHeight1;
							startCol = 0;
							stopRow = getInt( rowNo+cHeight1 + 32/num);
							stopCol =8;
							cHeight1+=getInt(32/num)+2;
							//rowNo3+=getInt(32/num)+2;
							}
							
							setPicture(serverPath+name, sheet, startRow, startCol, stopRow, stopCol);
							nRow = sheet.getRow(stopRow++);
							nCell = nRow.getCell(0);
							nCell.setCellValue(checks.get(i).getPicExplain() == null ? "" : checks.get(i).getPicExplain());			
						}
					}
			
			
			
			
		}else{
			  
			if(packages == null || packages.size() == 0){
					//添加有问题图片
					rowNo = rowNo + height2;
					 int rowNo2=0;
					 int a = (int) Math.ceil((double)(rowNo2)/56);
					 int rowNo3=56*(a-1);
					int bHeight1=0;
					for (int i=0;i<b_tl;i++){
//						File picFile = new File(bads.get(i).getPicName());
//						String name = picFile.getName();
						
						String picName = bads.get(i).getPicName();
						String[] spilt = picName.split("\\/");
						String name = spilt[spilt.length-1];
						BufferedImage bufferedImage = ImageIO.read(new File(serverPath+name));
						double height = bufferedImage.getHeight();
						double width = bufferedImage.getWidth();
						int startRow=0;
						int startCol = 0;
						int stopCol=0;
						int stopRow=0;
						double num=(width)/(height);
						rowNo2=rowNo+bHeight1+getInt(32/num)+2-rowNo3;
						int d1 = (int)(rowNo2)/56;
						if(d1>=1){
						int x = (int) Math.ceil(((double)(rowNo+bHeight1+(getInt(32/num)+2)))/56);
						if((getInt(32/num)+2)>=56){
							x = (int) Math.ceil(((double)(rowNo+bHeight1+54))/56);
							startRow = 56*(x-1);
							startCol = 0;
							stopRow = getInt(56*(x-1) + 54);
							stopCol =8;
							bHeight1=getInt(56*(x-1))-rowNo+56;
							rowNo3=	56*(x-1);
						}else{
						startRow = 56*(x-1);
						startCol = 0;
						stopRow = getInt(56*(x-1) + 32/num);
						stopCol =8;
						bHeight1=getInt(56*(x-1))-rowNo+(getInt(32/num)+2);
						rowNo3=	56*(x-1);
						}
						}else{
						startRow = rowNo+ bHeight1;
						startCol = 0;
						stopRow = getInt( rowNo+bHeight1 + 32/num);
						stopCol =8;
						bHeight1+=getInt(32/num)+2;
						//rowNo3+=getInt(32/num)+2;
						}
						
						setPicture(serverPath+name, sheet, startRow, startCol, stopRow, stopCol);
						nRow = sheet.getRow(stopRow++);
						nCell = nRow.getCell(0);
						nCell.setCellValue(bads.get(i).getPicExplain() == null ? "" : bads.get(i).getPicExplain());			
					}
					
					//添加材质证明图片
					rowNo = rowNo + bHeight1;
					
					rowNo2=rowNo;
				    a = (int) Math.ceil((double)(rowNo2)/56);
					rowNo3=56*(a-1);
					int mHeight1=0;
					for (int i=0;i<m_tl;i++){
//						File picFile = new File(materials.get(i).getPicName());
//						String name = picFile.getName();
						
						
						String picName = materials.get(i).getPicName();
						String[] spilt = picName.split("\\/");
						String name = spilt[spilt.length-1];
						BufferedImage bufferedImage = ImageIO.read(new File(serverPath+name));
						double height = bufferedImage.getHeight();
						double width = bufferedImage.getWidth();
						int startRow=0;
						int startCol = 0;
						int stopCol=0;
						int stopRow=0;
						double num=(width)/(height);
						rowNo2=rowNo+mHeight1+getInt(32/num)+2-rowNo3;
						int d1 = (int)(rowNo2)/56;
						if(d1>=1){
						int x = (int) Math.ceil(((double)(rowNo+mHeight1+(getInt(32/num)+2)))/56);
						if((getInt(32/num)+2)>=56){
							x = (int) Math.ceil(((double)(rowNo+mHeight1+54))/56);
						startRow = 56*(x-1);
						startCol = 0;
						stopRow = getInt(56*(x-1) + 54);
						stopCol =8;
						mHeight1=getInt(56*(x-1))-rowNo+56;
						rowNo3=	56*(x-1);
						}else{
						startRow = 56*(x-1);
						startCol = 0;
						stopRow = getInt(56*(x-1) + 32/num);
						stopCol =8;
						mHeight1=getInt(56*(x-1))-rowNo+(getInt(32/num)+2);
						rowNo3=	56*(x-1);
						}
						}else{
						startRow = rowNo+ mHeight1;
						startCol = 0;
						stopRow = getInt( rowNo+mHeight1 + 32/num);
						stopCol =8;
						mHeight1+=getInt(32/num)+2;
						//rowNo3+=getInt(32/num)+2;
						}
						
						setPicture(serverPath+name, sheet, startRow, startCol, stopRow, stopCol);
						nRow = sheet.getRow(stopRow++);
						nCell = nRow.getCell(0);
						nCell.setCellValue(materials.get(i).getPicExplain() == null ? "" : materials.get(i).getPicExplain());			
					}
					
					//添加检验表格图片
					rowNo = rowNo +mHeight1;
					rowNo2=rowNo;
				    a = (int) Math.ceil((double)(rowNo2)/56);
					rowNo3=56*(a-1);
					int cHeight1=0;
					for (int i=0;i<c_tl;i++){
//						File picFile = new File(checks.get(i).getPicName());
//						String name = picFile.getName();
						
						String picName = checks.get(i).getPicName();
						String[] spilt = picName.split("\\/");
						String name = spilt[spilt.length-1];
						
						BufferedImage bufferedImage = ImageIO.read(new File(serverPath+name));
						double height = bufferedImage.getHeight();
						double width = bufferedImage.getWidth();
						int startRow=0;
						int startCol = 0;
						int stopCol=0;
						int stopRow=0;
						double num=(width)/(height);
						rowNo2=rowNo+cHeight1+getInt(32/num)+2-rowNo3;
						int d1 = (int)(rowNo2)/56;
						if(d1>=1){
						int x = (int) Math.ceil(((double)(rowNo+cHeight1+(getInt(32/num)+2)))/56);
						if((getInt(32/num)+2)>=56){
							x = (int) Math.ceil(((double)(rowNo+cHeight1+54))/56);
							startRow = 56*(x-1);
							startCol = 0;
							stopRow = getInt(56*(x-1) + 54);
							stopCol =8;
							cHeight1=getInt(56*(x-1))-rowNo+56;
							rowNo3=	56*(x-1);	
						}else{
						startRow = 56*(x-1);
						startCol = 0;
						stopRow = getInt(56*(x-1) + 32/num);
						stopCol =8;
						cHeight1=getInt(56*(x-1))-rowNo+(getInt(32/num)+2);
						rowNo3=	56*(x-1);
						}
						}else{
						startRow = rowNo+ cHeight1;
						startCol = 0;
						stopRow = getInt( rowNo+cHeight1 + 32/num);
						stopCol =8;
						cHeight1+=getInt(32/num)+2;
						//rowNo3+=getInt(32/num)+2;
						}
						
						setPicture(serverPath+name, sheet, startRow, startCol, stopRow, stopCol);
						nRow = sheet.getRow(stopRow++);
						nCell = nRow.getCell(0);
						nCell.setCellValue(checks.get(i).getPicExplain() == null ? "" : checks.get(i).getPicExplain());			
					}
			}else{
				//添加有问题图片
				
				rowNo = rowNo+ height1;
				int rowNo2=rowNo;
				int a = (int) Math.ceil((double)(rowNo2)/56);
				int rowNo3=56*(a-1);
				int bHeight1=0;
				for (int i=0;i<b_tl;i++){
//					File picFile = new File(bads.get(i).getPicName());
//					String name = picFile.getName();
					
					String picName = bads.get(i).getPicName();
					String[] spilt = picName.split("\\/");
					String name = spilt[spilt.length-1];
					
					BufferedImage bufferedImage = ImageIO.read(new File(serverPath+name));
					double height = bufferedImage.getHeight();
					double width = bufferedImage.getWidth();
					int startRow=0;
					int startCol = 0;
					int stopCol=0;
					int stopRow=0;
					double num=(width)/(height);
					rowNo2=rowNo+bHeight1+getInt(32/num)+2-rowNo3;
					int d1 = (int)(rowNo2)/56;
					if(d1>=1){
					int x = (int) Math.ceil(((double)(rowNo+bHeight1+(getInt(32/num)+2)))/56);
					if((getInt(32/num)+2)>=56){
						x = (int) Math.ceil(((double)(rowNo+bHeight1+54))/56);
						startRow = 56*(x-1);
						startCol = 0;
						stopRow = getInt(56*(x-1) + 54);
						stopCol =8;
						bHeight1=getInt(56*(x-1))-rowNo+56;
						rowNo3=	56*(x-1);
					}else{
					startRow = 56*(x-1);
					startCol = 0;
					stopRow = getInt(56*(x-1) + 32/num);
					stopCol =8;
					bHeight1=getInt(56*(x-1))-rowNo+(getInt(32/num)+2);
					rowNo3=	56*(x-1);
					}
					}else{
					startRow = rowNo+ bHeight1;
					startCol = 0;
					stopRow = getInt( rowNo+bHeight1 + 32/num);
					stopCol =8;
					bHeight1+=getInt(32/num)+2;
					//rowNo3+=getInt(32/num)+2;
					}
					setPicture(serverPath+name, sheet, startRow, startCol, stopRow, stopCol);
					nRow = sheet.getRow(stopRow++);
					nCell = nRow.getCell(0);
					nCell.setCellValue(bads.get(i).getPicExplain() == null ? "" : bads.get(i).getPicExplain());			
				}
				
				//添加材质证明图片
				rowNo = rowNo + bHeight1;
				int materialsHeight1=0;
				for (int i=0;i<m_tl;i++){
//					File picFile = new File(materials.get(i).getPicName());
//					String name = picFile.getName();
					
					String picName = materials.get(i).getPicName();
					String[] spilt = picName.split("\\/");
					String name = spilt[spilt.length-1];
					
					BufferedImage bufferedImage = ImageIO.read(new File(serverPath+name));
					double height = bufferedImage.getHeight();
					double width = bufferedImage.getWidth();
					int startRow=0;
					int startCol = 0;
					int stopCol=0;
					int stopRow=0;
					double num=(width)/(height);
					rowNo2=rowNo+materialsHeight1+getInt(32/num)+2-rowNo3;
					int d1 = (int)(rowNo2)/56;
					if(d1>=1){
					int x = (int) Math.ceil(((double)(rowNo+materialsHeight1+(getInt(32/num)+2)))/56);
					if((getInt(32/num)+2)>=56){
						x = (int) Math.ceil(((double)(rowNo+materialsHeight1+54))/56);
					startRow = 56*(x-1);
					startCol = 0;
					stopRow = getInt(56*(x-1) + 54);
					stopCol =8;
					materialsHeight1=getInt(56*(x-1))-rowNo+56;
					rowNo3=	56*(x-1);
					}else{
					startRow = 56*(x-1);
					startCol = 0;
					stopRow = getInt(56*(x-1) + 32/num);
					stopCol =8;
					materialsHeight1=getInt(56*(x-1))-rowNo+(getInt(32/num)+2);
					rowNo3=	56*(x-1);
					}
					}else{
					startRow = rowNo+ materialsHeight1;
					startCol = 0;
					stopRow = getInt( rowNo+materialsHeight1 + 32/num);
					stopCol =8;
					materialsHeight1+=getInt(32/num)+2;
					//rowNo3+=getInt(32/num)+2;
					}
					setPicture(serverPath+name, sheet, startRow, startCol, stopRow, stopCol);
					nRow = sheet.getRow(stopRow++);
					nCell = nRow.getCell(0);
					nCell.setCellValue(materials.get(i).getPicExplain() == null ? "" : materials.get(i).getPicExplain());			
				}
				
				//装箱信息
				rowNo = rowNo + materialsHeight + 1;
				nRow = sheet.getRow(rowNo);
				nCell = nRow.getCell(2);
				nCell.setCellValue(qualityReport.getBoxNumber());
				nCell = nRow.getCell(6);
				nCell.setCellValue(qualityReport.getPerQty());
				rowNo++;
				nRow = sheet.getRow(++rowNo);
				nCell = nRow.getCell(2);
				nCell.setCellValue(qualityReport.getInventoryQty());
				nCell = nRow.getCell(6);
				nCell.setCellValue(qualityReport.getOpenQty());					
				int pHeight=0;
				int cHeight=0;
				rowNo = rowNo + 3;
				if(packages != null && packages.size() > 0){
				p_tl = packages.size();
				
					rowNo1=rowNo;
					    rowNo2=rowNo;
						 a = (int) Math.ceil((double)(rowNo2)/56);
						rowNo4=56*(a-1);
						for (int j=0;j<p_tl;j++){
//							File picFile1 = new File(packages.get(j).getPicName());
//							String name1 = picFile1.getName();
							
							String picName = packages.get(j).getPicName();
							String[] spilt = picName.split("\\/");
							String name = spilt[spilt.length-1];
							
							BufferedImage bufferedImage1 = ImageIO.read(new File(serverPath+name));
							double height = bufferedImage1.getHeight();
							double width = bufferedImage1.getWidth();
							double num=width/height;
							rowNo1=rowNo2+pHeight-rowNo4+(getInt(32/num)+2);
							int d = (int) (rowNo1)/56;
							if(d>=1){
							int x = (int) Math.ceil(((double)(rowNo2+pHeight+getInt(32/num)+2))/56);
							if((getInt(32/num)+2)>=56){
								x = (int) Math.ceil(((double)(rowNo2+pHeight+54))/56);
								pHeight=56*(x-1)-rowNo2+56;
							rowNo4=56*(x-1);
							}else{
								pHeight=56*(x-1)-rowNo2+getInt(32/num)+2;
							rowNo4=56*(x-1);
							}
							}else{
								pHeight+=getInt(32/num)+2;	
							}
						}
					
					if(pHeight>28){
					insertRow(wb, sheet, rowNo, pHeight-28);
					}
					//insertRow(wb, sheet, rowNo, 34*(p_tl-1));
				}
				
				if(checks != null && checks.size() > 0){
					c_tl = checks.size();
					    rowNo2=rowNo+pHeight;
						 a = (int) Math.ceil((double)(rowNo2)/56);
						rowNo4=56*(a-1);
						for (int j=0;j<c_tl;j++){
//							File picFile1 = new File(checks.get(j).getPicName());
//							String name1 = picFile1.getName();
							
							String picName = checks.get(j).getPicName();
							String[] spilt = picName.split("\\/");
							String name = spilt[spilt.length-1];
							
							BufferedImage bufferedImage1 = ImageIO.read(new File(serverPath+name));
							double height = bufferedImage1.getHeight();
							double width = bufferedImage1.getWidth();
							double num=width/height;
							rowNo1=rowNo2+cHeight-rowNo4+(getInt(32/num)+2);
							int d = (int) (rowNo1)/56;
							if(d>=1){
							int x = (int) Math.ceil(((double)(rowNo2+cHeight+getInt(32/num)+2))/56);
							if((getInt(32/num)+2)>=56){
								x = (int) Math.ceil(((double)(rowNo2+cHeight+54))/56);
								cHeight=56*(x-1)-rowNo2+56;
							rowNo4=56*(x-1);
							}else{
								cHeight=56*(x-1)-rowNo2+getInt(32/num)+2;
							rowNo4=56*(x-1);
							}
							}else{
								cHeight+=getInt(32/num)+2;	
							}
						}
					
					insertRow(wb, sheet, rowNo, cHeight);
					//insertRow(wb, sheet, rowNo, 34*c_tl);
				}
				int pHeight1=0;
				 rowNo2=rowNo;
			     a = (int) Math.ceil((double)(rowNo2)/56);
				 rowNo3=56*(a-1);
				for (int i=0;i<p_tl;i++){
//					File picFile = new File(packages.get(i).getPicName());
//					String name = picFile.getName();
					
					String picName = packages.get(i).getPicName();
					String[] spilt = picName.split("\\/");
					String name = spilt[spilt.length-1];
					
					BufferedImage bufferedImage = ImageIO.read(new File(serverPath+name));
					double height = bufferedImage.getHeight();
					double width = bufferedImage.getWidth();
					int startRow=0;
					int startCol = 0;
					int stopCol=0;
					int stopRow=0;
					double num=(width)/(height);
					rowNo2=rowNo+pHeight1+getInt(32/num)+2-rowNo3;
					int d1 = (int)(rowNo2)/56;
					if(d1>=1){
					int x = (int) Math.ceil(((double)(rowNo+pHeight1+(getInt(32/num)+2)))/56);
					if((getInt(32/num)+2)>=56){
						x = (int) Math.ceil(((double)(rowNo+pHeight1+54))/56);
						startRow = 56*(x-1);
						startCol = 0;
						stopRow = getInt(56*(x-1) + 54);
						stopCol =8;
						pHeight1=getInt(56*(x-1))-rowNo+56;
						rowNo3=	56*(x-1);	
					}else{
					startRow = 56*(x-1);
					startCol = 0;
					stopRow = getInt(56*(x-1) + 32/num);
					stopCol =8;
					pHeight1=getInt(56*(x-1))-rowNo+(getInt(32/num)+2);
					rowNo3=	56*(x-1);
					}
					}else{
					startRow = rowNo+ pHeight1;
					startCol = 0;
					stopRow = getInt( rowNo+pHeight1 + 32/num);
					stopCol =8;
					pHeight1+=getInt(32/num)+2;
					//rowNo3+=getInt(32/num)+2;
					}
				
					setPicture(serverPath+name, sheet, startRow, startCol, stopRow, stopCol);
					nRow = sheet.getRow(stopRow++);
					nCell = nRow.getCell(0);
					nCell.setCellValue(packages.get(i).getPicExplain() == null ? "" : packages.get(i).getPicExplain());			
				}
				
				//添加检验表格图片
				rowNo = rowNo + pHeight1;
				rowNo2=rowNo;
			    a = (int) Math.ceil((double)(rowNo2)/56);
				rowNo3=56*(a-1);
				int cHeight1=0;
				for (int i=0;i<c_tl;i++){
//	               File picFile = new File(checks.get(i).getPicName());
//					String name = picFile.getName();
					
					String picName = checks.get(i).getPicName();
					String[] spilt = picName.split("\\/");
					String name = spilt[spilt.length-1];
					
					BufferedImage bufferedImage = ImageIO.read(new File(serverPath+name));
					double height = bufferedImage.getHeight();
					double width = bufferedImage.getWidth();
					int startRow=0;
					int startCol = 0;
					int stopCol=0;
					int stopRow=0;
					double num=(width)/(height);
					rowNo2=rowNo+cHeight1+getInt(32/num)+2-rowNo3;
					int d1 = (int)(rowNo2)/56;
					if(d1>=1){
					int x = (int) Math.ceil(((double)(rowNo+cHeight1+(getInt(32/num)+2)))/56);
					if((getInt(32/num)+2)>=56){
						x = (int) Math.ceil(((double)(rowNo+cHeight1+54))/56);
						startRow = 56*(x-1);
						startCol = 0;
						stopRow = getInt(56*(x-1) + 54);
						stopCol =8;
						cHeight1=getInt(56*(x-1))-rowNo+56;
						rowNo3=	56*(x-1);	
					}else{
					startRow = 56*(x-1);
					startCol = 0;
					stopRow = getInt(56*(x-1) + 32/num);
					stopCol =8;
					cHeight1=getInt(56*(x-1))-rowNo+(getInt(32/num)+2);
					rowNo3=	56*(x-1);
					}
					}else{
					startRow = rowNo+ cHeight1;
					startCol = 0;
					stopRow = getInt( rowNo+cHeight1 + 32/num);
					stopCol =8;
					cHeight1+=getInt(32/num)+2;
					//rowNo3+=getInt(32/num)+2;
					}
					
					setPicture(serverPath+name, sheet, startRow, startCol, stopRow, stopCol);
					nRow = sheet.getRow(stopRow++);
					nCell = nRow.getCell(0);
					nCell.setCellValue(checks.get(i).getPicExplain() == null ? "" : checks.get(i).getPicExplain());			
				}
			}
		}

		HSSFPrintSetup ps = sheet.getPrintSetup();		 
		ps.setLandscape(false); // 打印方向，true：横向，false：纵向
		ps.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE); //纸张
		sheet.setMargin(HSSFSheet.BottomMargin,( double ) 0.5 );// 页边距（下）
		sheet.setMargin(HSSFSheet.LeftMargin,( double ) 0.1 );// 页边距（左）
		sheet.setMargin(HSSFSheet.RightMargin,( double ) 0.1 );// 页边距（右）
		sheet.setMargin(HSSFSheet.TopMargin,( double ) 0.5 );// 页边距（上）
		sheet.setHorizontallyCenter(true);//设置打印页面为水平居中
		sheet.setVerticallyCenter(true);//设置打印页面为垂直居中使用POI输出Excel时打印页面的设置
		String paths = UploadAndDownloadPathUtil.getFilePath();
        tempPath = new File(paths);
		// deleteFile(tempPath);
		if (!tempPath.exists() || !tempPath.isDirectory()) {
			tempPath.mkdir(); // 如果不存在，则创建该文件夹
		}
		wb.removeSheetAt(0); // 删除模板sheet
		StringBuffer fileName = new StringBuffer();
		fileName.append(DateFormat.currentDate().replace("-", ""));
		fileName.append("_");
		fileName.append(project.getProjectNo());
		fileName.append("_");
		if(StringUtils.isNotBlank(project.getProjectName())){
			String projectName = project.getProjectName();
			projectName = projectName.replaceAll("[^(a-zA-Z0-9\\u4e00-\\u9fa5)]", "");
			fileName.append(projectName);
			fileName.append("_");
		}
		if(StringUtils.isNotBlank(qualityReport.getPicNum())){
			String picNum = qualityReport.getPicNum();
			picNum = picNum.replaceAll("[^(a-zA-Z0-9\\u4e00-\\u9fa5)]", "");
			fileName.append(picNum);
			fileName.append("_");
		}
		fileName.append(QualityTypeEnum.getEnum(qualityReport.getType()).getValue());
		fileName.append("_");
		fileName.append(qualityReport.getUser());
		fileName.append(".xls");
		paths = paths + File.separator + fileName;
		FileOutputStream fs = new FileOutputStream(paths, false);
		wb.write(fs);
		fs.close();		

		return paths;
	}


	
	
	
	private static int getHeight(int d_tl, List<QualityPicExplain> details,
			String serverPath) {
		int height2=0;
		try {
		for (int j=0;j<d_tl;j++){
			File picFile1 = new File(details.get(j).getPicName());
			String name1 = picFile1.getName();
			BufferedImage bufferedImage1 = ImageIO.read(new File(serverPath+name1));
			double height3 = bufferedImage1.getHeight();
			double width3 = bufferedImage1.getWidth();
			if(height3>width3){
				double num2=width3/height3;
				 height2+=getInt(32/num2)+2;
			}else {
				double num=height3/width3;
				height2+=getInt(32*num)+2;
			}
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return height2;
	}






	// 删除文件和目录
	private static void clearFiles(String workspaceRootPath) {
		File file = new File(workspaceRootPath);
		if (file.exists()) {
			deleteFile(file);
		}
	}

	public static void deleteFile(File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				deleteFile(files[i]);
			}
		}
		file.delete();
	}

	public static void delFolder(String folderPath) {
		try {
			boolean delAllFile = delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除指定文件夹下所有文件
	// param path 文件夹完整绝对路径
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * excel插入行数据
	 * 
	 * @param wb
	 * @param sheet
	 * @param starRow
	 * @param rows
	 */
	public static void insertRow(HSSFWorkbook wb, HSSFSheet sheet, int starRow,
			int rows) {

		sheet.shiftRows(starRow + 1, sheet.getLastRowNum(), rows, true, false);

		starRow = starRow - 1;

		for (int i = 0; i < rows; i++) {

			HSSFRow sourceRow = null;
			HSSFRow targetRow = null;
			HSSFCell sourceCell = null;
			HSSFCell targetCell = null;
			short m;

			starRow = starRow + 1;
			sourceRow = sheet.getRow(starRow);
			targetRow = sheet.createRow(starRow + 1);
			targetRow.setHeight(sourceRow.getHeight());

			for (m = sourceRow.getFirstCellNum(); m < sourceRow
					.getLastCellNum(); m++) {

				sourceCell = sourceRow.getCell(m);
				targetCell = targetRow.createCell(m);
				targetCell.setCellStyle(sourceCell.getCellStyle());
				targetCell.setCellType(sourceCell.getCellType());

			}
		}

	}

	

	// 处理图片备注存放 偏移量不同 dx1 = 255; dy1 = 125; dx2 = 200; dy2 = 150;
	public static void setPicture(String pic, HSSFSheet sheet, int startRow,
			int startCol, int stopRow, int stopCol) throws IOException {
		File imageFile = new File(pic);
		
		if (imageFile.exists()) {
			InputStream is = new FileInputStream(new File(pic));
			byte[] bytes = IOUtils.toByteArray(is);
			int pictureIdx = sheet.getWorkbook().addPicture(bytes,
					Workbook.PICTURE_TYPE_JPEG); // 扩展名可为.jpg/.jpeg/.png
			is.close();

			HSSFPatriarch drawing = sheet.createDrawingPatriarch(); // Create
																	// the
																	// drawing
																	// patriarch.
																	// This is
																	// the top
																	// level
																	// container
																	// for all
																	// shapes.
			
	
			// 前面四个参数是图片偏移量
			HSSFClientAnchor anchor = new HSSFClientAnchor(500, 0, 500, 0,
					(short) startCol, startRow, (short) stopCol, stopRow); // add
/*			// 前面四个参数是图片偏移量
			HSSFClientAnchor anchor = new HSSFClientAnchor(150, 20, 200, 0,
					(short) startCol, startRow, (short) stopCol, stopRow); // add
*/																			// a
																			// picture
																			// shape
			anchor.setRow1(startRow); // set position corner of the picture
			anchor.setCol1(startCol);
			anchor.setRow2(stopRow);
			anchor.setCol2(stopCol);

			drawing.createPicture(anchor, pictureIdx);
		}

	}

	public static int getInt(double number){
	    BigDecimal bd=new BigDecimal(number).setScale(0, BigDecimal.ROUND_HALF_UP);
	    return Integer.parseInt(bd.toString()); 
	    } 
	


	

	public static void main(String[] args) throws Exception {
		
		String str = "20180830_SHS11548_白色ABSPC件_-01/-02_样品检验_zoumin.xls";
		str = str.replaceAll("[^(a-zA-Z0-9\\u4e00-\\u9fa5)]", "");
		System.out.println(str);
		
	}

}
