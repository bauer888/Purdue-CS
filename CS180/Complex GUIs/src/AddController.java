import javax.swing.*;


/**
 * An add controller of a product inventory application.
 *
 * <p>CS18000 -- Summer 2019 -- Complex GUIs -- Homework</p>
 */
public final class AddController {
    /**
     * The inventory model of this add controller.
     */
    private InventoryModel inventoryModel;

    /**
     * The add view of this add controller.
     */
    private AddView addView;

    /**
     * Constructs a newly allocated {@code AddController} object with the specified inventory model and add view.
     *
     * @param inventoryModel the inventory model of this add controller
     * @param addView the add view of this add controller
     * @throws IllegalArgumentException if the {@code inventoryModel} argument or {@code addView} argument is
     * {@code null}
     */
    public AddController(InventoryModel inventoryModel, AddView addView) throws IllegalArgumentException {
        if (inventoryModel == null) {
            throw new IllegalArgumentException("inventoryModel argument is null");
        } else if (addView == null) {
            throw new IllegalArgumentException("addView argument is null");
        } else {
            this.inventoryModel = inventoryModel;
            this.addView = addView;

            this.addView.getAddButton().addActionListener(e -> this.getAddButtonSemantics());

            this.addView.getClearButton().addActionListener(e -> this.getClearButtonSemantics());
        } //end if
    } //AddController

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
     * Gets the semantics of an add view's add button.
     */
    private void getAddButtonSemantics() {
        if (inventoryModel.searchBySku(addView.getSkuTextField().getText()).isPresent()) {
            JOptionPane.showMessageDialog(null, "This product could not be added to the " +
                    "inventory! A product with the given SKU already exists", "Product Inventory",
                    JOptionPane.ERROR_MESSAGE);
            addView.getSkuTextField().requestFocus();
        } else {
            if (!dub(addView.getWholesalePriceTextField().getText())) {
                JOptionPane.showMessageDialog(null, "The specified wholesale price is not a " +
                        "valid number!", "Inventory Model", JOptionPane.ERROR_MESSAGE);
                addView.getWholesalePriceTextField().requestFocus();
            } else if (!dub(addView.getRetailPriceTextField().getText())) {
                JOptionPane.showMessageDialog(null, "The specified retail price is not a " +
                        "valid number!", "Inventory Model", JOptionPane.ERROR_MESSAGE);
                addView.getRetailPriceTextField().requestFocus();
            } else if (!integer(addView.getQuantityTextField().getText())) {
                JOptionPane.showMessageDialog(null, "The specified quantity price is not a " +
                        "valid number!", "Inventory Model", JOptionPane.ERROR_MESSAGE);
                addView.getQuantityTextField().requestFocus();
            } else {
                Product product = new Product(addView.getSkuTextField().getText(), addView.getNameTextField().getText(),
                        Double.parseDouble(addView.getWholesalePriceTextField().getText()), Double.parseDouble(addView.
                        getRetailPriceTextField().getText()), Integer.parseInt(addView.getQuantityTextField().getText()));
                inventoryModel.add(product);
                JOptionPane.showMessageDialog(null, "This product has been added to the " +
                        "inventory", "Product Inventory", JOptionPane.INFORMATION_MESSAGE);
                addView.getSkuTextField().requestFocus();
            }
        }
        //TODO implement method
    } //getAddButtonSemantics

    /**
     * Gets the semantics of an add view's clear button.
     */
    private void getClearButtonSemantics() {
        addView.getSkuTextField().setText("");
        addView.getNameTextField().setText("");
        addView.getQuantityTextField().setText("");
        addView.getRetailPriceTextField().setText("");
        addView.getWholesalePriceTextField().setText("");
        addView.getSkuTextField().requestFocus();
        //TODO implement method
    } //getClearButtonSemantics

    /**
     * Gets the hash code of this add controller.
     *
     * @return the hash code of this add controller
     */
    @Override
    public int hashCode() {
        int result = 23;

        result = 19 * result + (this.inventoryModel == null ? 0 : this.inventoryModel.hashCode());

        result = 19 * result + (this.addView == null ? 0 : this.addView.hashCode());

        return result;
    } //hashCode

    /**
     * Determines whether or not this add controller is equal to the specified object. {@code true} is returned if and
     * only if the specified object is an instance of {@code AddController}, and its field values are equal to this
     * add controller's.
     *
     * @param anObject the object to be compared
     * @return {@code true}, if this add controller is equal to the specified object, and {@code false} otherwise
     */
    @Override
    public boolean equals(Object anObject) {
        return (anObject instanceof AddController)
                && (this.inventoryModel == null ? ((AddController) anObject).inventoryModel ==  null : this.inventoryModel.equals(((AddController) anObject).inventoryModel))
                && (this.addView == null ? ((AddController) anObject).addView ==  null : this.addView.equals(((AddController) anObject).addView));
    } //equals

    /**
     * Gets a {@code String} representation of this add controller.
     *
     * @return a {@code String} representation of this add controller
     */
    @Override
    public String toString() {
        return String.format("AddController[%s, %s]", this.inventoryModel, this.addView);
    } //toString
}