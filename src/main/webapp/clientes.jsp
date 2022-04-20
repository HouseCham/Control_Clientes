
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" 
              rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
              crossorigin="anonymous">
        <!-- FontAwesome -->
        <script src="https://kit.fontawesome.com/2d01b7fa30.js" crossorigin="anonymous"></script>
        
        <title>Control de Clientes</title>
    </head>
    <body>
        <!-- Header -->
        <jsp:include page="/WEB-INF/pages/comunes/header.jsp"/>
        <!-- Navigation Btn's -->
        <jsp:include page="/WEB-INF/pages/comunes/navigationBtn.jsp"/>
        <!-- Listado de clientes -->
        <jsp:include page="/WEB-INF/pages/cliente/listadoClientes.jsp"/>
        <!-- Footer -->
        <jsp:include page="/WEB-INF/pages/comunes/footer.jsp"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </body>
</html>