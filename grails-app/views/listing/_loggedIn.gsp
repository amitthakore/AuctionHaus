<%--
  Created by IntelliJ IDEA.
  User: amitthakore
  Date: 4/9/12
  Time: 10:53 PM
  To change this template use File | Settings | File Templates.
--%>
<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_USER">
    <div class="contentcontainer med left" style="margin-left: 270px;">
    <li><sec:username />&nbsp;<g:link controller="logout">Logout</g:link></li>

    </div>
</sec:ifAnyGranted>
