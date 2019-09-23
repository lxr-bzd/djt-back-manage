package com.jr.djt.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jr.djt.beans.Crow;
import com.jr.djt.beans.Crow_Sum;
import com.jr.djt.beans.DataBaseBean;
import com.jr.djt.beans.MessageBean;
import com.jr.djt.beans.WorkerBean;
import com.jr.djt.service.WorkManageService;

/**
 * 后台管理用户信息及工作查看
 * 
 * @author qiuchen
 *
 */
@Controller
public class WorkManageController extends BaseController {
	@Autowired
	private WorkManageService wms;
	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping("/workerspage")
	public String workerPage(){
		return "worker/all_workers";
	}
	/**
	 * 單個刪除
	 * @param unum
	 * @return
	 */
	@RequestMapping("deleteOneById")
	@ResponseBody
	public MessageBean deleteOneById(Integer unum){
		wms.deleteOneById(unum);
		return MessageBean.success();
	}
	
	/**
	 * 模糊查詢用戶
	 * @param pageNum 查詢的頁數
	 * @param name 查詢的名字
	 * @return
	 */
	@RequestMapping("getWorkerByName")
	@ResponseBody
	public MessageBean getWorkerByName(Integer pageNum,String name){
		PageHelper.startPage(pageNum, 15);
		List<WorkerBean> list = wms.getWorkerByName(name);
		PageInfo page = new PageInfo(list);
		return MessageBean.success().add("pageInfo", page);
	}
	
	/**
	 * 刪除方法
	 * @param list 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/deleteworkerlist",method=RequestMethod.POST)
	@ResponseBody
	public MessageBean deleteworkerByIds(String list) throws Exception{
		if(list==null || list.trim().equals("")){
			System.out.println("異常");
			throw new Exception();
		}
		wms.deleteworkerByIds(list);
		return MessageBean.success();
	}
	/**
	 * 插入新用戶
	 * @return
	 */
	@RequestMapping(value="/insert_user",method=RequestMethod.POST)
	@ResponseBody
	public MessageBean insertUser(WorkerBean worker){
		worker.setLogin_time(System.currentTimeMillis());
		//设置工作表的组数：1.查出现在使用的是哪一组生表数据
		//wms.getUseTable();
		wms.insertUser(worker);
		return MessageBean.success();
	}
	/**
	 * 分页查看用户信息
	 * @return
	 */
	@RequestMapping("/allWorker")
	@ResponseBody
	public MessageBean selectAllWorker(@RequestParam(value = "pn", defaultValue = "1") Integer pn){
		PageHelper.startPage(pn, 15);
		//获得普通用户集合(包含用户的计算表信息)
		List<WorkerBean> workerlist = wms.getAllWorker();
		if(workerlist.size()>0){
			PageInfo<WorkerBean> page = new PageInfo<WorkerBean>(workerlist,5);
			return MessageBean.success().add("pageInfo", page);
		}
		return MessageBean.fail();
	}
	
