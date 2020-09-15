<%@page import="myapp.pojo.Car, 
    java.util.List" %>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	
<% List<Car> cars = (List<Car>) request.getAttribute("cars"); %>	

<table>
	<tr>
		<td>Nome</td><td></td>
	</tr>

<% for(Car car : cars){ %>
      <tr id="row-<%= car.getId() %>">
      	<td><%= car.getName() %></td>
      	<td><button class="addCarButtonClass">Delete</button><input type="hidden" name="carId" value="<%= car.getId() %>" /></td>
      </tr>

<% } %>

</table>

<script>
	$(document).ready(function() {
		
		$(".addCarButtonClass").on('click', function() {
			var carId = $(this).parent().children("input").val();
			
			if($.isNumeric(carId)){
				$.ajax({
					method: 'DELETE',
					url : '/cars/removeCar/' + carId,
					success : function(responseText) {
						if(responseText !== "-1"){
							 $("#row-" + responseText).remove();
						}
					}
	
				});
			}else{
				$("#addCarResultDiv").text('il valore inserito non è corretto!');
			}
		});
	
	});
</script>