
<%@ page import="mn.groovy.Knight" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'knight.label', default: 'Knight')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-knight" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-knight" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="username" title="${message(code: 'knight.username.label', default: 'Username')}" />
					
						<g:sortableColumn property="accountExpired" title="${message(code: 'knight.accountExpired.label', default: 'Account Expired')}" />
					
						<g:sortableColumn property="accountLocked" title="${message(code: 'knight.accountLocked.label', default: 'Account Locked')}" />
					
						<g:sortableColumn property="enabled" title="${message(code: 'knight.enabled.label', default: 'Enabled')}" />
					
						<g:sortableColumn property="favoriteColor" title="${message(code: 'knight.favoriteColor.label', default: 'Favorite Color')}" />
					
						<g:sortableColumn property="passwordExpired" title="${message(code: 'knight.passwordExpired.label', default: 'Password Expired')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${knightInstanceList}" status="i" var="knightInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${knightInstance.id}">${fieldValue(bean: knightInstance, field: "username")}</g:link></td>
					
						<td><g:formatBoolean boolean="${knightInstance.accountExpired}" /></td>
					
						<td><g:formatBoolean boolean="${knightInstance.accountLocked}" /></td>
					
						<td><g:formatBoolean boolean="${knightInstance.enabled}" /></td>
					
						<td>${fieldValue(bean: knightInstance, field: "favoriteColor")}</td>
					
						<td><g:formatBoolean boolean="${knightInstance.passwordExpired}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${knightInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
