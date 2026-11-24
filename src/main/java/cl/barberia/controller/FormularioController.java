package cl.barberia.controller;

import cl.barberia.model.AtencionBarberia;
import cl.barberia.model.CorteYBarba;
import cl.barberia.repository.AtencionRepository;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class FormularioController {

    @FXML private Label lblTitulo;
    @FXML private TextField txtCliente;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtFecha;
    @FXML private TextField txtHora;
    @FXML private TextField txtBarbero;
    @FXML private ComboBox<String> cmbServicio;

    private final AtencionRepository repository = new AtencionRepository();
    private AtencionBarberia atencionEnEdicion = null;

    @FXML
    public void initialize() {
        cmbServicio.getItems().addAll("CorteYBarba");
        cmbServicio.getSelectionModel().selectFirst();
    }

    public void cargarDatosParaEditar(AtencionBarberia atencion) {
        this.atencionEnEdicion = atencion;
        lblTitulo.setText("EDITAR ATENCIÓN #" + atencion.getId());
        txtCliente.setText(atencion.getNombreCliente());
        txtTelefono.setText(atencion.getNumeroTelefono());
        txtFecha.setText(atencion.getFecha());
        txtHora.setText(atencion.getHora());
        txtBarbero.setText(atencion.getNombreBarbero());
        cmbServicio.setValue(atencion.getTipoServicio());
    }

    @FXML
    private void guardarAtencion() {
        if (txtCliente.getText().isBlank() || txtTelefono.getText().isBlank()
                || txtFecha.getText().isBlank() || txtHora.getText().isBlank()
                || txtBarbero.getText().isBlank() || cmbServicio.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos incompletos");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, completa todos los campos antes de guardar.");
            alert.showAndWait();
            return;
        }

        String cliente = txtCliente.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String fecha = txtFecha.getText().trim();
        String hora = txtHora.getText().trim();
        String barbero = txtBarbero.getText().trim();

        AtencionBarberia nueva = new CorteYBarba(cliente, telefono, fecha, hora, barbero);

        if (atencionEnEdicion == null) {
            repository.guardarAtencion(nueva);
        } else {
            nueva.setId(atencionEnEdicion.getId());
            repository.actualizarAtencion(nueva);
        }

        cerrarVentana();
    }

    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) txtCliente.getScene().getWindow();
        stage.close();
    }
}