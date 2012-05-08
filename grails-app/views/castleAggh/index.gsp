<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>The Castle Argh!</title>
	</head>
	<body> 
		<h1>The Castle Argh!</h1>
		<p>You have entered the Castle Argh, what will you do?</p>
		<ul style="margin-left: 2em; margin-bottom: 1em;">
			<li><g:link action="gates">Enter the Castle Gates</g:link></li>
			<li><g:link action="throne">Enter the Throne Room</g:link></li>
		</ul>
		<img src="${resource(dir: 'images', file: 'the_castle_argh.jpg')}" alt="Picture of Castle Argh..." />
	</body>
</html>
