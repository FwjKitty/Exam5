<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,entity.Customer" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
</head>
<%
	List<Customer> list = (List)request.getAttribute("list");
	int _page = Integer.parseInt((String)request.getAttribute("page"));
	int count = Integer.parseInt((String)request.getAttribute("count"));
	
	int nextpage ;
	int lastpage ;
	int finalpage=count/10+1;
	if(_page==1){
	lastpage=1;
	}else lastpage=_page-1;
	 if(finalpage==_page){
	nextpage=_page;
	}else nextpage=_page+1;
%>
<body>
	<div class="container-fuild">
		<div class="row">
			<div class="col-sm-12" style="background-color:#eee;">
				<div class="col-sm-11">
					<h3>1507A05冯婉君</h3>
				</div>
				<div class="col-sm-1">
					<h3>
						${first_name }
						<a href="ExitServlet">
							<button class="btn btn-danger">退出系统</button>
						</a>
					</h3>
				</div>
			</div>
			<div class="col-sm-12">
				<div class="col-sm-2">
					<h4><a href="CustomerShow?page=1">Customer管理</a></h4>
					<h4><a href="">Film设置</a></h4>
				</div>
				<div class="col-sm-10">
					<h1>客户管理</h1>
					<hr/>
					<div class="col-sm-12">
						<div class="col-sm-4">
							客户列表
						</div>
						<div class="col-sm-4">
							<a href="CustomerQueryAddress"><button class="btn btn-primary">新建</button></a>
							<br/><br/>
						</div>
					</div>
					<div class="col-sm-12">
						<table class="table table-stripeed table-bordered table-hover">
							<thead>
								<tr>
									<th>操作</th>
									<th>First Name</th>
									<th>Last Name</th>
									<th>Address</th>
									<th>Email</th>
									<th>CustomerId</th>
									<th>LastUpdate</th>
								</tr>
							</thead>
							<tbody>
							<%
								for(Customer customer : list){
							%>
								<tr>
									<td><a href="CustomerEdit?customer_id=<%=customer.getCustomer_id() %>">编辑</a> | <a href="CustomerDel?customer_id=<%=customer.getCustomer_id() %>">删除</a>
									<td><%=customer.getFirst_name() %></td>
									<td><%=customer.getLast_name() %></td>
									<td><%=customer.getAddress() %></td>
									<td><%=customer.getEmail() %></td>
									<td><%=customer.getCustomer_id() %></td>
									<td><%=customer.getLast_update() %></td>
								</tr>
							<%
								}
							%>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="7">
									<div class="pull-right">
										<a href="CustomerShow?page=<%=lastpage %>" class="btn btn-default"><<</a>
										<a href="CustomerShow?page=1" class="btn btn-default">first</a>
										<a href="CustomerShow?page=<%=finalpage %>" class="btn btn-default">last</a>
										<a href="CustomerShow?page=<%=nextpage %>" class="btn btn-default">>></a>
									</div>
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>