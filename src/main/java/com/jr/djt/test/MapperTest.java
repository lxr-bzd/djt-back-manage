package com.jr.djt.test;

import java.util.List;
import java.util.UUID;

import javax.xml.transform.Source;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jr.djt.beans.AdminTimeBean;
import com.jr.djt.beans.Crow;
import com.jr.djt.beans.TableBean;
import com.jr.djt.beans.UserDataBean;
import com.jr.djt.beans.WorkerBean;
import com.jr.djt.dao.DjtUserMapper;
import com.jr.djt.dao.ISupAdminMapper;
import com.jr.djt.dao.ITableMapper;
import com.jr.djt.dao.StartupListenerMapper;
import com.jr.djt.dao.UpdateDBMapper;
import com.jr.djt.dao.WorkManageMapper;
import com.jr.djt.service.ISupAdminService;
import com.jr.djt.service.ITableService;

/**
 * 测试dao层工作
 * @author qiuchen
 * 推荐spring的项目使用spring的单元测试，可以自动注入我们需要的组件
 * 1.导入springTest模块
 * 2.@ContextConfiguration指定spring配置文件的位置
 * 3.直接用@autowired 可以直接注入要使用的组件即可
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {
	@Autowired
	private UpdateDBMapper udbm;
	@Autowired
	private SqlSession sqlSession;//批量的sqlsession
	@Autowired
	private StartupListenerMapper slm;
	@Autowired
	private ISupAdminMapper isam;
	@Autowired
	WorkManageMapper wmm;
	@Autowired
	ISupAdminService ias;
	@Autowired
	ITableService itm;
	/**
	 * 测试dept部门的mapper
	 */
	@Test
	public void testUserBean(){
		//System.out.println(udbm.getDataCount());
		/*AdminTimeBean adb = slm.getAdminTimeById(1);
		System.out.println(adb.getT_val());*/
		UserDataBean ud = new UserDataBean();
		ud.setU_name("zch");
		ud.setU_password("123456");
		ias.insertAdmin(ud);
		//System.out.println(ud.getU_id());
		/*WorkerBean wb = wmm.selectWorkerById(6);
		List<Crow> cr = wb.getCr();
		for (Crow crow : cr) {
			System.out.println(crow.getId());
		}*/
	}
	@Test
	public void testable(){
		System.out.println(itm);
		List<TableBean> getusetable = itm.getusetable();
		for (TableBean tableBean : getusetable) {
			System.out.println(tableBean.getdTableId());
			
		}
	}
	@Test
	public void test3(){
		Boolean boo = wmm.getboolByUname("q");
		if(boo){
			System.out.println("存在");
		}else{
			System.out.println("不存在");
		}
		
	}
}
