package cl.barberia.controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import cl.barberia.model.AtencionBarberia;
import cl.barberia.repository.AtencionRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrincipalController {

    @FXML
    private TextField txtBuscar;
    @FXML
    private TableView<AtencionBarberia> tablaAtenciones;
    @FXML
    private TableColumn<AtencionBarberia, Integer> colId;
    @FXML
    private TableColumn<AtencionBarberia, String> colCliente;
    @FXML
    private TableColumn<AtencionBarberia, String> colTelefono;
    @FXML
    private TableColumn<AtencionBarberia, String> colFecha;
    @FXML
    private TableColumn<AtencionBarberia, String> colHora;
    @FXML
    private TableColumn<AtencionBarberia, String> colBarbero;
    @FXML
    private TableColumn<AtencionBarberia, String> colServicio;
    @FXML
    private TableColumn<AtencionBarberia, Integer> colPrecio;

    private final AtencionRepository repository = new AtencionRepository();
    private final ObservableList<AtencionBarberia> listaMaster = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Conectar columnas con los getters de AtencionBarberia
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCliente.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("numeroTelefono"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        colBarbero.setCellValueFactory(new PropertyValueFactory<>("nombreBarbero"));
        colServicio.setCellValueFactory(new PropertyValueFactory<>("tipoServicio"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precioBase"));

        cargarTabla();
        configurarBuscadorEnTiempoReal();
    }

    public void cargarTabla() {
        listaMaster.setAll(repository.obtenerTodas());
    }

    private void configurarBuscadorEnTiempoReal() {
        FilteredList<AtencionBarberia> filtrados = new FilteredList<>(listaMaster, p -> true);

        txtBuscar.textProperty().addListener((obs, valorAnterior, nuevoValor) -> {
            filtrados.setPredicate(atencion -> {
                if (nuevoValor == null || nuevoValor.isBlank()) return true;
                String filtro = nuevoValor.toLowerCase();
                return atencion.getNombreCliente().toLowerCase().contains(filtro)
                        || atencion.getNombreBarbero().toLowerCase().contains(filtro)
                        || atencion.getTipoServicio().toLowerCase().contains(filtro);
            });
        });

        tablaAtenciones.setItems(filtrados);
    }

    @FXML
    private void eliminarSeleccionado() {
        AtencionBarberia seleccionada = tablaAtenciones.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            mostrarAlerta("Aviso", "Selecciona una fila de la tabla para eliminar.");
            return;
        }
        repository.borrarAtencion(seleccionada.getId());
        cargarTabla(); // Actualización dinámica inmediata
    }

    // Estos métodos los conectaremos en los siguientes pasos
    @FXML
    private void abrirFormularioNuevo() {
        abrirModalFormulario(null);
    }

    @FXML
    private void abrirFormularioEditar() {
        AtencionBarberia seleccionada = tablaAtenciones.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            mostrarAlerta("Aviso", "Selecciona una fila de la tabla para editar.");
            return;
        }
        abrirModalFormulario(seleccionada);
    }

    private void abrirModalFormulario(AtencionBarberia atencion) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cl/barberia/view/vista_formulario.fxml"));
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(getClass().getResource("/cl/barberia/view/estilos.css").toExternalForm());

            FormularioController controller = loader.getController();
            if (atencion != null) {
                controller.cargarDatosParaEditar(atencion);
            }

            Stage stage = new Stage();
            stage.setTitle(atencion == null ? "Nueva Atención" : "Editar Atención");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait(); // Espera a que se cierre el formulario

            cargarTabla(); // Recarga la tabla automáticamente
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo abrir el formulario: " + e.getMessage());
        }
    }
    @FXML
    private void abrirResumen() {
        mostrarAlerta("Próximo paso", "Aquí se abrirá la vista de resumen.");
    }

    @FXML
    private void exportarDatos() {
        mostrarAlerta("Próximo paso", "Aquí se exportará a CSV.");
    }

    @FXML
    private void importarDatos() {
        mostrarAlerta("Próximo paso", "Aquí se importará desde CSV.");
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}