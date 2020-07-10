<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="esS" >
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrapValidator.js"></script>
<link rel="stylesheet" href="css/bootstrap.css"/>
<link rel="stylesheet" href="css/dataTables.bootstrap.min.css"/>
<link rel="stylesheet" href="css/bootstrapValidator.css"/>
<title>Registra Club</title>
</head>
<body>


<div class="container">
<h1>Registra Director</h1>

	<c:if test="${sessionScope.MENSAJE != null}">
		<div class="alert alert-success fade in" id="success-alert">
		 <a href="#" class="close" data-dismiss="alert">&times;</a>
		 <strong>${sessionScope.MENSAJE}</strong>
		</div>
	</c:if>
	<c:remove var="MENSAJE" />

	<form action="registraClub" id="id_form"> 
			<input type="hidden" name="metodo" value="registra">	
			<div class="form-group">
				<label class="control-label" for="id_nombre">Nombres</label>
				<input class="form-control" type="text" id="id_nombre" name="nombre" placeholder="Ingrese el nombre">
			</div>
			<div class="form-group">
				<label class="control-label" for="id_fecha">Fecha de Nacimiento</label>
				<input class="form-control" type="date" id="id_fecha" name="fechaNacimiento" placeholder="Ingrese la fecha de nacimiento">
			</div>
			<div class="form-group">
				<label class="control-label" for="id_grado">Grado</label>
				<select id="id_grado" name="grado" class='form-control'>
					<option value="" >[SELECCIONE]</option>
				</select>
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-primary" >Crea Club</button>
			</div>
	</form>
</div>

<script type="text/javascript">
	console.log("En carga auspiciador");
	$.getJSON("cargaCombo",{"metodo":"auspiciador"} ,function(data){
		console.log(data)
		$.each(data, function(i, x){
			$("#id_auspiciador").append("<option value='"+ x.idAuspiciador +"'>"+ x.nombre +"</option>");		
		});
	});
</script>

<script type="text/javascript">
$("#success-alert").fadeTo(1000, 500).slideUp(500, function(){
    $("#success-alert").slideUp(500);
});
</script>

<script type="text/javascript">
$(document).ready(function() {
    $('#id_form').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        
        fields:{
        	nombre :{
          		selector : '#id_nombre',
        		validators :{
        			notEmpty :{
        				message :"El nombre es obligatorio"
        			},
        			stringLength :{
                     	message:"El nombre es de 2 a 20 caracteres",
                     	min : 2,
                     	max : 20
                    }
        		}
        	},
        	fecha :{
        		selector : '#id_fecha',
        		validators :{
        			notEmpty :{
        				message :"La fecha es obligatorio"
        			},
        			date: {
                        format: 'YYYY-MM-DD',
                        message: 'La fecha tiene formato YYYY-MM-DD'
                    }
        		}
        	},
        	auspiciador :{
          		selector : '#id_grado',
        		validators :{
        			notEmpty :{
        				message :"El grado es obligatorio"
        			},
        		}
        	},
        }
  
    });

    // Validate the form manually
    $('#validateBtn').click(function() {
        $('#id_form').bootstrapValidator('validate');
    });
});
</script>

</body>
</html>




