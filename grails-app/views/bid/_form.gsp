<%@ page import="auctionhaus.Bid" %>



<div class="fieldcontain ${hasErrors(bean: bidInstance, field: 'bidDateTime', 'error')} required">
	<label for="bidDateTime">
		<g:message code="bid.bidDateTime.label" default="Bid Date Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="bidDateTime" precision="day"  value="${bidInstance?.bidDateTime}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: bidInstance, field: 'bidAmount', 'error')} required">
	<label for="bidAmount">
		<g:message code="bid.bidAmount.label" default="Bid Amount" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="bidAmount" required="" value="${fieldValue(bean: bidInstance, field: 'bidAmount')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: bidInstance, field: 'bidder', 'error')} required">
	<label for="bidder">
		<g:message code="bid.bidder.label" default="Bidder" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="bidder" name="bidder.id" from="${auctionhaus.Customer.list()}" optionKey="id" required="" value="${bidInstance?.bidder?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: bidInstance, field: 'listing', 'error')} required">

    <span id="Listing-Name" class="property-label"><g:message code="Bid.listing.label" default="Listing Name" /></span>
    <span class="property-value" aria-labelledby="bid.listing.label">${bidInstance?.listing?.listingName}</span>
    <g:hiddenField name="listing.id" value = "${bidInstance?.listing?.id}"></g:hiddenField>

</div>
<%--<div class="fieldcontain >

<g:if test="${bidInstance.listing?.bidIncAmt}">

    <span id="Bid-Inc-Amt" class="property-label"><g:message code="Bid.listing.label" default="Bid Inc Amt" /></span>
    <span class="property-value" aria-labelledby="bid.Inc.label">${bidInstance?.listing?.bidIncAmt}</span>
</g:if>
</div>
  <div class="fieldcontain>
<g:if test ="${bidInstance.listing.bids}">
    <label for="Most Recent Bid">
        <g:message code="bid.bidder.label" default="Most Recent Bid Amount" />

    </label>
    <g:maxBidForListingTagLib listingInstance="${bidInstance.listing}"/>

</g:if>
</div>  --%>