	/**
	 * 通过id锁定/解鎖用户
	 * @param uid
	 * @return
	 */
	@RequestMapping("/lockWorker")
	@ResponseBody
	public MessageBean lockWorker(HttpServletRequest req,Integer uid, Integer lock_status){
		System.out.println("用户id:"+uid+",锁定状态："+lock_status);
		wms.lockWorker(uid,lock_status);
		return MessageBean.success();
		
		
	}
	/**
	 * 通过id查询用户详细信息
	 * @param uid
	 * @return
	 */
	@RequestMapping(value="/workerById")
	@ResponseBody
	public MessageBean selectWorkerById(Integer u_num, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,@RequestParam(value = "model", defaultValue = "1")Integer model){
		System.out.println("传入id:"+u_num+",传入pageNum:"+pageNum+",採用模式為:"+model);
		long time1 = System.currentTimeMillis();
		WorkerBean worker = wms.getWorkerById(u_num);
		if(worker!=null){
			List<Crow> cr = worker.getCr();
			//转换集合，将行集合转换成组集合
			List<Crow_Sum> db = rowChangList(cr,pageNum,model);
			long time2 = System.currentTimeMillis();
			worker.setCr(null);
			System.out.println("轉換花費事件:"+(time2-time1));
			return MessageBean.success().add("worker", worker).add("list", db);
		}
		return MessageBean.fail();
	}
	//转换对象结果集,確認是那種模式下的
	private List<Crow_Sum> rowChangList(List<Crow> cr,Integer pageNum,Integer model) {
		// TODO Auto-generated method stub
		//先给cr排序
		Collections.sort(cr, new Comparator(){
			@Override
			public int compare(Object o1, Object o2) {
				// TODO Auto-generated method stub
				if(o1 instanceof Crow && o2 instanceof Crow){
					Crow cr1 = (Crow) o1;
					Crow cr2 = (Crow) o2;
					return cr1.getCrow()-cr2.getCrow();
				}
				return 1;
			}
		});
		List<Crow_Sum> list = new ArrayList();
		//只需要3组数据
		for(int index=0;index<3;index++){
			StringBuffer sheng = new StringBuffer(),pei = new StringBuffer(),dui = new StringBuffer(),gong = new StringBuffer(),gong_col = new StringBuffer(),count = new StringBuffer(),c_sum = new StringBuffer(),a_sum = new StringBuffer();
			//每次循环获得一组的数据
			for(int cr_index=0;cr_index<cr.size();cr_index++){
				Crow crow = cr.get(cr_index);
				//获取生的值
				String str_s = crow.getSheng();
				if(str_s!=null){
					String[] str_s_arr = str_s.split(",");
					sheng.append(str_s_arr[(pageNum-1)*3+index]).append(",");
				}
				//获取配的值
				String str_p = crow.getPei();
				//获取兑的值
				String str_d = crow.getDui();
				if(str_p!=null){
					String[] str_d_arr = str_d.split(",");
					pei.append(str_p).append(",");
					dui.append(str_d_arr[(pageNum-1)*3+index]).append(",");
				}
				//获取供的值
				String str_g = crow.getGong();
				if(str_g!=null){
					String[] str_g_arr = str_g.split(",");
					gong.append(str_g_arr[(pageNum-1)*3+index]).append(",");
				}
				//获取供的颜色值
				String str_gong_col = crow.getGong_col();
				//获取统计值
				String str_count = crow.getCount();
				//先设置实计值与统计值相同
				String str_actual = str_count;
				if(str_gong_col!=null){
					String[] str_gong_col_arr = str_gong_col.split(",");
					String[] str_count_arr = str_count.split(",");
					gong_col.append(str_gong_col_arr[(pageNum-1)*3+index]).append(",");
					count.append(str_count_arr[(pageNum-1)*3+index]).append(",");
					int sum_c=0;
					int sum_a=0;
					String[] a_arr = str_actual.split(",");
					if(cr.get(cr_index-1).getCount()!=null){
						String[] last_count_arr = cr.get(cr_index-1).getCount().split(",");
						for(int arr_index=0;arr_index<last_count_arr.length;arr_index++){
							if(model==1 && Integer.parseInt(last_count_arr[arr_index])<=0){
								a_arr[arr_index]="0";
							}else if(model==2 && Integer.parseInt(last_count_arr[arr_index])>=0){
								a_arr[arr_index]="0";
							}
						}
					}
					//设置统计和的值
					for(int i=0;i<(pageNum-1)*3+index+1;i++){
						//每组的统计和的值
						sum_c+=Integer.parseInt(str_count_arr[i]);
						//每组的实计和的值
						sum_a+=Integer.parseInt(a_arr[i]);
					}
					c_sum.append(sum_c).append(",");
					a_sum.append(sum_a).append(",");
				}
			}
			//将获得的数据放入新的 对象中
			Crow_Sum cr1 = new Crow_Sum();
			//设置新生
			if(sheng.length()>1)
				cr1.setSheng(sheng.substring(0, sheng.length()-1));
			//设置新配
			if(pei.length()>1)
				cr1.setPei(pei.substring(0, pei.length()-1));
			//设置新兑
			if(dui.length()>1)
				cr1.setDui(dui.substring(0, dui.length()-1));
			//设置新供
			if(gong.length()>1)
				cr1.setGong(gong.substring(0,gong.length()-1));
			//设置新供颜色
			if(gong_col.length()>1)
				cr1.setGong_col(gong_col.substring(0, gong_col.length()-1));
			//设置统计
			if(count.length()>1)
				cr1.setCount(count.substring(0, count.length()-1));
			//设置统计和
			if(c_sum.length()>1)
				cr1.setCount_sum(c_sum.substring(0, c_sum.length()-1));
			//设置实计和
			if(a_sum.length()>1)
				cr1.setActual_sum(a_sum.substring(0, a_sum.length()-1));
			list.add(cr1);
		}

		return list;
	}
	@RequestMapping("checkuname")
	@ResponseBody
	public MessageBean getboolByUname(String uname){
		System.out.println("传入的名字:"+uname);
		boolean flag = wms.getboolByUname(uname);
		if(flag){
			System.out.println("这个人存在");
			return MessageBean.fail();
		}
		System.out.println("这个人不存在=======");
		return MessageBean.success();
	}
	/**
	 * 修改用户密码
	 * @param user_num
	 * @param new_password
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateuserpassword",method=RequestMethod.POST)
	@ResponseBody
	public MessageBean updateuserpassword(Integer user_num,String new_password,String name) throws Exception{
		System.out.println(user_num+":"+new_password+"**********************");
		if(user_num == null ||name==null||name.equals("")){
			throw new Exception();
		}
		if(new_password==null||new_password.equals("")) {
			new_password=null;
		}else
		new_password = new_password.replace(" ", "");
		System.out.println("修改的用户id:"+user_num+",新密码:"+new_password);
		wms.updateuserpassword(user_num,new_password,name);
		return MessageBean.success();
	}
	
	/**
	 * 修改用户密码
	 * @param user_num
	 * @param new_password
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/history",method=RequestMethod.POST)
	@ResponseBody
	public MessageBean history(String uid) throws Exception{
		
		return MessageBean.success().add("data", wms.history(uid));
	}
	/**
	 * 
	 * @param hid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delHistory",method=RequestMethod.POST)
	@ResponseBody
	public MessageBean delHistory(String hid) throws Exception{
		if(hid==null||"".equals(hid))
			throw new Exception("参数错误");
		String[] sqls = new String[] {
				"delete from game_history where id="+hid,
				"delete from game_runing where hid="+hid,
				"delete from game_runing_count where hid="+hid
		};
		jdbcTemplate.batchUpdate(sqls);
		return MessageBean.success();
	}
	
	
	/**
	 * 详情表
	 * @param user_num
	 * @param new_password
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="workData",method=RequestMethod.POST)
	@ResponseBody
	public MessageBean workData(String hid) throws Exception{
		
		if(hid==null||hid.equals(""))
			throw new RuntimeException("hid不能为空");
		return MessageBean.success()
				.add("data", wms.workData(hid))
				.add("count", wms.workCount(hid));
	}
	
	
	/**
	 * 汇总表数据
	 * @param user_num
	 * @param new_password
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="totalData",method=RequestMethod.POST)
	@ResponseBody
	public MessageBean totalData(String hid) throws Exception{
		
		if(hid==null||hid.equals(""))
			throw new RuntimeException("hid不能为空");
		return MessageBean.success()
				.add("count", wms.workCount(hid));
	}
	
	/**
	 * 历史汇总表数据
	 * @param user_num
	 * @param new_password
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="historyTotalData",method=RequestMethod.POST)
	@ResponseBody
	public MessageBean historyTotalData(String uid) throws Exception{
		
		if(uid==null||uid.equals(""))
			throw new RuntimeException("uid不能为空");
		
		return MessageBean.success().add("hs", wms.workCounts(uid));
	}
	
	
	/**
	 * 历史汇总表数据
	 * @param user_num
	 * @param new_password
	 * @return
	 * @throws ExceptiongetRule
	 */
	@RequestMapping(value="allTotalData",method=RequestMethod.POST)
	@ResponseBody
	public MessageBean allTotalData(String uid) throws Exception{
		
		/*List<Map<String, Object>> list = jdbcTemplate.queryForList("select a.id,a.uid,b.djt_u_name uname,c.queue_count from game_history a LEFT JOIN djt_user b ON a.uid = b.djt_u_id\r\n" + 
				"left join game_runing_count c ON a.id=c.hid");*/
		List<Map<String, Object>> list = jdbcTemplate.queryForList(
				"select * from djt_history ORDER BY hid");
		return MessageBean.success().add("his", list);
	}
	
