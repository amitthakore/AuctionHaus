<%--
  Created by IntelliJ IDEA.
  User: amitthakore
  Date: 4/9/12
  Time: 9:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="org.springframework.security.web.WebAttributes"%>
<%@ page import="org.springframework.security.core. AuthenticationException" %>.

<sec:ifNotLoggedIn>

<g:if test = "${session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION)!= null}">
        <div class="error">
             <g:set var="loginErrorMessage" value="Incorrect Username or Password" scope="page" color="#cc0000" />
            <% session.removeValue("SPRING_SECURITY_LAST_EXCEPTION")%>
            <% session.invalidate() %>
        </div>
 </g:if>
 <g:else>
     <g:set var="loginErrorMessage" value=" " scope="page" />
   </g:else>
    ${loginErrorMessage}
    <div style="margin-left: 275px;">
        <form method="POST" action="${resource(file: 'j_spring_security_check')}">
            <table >
                <tr>
                    <td>Username:</td><td><g:textField name="j_username"/></td>
                 </tr>
                  <tr>
                    <td>Password:</td><td><input name="j_password" type="password"/></td>
                    </tr>
                    <tr>
                    <div class="contentcontainer med left" style="margin-left: 275px;">
                        <td><g:submitButton name="login" value="Login"/></td>
                    </div>
                </tr>

            </table>
        </form>
    </div>

    </sec:ifNotLoggedIn>