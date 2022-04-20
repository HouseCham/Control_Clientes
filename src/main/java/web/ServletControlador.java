package web;

import data.ClienteDaoJDBC;
import domain.Cliente;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                    this.editarCliente(request, response);
                    break;
                case "eliminar":
                    this.eliminarCliente(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "insertar":
                    this.insertarCliente(request, response);
                    break;
                case "modificar":
                    this.modificarCliente(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> clientes = new ClienteDaoJDBC().listar();
        System.out.println("clientes = " + clientes);
        HttpSession session = request.getSession();
        session.setAttribute("clientes", clientes);
        session.setAttribute("totalClientes", clientes.size());              //Cantidad de clientes
        session.setAttribute("saldoTotal", this.getSaldoTotal(clientes));    //Obtener saldo total
        //request.getRequestDispatcher("clientes.jsp").forward(request, response);  este codigo genera que el URL no cambie
        response.sendRedirect("clientes.jsp");
    }

    private double getSaldoTotal(List<Cliente> clientes) {
        double saldoTotal = 0;
        for (Cliente cliente : clientes) {
            saldoTotal += cliente.getSaldo();
        }
        return saldoTotal;
    }

    private void insertarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Recuperamos los valores del formulario AgregarCliente
        String nombre = request.getParameter("name");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("phone");
        double saldo = 0;
        String saldoString = request.getParameter("balance");

        if (saldoString != null && !"".equals(saldoString)) {
            saldo = Double.parseDouble(saldoString);
        }

        //Creamos el objeto cliente
        Cliente cliente = new Cliente(nombre, apellido, email, telefono, saldo);

        //Insertamos el cliente en la base de datos
        int registrosModificados = new ClienteDaoJDBC().insertar(cliente);
        System.out.println("Registros modificados: " + registrosModificados);

        //Redirigimos a la accion por default
        this.accionDefault(request, response);
    }

    private void editarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Recuperamos el idCliente
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        Cliente cliente = new ClienteDaoJDBC().encontrar(new Cliente(idCliente));
        request.setAttribute("cliente", cliente);

        String jspEditar = "/WEB-INF/pages/cliente/editarCliente.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    private void modificarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Recuperamos los valores del formulario AgregarCliente
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        String nombre = request.getParameter("editNombre");
        String apellido = request.getParameter("editApellido");
        String email = request.getParameter("editEmail");
        String telefono = request.getParameter("editPhone");
        double saldo = 0;
        String saldoString = request.getParameter("editBalance");

        if (saldoString != null && !"".equals(saldoString)) {
            saldo = Double.parseDouble(saldoString);
        }

        //Creamos el objeto cliente
        Cliente cliente = new Cliente(idCliente, nombre, apellido, email, telefono, saldo);

        //Modificamos el cliente en la base de datos
        int registrosModificados = new ClienteDaoJDBC().actualizar(cliente);
        System.out.println("Registros modificados: " + registrosModificados);

        //Redirigimos a la accion por default
        this.accionDefault(request, response);
    }

    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos los valores del formulario editarCliente
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));

        //Creamos el objeto de cliente (modelo)
        Cliente cliente = new Cliente(idCliente);

        //Eliminamos el  objeto en la base de datos
        int registrosModificados = new ClienteDaoJDBC().eliminar(cliente);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }
}