	/**
	 * 提供报告汇总表
	 * @param user_num
	 * @param new_password
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="tgData",method=RequestMethod.POST)
	@ResponseBody
	public MessageBean tgData() throws Exception{
		Map<String, Object> trun = null;
		try {
			trun = jdbcTemplate.queryForMap("select * from game_turn WHERE state = 1 limit 1");
			
			
		} catch (EmptyResultDataAccessException e) {
			throw new RuntimeException("没有正在工作的表格");
		}
		
		List<Map<String, Object>> counts = jdbcTemplate.queryForList("select b.id,b.tid,b.hid,b.tg,b.rule,b.rule_type,b.ys,b.g,b.g_sum from game_runing_count b  where b.tid=?",trun.get("id"));;
		
		return MessageBean.success().add("turn", trun)
				.add("counts", counts);
		
		
	}
	
	
	/**
	 * 提供报告汇总表
	 * @param user_num
	 * @param new_password
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="allTgData",method=RequestMethod.POST)
	@ResponseBody
	public MessageBean allTgData(String uid) throws Exception{
		List<Map<String, Object>> his = jdbcTemplate.queryForList("select * from djt_history order by tid");
		List<Map<String, Object>> turns = jdbcTemplate.queryForList("SELECT * FROM game_turn WHERE state=2");
		List<Map<String, Object>> bigTurns = jdbcTemplate.queryForList("SELECT a.* FROM game_big_turn a WHERE a.state=2");
		
		return MessageBean.success().add("his", his)
				.add("turns", turns)
				.add("bigTurns", bigTurns);
		
		
	}
	
	
	/**
	 * 删除提供记录
	 * @param uid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="delHis",method=RequestMethod.POST)
	@ResponseBody
	public MessageBean delHis(String mod,String tid) throws Exception{
		if(mod==null)throw new RuntimeException("错误的模块类型");
		
		if(mod.equals("1")) {
			if(org.springframework.util.StringUtils.isEmpty(tid))
				throw new RuntimeException("参数错误");
				jdbcTemplate.batchUpdate(
					"delete from djt_history WHERE tid in (select id from game_turn  WHERE big_turn_id="+tid+")"
					,"delete from game_turn WHERE big_turn_id="+tid
					 ,"delete from game_big_turn WHERE id="+tid
					);
			
		} 
		if(mod.equals("2")) {//全部删除
		jdbcTemplate.batchUpdate(
					"delete from djt_history "
					, "delete from game_turn WHERE state=2"
					,"delete from game_big_turn WHERE state=2");
		}
		
		return MessageBean.success();
		
		
	}
	
	@RequestMapping(value="getRule",method=RequestMethod.POST)
	@ResponseBody
	public MessageBean rule() throws Exception{
		
		List<Map<String, Object>> rules = jdbcTemplate.queryForList("select ckey, val from djt_sys ");
		
		

		return MessageBean.success().add("rules", rules);
		
		
	}
	
	/**
	 * 
	 * @param mod 1:use_rule,2:rule,3:rule2
	 * @param start
	 * @param end
	 * @param useRule 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="setRule",method=RequestMethod.POST)
	@ResponseBody
	public MessageBean setRule(HttpServletRequest request,String mod,Integer start,Integer end,Integer useRule,Integer mod2) throws Exception{
		int maxEnd = 51;
		if(mod==null)throw new RuntimeException("错误的模块类型");
		
		switch (mod) {
		
		case "conf_len":
			int conf_len = Integer.valueOf(request.getParameter("conf_len"));
			if(conf_len<2)throw new RuntimeException("值范围错误");
			jdbcTemplate.update("update djt_sys set val=? where ckey ='conf_len'",conf_len);
			break;
		case "tg_mod":
			int tg_mod = Integer.valueOf(request.getParameter("tg_mod"));
			jdbcTemplate.update("update djt_sys set val=? where ckey ='tg_mod'",tg_mod);
			int tg_thre = Integer.valueOf(request.getParameter("tg_thre"));
			
			jdbcTemplate.update("update djt_sys set val=? where ckey ='tg_thre'",tg_thre);
			break;
		case "turn_num":
			int turn_num = Integer.valueOf(request.getParameter("turn_num"));
			if(turn_num<1)	throw new RuntimeException("值范围错误");
			jdbcTemplate.update("update djt_sys set val=? where ckey ='turn_num'",turn_num);
			break;
		case "9":
			int hbQh = Integer.valueOf(request.getParameter("hbQh"));
			if(hbQh<0)	throw new RuntimeException("值范围错误");
				
			jdbcTemplate.update("update djt_sys set val=? where ckey ='hbQh'",hbQh);
			break;
		case "8":
			int hbBg = Integer.valueOf(request.getParameter("hbBg"));
			if(hbBg<0)	throw new RuntimeException("值范围错误");
				
			jdbcTemplate.update("update djt_sys set val=? where ckey ='hbBg'",hbBg);
			break;
		case "7":
			int tip_open = Integer.valueOf(request.getParameter("tip_open"));
			if(!(tip_open==1||tip_open==2))	throw new RuntimeException("值范围错误");
				
			jdbcTemplate.update("update djt_sys set val=? where ckey ='tip_open'",tip_open);
			break;
		case "6":
			int gameRowNum = Integer.valueOf(request.getParameter("gameRowNum"));
			if(gameRowNum<3)	throw new RuntimeException("值范围错误");
				
			jdbcTemplate.update("update djt_sys set val=? where ckey ='gameRowNum'",gameRowNum);
			break;
		case "5":
			int gameGroupNum = Integer.valueOf(request.getParameter("gameGroupNum"));
			if(gameGroupNum<10||gameGroupNum>1000000)	throw new RuntimeException("值范围错误");
				
			jdbcTemplate.update("update djt_sys set val=? where ckey ='gameGroupNum'",gameGroupNum);
			break;
		case "4":
			if(mod2==null||mod2<1||mod2>2)	throw new RuntimeException("值范围错误");
				
			jdbcTemplate.update("update djt_sys set val=? where ckey ='mod2'",mod2);
			break;
		case "3":
			if(useRule==null||useRule<1||useRule>2)	throw new RuntimeException("值范围错误");
				
			jdbcTemplate.update("update djt_sys set val=? where ckey ='use_rule'",useRule);
			break;
		case "1":
			if(start<1||start>maxEnd||end<1||end>maxEnd||start>=end)throw new RuntimeException("值范围错误");
			
			jdbcTemplate.update("update djt_sys set val=? where ckey ='rule'",start+","+end);
			break;
		case "2":
			if(start<1||start>maxEnd||end<1||end>maxEnd||start>=end)
				throw new RuntimeException("值范围错误");
			
			jdbcTemplate.update("update djt_sys set val=? where ckey ='rule2'",start+","+end);
			break;
			 
		default:
			throw new RuntimeException("错误的模块类型");
		}
		
		

		return MessageBean.success();
		
		
	}
	
	
	
	@RequestMapping(value="removeAll",method=RequestMethod.POST)
	@ResponseBody
	private MessageBean removeAll() {
		
		
		jdbcTemplate.batchUpdate(
				"TRUNCATE `game_big_turn`" ,
				"TRUNCATE `game_turn`" ,
				"TRUNCATE `djt_history`" ,
				"TRUNCATE `game_history`" ,
				"TRUNCATE `game_runing`" ,
				"TRUNCATE `game_runing_count`");
		
		return MessageBean.success();

	}
	
	
	@RequestMapping(value="currentTg",method=RequestMethod.POST)
	@ResponseBody
	private MessageBean currentTg() {
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		Map<String, Object> bigTurn = jdbcTemplate.queryForMap("select * from game_big_turn where  state=1 limit 1");
		Integer turnNum = JSONObject.parseObject(bigTurn.get("config_json").toString()).getInteger("turnNum");
		 for (int i = 0; i < turnNum; i++) {
			 map.put(i+"", getTurnModel(bigTurn.get("id").toString(),i));
		}
		 map.put("bigTurn", bigTurn);
		
		return MessageBean.success().add("data", map);

	}
	
	
	private Map<String, Object> getTurnModel(String bigTurnId,Integer turnNo) {
		Map<String, Object> turn = jdbcTemplate.queryForMap(
				"select * from game_turn where big_turn_id=? AND turn_no=? limit 1",bigTurnId,turnNo);
		
		Map<String, Object> ret = new HashMap<>();
		
		//ret.put("lastRow", crowDao.getInputRow(game.getId()));
		List<Map<String, Object>> maps = jdbcTemplate.queryForList(
				"select b.id,b.tid,b.hid,b.tg,b.tg_sum,b.rule,b.rule_type,b.ys,b.g,b.g_sum,a.uid from game_history a left join game_runing_count b on a.id=b.hid  where a.state=1 AND b.tid=?",turn.get("id"));
		ret.put("counts", maps);
		ret.put("turn", turn);
		
		return ret;

	}
	
}
