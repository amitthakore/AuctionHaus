
<%@ page import="auctionhaus.Listing" %>
<%@ page import="auctionhaus.Bid" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'listing.label', default: 'Listing')}" />
        <g:set var="customerEntity" value="${message(code: 'customer.label', default: 'User ')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>

	</head>
	<body>
    <sec:ifNotLoggedIn>
        <form method="POST" action="${resource(file: 'j_spring_security_check')}">
            <table>
                <tr>
                    <td>Username:</td><td><g:textField name="j_username"/></td>

                    <td>Password:</td><td><input name="j_password" type="password"/></td>

                    <td colspan="1"><g:submitButton name="login" value="Login"/></td>
                </tr>

            </table>
        </form>
    </sec:ifNotLoggedIn>
		<a href="#list-listing" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
             <%--   <sec:ifNotLoggedIn> --%>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" controller="customer" action="create"><g:message code="default.register.label" args="[customerEntity]" /></g:link></li>
              <%--  <li><g:link controller="login" action="auth"><g:message code="default.login.label" args="[customerEntity]" /></g:link></li>  --%>
           <%--     </sec:ifNotLoggedIn>   --%>

             <%--   <sec:ifAllGranted roles="ROLE_USER"> --%>
                <li><g:link class="create" controller="customer" action="list"><g:message code="default.list.label" args="[customerEntity]" /></g:link></li>
                <li><g:link class="create" controller="listing" action="myListing"><g:message code="My Listings"/></g:link></li>
                <sec:username /> <g:link controller="logout">sign out</g:link>

             <%--  </sec:ifAllGranted>  --%>
            </ul>
		</div>

		<div id="list-listing" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="listingName" title="${message(code: 'listing.listingName.label', default: 'Listing Name')}" />
					
						<g:sortableColumn property="startingBidPrice" title="${message(code: 'listing.startingBidPrice.label', default: 'Starting Bid Price')}" />
					
						<g:sortableColumn property="listingDescription" title="${message(code: 'listing.listingDescription.label', default: 'Description')}" />
					
						<g:sortableColumn property="listingEndDateTime" title="${message(code: 'listing.listingEndDateTime.label', default: 'End Date Time')}" />

                        <g:sortableColumn property="bids" title="${message(code: 'listing.bids.label', default: 'Total Bids')}" />

					</tr>
				</thead>
				<tbody>

				<g:each in="${listingInstanceList}" status="i" var="listingInstance">

                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                        <%--M-5: The name is visible for each listing--%>
						<td><g:link action="show" id="${listingInstance.id}">${fieldValue(bean: listingInstance, field: "listingName")}</g:link></td>
                        <%--M-7: The starting price is visible for each listing--%>
						<td>${fieldValue(bean: listingInstance, field: "startingBidPrice")}</td>
					
						<td>${fieldValue(bean: listingInstance, field: "listingDescription")}</td>
                        <%--M-8: The end date/time is visible for each listing --%>
                        <td><g:formatDate date="${listingInstance.listingEndDateTime}" type="datetime" style="medium" /></td>
                       <%--M-6: The number of bids is visible for each listing--%>
                        <td>${listingInstance.bids.size()}</td>

					</tr>

				</g:each>
				</tbody>
			</table>
			<div class="pagination">

			<g:paginate total="${listingInstanceTotal}" />

			</div>




        </div>
	</body>
</html>
