package com.hotent.platform.service.form.parser.edit;

import com.hotent.core.util.MapUtil;
import com.hotent.platform.model.form.BpmFormData;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.service.form.parser.util.FieldRight;
import com.hotent.platform.service.form.parser.util.ParserParam;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CheckBoxEditParser extends EditAbstractParser
{
  public Object parse(ParserParam param, Element element)
  {
    BpmFormData data = param.getBpmFormData();
    Map permission = param.getPermission();
    Map external = handleExternal(element);

    String ename = getElementName(element, data);
    String val = getValue(element, data);

    String right = getRight(element, permission);

    BpmFormField bpmFormField = getField(element, data);

    Elements labelEs = element.select("label");
    String text = "";
    for (Element e : labelEs) {
      Element checkboxE = e.select("input").get(0);
      checkboxE.attr("name", ename);
      //为了只读无name
      checkboxE.attr("e_name", ename);
      if (valEqual(checkboxE.val(), val.split(","), bpmFormField)) {
        checkboxE.attr("checked", "checked");
        if (text != "") text = text + ",";
        text = text + e.text();
      } else {
        checkboxE.removeAttr("checked");
      }
      if ((FieldRight.W.equals(right)) || (FieldRight.B.equals(right))) {
        element.before(e);
      }
      if ((FieldRight.B.equals(right)) || ((Double.parseDouble(MapUtil.getString(external, "isRequired")) == 1.0D) && ("default".equals(MapUtil.getString(permission, "permissionType"))))) {
        addRequred(checkboxE);
      }
      if ((FieldRight.R.equals(right)) || (FieldRight.RP.equals(right))) {
        if (FieldRight.R.equals(right))
        {
          checkboxE.attr("name", "");
        }
        checkboxE.attr("disabled", "disabled");
        element.before(e);
      }
    }

    if (FieldRight.RP.equals(right))
    {
      Element emt = new Element(Tag.valueOf("input"), element.baseUri());
      emt.attr("type", "hidden");
      emt.val(val);
      emt.attr("name", ename);
      element.after(emt.toString());

      element.after(text);
    }

    element.remove();
    return null;
  }

  public String getValue(Element element, BpmFormData data)
  {
    Map external = handleExternal(element);
    String fieldName = (String)external.get("name");
    JSONArray ja = JSONArray.fromObject(external.get("options"));
    String initVal = "";
    for (int i = 0; i < ja.size(); i++) {
      JSONObject jo = ja.getJSONObject(i);
      if ((jo.get("isDefault") != null) && (jo.getInt("isDefault") == 1))
      {
        if (initVal != "") {
          initVal = initVal + ",";
        }
        initVal = initVal + jo.getString("key");
      }
    }
    String val = getValue(element, data, fieldName, true, initVal);
    return val;
  }

  private boolean valEqual(String val, String[] strs, BpmFormField bpmFormField) {
    for (String str : strs) {
      if (super.valEqual(val, str, bpmFormField)) {
        return true;
      }
    }
    return false;
  }
}