package com.hotent.platform.service.system;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.platform.dao.system.SysUserDao;
import com.hotent.platform.model.system.SysUser;

@Service
public class StudentSysUserService extends BaseService<SysUser>{
	@Resource
	private SysUserDao dao;

	@Override
	protected IEntityDao<SysUser, Long> getEntityDao() {
		return dao;
	}
	
	//学生相关查询
		//查询学生所在班级班主任
	public List<SysUser> getBrzByXh(Long userId){
		return dao.getBrzByXh(userId);
	}
		//查询学生所在班级辅导员
	public List<SysUser> getFdyByXh(Long userId){
		return dao.getFdyByXh(userId);
	}
		//查询学生所在学院学工办主任
		//发起人所在学院院长(上海体育本科生)
	public List<SysUser> getXgbzrByXh(Long userId){
		return dao.getXgbzrByXh(userId);
	}
		//查询学生所在学院党总支书记
	public List<SysUser> getDzzsjByXh(Long userId){
		return dao.getDzzsjByXh(userId);
	}
		//查询学生所在学院教学秘书
	public List<SysUser> getJxmsByXh(Long userId){
		return dao.getJxmsByXh(userId);
	}
		//查询学生所在学院实习负责老师
	public List<SysUser> getSxfzrByXh(Long userId){
		return dao.getSxfzrByXh(userId);
	}
	
	//发起人本科生辅导员
	public List<SysUser> getShtyfdyByXh(Long userId){
		return dao.getShtyfdyByXh(userId);
	}
		
	//发起人研究生导师
	public List<SysUser> getShtydsByXh(Long userId){
		return dao.getShtydsByXh(userId);
	}
	
	//发起人研究生辅导员
	public List<SysUser> getShtyyjsfdyByXh(Long userId){
		return dao.getShtyyjsfdyByXh(userId);
	}
	
//	查询学生所在学院勤工负责人
//	public List<SysUser> getQgfzrByXh(Long userId){
//		return dao.getQgfzrByXh(userId);
//	}
		//学生相关查询
	
	//查询发起人所在市县
	public List<SysUser> getSprByUserId(String posname){
		return dao.getSprByUserId(posname);
	}
	
	//岗位（人事处）负责人
	public List<SysUser> getPersonnelHead(String posname){
		return dao.getPersonnelHead(posname);
	}
	
}
