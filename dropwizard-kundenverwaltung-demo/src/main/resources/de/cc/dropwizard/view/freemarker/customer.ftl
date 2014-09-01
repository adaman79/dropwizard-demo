<#-- @ftlvariable name="" type="de.cc.dropwizard.views.CustomerView" -->
<html>
<body>
<!-- calls getCustomer().getFullName() and sanitizes it -->
<h1>Hello, ${customer.fullName?html}!</h1>
</body>
</html>