<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

Welcome to microservices main page!

<div>
  <button id="getAllCarsButtonButton">Click</button> for getting all cars
  <div id="getAllCarsResult"></div>
</div>

</br></br>

<div>
  <button id="getCoolCarsButtonButton">Click</button> for getting cool cars
  <div id="getCoolCarsResult"></div>
</div>

</br></br>

<div>
  Auto <input type="text" id="carName" /> <button id="addCarButton">Aggiungi</button>
  <div id="addCarResultDiv"></div>
</div>

</br></br>

<p>Go to <a href="/carsPage">cars page</a></p>

<script>
	$(document).ready(function() {
		$("#addCarButton").on('click', function() {
			var carName = $('#carName').val();
			
			if(carName != ''){
				$.ajax({
					method: 'POST',
					url : '/cars/addCar',
					data: { carName : carName },
					success : function(responseText) {
						$("#carName").text('');
						$("#addCarResultDiv").text(responseText);
					}
	
				});
			}else{
				$("#addCarResultDiv").text('il valore inserito non è corretto!');
			}
		});
		
		$("#getAllCarsButtonButton").on('click', function() {
			$.ajax({

				url : '/cars/getAllCars',
				data : {
				
				},
				success : function(responseText) {

					if (responseText != "") {
						$("#getAllCarsResult").text(JSON.stringify(responseText));
					}else{
						$("#getAllCarsResult").text('');
					}

				}

			});
		});
		
		$("#getCoolCarsButtonButton").on('click', function() {
			$.ajax({

				url : '/cars/getCoolCars',
				data : {
				
				},
				success : function(responseText) {

					if (responseText != "") {
						$("#getCoolCarsResult").text(JSON.stringify(responseText));
					}else{
						$("#getCoolCarsResult").text('');
					}

				}

			});

		});
	});
</script>