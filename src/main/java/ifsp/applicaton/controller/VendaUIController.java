package ifsp.applicaton.controller;

import ifsp.applicaton.view.WindowLoader;
import ifsp.domain.entities.pessoa.Cliente;
import ifsp.domain.entities.pessoa.Funcionario;
import ifsp.domain.entities.pessoa.Pessoa;
import ifsp.domain.entities.venda.CategoriaProduto;
import ifsp.domain.entities.venda.Venda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Optional;

import static ifsp.applicaton.main.Main.*;

public class VendaUIController {

    @FXML
    private DatePicker dtData;
    @FXML
    private ComboBox<CategoriaProduto> comboBoxCategoria;
    @FXML
    private TextField txtValorProduto;
    @FXML
    private TextField txtNomeProduto;
    @FXML
    private TextField txtCpfCliente;
    @FXML
    private TextField txtCpfVendedor;
    @FXML
    private Button btnSalvar;

    private Venda venda;

    @FXML
    private void initialize() {
        comboBoxCategoria.getItems().setAll(CategoriaProduto.values());
    }

    public void voltarParaMain(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("MainUI");
    }

    public void saveAndUpdate(ActionEvent actionEvent) throws IOException {
        getEntityFromView();

        if (btnSalvar.getText().equals("Atualizar")){
            updateVendaUseCase.update(venda);
            WindowLoader.setRoot("MainUI");
            return;
        }

        createVendaUseCase.create(venda);
        WindowLoader.setRoot("MainUI");
    }

    private void getEntityFromView() {
        if (venda == null){
            createVenda();
        }

        venda.setNomeProduto(txtNomeProduto.getText());
        venda.setValorVenda(Double.parseDouble(txtValorProduto.getText()));
        venda.setDataVenda(dtData.getValue());
    }

    private void createVenda() {
        Venda vendaRealizada = new Venda();

        vendaRealizada.setNomeProduto(txtNomeProduto.getText());
        vendaRealizada.setValorVenda(Double.parseDouble(txtValorProduto.getText()));
        vendaRealizada.setDataVenda(dtData.getValue());
        vendaRealizada.setCategoriaProduto(comboBoxCategoria.getSelectionModel().getSelectedItem());
        vendaRealizada.setCliente((Cliente) getPessoa(txtCpfCliente.getText()));
        vendaRealizada.setFuncionario((Funcionario) getPessoa(txtCpfVendedor.getText()));

        venda = vendaRealizada;
    }

    private Pessoa getPessoa(String cpf) {
        Optional<Pessoa> pessoa = readPessoaUseCase.readOne(cpf);
        return pessoa.get();
    }

    public void setVenda(Venda venda, UIMode mode) {
        if (venda == null) {
            throw new IllegalArgumentException("Venda não pode ser nula.");
        }

        this.venda = venda;
        setEntityIntoView();

        if (mode == UIMode.VISUALIZAR) {
            configureViewMode();
        }

        if (mode == UIMode.EDITAR) {
            txtCpfCliente.setDisable(true);
            txtCpfVendedor.setDisable(true);
            comboBoxCategoria.setDisable(true);
            btnSalvar.setText("Atualizar");
        }
    }

    private void setEntityIntoView() {
        txtNomeProduto.setText(venda.getNomeProduto());
        txtValorProduto.setText(String.valueOf(venda.getValorVenda()));
        txtCpfCliente.setText(venda.getCliente().getCpf());
        txtCpfVendedor.setText(venda.getFuncionario().getCpf());
        comboBoxCategoria.getSelectionModel().select(venda.getCategoriaProduto());
        dtData.setValue(venda.getDataVenda());
    }

    private void configureViewMode() {
        txtNomeProduto.setDisable(true);
        txtValorProduto.setDisable(true);
        txtCpfCliente.setDisable(true);
        txtCpfVendedor.setDisable(true);
        comboBoxCategoria.setDisable(true);
        dtData.setDisable(true);
    }
}
