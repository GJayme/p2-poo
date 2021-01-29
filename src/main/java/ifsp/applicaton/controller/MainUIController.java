package ifsp.applicaton.controller;

import ifsp.applicaton.view.WindowLoader;
import ifsp.domain.entities.venda.Venda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

import static ifsp.applicaton.main.Main.deleteVendaUseCase;
import static ifsp.applicaton.main.Main.readVendaUseCase;

public class MainUIController {

    @FXML
    private TableView<Venda> tableView;
    @FXML
    private TableColumn<Venda, String> cData;
    @FXML
    private TableColumn<Venda, String> cValor;
    @FXML
    private TableColumn<Venda, String> cProduto;
    @FXML
    private TableColumn<Venda, String> cCategoria;
    @FXML
    private TableColumn<Venda, String> cCliente;
    @FXML
    private TableColumn<Venda, String> cFuncionario;
    @FXML
    private TextField txtCpfCliente;

    private ObservableList<Venda> tableData;

    @FXML
    private void initialize() {
        bindTableViewToItemsList();
        bindColumnsToValuesSources();
        loadDataAndShow();
    }

    private void bindTableViewToItemsList() {
        tableData = FXCollections.observableArrayList();
        tableView.setItems(tableData);
    }

    private void bindColumnsToValuesSources() {
        cData.setCellValueFactory(new PropertyValueFactory<>("dataVenda"));
        cValor.setCellValueFactory(new PropertyValueFactory<>("valorVenda"));
        cProduto.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
        cCategoria.setCellValueFactory(new PropertyValueFactory<>("categoriaProduto"));
        cCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        cFuncionario.setCellValueFactory(new PropertyValueFactory<>("funcionario"));
    }

    private void loadDataAndShow() {
        List<Venda> vendas = readVendaUseCase.readAll();
        tableData.clear();
        tableData.addAll(vendas);
    }

    public void gerenciarPessoas(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("PessoasManagementUI");
    }

    public void realizarVenda(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("VendaUI");
    }

    public void buscarCpfCliente(ActionEvent actionEvent) {
        List<Venda> vendas = readVendaUseCase.readByCpf(txtCpfCliente.getText());
        tableData.clear();
        tableData.addAll(vendas);
    }

    public void visualizarVenda(ActionEvent actionEvent) throws IOException {
        showVendaInMode(UIMode.VISUALIZAR);
    }

    //TODO bug ao editar venda, gera nova venda ao inves de sobreescrever
    public void editarVenda(ActionEvent actionEvent) throws IOException {
        showVendaInMode(UIMode.EDITAR);
    }

    public void deletarVenda(ActionEvent actionEvent) {
        Venda selectedVenda = tableView.getSelectionModel().getSelectedItem();
        if (selectedVenda != null) {
            deleteVendaUseCase.delete(selectedVenda.getId());
            loadDataAndShow();
        }
    }

    private void showVendaInMode(UIMode mode) throws IOException {
        Venda selectedVenda = tableView.getSelectionModel().getSelectedItem();
        if (selectedVenda != null) {
            WindowLoader.setRoot("VendaUI");
            VendaUIController controller = (VendaUIController) WindowLoader.getController();
            controller.setVenda(selectedVenda, mode);
        }
    }
}
