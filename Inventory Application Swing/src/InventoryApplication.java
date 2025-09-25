import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class InventoryItem {
    private int id;
    private String name;
    private int quantity;
    private double price;

    public InventoryItem(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    public void setName(String name) { this.name = name; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(double price) { this.price = price; }
}

class InventoryApp extends JFrame {
    private ArrayList<InventoryItem> inventory;
    private DefaultTableModel tableModel;
    private JTable inventoryTable;
    private JTextField idField, nameField, quantityField, priceField;

    public InventoryApp() {
        inventory = new ArrayList<>();
        setTitle("Inventory Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for adding/editing inventory items
        JPanel addPanel = new JPanel(new GridLayout(5, 2));
        addPanel.setBorder(BorderFactory.createTitledBorder("Add/Edit Inventory"));

        idField = new JTextField();
        nameField = new JTextField();
        quantityField = new JTextField();
        priceField = new JTextField();

        addPanel.add(new JLabel("ID:"));
        addPanel.add(idField);
        addPanel.add(new JLabel("Name:"));
        addPanel.add(nameField);
        addPanel.add(new JLabel("Quantity:"));
        addPanel.add(quantityField);
        addPanel.add(new JLabel("Price:"));
        addPanel.add(priceField);

        JButton addButton = new JButton("Add/Update");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOrUpdateItem();
            }
        });
        addPanel.add(addButton);

        add(addPanel, BorderLayout.NORTH);

        // Table for displaying inventory items
        String[] columnNames = {"ID", "Name", "Quantity", "Price"};
        tableModel = new DefaultTableModel(columnNames, 0);
        inventoryTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(inventoryTable);
        add(scrollPane, BorderLayout.CENTER);

        // Panel for deleting inventory items
        JPanel deletePanel = new JPanel();
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteItem();
            }
        });
        deletePanel.add(deleteButton);
        add(deletePanel, BorderLayout.SOUTH);
    }

    private void addOrUpdateItem() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            double price = Double.parseDouble(priceField.getText());

            InventoryItem newItem = new InventoryItem(id, name, quantity, price);
            boolean updated = false;

            for (int i = 0; i < inventory.size(); i++) {
                if (inventory.get(i).getId() == id) {
                    inventory.set(i, newItem);
                    tableModel.setValueAt(name, i, 1);
                    tableModel.setValueAt(quantity, i, 2);
                    tableModel.setValueAt(price, i, 3);
                    updated = true;
                    break;
                }
            }

            if (!updated) {
                inventory.add(newItem);
                Object[] row = {id, name, quantity, price};
                tableModel.addRow(row);
            }

            clearFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter numbers only for ID, Quantity, and Price.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteItem() {
        int selectedRow = inventoryTable.getSelectedRow();
        if (selectedRow != -1) {
            int idToDelete = (int) inventoryTable.getValueAt(selectedRow, 0);

            for (int i = 0; i < inventory.size(); i++) {
                if (inventory.get(i).getId() == idToDelete) {
                    inventory.remove(i);
                    tableModel.removeRow(selectedRow);
                    clearFields();
                    break;
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        quantityField.setText("");
        priceField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InventoryApp().setVisible(true);
            }
        });
    }
}
