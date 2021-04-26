import javax.swing.*;

/**
 * A search controller of a product inventory application.
 *
 * <p>CS18000 -- Summer 2019 -- Complex GUIs -- Homework</p>
 */
public final class SearchController {
    /**
     * The inventory model of this search controller.
     */
    private InventoryModel inventoryModel;

    /**
     * The search view of this search controller.
     */
    private SearchView searchView;

    /**
     * Constructs a newly allocated {@code SearchController} object with the specified inventory model and search view.
     *
     * @param inventoryModel the inventory model of this search controller
     * @param searchView the search view of this search controller
     * @throws IllegalArgumentException if the {@code inventoryModel} argument or {@code searchView} argument is
     * {@code null}
     */
    public SearchController(InventoryModel inventoryModel, SearchView searchView) throws IllegalArgumentException {
        if (inventoryModel == null) {
            throw new IllegalArgumentException("inventoryModel argument is null");
        } else if (searchView == null) {
            throw new IllegalArgumentException("searchView argument is null");
        } else {
            this.inventoryModel = inventoryModel;
            this.searchView = searchView;

            this.searchView.getSearchButton().addActionListener(e -> this.getSearchButtonSemantics());

            this.searchView.getClearButton().addActionListener(e -> this.getClearButtonSemantics());
        } //end if
    } //SearchController

    public boolean integer(String string) {
        try {
            Integer.parseInt(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean dub(String string) {
        try {
            Double.parseDouble(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Gets the semantics of a search view's search button.
     */
    private void getSearchButtonSemantics() {
        if (searchView.getFieldComboBox().getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "You must select a valid field!",
                    "Inventory Model", JOptionPane.ERROR_MESSAGE);
            searchView.getFieldComboBox().requestFocus();
        } else if (searchView.getFieldComboBox().getSelectedIndex() == 0) {
            if (inventoryModel.searchBySku(searchView.getSearchValueTextField().getText()).isPresent()) {
                searchView.getResultsTextArea().setText(inventoryModel.searchBySku(searchView.getSearchValueTextField().
                        getText()).get().toString());
                searchView.getFieldComboBox().requestFocus();
            } else {
                searchView.getFieldComboBox().requestFocus();
            }
        } else if (searchView.getFieldComboBox().getSelectedIndex() == 1) {
            String s = "";
            for (int i = 0; i < inventoryModel.searchByName(searchView.getSearchValueTextField().getText()).size(); i++) {
                s += inventoryModel.searchByName(searchView.getSearchValueTextField().getText()).get(i) + "\n\n";
            }
            searchView.getResultsTextArea().setText(s);
            searchView.getFieldComboBox().requestFocus();
        } else if (searchView.getFieldComboBox().getSelectedIndex() == 2) {
            if (!dub(searchView.getSearchValueTextField().getText())) {
                JOptionPane.showMessageDialog(null, "The specified wholesale price must be " +
                        "a valid number!", "Inventory Model", JOptionPane.ERROR_MESSAGE);
                searchView.getSearchValueTextField().requestFocus();
            } else {
                String s = "";
                for (int i = 0; i < inventoryModel.searchByWholesalePrice(Double.parseDouble(searchView.getSearchValueTextField().getText())).size(); i++) {
                    s += inventoryModel.searchByWholesalePrice(Double.parseDouble(searchView.getSearchValueTextField().getText())).get(i) + "\n\n";
                }
                searchView.getResultsTextArea().setText(s);
                searchView.getFieldComboBox().requestFocus();
            }
        } else if (searchView.getFieldComboBox().getSelectedIndex() == 3) {
            if (!dub(searchView.getSearchValueTextField().getText())) {
                JOptionPane.showMessageDialog(null, "The specified retail price must be " +
                        "a valid number!", "Inventory Model", JOptionPane.ERROR_MESSAGE);
                searchView.getSearchValueTextField().requestFocus();
            } else {
                String s = "";
                for (int i = 0; i < inventoryModel.searchByRetailPrice(Double.parseDouble(searchView.getSearchValueTextField().getText())).size(); i++) {
                    s += inventoryModel.searchByRetailPrice(Double.parseDouble(searchView.getSearchValueTextField().getText())).get(i) + "\n\n";
                }
                searchView.getResultsTextArea().setText(s);
                searchView.getFieldComboBox().requestFocus();
            }
        } else if (searchView.getFieldComboBox().getSelectedIndex() == 4) {
            if (!integer(searchView.getSearchValueTextField().getText())) {
                JOptionPane.showMessageDialog(null, "The specified quantity price must be " +
                        "a valid number!", "Inventory Model", JOptionPane.ERROR_MESSAGE);
                searchView.getSearchValueTextField().requestFocus();
            } else {
                String s = "";
                for (int i = 0; i < inventoryModel.searchByQuantity(Integer.parseInt(searchView.getSearchValueTextField().getText())).size(); i++) {
                    s += inventoryModel.searchByQuantity(Integer.parseInt(searchView.getSearchValueTextField().getText())).get(i) + "\n\n";
                }
                searchView.getResultsTextArea().setText(s);
                searchView.getFieldComboBox().requestFocus();
            }
        }
        //TODO implement method
    } //getSearchButtonSemantics

    /**
     * Gets the semantics of a search view's clear button.
     */
    private void getClearButtonSemantics() {
        searchView.getFieldComboBox().setSelectedIndex(-1);
        searchView.getResultsTextArea().setText("");
        searchView.getSearchValueTextField().setText("");
        searchView.getSearchValueTextField().requestFocus();
        //TODO implement method
    } //getClearButtonSemantics

    /**
     * Gets the hash code of this search controller.
     *
     * @return the hash code of this search controller
     */
    @Override
    public int hashCode() {
        int result = 23;

        result = 19 * result + (this.inventoryModel == null ? 0 : this.inventoryModel.hashCode());

        result = 19 * result + (this.searchView == null ? 0 : this.searchView.hashCode());

        return result;
    } //hashCode

    /**
     * Determines whether or not this search controller is equal to the specified object. {@code true} is returned if
     * and only if the specified object is an instance of {@code SearchController}, and its field values are equal to
     * this search controller's.
     *
     * @param anObject the object to be compared
     * @return {@code true}, if this search controller is equal to the specified object, and {@code false} otherwise
     */
    @Override
    public boolean equals(Object anObject) {
        return (anObject instanceof SearchController)
                && (this.inventoryModel == null ? ((SearchController) anObject).inventoryModel ==  null : this.inventoryModel.equals(((SearchController) anObject).inventoryModel))
                && (this.searchView == null ? ((SearchController) anObject).searchView ==  null : this.searchView.equals(((SearchController) anObject).searchView));
    } //equals

    /**
     * Gets a {@code String} representation of this search controller.
     *
     * @return a {@code String} representation of this search controller
     */
    @Override
    public String toString() {
        return String.format("SearchController[%s, %s]", this.inventoryModel, this.searchView);
    } //toString
}