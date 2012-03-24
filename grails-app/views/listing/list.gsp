
<%@ page import="auctionhaus.Listing" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'listing.label', default: 'Listing')}" />
        <g:set var="customerEntity" value="${message(code: 'customer.label', default: 'Customer')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-listing" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" controller="customer" action="create"><g:message code="default.new.label" args="[customerEntity]" /></g:link></li>
                <li><g:link class="create" controller="customer" action="list"><g:message code="default.list.label" args="[customerEntity]" /></g:link></li>
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
                      <%--  <g:sortableColumn property="listingCreatedDate" title="${message(code: 'listing.listingCreatedDate.label', default: 'Created Date Time')}" /> --%>
                        <g:sortableColumn property="bids" title="${message(code: 'listing.bids.label', default: 'Total Bids')}" />

						<%--		<th><g:message code="listing.winner.label" default="Winner" /></th>      --%>
					
					<%--	<g:sortableColumn property="bidIncAmt" title="${message(code: 'listing.bidIncAmt.label', default: 'Bid Inc Amt')}" /> --%>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${listingInstanceList}" status="i" var="listingInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					   <g:if test="${listingInstance.listingEndDateTime>new Date()}">
						<td><g:link action="show" id="${listingInstance.id}">${fieldValue(bean: listingInstance, field: "listingName")}</g:link></td>
					
						<td>${fieldValue(bean: listingInstance, field: "startingBidPrice")}</td>
					
						<td>${fieldValue(bean: listingInstance, field: "listingDescription")}</td>


                        <td><g:formatDate date="${listingInstance.listingEndDateTime}" type="datetime" style="medium" /></td>
                     <%--   <td><g:formatDate date="${listingInstance.listingCreatedDate}" /></td>   --%>
                        <td>${listingInstance.bids.size()}</td>
                        </g:if>
					
				<%--		<td>${fieldValue(bean: listingInstance, field: "winner")}</td>  --%>
					
					<%--	<td>${fieldValue(bean: listingInstance, field: "bidIncAmt")}</td>  --%>
					
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
