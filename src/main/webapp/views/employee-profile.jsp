<!DOCTYPE html>
<html>
<head>
    <title>AJAX Example</title>
    <script>
        function loadPage(url) {
            var xhr = new XMLHttpRequest();
            xhr.open("GET", url, true);
            xhr.onreadystatechange = function() {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    document.getElementById("content").innerHTML = xhr.responseText;
                }
            };
            xhr.send();
        }
    </script>
</head>
<body>
<h1>AJAX Example</h1>
<button onclick="loadPage('/views/login.jsp')">Load New Content</button>
<div id="content">
    <p>This is the initial content of the page.</p>
</div>
</body>
</html>
