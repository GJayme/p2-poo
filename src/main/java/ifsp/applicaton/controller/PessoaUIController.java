package ifsp.applicaton.controller;

import ifsp.applicaton.view.WindowLoader;
import ifsp.domain.entities.pessoa.Cliente;
import ifsp.domain.entities.pessoa.Funcionario;
import ifsp.domain.entities.pessoa.Pessoa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;

import static ifsp.applicaton.main.Main.*;

public class PessoaUIController {

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCpf;
    @FXML
    private TextField txtDataNascimento;
    @FXML
    private TextField txtSalario;
    @FXML
    private TextField txtTurno;
    @FXML
    private ChoiceBox<String> cbSexo;
    @FXML
    private ChoiceBox<String> cbTipo;
    @FXML
    private Label lblNome;
    @FXML
    private Label lblCpf;
    @FXML
    private Label lblSexo;
    @FXML
    private Label lblDataNascimento;
    @FXML
    private Label lblSalario;
    @FXML
    private Label lblTipo;
    @FXML
    private Label lblPontosFidelidade;
    @FXML
    private Label lblTurno;
    @FXML
    private Label lblTotalPontosFidelidade;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnVoltar;

    private Pessoa pessoa;
    private ObservableList<String > sexo;
    private ObservableList<String> pessoaTipos;

    @FXML
    private void initialize() {
        bindChoiceBoxToItems();
        loadChoices();
    }

    private void bindChoiceBoxToItems() {
        sexo = FXCollections.observableArrayList();
        pessoaTipos = FXCollections.observableArrayList();

        cbSexo.setItems(sexo);
        cbTipo.setItems(pessoaTipos);
    }

    private void loadChoices() {
        sexo.addAll("Masculino", "Feminino");
        pessoaTipos.addAll("Cliente", "Funcionário");
    }

    public void setPessoa(Pessoa pessoa, UIMode mode) {
        if (pessoa == null) {
            throw new IllegalArgumentException("Pessoa não pode ser nula.");
        }

        this.pessoa = pessoa;
        setEntityIntoView();

        if (mode == UIMode.VISUALIZAR) {
            configureViewMode();
        }
    }

    private void configureViewMode() {
        txtNome.setDisable(true);
        txtCpf.setDisable(true);
        txtDataNascimento.setDisable(true);
        cbSexo.setDisable(true);
        cbTipo.setDisable(true);
    }

    //TODO: Bug ao exibir o tipo de funcionario
    private void setEntityIntoView() {
        txtNome.setText(pessoa.getNome());
        txtCpf.setText(pessoa.getCpf());
        txtDataNascimento.setText(String.valueOf(pessoa.getDataNascimento()));
        cbSexo.getSelectionModel().select(pessoa.getSexo());
        cbTipo.getSelectionModel().select(pessoa.getType());

        if (pessoa instanceof Cliente) {
            lblTotalPontosFidelidade.setText(String.valueOf(((Cliente)pessoa).getPontosFidelidade()));
            txtTurno.setVisible(false);
            txtSalario.setVisible(false);

        } else {
            txtTurno.setText(((Funcionario)pessoa).getTurno());
            txtSalario.setText(String.valueOf(((Funcionario)pessoa).getSalario()));
            lblPontosFidelidade.setVisible(false);
            txtTurno.setDisable(true);
            txtSalario.setDisable(true);
        }
    }

    public void salvar(ActionEvent actionEvent) throws IOException {
        getEntityFromView();
        boolean newPessoa = readPessoaUseCase.readOne(pessoa.getCpf()).isEmpty();

        if (newPessoa) {
            createPessoaUseCase.create(pessoa);
        } else {
            updatePessoaUseCase.update(pessoa);
        }
        WindowLoader.setRoot("PessoasManagementUI");
    }

    private void getEntityFromView() {
        if (pessoa == null) {
            createPessoaProperKind();
        }

        pessoa.setNome(txtNome.getText());
        pessoa.setCpf(txtCpf.getText());
        pessoa.setDataNascimento(LocalDate.parse(txtDataNascimento.getText()));
        pessoa.setSexo(cbSexo.getSelectionModel().getSelectedItem());

    }

    private void createPessoaProperKind() {
        String pessoaTipo = cbTipo.getSelectionModel().getSelectedItem();
        if (pessoaTipo.equals("Cliente")) {
            Cliente cliente = new Cliente();
            cliente.setPontosFidelidade(0D);
            pessoa = cliente;
        } else {
            Funcionario funcionario = new Funcionario();
            funcionario.setTurno(txtTurno.getText());
            funcionario.setSalario(Double.parseDouble(txtSalario.getText()));
            pessoa = funcionario;
        }
    }

    public void voltar(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("PessoasManagementUI");
    }

    public void choiceTipo(ActionEvent actionEvent) {
        String selectedTipo = cbTipo.getSelectionModel().getSelectedItem();
        if (selectedTipo.equals("Cliente")) {
            lblTotalPontosFidelidade.setVisible(true);
            lblPontosFidelidade.setVisible(true);
            txtSalario.setDisable(true);
            txtTurno.setDisable(true);

        } else {
            lblTotalPontosFidelidade.setVisible(false);
            lblPontosFidelidade.setVisible(false);
            txtSalario.setDisable(false);
            txtTurno.setDisable(false);
        }
    }
}
