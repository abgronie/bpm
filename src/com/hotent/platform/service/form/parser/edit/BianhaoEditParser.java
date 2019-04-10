/**
 * 描述：TODO
 * 包名：com.hotent.platform.service.form.parser
 * 文件名：DanhwbkParser.java
 * 作者：User-mailto:liyj@jee-soft.cn
 * 日期2015-12-11-下午2:49:51
 *  2015广州宏天软件有限公司版权所有
 * 
 */
package com.hotent.platform.service.form.parser.edit;

import java.util.Map;

import net.sf.json.JSONObject;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.MapUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.form.BpmFormData;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.service.form.parser.util.FieldRight;
import com.hotent.platform.service.form.parser.util.ParserParam;

/**
 * <pre>
 * 描述：单行文本框
 * 构建组：bpm33
 * 作者：aschs
 * 邮箱:liyj@jee-soft.cn
 * 日期:2015-12-11-下午2:49:51
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
@Service
public class BianhaoEditParser extends EditAbstractParser {

	@Override
	public Object parse(ParserParam param, Element element) {
		BpmFormData data = param.getBpmFormData();
		Map<String, Object> permission = param.getPermission();

		Map<String, Object> external = handleExternal(element);
		
		String ename = getElementName(element, data);
		String val = getValue(element, data);
		String right = getRight(element, permission);
		
		Element inputE = element.select("input").get(0);
		inputE.attr("name", ename);
		inputE.val(val);
		inputE.attr("readonly", "readonly");
		Element inputa = element.select("a").get(0);
		inputa.attr("name", ename);
		addLengthValidate(inputE,external);
		
		String isRequired = MapUtil.getString(external, "isRequired");
		String permissionType = MapUtil.getString(permission, "permissionType");
		double isRequiredDoub = 0;
		if(StringUtil.isNotEmpty(isRequired)){
			isRequiredDoub = Double.parseDouble(isRequired);
		}
		if(isRequiredDoub==1.0 && "default".equals(permissionType)){//必填
			//permission是获取的默认权限（即在节点上没有配置相应表单时，获取的是默认权限），此时若external中isRequired等于1，则添加必填标志
			addRequred(inputE);
		}
		if (FieldRight.W.equals(right)) {//编辑
			element.after(inputE.toString()+inputa.toString());
		} else if (FieldRight.R.equals(right)) {//只读
			element.after(val);
		} else if (FieldRight.B.equals(right)) {//必填
			addRequred(inputE);
			element.after(inputE.toString()+inputa.toString());
		} else if (FieldRight.RP.equals(right)) {//只读提交
			inputE.attr("type","hidden");
			element.after(val);
			//element.after(inputE.toString()+inputa.toString());
		}else {//没有就代表隐藏
		}
		element.remove();
		return null;
	}
}
