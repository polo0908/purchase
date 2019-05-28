package org.zsl.testmybatis;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cn.hnust.dao.TrackMapper;
import com.cn.hnust.pojo.TrackPlace;
import com.cn.hnust.pojo.TrackQuery;
import com.cn.hnust.service.TrackService;
import com.github.pagehelper.PageHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
// 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class TestTrack {
	
	@Resource
	private TrackMapper trackMapper;
	@Resource
	private TrackService trackService;

	 @Test
	public void test1(){
		 
		 List<TrackQuery> tracks = new ArrayList<TrackQuery>();
		  for (int i = 0; i < 5; i++) {
			  TrackQuery track = new TrackQuery();
	          track.setCreateTime(new Date());
	          track.setUserName("zhaochun");
	          track.setOutDate(new Date());
	          List<TrackPlace> list = new ArrayList<TrackPlace>();
	          TrackPlace trackPlace = new TrackPlace();
	          trackPlace.setPlace("公司");
	          list.add(trackPlace);
//	          track.setList(list);
	          tracks.add(track);
		  }
		  int count = trackService.insertBatch(tracks);
         
		  assertEquals(true,count>0);
	}

	 @Test
	public void test2(){
		TrackQuery track = new TrackQuery(10,"zhaochun","2019-03","2019-05",null,0,10);
	    PageHelper.startPage(0,10);
		List<TrackQuery> trackList = trackService.selectByDate(track);		
		System.out.println(trackList);
		assertEquals(true,trackList!=null);
	}

		
	

}
