
<%@ page import="auctionhaus.CustomerRole" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'customerRole.label', default: 'CustomerRole')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-customerRole" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-customerRole" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list customerRole">
			
				<g:if test="${customerRoleInstance?.customer}">
				<li class="fieldcontain">
					<span id="customer-label" class="property-label"><g:message code="customerRole.customer.label" default="Customer" /></span>
					
						<span class="property-value" aria-labelledby="customer-label"><g:link controller="customer" action="show" id="${customerRoleInstance?.customer?.id}">${customerRoleInstance?.customer?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${customerRoleInstance?.role}">
				<li class="fieldcontain">
					<span id="role-label" class="property-label"><g:message code="customerRole.role.label" default="Role" /></span>
					
						<span class="property-value" aria-labelledby="role-label"><g:link controller="role" action="show" id="${customerRoleInstance?.role?.id}">${customerRoleInstance?.role?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${customerRoleInstance?.id}" />
					<g:link class="edit" action="edit" id="${customerRoleInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
