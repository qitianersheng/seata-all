package com.example.one.service;

import com.example.one.dao.TblOneDao;
import com.example.one.entity.TblOne;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * @author yueyi2019
 */
@Service
public class RmOneService {

	@Autowired
	TblOneDao mapper;


	public String rm1() {



		//给自己的one的数据库插入数据
		TblOne o = new TblOne();
		o.setId(1);
		o.setName("rm1");
		mapper.insertSelective(o);

		//调用其他两个表的方法
		rm2();
		rm3();

		return "";
	}


	public String rm2Update() {
		rm2UpdateRemote();

		TblOne o = new TblOne();
		o.setId(2);
		o.setName("rm2");
		mapper.insertSelective(o);
//		int i = 1/0;
		return "";
	}


	@Autowired
	private RestTemplate restTemplate;

	private void rm2() {
		//这里就是通过eureka，这个two是服务二注册到eureka的名称
		restTemplate.getForEntity("http://two/rm2", null);
	}

	private void rm2UpdateRemote() {
		restTemplate.getForEntity("http://two/rm2-update", null);
	}

	private void rm3() {
		restTemplate.getForEntity("http://three/rm3", null);
	}
}
