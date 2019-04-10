package com.hotent.platform.dao.system;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.StudentInfo;

@Repository
public class StudentInfoDao extends BaseDao<StudentInfo> {

	@Override
	public Class<?> getEntityClass() {
		return StudentInfo.class;
	}

	public StudentInfo getStuentInfoByAccount(String account) {
		StudentInfo studentInfo = (StudentInfo) getUnique("getStuentInfoByAccount", account);
		return studentInfo;
	}
	
	public StudentInfo getJbxxByAccount(String account) {
		StudentInfo studentInfo = (StudentInfo) getUnique("getJbxxByAccount", account);
		return studentInfo;
	}
	
	public StudentInfo getGzdxJbxxByAccount(String account) {
		StudentInfo studentInfo = (StudentInfo) getUnique("getGzdxJbxxByAccount", account);
		return studentInfo;
	}
}
