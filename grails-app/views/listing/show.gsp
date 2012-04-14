
<%@ page import="auctionhaus.Listing" %>
<%@ page import="auctionhaus.Bid" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'listing.label', default: 'Listing')}" />
        <g:set var="customerEntity" value="${message(code: 'customer.label', default: 'User ')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
        <g:javascript library="jquery" plugin="jquery"/>
        <g:setProvider library="jquery"/>
	</head>

	<body onload="bidUpdater()">

	<a href="#show-listing" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
    <div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <sec:ifNotLoggedIn>
                <li><g:link class="create" controller="customer" action="create"><g:message code="default.register.label" args="[customerEntity]" /></g:link></li>
                </sec:ifNotLoggedIn>
                <sec:ifAllGranted roles="ROLE_USER">
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" controller="listing" action="myListing"><g:message code="My Listings"/></g:link></li>
                </sec:ifAllGranted>

            </ul>
		</div>
		<div id="show-listing" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>

            <g:if test="${flash.message}">
			<div class="message">${flash.message}</div>
			</g:if>

			<ol class="property-list listing">

			  <%--L-1: The detail page for the listing shows the name of the listing--%>

                <g:if test="${listingInstance?.listingName}">
				<li class="fieldcontain">
					<span id="listingName-label" class="property-label"><g:message code="listing.listingName.label" default="Listing Name" /></span>
					
					<span class="property-value" aria-labelledby="listingName-label"><g:fieldValue bean="${listingInstance}" field="listingName"/></span>
					
				</li>
				</g:if>

                <%--L-2: The detail page for the listing shows the starting bid price of the listing --%>

                <g:if test="${listingInstance?.startingBidPrice}">
				<li class="fieldcontain">
					<span id="startingBidPrice-label" class="property-label"><g:message code="listing.startingBidPrice.label" default="Starting Bid Price" /></span>

						<span class="property-value" aria-labelledby="startingBidPrice-label"><g:fieldValue bean="${listingInstance}" field="startingBidPrice"/></span>

				</li>
				</g:if>
			    <%--L-5: The detail page for the listing optionally shows the description--%>
			<g:if test="${listingInstance?.listingDescription}">
                 <g:if test="${!listingInstance.listingDescription.isEmpty()}">
				<li class="fieldcontain">
					<span id="listingDescription-label" class="property-label"><g:message code="listing.listingDescription.label" default="Listing Description" /></span>
					
						<span class="property-value" aria-labelledby="listingDescription-label"><g:fieldValue bean="${listingInstance}" field="listingDescription"/></span>
					
				</li>
             </g:if>
			 </g:if>
               <%-- L-4: The detail page for the listing shows the end date/time of the listing --%>
				<g:if test="${listingInstance?.listingEndDateTime}">
				<li class="fieldcontain">
					<span id="listingEndDateTime-label" class="property-label"><g:message code="listing.listingEndDateTime.label" default="Listing End Date Time" /></span>
					
						<span class="property-value" aria-labelledby="listingEndDateTime-label"><g:formatDate date="${listingInstance?.listingEndDateTime}" /></span>
					
				</li>
				</g:if>


			   <%--L-3: The detail page for the listing shows the most recent bid - Used maxBidForListingTagLib --%>
				<g:if test="${listingInstance?.bids}">
				<li class="fieldcontain">
					<span id="bids-label" class="property-label"><g:message code="listing.bids.label" default="Most Recent Bid" /></span>

                    <span class="property-value" aria-  labelledby="bids-label">
                        <div class = "maxbid">
                        <g:maxBidForListingTagLib listingInstance="${listingInstance}"/>
                    </div>
				</li>
				</g:if>

				<g:if test="${listingInstance?.seller}">
				<li class="fieldcontain">
					<span id="seller-label" class="property-label"><g:message code="listing.seller.label" default="Seller" /></span>
					
						<span class="property-value" aria-labelledby="seller-label">${sellername.encodeAsHTML()}</span>
					
				</li>
				</g:if>
               <g:form>
                   <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_USER">
					<fieldset class="buttons">
					<g:hiddenField name="id" value="${listingInstance?.id}" />

                    <g:if test="${listingInstanceList.size()==0}">
					<g:link class="edit" action="edit" id="${listingInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    </g:if>
				   <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
                    </sec:ifAnyGranted>
                   </g:form>

              </div>
<sec:ifAllGranted roles="ROLE_USER">
    <g:formRemote name="createBid"
                  url= "[controller:'bid', action:'save']"
       onSuccess="addTableRow(data)"
       onFailure="bidError()">
        <input name="bidAmount" type="number">
        <g:hiddenField name="bidder.id" value = "${listingInstance?.seller?.id}"></g:hiddenField>
        <g:hiddenField name="listing.id" value = "${listingInstance?.id}"></g:hiddenField>
        <g:submitButton name="create" value="${message(code: 'default.button.create.bid.label', default: 'Create Bid')}" />

    </g:formRemote>
</sec:ifAllGranted>

<sec:ifNotLoggedIn>
    <h1>Please register/login to place bid</h1>
</sec:ifNotLoggedIn>
    <table id="bidtable">

        <thead>
        <tr>

            <g:sortableColumn property="bidDateTime" title="${message(code: 'bid.bidDateTime.label', default: 'Bid Date Time')}" />

            <g:sortableColumn property="bidAmount" title="${message(code: 'bid.bidAmount.label', default: 'Bid Amount')}" />

            <th><g:message code="bid.bidder.label" default="Bidder" /></th>


        </tr>
        </thead>

        <tbody>


        <g:each in="${listingInstanceList}" status="i" var="bidInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td>${fieldValue(bean: bidInstance, field: "bidDateTime")}</td>

                <td>${fieldValue(bean: bidInstance, field: "bidAmount")}</td>

                <td>${fieldValue(bean: bidInstance, field: "bidder.username")}</td>


            </tr>
        </g:each>
        </tbody>

    </table>


        <script type="text/javascript">

            var addTableRow = function(data){
               try{
                var obj = jQuery.parseJSON(data);

                   $("table#bidtable tr:first").after('<tr><td>'+obj.bidDateTime+'</td><td>'+obj.bidAmount+'</td><td>'+obj.bidder+'</td></tr>').fadeIn('slow');

                   $('div.maxbid').replaceWith( "<div class='maxbid'>"+ ''+obj.bidAmount+'' + "</div>")

                  alert("Bid Created");
               }catch(e){

                   alert(data);

               }
            }

      function bidUpdater() {

               var listingId = "${listingInstance.id}";

                   $.ajax({
                       url: '/AuctionHaus/listing/bidList',
                       data: {name: listingId},
                       type: "GET",
                       dataType: "json",
                       timeout:10000,
                       success:function(data,textStatus){addBidsTable(data);}
               },10000);

                }

       var addBidsTable = function(data){

           $('div.maxbid').replaceWith( "<div class='maxbid'>"+ ''+data+'' + "</div>")
           setTimeout("bidUpdater();", 50000)

        }

       function bidError(){

           $('div.bidmessage').replaceWith( "<div class='bidmessage'>" + "Login is required for placing the bid!" + "</div>")

                    }

    </script>


    </body>
  </html>
