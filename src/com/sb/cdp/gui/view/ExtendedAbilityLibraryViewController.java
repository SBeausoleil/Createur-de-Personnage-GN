package com.sb.cdp.gui.view;

import java.util.Collection;
import java.util.LinkedList;

import com.sb.cdp.Library;
import com.sb.cdp.ability.Ability;
import com.sb.cdp.gui.FXUtil;
import com.sb.util.Pair;
import com.sb.util.Search;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ExtendedAbilityLibraryViewController implements Controller {
    @FXML
    private TextField searchField;
    @FXML
    private VBox layout;

    private Collection<Library<String, Ability>> libraries;

    private Search searcher;

    public ExtendedAbilityLibraryViewController() {}

    @FXML
    private void initialize() {
	searchField.textProperty().addListener((obs, old, search) -> searcher.search(search));
    }

    @Override
    public void update() {
	// Remove everything that follows the searchField bar within the layout
	while (layout.getChildren().size() > 1)
	    layout.getChildren().remove(1);

	if (libraries != null) {
	    // Get the total number of abilityLibraryViews that will be stored (this is an optimization to avoid resizing the abilityLibraryViews arraylist and taking more memory than needed)

	    LinkedList<AbilityLibraryViewController> libs = new LinkedList<>();
	    // Add AbilityLibraryView for each libraries
	    for (Library<?, Ability> lib : libraries) {
		Pair<AnchorPane, AbilityLibraryViewController> pair = FXUtil.abilityLibraryView(lib);
		layout.getChildren().add(pair.getX());
		libs.add(pair.getY());
	    }

	    searcher = new Search(libs);
	}
    }

    /**
     * Returns the libraries.
     * 
     * @return the libraries
     */
    public Collection<Library<String, Ability>> getLibraries() {
	return libraries;
    }

    /**
     * Sets the value of libraries to that of the parameter.
     * 
     * @param collection
     *            the libraries to set
     */
    public void setLibraries(Collection<Library<String, Ability>> collection) {
	this.libraries = collection;
	update();
    }

    @Override
    protected void finalize() {
	searcher = null;
    }
}
