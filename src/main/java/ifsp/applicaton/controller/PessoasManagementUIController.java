package ifsp.applicaton.controller;

import ifsp.applicaton.view.WindowLoader;
import ifsp.domain.entities.pessoa.Cliente;
import ifsp.domain.entities.pessoa.Pessoa;
import ifsp.domain.entities.venda.Venda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.util.List;

import static ifsp.applicaton.main.Main.*;

public class PessoasManagementUIController {

    @FXML
    private TableView<Pessoa> tableView;
    @FXML
    private TableColumn<Pessoa, String> cNome;
    @FXML
    private TableColumn<Pessoa, String> cCpf;
    @FXML
    private TableColumn<Pessoa, String> cSexo;
    @FXML
    private TableColumn<Pessoa, String> cNascimento;
    @FXML
    private TableColumn<Pessoa, String> cTipo;

    private ObservableList<Pessoa> tableData;

    @FXML
    private void initialize() {
        bindTableViewToItemList();
        bindColumnsToValuesSources();
        loadDataAndShow();
    }

    private void bindTableViewToItemList() {
        tableData = FXCollections.observableArrayList();
        tableView.setItems(tableData);
    }

    private void bindColumnsToValuesSources() {
        cNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        cSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        cNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        cTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
    }

    private void loadDataAndShow() {
        List<Pessoa> pessoas = readPessoaUseCase.readAll();
        tableData.clear();
        tableData.addAll(pessoas);
    }

    public void deletePessoa(ActionEvent actionEvent) {
        Pessoa selectedPessoa = tableView.getSelectionModel().getSelectedItem();

        boolean haveVenda = haveAnyVenda(selectedPessoa);

        if (selectedPessoa != null && !haveVenda) {
            deletePessoaUseCase.delete(selectedPessoa);
            loadDataAndShow();
            return;
        }

        throw new IllegalArgumentException("Não foi possível apagar o Cliente/Funcionário. Pessoa não pode ser nula," +
                " ou a pessoa já possui alguma venda.");
    }

    private boolean haveAnyVenda(Pessoa selectedPessoa) {
        if (selectedPessoa instanceof Cliente) {
            List<Venda> listComprasByCliente = readVendaUseCase.readByClienteCpf(selectedPessoa.getCpf());
            return listComprasByCliente.size() > 0;
        } else {
            List<Venda> listVendasByFuncionario = readVendaUseCase.readFuncionarioByCpf(selectedPessoa.getCpf());
            return listVendasByFuncionario.size() > 0;
        }
    }

    public void backToDashboard(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("MainUI");
    }

    public void createNewPessoa(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("PessoaUI");
    }

    public void viewDetailPessoa(ActionEvent actionEvent) throws IOException {
        showPessoaInMode(UIMode.VISUALIZAR);
    }

    public void editPessoa(ActionEvent actionEvent) throws IOException {
        showPessoaInMode(UIMode.EDITAR);
    }

    private void showPessoaInMode(UIMode mode) throws IOException {
        Pessoa selectedPessoa = tableView.getSelectionModel().getSelectedItem();
        if (selectedPessoa != null) {
            WindowLoader.setRoot("PessoaUI");
            PessoaUIController controller = (PessoaUIController) WindowLoader.getController();
            controller.setPessoa(selectedPessoa, mode);
        }
    }
}
