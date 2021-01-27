package ifsp.applicaton.controller;

import ifsp.applicaton.view.WindowLoader;
import ifsp.domain.entities.pessoa.Pessoa;
import ifsp.domain.entities.venda.Venda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

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
    private Label lblCpfCliente;
    @FXML
    private TextField txtCpfCliente;
    @FXML
    private Button btnGerenciarPessoas;
    @FXML
    private Button btnRealizarVenda;
    @FXML
    private Button btnBuscarCpf;

    private ObservableList<Venda> tableData;
    private Pessoa pessoa;

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
        cData.setCellValueFactory(new PropertyValueFactory<>("data_venda"));
        cValor.setCellValueFactory(new PropertyValueFactory<>("valor_venda"));
        cProduto.setCellValueFactory(new PropertyValueFactory<>("nome_produto"));
        cCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        cCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        cFuncionario.setCellValueFactory(new PropertyValueFactory<>("funcionario_responsavel"));
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
        WindowLoader.setRoot("VendaManagementUI");
    }

    public void buscarCpfCliente(ActionEvent actionEvent) {
    }
}
