<%

    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
    session.invalidate();

    response.sendRedirect(request.getContextPath() + "/index.jsp");


%>
