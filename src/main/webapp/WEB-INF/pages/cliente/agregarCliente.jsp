
<div class="modal fade" id="agregarClienteModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Agregar Cliente</h5>
                <button class="btn-close" data-bs-dismiss="modal">
                </button>
            </div>
            <!-- Form -->
            <form action="${pageContext.request.contextPath}/ServletControlador?accion=insertar" method="post" class="m-3 was-validated">
                <div class="modal-body">
                    <div class="form-group">
                        <!-- name -->
                        <div class="mb-3 mt-3">
                            <label for="name" class="form-label">Nombre:</label>
                            <input type="text" class="form-control" id="name" placeholder="Nombre" name="name" required>
                        </div>
                        <!-- last name -->
                        <div class="mb-3 mt-3">
                            <label for="lastName" class="form-label">Apellido:</label>
                            <input type="text" class="form-control" id="lastname" placeholder="Apellido" name="apellido" required>
                        </div>
                        <!-- email -->
                        <div class="mb-3 mt-3">
                            <label for="email" class="form-label">Email:</label>
                            <input type="email" class="form-control" id="email" placeholder="Enter email" name="email" required>
                        </div>
                        <!-- telephone -->
                        <div class="mb-3 mt-3">
                            <label for="phone" class="form-label">Telefono:</label>
                            <input type="tel" class="form-control" id="phone" placeholder="Telefono" name="phone" required>
                        </div>
                        <!-- balance -->
                        <div class="mb-3 mt-3">
                            <label for="balance" class="form-label">Telefono:</label>
                            <input type="number" class="form-control" id="balance" placeholder="Saldo" name="balance" required>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Guardar</button>
                </div>
            </form>
        </div>
    </div>
</div>