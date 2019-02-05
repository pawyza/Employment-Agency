package managers.loggedEmployeesManager.addEmployee.additionalLanguagesAdding;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class LanguagesTable {

    private TableView<Language> tabLanguage;
    private TableColumn<Language, String> colInformation;
    private TableColumn<Language, String> colLanguage;
    private ObservableList<Language> observableLanguages = FXCollections.observableArrayList();

    public LanguagesTable(TableView<Language> tableView, TableColumn<Language, String> colInformation, TableColumn<Language, String>  colLanguage, String nativeLan) {
        this.tabLanguage = tableView;
        this.colInformation = colInformation;
        this.colLanguage = colLanguage;
        observableLanguages.add(new Language("Native",nativeLan));
        this.colInformation.setCellValueFactory(new PropertyValueFactory<>("information"));
        this.colLanguage.setCellValueFactory(new PropertyValueFactory<>("language"));
        this.tabLanguage.setItems(observableLanguages);
    }

    public void updateAdd(String language){
        if(!checkIfDuplicate(language))
            observableLanguages.add(new Language("Additional",language));
    }

    public boolean updateCheck(){
        return observableLanguages.size() > 1;
    }

    public void updateRemove(Language language) throws RuntimeException {
        if(!language.getInformation().equals("Native"))
        observableLanguages.remove(language);
    }

    public ArrayList<String> getAdditionalLanguages(){
        ArrayList<String> additionalLanguages = new ArrayList<>();
        for (Language l: observableLanguages) {
            if(!l.getInformation().equals("Native"))
            additionalLanguages.add(l.getLanguage());
        }
        return additionalLanguages;
    }

    private boolean checkIfDuplicate(String language){
        for (Language l: observableLanguages ) {
            if(l.getLanguage().equals(language)){
                return true;
            }
        }
        return false;
    }
}
