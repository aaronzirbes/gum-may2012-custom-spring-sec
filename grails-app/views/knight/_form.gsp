<%@ page import="mn.groovy.Knight" %>



<div class="fieldcontain ${hasErrors(bean: knightInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="knight.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${knightInstance?.username}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: knightInstance, field: 'accountExpired', 'error')} ">
	<label for="accountExpired">
		<g:message code="knight.accountExpired.label" default="Account Expired" />
		
	</label>
	<g:checkBox name="accountExpired" value="${knightInstance?.accountExpired}" />
</div>

<div class="fieldcontain ${hasErrors(bean: knightInstance, field: 'accountLocked', 'error')} ">
	<label for="accountLocked">
		<g:message code="knight.accountLocked.label" default="Account Locked" />
		
	</label>
	<g:checkBox name="accountLocked" value="${knightInstance?.accountLocked}" />
</div>

<div class="fieldcontain ${hasErrors(bean: knightInstance, field: 'enabled', 'error')} ">
	<label for="enabled">
		<g:message code="knight.enabled.label" default="Enabled" />
		
	</label>
	<g:checkBox name="enabled" value="${knightInstance?.enabled}" />
</div>

<div class="fieldcontain ${hasErrors(bean: knightInstance, field: 'favoriteColor', 'error')} ">
	<label for="favoriteColor">
		<g:message code="knight.favoriteColor.label" default="Favorite Color" />
		
	</label>
	<g:textField name="favoriteColor" value="${knightInstance?.favoriteColor}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: knightInstance, field: 'passwordExpired', 'error')} ">
	<label for="passwordExpired">
		<g:message code="knight.passwordExpired.label" default="Password Expired" />
		
	</label>
	<g:checkBox name="passwordExpired" value="${knightInstance?.passwordExpired}" />
</div>

<div class="fieldcontain ${hasErrors(bean: knightInstance, field: 'quest', 'error')} ">
	<label for="quest">
		<g:message code="knight.quest.label" default="Quest" />
		
	</label>
	<g:textField name="quest" value="${knightInstance?.quest}"/>
</div>

