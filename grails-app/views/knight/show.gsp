
<%@ page import="mn.groovy.Knight" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'knight.label', default: 'Knight')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-knight" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-knight" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list knight">
			
				<g:if test="${knightInstance?.username}">
				<li class="fieldcontain">
					<span id="username-label" class="property-label"><g:message code="knight.username.label" default="Username" /></span>
					
						<span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${knightInstance}" field="username"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${knightInstance?.accountExpired}">
				<li class="fieldcontain">
					<span id="accountExpired-label" class="property-label"><g:message code="knight.accountExpired.label" default="Account Expired" /></span>
					
						<span class="property-value" aria-labelledby="accountExpired-label"><g:formatBoolean boolean="${knightInstance?.accountExpired}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${knightInstance?.accountLocked}">
				<li class="fieldcontain">
					<span id="accountLocked-label" class="property-label"><g:message code="knight.accountLocked.label" default="Account Locked" /></span>
					
						<span class="property-value" aria-labelledby="accountLocked-label"><g:formatBoolean boolean="${knightInstance?.accountLocked}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${knightInstance?.enabled}">
				<li class="fieldcontain">
					<span id="enabled-label" class="property-label"><g:message code="knight.enabled.label" default="Enabled" /></span>
					
						<span class="property-value" aria-labelledby="enabled-label"><g:formatBoolean boolean="${knightInstance?.enabled}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${knightInstance?.favoriteColor}">
				<li class="fieldcontain">
					<span id="favoriteColor-label" class="property-label"><g:message code="knight.favoriteColor.label" default="Favorite Color" /></span>
					
						<span class="property-value" aria-labelledby="favoriteColor-label"><g:fieldValue bean="${knightInstance}" field="favoriteColor"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${knightInstance?.passwordExpired}">
				<li class="fieldcontain">
					<span id="passwordExpired-label" class="property-label"><g:message code="knight.passwordExpired.label" default="Password Expired" /></span>
					
						<span class="property-value" aria-labelledby="passwordExpired-label"><g:formatBoolean boolean="${knightInstance?.passwordExpired}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${knightInstance?.quest}">
				<li class="fieldcontain">
					<span id="quest-label" class="property-label"><g:message code="knight.quest.label" default="Quest" /></span>
					
						<span class="property-value" aria-labelledby="quest-label"><g:fieldValue bean="${knightInstance}" field="quest"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${knightInstance?.id}" />
					<g:link class="edit" action="edit" id="${knightInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
