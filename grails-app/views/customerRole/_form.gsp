<%@ page import="auctionhaus.CustomerRole" %>



<div class="fieldcontain ${hasErrors(bean: customerRoleInstance, field: 'customer', 'error')} required">
	<label for="customer">
		<g:message code="customerRole.customer.label" default="Customer" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="customer" name="customer.id" from="${auctionhaus.Customer.list()}" optionKey="id" required="" value="${customerRoleInstance?.customer?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: customerRoleInstance, field: 'role', 'error')} required">
	<label for="role">
		<g:message code="customerRole.role.label" default="Role" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="role" name="role.id" from="${auctionhaus.Role.list()}" optionKey="id" required="" value="${customerRoleInstance?.role?.id}" class="many-to-one"/>
</div>

