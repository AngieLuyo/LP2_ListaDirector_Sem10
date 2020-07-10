<!DOCTYPE html>
<html lang="esS" >
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrapValidator.js"></script>
<link rel="stylesheet" href="css/bootstrap.css"/>
<link rel="stylesheet" href="css/dataTables.bootstrap.min.css"/>
<link rel="stylesheet" href="css/bootstrapValidator.css"/>

<title>Ejemplos PIT CIBERTEC - Jorge Jacinto </title>
</head>
<body> 

 <div class="container">
 <h1>Lista Club</h1>
		 <div class="col-md-23" >  
			  <form id="idFormElimina" action="eliminaModalidad">
			  		<input type="hidden" id="id_elimina" name="id">
			  </form>	
				
		       <form accept-charset="UTF-8"  class="simple_form" id="defaultForm2"  method="post">
					<div class="row">
						<div class="col-md-3">	
							<div class="form-group">
							  	<input class="form-control" id="id_nombre"  placeholder="Ingrese el nombre" type="text" maxlength="30"/>
							</div>
						</div>
						<div class="col-md-3">	
							<div class="form-group">
							  	 <select id="id_auspiciador" class='form-control'>
							               <option value="-1">[Seleccione Auspiciador]</option>    
							     </select>
							</div>
						</div>
						
							                         
						<div class="col-md-3">
							<button  id="id_envio"  type="button" class="btn btn-primary">FILTRA</button><br>&nbsp;<br>
						</div>
					</div>			
					
					<div class="row" > 
						<div class="col-md-12">
								<div class="content" >
						
									<table id="id_table" class="table table-striped table-bordered" >
										<thead>
											<tr>
												<th>ID</th>
												<th>Nombre</th>
												<th>Fecha de creación</th>
												<th>País</th>
												<th>Auspiciador</th>
											</tr>
										</thead>
										<tbody>
												   

										</tbody>
										</table>	
									
								</div>	
						</div>
					</div>
		 		</form>
		  </div>
  


</div>

<script type="text/javascript">
$('#id_envio').on("click", function(){ 
	   console.log("Se pulsa el boton");
	    $("#id_table tbody").empty(); 
	   
	    var nom = $("#id_nombre").val();
	    var aus = $("#id_auspiciador").val();
	 
	 	$.getJSON("clubConsulta", {"nombre":nom,"idAuspiciador":aus}, function(data){
			$.each(data, function(index,item){
				$("#id_table").append("<tr><td>"+ item.idClub +"</td><td>"+ item.nombre +"</td><td>"+ item.fechaCreacion +"</td><td>"+ item.pais +"</td><td>"+ item.auspiciador.nombre +"</td></tr>");
			});
		});
});


$.getJSON("cargaCombo", {"metodo":"auspiciador"}, function(data){
	$.each(data, function(index,item){
		$("#id_auspiciador").append("<option value="+item.idAuspiciador +">"+ item.nombre +"</option>");
	});
});
</script>
    
</body>
</html> 
