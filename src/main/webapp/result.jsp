<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<title>Result</title>
<link rel="stylesheet" href="resstyle.css">
</head>

<body>

<div class="card">

    <h1>🎉 Your Age</h1>

    <div class="age">
        <%= request.getAttribute("years") %> Years
    </div>

    <%
    if ((Boolean) request.getAttribute("isBirthday")) {
    %>

    <div style="text-align:center; margin-top:50px; font-family:Poppins, sans-serif;">

        <h1 style="font-size:32px;">🎉 Happy Birthday! 🎂</h1>

        <p style="font-size:18px; opacity:0.8;">
            Today is your special day ✨<br>
            Enjoy every moment 💖
        </p>

    </div>

    <% } else { %>

        <div class="sub">
            <%= request.getAttribute("months") %> Months •
            <%= request.getAttribute("days") %> Days
        </div>

    <h3>🎂 Next birthday in <%= request.getAttribute("daysLeft") %> days</h3>

    <% } %>

    <button class="btn" onclick="window.location.href='index.html'">
        Calculate Again
    </button>

</div>

</body>
</html>