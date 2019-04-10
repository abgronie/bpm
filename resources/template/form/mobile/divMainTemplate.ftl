<#setting number_format="0">
<#function getFieldList fieldList>
 	<#assign rtn>
		<#list fieldList as field>
			<#if field.isHidden == 0>
				 <div class="am-form-group" ng-if="'${getPermission(field,1,'main')}' != 'n' " >
					<label class="am-u-sm-3 am-form-label">${field.fieldDesc}</label>
					<div class="am-u-sm-9"> <@input field=field type=1 tableName='main'/> </div>
				 </div>
			</#if>
		</#list>
 	</#assign>
	<#return rtn>
</#function>

<#-- 权限 -->
<#function getPermission field type tableName>
	<#assign rtn="" />
	<#if  type == 1>
		<#assign rtn>{{main.${field.fieldName}}}</#assign>
	<#elseif type== 2>
		<#assign rtn>{{fields.${tableName}.${field.fieldName}}}</#assign>
	<#else>
		<#assign rtn>{{fields.${type}.${field.fieldName}}}</#assign>
	</#if>
	<#return rtn>
</#function>

<#--获取分组信息 -->
<#function setTeamField teams>
 	<#assign rtn>
		 <#list teams as team>
		 	<#if team.teamName?if_exists>
            	<fieldset>
					<legend> ${team.teamName}</legend>
			</#if>
				${getFieldList(team.teamFields)}
		 	<#if team.teamName?if_exists>
		 	  	</fieldset>
		 	</#if>
		</#list>
	</#assign>
	<#return rtn>
</#function>

 <h1 >${title}</h1>
<#--设置主表分组-->
<#if teamFields??>
	<#if isShow>
		<#if showPosition == 1>
			${setTeamField(teamFields)}
			${getFieldList(fields)}
		<#else>
			${getFieldList(fields)}
			${setTeamField(teamFields)}
		</#if>
	<#else>
		${setTeamField(teamFields)}
	</#if>
<#else>
	${getFieldList(fields)}
</#if>
	