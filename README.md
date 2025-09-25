# Inventory Management Swing

**Short description:** A desktop Inventory Management application built with Java Swing. It provides a graphical user interface (GUI) to add, update, delete, and search inventory items displayed in a table.

---

## Project summary

- **Main source file:** `src/InventoryApplication.java` (contains `InventoryItem` and `InventoryApp` classes)
- **UI:** Java Swing (`JFrame`, `JTable`, `JButton`, `JTextField`, `JOptionPane`)
- **Data storage:** In-memory `ArrayList<InventoryItem>` (no persistent save/load to file implemented in this project)
- **Features implemented:** Add item, Update item by ID, Delete selected row, Clear input fields, Search/filter functionality (basic), Table display of items
- **Build:** No build file included; uses only Java Standard Library (no external dependencies)

---

## How to compile & run (command-line)

1. Ensure JDK 20+ is installed and `javac`/`java` are available in your PATH.
2. Open a terminal and navigate to the `src` directory that contains `InventoryApplication.java`:

```bash
cd "Inventory Application Swing/src"
```

3. Compile the Java source (this creates `.class` files):

```bash
javac InventoryApplication.java
```

4. Run the application:

```bash
java InventoryApp
```

A window will open showing a form to input item ID, Name, Quantity, Price and a table listing the current items.

> Tip: Alternatively, open the folder in IntelliJ IDEA and run `InventoryApp` from the IDE for easier testing and debugging.

---

## Code structure & quick reference

- `InventoryItem` — model class representing an item (id, name, quantity, price) with getters/setters and `toString()` method.
- `InventoryApp` — Swing `JFrame` that builds the UI, maintains the in-memory `ArrayList<InventoryItem>`, and manipulates a `DefaultTableModel` to reflect changes.

Important methods in `InventoryApp` (high level):

- `addItem()` — parses input fields, creates `InventoryItem`, adds to `ArrayList` and table
- `updateItem()` — finds item by ID, updates item and table row
- `deleteItem()` — deletes the selected row from the table and `ArrayList`
- `clearFields()` — clears input text fields
- `main()` — launches the Swing UI (`SwingUtilities.invokeLater`)

---

## Possible improvements / next steps

- Split classes into separate `.java` files and add a package (e.g., `com.yourname.inventory`) for better structure.
- Add persistent storage (CSV or JSON) and Save/Load functionality — consider using `JFileChooser` to allow choosing file paths.
- Add input validation and better error handling for number parsing and duplicate IDs.
- Add `TableRowSorter` for advanced filtering, and improve search UX.
- Add unit tests for inventory logic (extract core logic from UI code) using JUnit.
- Create a `pom.xml` or `build.gradle` to support builds and packaging; produce a runnable JAR for easy distribution.
- Clean the repo by removing compiled `out/` directories and add a `.gitignore` for Java/IntelliJ.

---

## License & Author

```
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

Author: Muhammad Asad
```
