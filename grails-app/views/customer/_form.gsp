<%@ page import="auctionhaus.Customer" %>



<div class="fieldcontain ${hasErrors(bean: customerInstance, field: 'username', 'error')} required">
	<label for="email">
		<g:message code="customer.username.label" default="Email" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="Username" required="" value="${customerInstance?.username}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: customerInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="customer.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:passwordField name = "password" maxlength="8" required="" value="${customerInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: customerInstance, field: 'createdDate', 'error')} required">
	<label for="createdDate">
		<g:message code="customer.createdDate.label" default="Created Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="createdDate" precision="day"  value="${customerInstance?.createdDate}"  />
</div>



