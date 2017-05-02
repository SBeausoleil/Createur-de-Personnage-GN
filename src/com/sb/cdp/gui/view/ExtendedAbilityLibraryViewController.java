package com.sb.cdp.gui.view;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.sb.cdp.ability.Ability;
import com.sb.cdp.gui.FXUtil;
import com.sb.util.ConcretePair;
import com.sb.util.Pair;
import com.sb.util.Search;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ExtendedAbilityLibraryViewController implements Controller {
    @FXML
    private TextField searchField;
    @FXML
    private VBox layout;

    private Collection<Pair<String, Collection<Ability>>> libraries;

    private LinkedList<AbilityLibraryViewController> librariesControllers;
    
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

	if (searcher != null)
	    searcher.stop(false);

	if (libraries != null) {
	    // Get the total number of abilityLibraryViews that will be stored (this is an optimization to avoid resizing the abilityLibraryViews arraylist and taking more memory than needed)

	    librariesControllers = new LinkedList<>();
	    // Add AbilityLibraryView for each libraries
	    for (Pair<String, Collection<Ability>> lib : libraries) {
		ConcretePair<TitledListView<AbilityView>, AbilityLibraryViewController> viewPair = FXUtil.abilityLibraryView(lib);
		layout.getChildren().add(viewPair.getX());
		librariesControllers.add(viewPair.getY());
	    }

	    if (librariesControllers.size() > 0)
		searcher = new Search(librariesControllers);
	}
    }

    /**
     * Returns the libraries.
     * 
     * @return the libraries
     */
    public Collection<Pair<String, Collection<Ability>>> getLibraries() {
	return libraries;
    }

    /**
     * Sets the value of libraries to that of the parameter.
     * 
     * @param collection
     *            the libraries to set
     */
    public void setLibraries(Collection<Pair<String, Collection<Ability>>> collection) {
	this.libraries = collection;
	update();
    }

    @Override
    protected void finalize() {
	System.out.println("finalizing " + ExtendedAbilityLibraryViewController.class.getName());
	searcher.stop(false);
	searcher = null;
    }

    /**
     * Returns an unmodifiable list of all the library views.
     * @return the librariesControllers
     */
    public List<AbilityLibraryViewController> getLibrariesControllers() {
        return Collections.unmodifiableList(librariesControllers);
    }

    /**
     * Returns the layout.
     * @return the layout
     */
    public VBox getLayout() {
        return layout;
    }
}
