package com.jr.djt.listener;

import javax.servlet.ServletContextEvent;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ContextLoaderListener;

import com.jr.djt.beans.AdminTimeBean;
import com.jr.djt.service.WorkManageService;
import com.jr.djt.service.impl.StartupListenerService;
import com.jr.djt.service.impl.WorkManageServiceImpl;

//@Controller
public class StartupListener extends ContextLoaderListener {
	private static StartupListenerService sls;
	
	public StartupListener(){
		if(sls==null){
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			sls = context.getBean(StartupListenerService.class);
		}
	}
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("监听contextinit方法");
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//int i=0;
				while(true){
					AdminTimeBean adb = sls.getAdminTimeById(2);
					long t_c_val = adb.getT_c_val();
					long t_val = adb.getT_val();
					int t_isclock = adb.getT_isclock();
					if(t_val==t_c_val && t_isclock==2){
						try {
							//每30秒检测一次，是否加了时间
							Thread.sleep(30*1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(t_val>t_c_val && t_isclock==2){
						adb.setT_isclock(1);
						sls.updateAdminTimeById(adb);						
						//wms.locksys(1);
						try {
							Thread.sleep(3600*1000);
							//Thread.sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						adb.setT_c_val(t_c_val+1);
						sls.updateAdminTimeById(adb);
					}else if(t_val>t_c_val){
						adb.setT_c_val(t_c_val+1);
						try {
							Thread.sleep(3600*1000);
							//Thread.sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						sls.updateAdminTimeById(adb);
					}else if(t_val==t_c_val && t_isclock==1){
						adb.setT_isclock(2);
						sls.updateAdminTimeById(adb);
						
					}else{
						try {
							Thread.sleep(3600*1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					/*i++;
					if(i%10==0){
						System.gc();
					}*/
					adb = null;
				}
			}
		}).start();
	}
}
